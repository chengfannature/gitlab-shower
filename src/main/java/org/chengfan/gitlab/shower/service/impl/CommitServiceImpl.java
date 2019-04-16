package org.chengfan.gitlab.shower.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.chengfan.gitlab.shower.entity.Commit;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.service.CommitService;
import org.gitlab.api.models.GitlabCommitStats;
import org.gitlab.api.models.GitlabCommitWithStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitServiceImpl implements CommitService {

    @Autowired
    CommitRepository commitRepository;

    @Autowired
    MapperFacade mapperFacade;

    public void saveCommits(List<GitlabCommitWithStats> commits, int projectId) {
        commits.forEach(commitWithStats -> {
            Commit commit = buildCommit(commitWithStats, projectId);
            commitRepository.save(commit);
        });
    }

    @Override
    public GitlabCommitStats getAllCommitStats(int userId) {
        Integer additionsSum = commitRepository.getAdditionsSum(userId);
        Integer deletionsSum = commitRepository.getDeletionsSum(userId);
        GitlabCommitStats stats = new GitlabCommitStats();
        stats.setAdditions(additionsSum);
        stats.setDeletions(deletionsSum);
        return stats;
    }

    private Commit buildCommit(GitlabCommitWithStats cws, int projectId) {
        Commit commit = mapperFacade.map(cws, Commit.class);
        GitlabCommitStats stats = cws.getGitlabCommitStats();
        commit.setAdditions(stats.getAdditions());
        commit.setDeletions(stats.getDeletions());
        return commit;
    }
}
