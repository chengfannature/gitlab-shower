package org.chengfan.gitlab.shower.controller;

import org.chengfan.gitlab.shower.service.CommitService;
import org.gitlab.api.models.GitlabCommitStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommitController {

	@Autowired
	CommitService commitService;

	@RequestMapping(value = "/commits/{userId}")
	public GitlabCommitStats commitStats(@PathVariable int userId) {
//		return commitService.getAllCommitStats(userId);
		return null;
	}
}
