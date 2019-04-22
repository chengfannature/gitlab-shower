package org.chengfan.gitlab.shower.service.impl;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.chengfan.gitlab.shower.entity.Commit;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.service.CommitService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabCommitStats;
import org.gitlab.api.models.GitlabCommitWithStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class CommitServiceImpl implements CommitService {
    public static final int MAX_CODE_LINE_PER_COMMIT = 5000;
    public static final String DEFAULT_BRANCH = "develop";

    @Autowired
    CommitRepository commitRepository;

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    GitlabAPI gitlabAPI;

    public void saveCommits(int projectId) {
        List<GitlabCommitWithStats> commits = null;
        try {
            commits = gitlabAPI.getAllCommitsWithStats(projectId, DEFAULT_BRANCH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (commits == null || commits.size() == 0) {
            return;
        }
        long start = System.currentTimeMillis();
        //过滤超大addition/deletion的提交(通常都是代码同步)
        commits.stream().filter(c -> c.getGitlabCommitStats().getTotal() < MAX_CODE_LINE_PER_COMMIT)
                .forEach(commitWithStats -> {
                    Commit commit = buildCommit(commitWithStats, projectId);
                    commitRepository.save(commit);
                });
        long end = System.currentTimeMillis();
        log.info("take {} to insert into database, total records {}", end - start, commits.size());
    }

    @Override
    public List<Commit> findCommit(String authorName) {
        return commitRepository.findByAuthorName(authorName);
    }

    private Commit buildCommit(GitlabCommitWithStats cws, int projectId) {
        Commit commit = new Commit();
        commit.setId(cws.getId());
        commit.setShortId(cws.getShortId());
        commit.setAuthorName(cws.getAuthorName());
        commit.setAuthorEmail(cws.getAuthorEmail());
        commit.setCreatedAt(cws.getCreatedAt());
        commit.setProjectId(projectId);
        GitlabCommitStats stats = cws.getGitlabCommitStats();
        commit.setAdditions(stats.getAdditions());
        commit.setDeletions(stats.getDeletions());
        return commit;
    }
}
