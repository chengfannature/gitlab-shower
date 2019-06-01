package org.chengfan.gitlab.shower.service.impl;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.service.CommitStatisticService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class CommitStatisticServiceImpl implements CommitStatisticService {

    @Autowired
    private CommitRepository commitRepository;

    @Override
    public List<CommitStatisticDto> getCommitStatistics(Date startTime, Date endTime, String userName) {
        if (isNullOrEmpty(userName)) {
            return commitRepository.findCommitStatisticDtos(startTime, endTime);
        }
        return commitRepository.findCommitStatisticDtosByAuthor(startTime, endTime, userName);
    }
}
