package org.chengfan.gitlab.shower.controller;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.dto.ContributionDto;
import org.chengfan.gitlab.shower.dto.ContributorStatsticDto;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.service.CommitStatisticService;
import org.chengfan.gitlab.shower.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 提交统计信息controller，主要提供时间维度的提交信息，可以用于绘制团队或个人的提交趋势图.
 */
@RestController
@RequestMapping(value = "/commitStatistic")
public class CommitStatisticController {

    @Autowired
    CommitStatisticService statisticService;

    @Autowired
    ContributorService contributorService;


    @Autowired
    private CommitRepository commitRepository;

    @RequestMapping(value = "/{username}")
    public List<CommitStatisticDto> getByUsername(

            @PathVariable(required = false) String username) {

        return statisticService.getCommitStatistics(username);
    }

    @GetMapping
    public ContributorStatsticDto list() {
        return new ContributorStatsticDto(new ContributionDto(commitRepository.statisticAllCommit(), 0),
                statisticService.getAllCommitStatics());
    }

    @RequestMapping("/all")
    public List<ContributorStatsticDto> getContributorStatics() {
        Date startTime = getOneYearAgo();
        Date endTime = new Date();
        List<ContributionDto> dtoContributors = contributorService.getDtoContributors(new Sort(Sort.Direction.DESC, "created_at"), startTime, endTime);
        List<ContributorStatsticDto> list = new ArrayList<>();
        dtoContributors.forEach(contributionDto -> {
            List<CommitStatisticDto> commitStatistics = statisticService.getCommitStatistics(contributionDto.getUserName());
            list.add(new ContributorStatsticDto(contributionDto, commitStatistics));
        });
        return list;
    }

    private Date getOneYearAgo() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -100);
        return c.getTime();
    }


}
