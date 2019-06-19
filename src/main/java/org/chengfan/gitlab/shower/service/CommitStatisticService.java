package org.chengfan.gitlab.shower.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.entity.User;

public interface CommitStatisticService {

    List<CommitStatisticDto> getCommitStatistics(String userName);

    List<CommitStatisticDto> getAllCommitStatics();
}
