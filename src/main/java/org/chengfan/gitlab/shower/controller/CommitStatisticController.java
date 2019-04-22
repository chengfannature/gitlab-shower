package org.chengfan.gitlab.shower.controller;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.entity.Commit;
import org.chengfan.gitlab.shower.service.CommitService;
import org.chengfan.gitlab.shower.service.CommitStatisticService;
import org.gitlab.api.models.GitlabCommitStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/commitStatistic")
public class CommitStatisticController {

    @Autowired
    CommitStatisticService statisticService;


    public List<CommitStatisticDto> getAll() {
        return statisticService.getALl();
    }

    @RequestMapping(value = "/{username}")
    public List<CommitStatisticDto> getByUsername(@PathVariable String username) {
        return statisticService.getByUsername(username);
    }
}
