package org.chengfan.gitlab.shower.service.impl;

import org.chengfan.gitlab.shower.context.GitlabContext;
import org.chengfan.gitlab.shower.entity.Gitlab;
import org.chengfan.gitlab.shower.service.CommitService;
import org.gitlab.api.GitlabAPI;
import org.springframework.beans.factory.annotation.Autowired;

public class CommitServiceImpl implements CommitService {

	@Autowired
	GitlabAPI gitlabAPI;

	@Override
	public void getCommits(int projectId) {

	}

	@Override
	public void getCommitsByMember() {

	}
}
