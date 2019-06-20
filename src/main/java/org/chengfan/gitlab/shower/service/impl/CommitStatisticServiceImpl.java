package org.chengfan.gitlab.shower.service.impl;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.entity.User;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.service.CommitStatisticService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class CommitStatisticServiceImpl implements CommitStatisticService {

    @Autowired
    private CommitRepository commitRepository;

    @Override
    public List<CommitStatisticDto> getCommitStatistics(String userName) {
        if (isNullOrEmpty(userName)) {
            return commitRepository.findCommitStatisticDtos();
        }
        return commitRepository.findCommitStatisticDtosByAuthor(userName);
    }

    @Override
    public List<CommitStatisticDto> getAllCommitStatics() {

        return commitRepository.findCommitStatisticDtos();
    }
}
