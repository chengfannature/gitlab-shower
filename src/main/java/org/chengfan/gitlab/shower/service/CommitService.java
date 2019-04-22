package org.chengfan.gitlab.shower.service;

import org.chengfan.gitlab.shower.entity.Commit;

import java.util.List;

/**
 * Provides method to get commits for a project.
 */
public interface CommitService {
    void saveCommits(int projectId);
    List<Commit> findCommit(String authoNrame);
}
