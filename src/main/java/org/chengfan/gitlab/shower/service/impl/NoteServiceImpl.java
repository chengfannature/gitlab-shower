package org.chengfan.gitlab.shower.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.chengfan.gitlab.shower.entity.Note;
import org.chengfan.gitlab.shower.repository.NoteRepository;
import org.chengfan.gitlab.shower.service.NoteService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabDiscussion;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {
    @Autowired
    GitlabAPI gitlabAPI;

    @Autowired
    NoteRepository noteRepository;

    @Override
    public void saveNotes(int projectId) {
        long start = System.currentTimeMillis();

        Note lastUpdateNote = noteRepository.findFirstByOrderByCreatedAt();
        List<GitlabMergeRequest> mergeRequests = gitlabAPI.getMergeRequests(projectId);
        mergeRequests.forEach(mr -> {
            try {
                List<GitlabDiscussion> discussions = gitlabAPI.getDiscussions(mr);
                discussions.stream()
                        .filter(d -> !d.isIndividualNote()).forEach(d -> {
                    List<GitlabNote> notes = d.getNotes();
                    //只统计别人提出的检视意见,过滤到MR发起者的评论
                    notes.stream()
                            .filter(n -> !n.getBody().toUpperCase().contains("OK"))
                            .filter(n -> !isSameUser(mr, n))
                            .filter(n -> isNewerNote(n, lastUpdateNote))
                            .map(this::buildNote)
                            .forEach(note -> noteRepository.save(note));
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();


        log.debug("take {} to insert into database for project {}, " +
                "total MR records {}", end - start, projectId, mergeRequests.size());

    }

    private boolean isSameUser(GitlabMergeRequest mr, GitlabNote note) {
        return mr.getAuthor().getUsername().equalsIgnoreCase(note.getAuthor().getUsername());
    }

    private boolean isNewerNote(GitlabNote newNote, Note lastUpdateNote) {
        if (lastUpdateNote == null || lastUpdateNote.getCreatedAt() == null) {
            return true;
        }
        return newNote.getCreatedAt().after(lastUpdateNote.getCreatedAt());
    }

    private Note buildNote(GitlabNote gitlabNote) {
        Note note = new Note();
        note.setAuthorName(gitlabNote.getAuthor().getUsername());
        note.setId(gitlabNote.getId());
        note.setCreatedAt(gitlabNote.getCreatedAt());
        note.setBody(gitlabNote.getBody());
        note.setResolved(gitlabNote.isResolved());
        return note;
    }
}
