package org.chengfan.gitlab.shower.controller;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.service.CommitStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 提交统计信息controller，主要提供时间维度的提交信息，可以用于绘制团队或个人的提交趋势图.
 */
@RestController
@RequestMapping(value = "/commitStatistic")
public class CommitStatisticController {

    @Autowired
    CommitStatisticService statisticService;

    @RequestMapping(value = "/{username}")
    public List<CommitStatisticDto> getByUsername(
            @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
            @PathVariable(required = false) String username) {

        return statisticService.getCommitStatistics(startTime, endTime, username);
    }
}
