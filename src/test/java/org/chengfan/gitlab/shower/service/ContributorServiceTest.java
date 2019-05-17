package org.chengfan.gitlab.shower.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContributorServiceTest {

    @Autowired
    ContributorService contributorService;

    @Test
    public void testQuery() {
        contributorService.getDtoContributors("noteSum");

    }
}