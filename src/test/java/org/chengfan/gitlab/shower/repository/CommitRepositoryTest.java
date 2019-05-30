package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.dto.CommitDto;
import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.entity.Commit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommitRepositoryTest {

	/**
	 * 总的commit测试数据，在data.sql中可以读取
	 */
	private static final int TOTAL_COMMIT_RECORD_NUM = 4;
	/**
	 * 时间有效的测试数据，另外的数据是伪造的，时间不对
	 */
	private static final int REAL_COMMIT_RECORD_NUM = 3;


	@Autowired
	CommitRepository commitRepository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void testDbInit() {
		List<Commit> commits = commitRepository.findAll();
		assertEquals(TOTAL_COMMIT_RECORD_NUM, commits.size());
	}

	@Test
	public void saveCommitAndGetOne() {
		Commit commit = new Commit();
		commit.setId("aaanc");
		commit.setAuthorName("chengfan");
		commit.setAdditions(100);
		commit.setDeletions(10);
		commit.setCreatedAt(now());
		entityManager.persistAndFlush(commit);
		List<Commit> commits = commitRepository.findByAuthorName("chengfan");
		assertNotNull(commits);
	}

	@Test
	public void testGetCommitStatistic() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDateTime = format.parse("2018-07-30 00:00:00");
		Date endDateTime = format.parse("2019-07-30 00:00:00");
		assertTrue(commitRepository.count() > 0);
		List<CommitDto> commitStatisticList = commitRepository
				.findCommitGroupByAuthor(startDateTime, endDateTime, new Sort(Sort.Direction.DESC, "additions"));
		assertNotNull(commitStatisticList);
		assertEquals(REAL_COMMIT_RECORD_NUM, commitStatisticList.size());
	}

	public void testFindCommitsGroupByTime() {
		List<CommitStatisticDto> commitsGroupByTime = commitRepository.findCommitsGroupByTime(new Sort(Sort.Direction.DESC, "additions"));
		assertTrue(commitsGroupByTime.size() > 0);
	}
}