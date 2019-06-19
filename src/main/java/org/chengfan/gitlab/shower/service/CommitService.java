package org.chengfan.gitlab.shower.service;

import org.chengfan.gitlab.shower.entity.Commit;

import java.util.List;

/**
 * Provides method to get commits for a project.
 */
public interface CommitService {
    /**
     * 保存某项目的历史commit信息.
     * 
     * @param projectId gitlab project ID
     */
    void saveCommits(int projectId);

    /**
     * 后去某人的所有项目提交信息.
     * 
     * @param authoNrame
     * @return
     */
    List<Commit> findCommit(String authoNrame);
}
