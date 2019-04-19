package org.chengfan.gitlab.shower.service.scheduled;

import org.chengfan.gitlab.shower.Gitlab;
import org.chengfan.gitlab.shower.entity.Note;
import org.chengfan.gitlab.shower.entity.User;
import org.chengfan.gitlab.shower.repository.UserRepository;
import org.chengfan.gitlab.shower.service.CommitService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabCommitWithStats;
import org.gitlab.api.models.GitlabDiscussion;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabNote;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScheduledTasksService {

    public static final String DEFAULT_BRANCH = "develop";

    @Autowired
    GitlabAPI gitlabAPI;

    @Autowired
    CommitService commitService;

    @Autowired
    Gitlab gitlab;

    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "0 0 7 * * *")
    public void reloadCommits() {
        GitlabGroup group = null;
        try {
            group = gitlabAPI.getGroup(gitlab.getGroup());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (group == null) {
            return;
        }
        List<GitlabProject> projects = group.getProjects();
        projects.parallelStream().forEach(p -> {
            reloadCommitByProject(p.getId());
            reloadMrComments(p.getId());
        });
    }

    @Scheduled(cron = "0 0 7 * * *")
    public void reloadUsers() {
        List<GitlabUser> userList = gitlabAPI.getUsers();
        userList.stream().map(this::mapperToUser).forEach(user -> userRepository.save(user));
    }

    private User mapperToUser(GitlabUser gitlabUser) {
        User user = new User();
        user.setId(gitlabUser.getId());
        user.setUsername(gitlabUser.getUsername());
        user.setEmail(gitlabUser.getEmail());
        return user;
    }

    private void reloadCommitByProject(int projectId) {
        List<GitlabCommitWithStats> commits;
        try {
            commits = gitlabAPI.getAllCommitsWithStats(projectId, DEFAULT_BRANCH);
            commitService.saveCommits(commits, projectId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void reloadMrComments(Integer projectId) {

        List<GitlabMergeRequest> mergeRequests = gitlabAPI.getMergeRequests(projectId);
        mergeRequests.forEach(mr -> {
            try {
                List<GitlabDiscussion> discussions = gitlabAPI.getDiscussions(mr);
                discussions.stream()
                        .filter(d -> !d.isIndividualNote()).forEach(d -> {
                    List<GitlabNote> notes = d.getNotes();
                    //只统计别人提出的检视意见,过滤到MR发起者的评论
                    notes.stream().filter(n -> mr.getAuthor().getUsername().
                            equalsIgnoreCase(n.getAuthor().getUsername()))
                            .map(this::buildNote)
                            .forEach();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private Note buildNote(GitlabNote gitlabNote) {
        Note note = new Note();
        note.setAuthorName(gitlabNote.getAuthor().getUsername());
        note.setId(gitlabNote.getId());
        return note;
    }
}
