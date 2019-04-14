package org.chengfan.gitlab.shower.service;

/**
 * Provides method to get commits for a project.
 */
public interface CommitService {
    void getCommits(int projectId);
    void getCommitsByMember();
}
