package org.chengfan.gitlab.shower.service;

import org.chengfan.gitlab.shower.entity.Commit;
import org.gitlab.api.models.GitlabCommitWithStats;

import java.util.List;

/**
 * Provides method to get commits for a project.
 */
public interface CommitService {
    void saveCommits(List<GitlabCommitWithStats> commitList, int projectId);

    List<Commit> getAllCommits(String username);
}
