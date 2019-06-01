package org.chengfan.gitlab.shower.service;

import java.util.Date;
import java.util.List;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;

public interface CommitStatisticService {

    List<CommitStatisticDto> getCommitStatistics(Date startTime, Date endTime, String userName);
}
