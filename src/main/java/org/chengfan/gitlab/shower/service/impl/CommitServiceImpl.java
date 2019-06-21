package org.chengfan.gitlab.shower.service.impl;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.chengfan.gitlab.shower.config.GitlabProperties;
import org.chengfan.gitlab.shower.entity.Commit;
import org.chengfan.gitlab.shower.entity.Record;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.repository.RecordRepository;
import org.chengfan.gitlab.shower.service.CommitService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabCommitStats;
import org.gitlab.api.models.GitlabCommitWithStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.chengfan.gitlab.shower.utils.Tools.isNewer;

@Service
@Slf4j
public class CommitServiceImpl implements CommitService {
    public static final int MAX_CODE_LINE_PER_COMMIT = 5000;
    public static final String DEFAULT_BRANCH = "master";

    @Autowired
    CommitRepository commitRepository;

    @Autowired
    GitlabAPI gitlabAPI;

    @Autowired
    GitlabProperties gitlabProperties;

    @Autowired
    RecordRepository recordRepository;

    @Override
    public void saveCommits(int projectId) {
        List<GitlabCommitWithStats> commits = null;
        try {
            commits = gitlabAPI.getAllCommitsWithStats(projectId, gitlabProperties.getBranch());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (commits == null || commits.size() == 0) {
            return;
        }

        long start = System.currentTimeMillis();
        Record lastRecord = recordRepository.findFirstByOrderBySyncDateDesc();
        //过滤超大addition/deletion的提交(通常都是代码同步)
        commits.stream()
                .filter(c -> c.getGitlabCommitStats().getTotal() < MAX_CODE_LINE_PER_COMMIT)
                .filter(c -> !gitlabProperties.getExcludeUsers().contains(c.getAuthorName()))
                .filter(c -> isNewer(c.getCreatedAt(), lastRecord))
                .filter(c -> !c.getTitle().contains("Merge branch"))
                .forEach(commitWithStats -> {
                    Commit commit = buildCommit(commitWithStats, projectId);
                    commitRepository.save(commit);
                });
        long end = System.currentTimeMillis();
        log.debug("take {} to insert into database for project {}, " +
                "total records {}", end - start, projectId, commits.size());
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
