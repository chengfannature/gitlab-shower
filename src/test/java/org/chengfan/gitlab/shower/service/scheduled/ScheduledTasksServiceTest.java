package org.chengfan.gitlab.shower.service.scheduled;

import org.chengfan.gitlab.shower.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ScheduledTasksServiceTest {

	@Autowired
	private ScheduledTasksService scheduledTasksService;

	@Test
	public void reloadCommits() {
		scheduledTasksService.reloadCommits();
	}

	@Test
	public void reloadUsers() {
		scheduledTasksService.reloadUsers();
	}
}