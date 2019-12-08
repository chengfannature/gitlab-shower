package org.chengfan.gitlab.shower.service.scheduled;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.chengfan.gitlab.shower.entity.Commit;
import org.gitlab.api.models.GitlabCommitStats;
import org.gitlab.api.models.GitlabCommitWithStats;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapperFacadeTest {

    private MapperFacade mapperFacade;
    public MapperFacade dozerBeanMapper() {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }

    @Before
    public void setup() {
        mapperFacade = dozerBeanMapper();
    }


    @Test
    public void testMapperFacade() {
        GitlabCommitWithStats commitWithStats = new GitlabCommitWithStats();
        commitWithStats.setId("aadb");
        commitWithStats.setAuthorName("chengfan");
        commitWithStats.setAuthorEmail("chengfan@huawei.com");
        GitlabCommitStats stats = new GitlabCommitStats();
        stats.setAdditions(222);
        stats.setDeletions(222);
        commitWithStats.setGitlabCommitStats(stats);

        Commit commit = mapperFacade.map(commitWithStats, Commit.class);
        assertEquals(commitWithStats.getId(), commit.getId());
        assertEquals(commitWithStats.getAuthorName(), commit.getAuthorName());
//        assertEquals(commitWithStats.getGitlabCommitStats().getAdditions(), commit.getAdditions());
    }
}
