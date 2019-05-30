package org.chengfan.gitlab.shower.controller;

import lombok.extern.slf4j.Slf4j;
import org.chengfan.gitlab.shower.dto.ContributionDto;
import org.chengfan.gitlab.shower.entity.Commit;
import org.chengfan.gitlab.shower.service.CommitService;
import org.chengfan.gitlab.shower.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/contributions")
@Slf4j
public class ContributorController {

	@Autowired
	ContributorService contributorService;

	@Autowired
	CommitService commitService;

	@RequestMapping("/history")
	public List<ContributionDto> showContributors(@RequestParam(value = "orderBy") String orderBy) {
		Date startTime = getOneYearAgo();
		Date endTime = new Date();
		return contributorService.getDtoContributors(new Sort(Sort.Direction.DESC, orderBy), startTime, endTime);
	}


	@RequestMapping("/byDate")
	public List<ContributionDto> showContributors(@RequestParam("orderBy") String orderBy,
												  @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
												  @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
		return contributorService.getDtoContributors(new Sort(Sort.Direction.DESC, orderBy), startTime, endTime);
	}

	@RequestMapping("/{username}")
	public List<Commit> showContributorDetail(@PathVariable String username) {
		return commitService.findCommit(username);
	}


	private Date getOneYearAgo() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -100);
		return c.getTime();
	}
}
