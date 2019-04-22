package org.chengfan.gitlab.shower.service;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;

import java.util.List;

public interface CommitStatisticService {

    List getALl();

    List<CommitStatisticDto> getByUsername(String username);
}
