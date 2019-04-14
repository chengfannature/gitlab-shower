package org.chengfan.gitlab.shower.service.scheduled;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabCommit;
import org.gitlab.api.models.GitlabProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScheduledTasksService {

	public static final String DEFAULT_BRANCH = "master";

	@Autowired
	GitlabAPI gitlabAPI;

	@Scheduled(cron = "0 0 7 * * *")
	public void reloadCommits() {
		List<GitlabProject> projects = gitlabAPI.getAllProjects();
		projects.parallelStream().forEach(p -> reloadCommitByProject(p.getId()));
	}

	private void reloadCommitByProject(int projectId) {
		List<GitlabCommit> commits = null;
		try {
			commits = gitlabAPI.getAllCommits(projectId, DEFAULT_BRANCH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		commits.parallelStream().forEach(c -> reloadCommitComments(projectId, c.getId()));
	}


	private void reloadCommitComments(Integer projectId, String commitId) {
		try {
			gitlabAPI.getCommitComments(projectId, commitId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
