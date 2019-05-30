package org.chengfan.gitlab.shower.service.impl;

import org.chengfan.gitlab.shower.dto.CommitDto;
import org.chengfan.gitlab.shower.dto.ContributionDto;
import org.chengfan.gitlab.shower.dto.NoteDto;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.repository.NoteRepository;
import org.chengfan.gitlab.shower.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author chengfan
 */
@Service
public class ContributorServiceImpl implements ContributorService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private NoteRepository noteRepository;

    /**
     * map大小由工程大贡献者决定，当前团队大小不会超过100人；
     */
    public static final int INIT_NOTE_MAP_SIZE = 100;

    @Override
    public List<ContributionDto> getDtoContributors(Sort sort, Date startTime, Date endTime) {


        List<CommitDto> commitDtoList = commitRepository.
                findCommitGroupByAuthor(startTime, endTime, sort);
        List<NoteDto> noteDtoList = noteRepository.
                countNoteGroupByAuthorName(startTime, endTime);
        Map<String, Long> authorNoteMap = buildAuthorNoteMap(noteDtoList);
        return buildContributionList(commitDtoList, authorNoteMap);
    }

    private List<ContributionDto> buildContributionList(List<CommitDto> commitDtoList,
                                                        Map<String, Long> noteMap) {
        List<ContributionDto> contributionDtos = new LinkedList<>();
        for (CommitDto commitDto : commitDtoList) {
            ContributionDto contributionDto = new
                    ContributionDto(commitDto, noteMap.get(commitDto.getUserName()));
            contributionDtos.add(contributionDto);
        }
        return contributionDtos;
    }

    private Map<String, Long> buildAuthorNoteMap(List<NoteDto> noteDtos) {
        Map<String, Long> noteMap = new HashMap<>(INIT_NOTE_MAP_SIZE);
        for (NoteDto noteDto : noteDtos) {
            noteMap.put(noteDto.getUserName(), noteDto.getNoteCount());
        }
        return noteMap;
    }

}
