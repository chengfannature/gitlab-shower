package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.dto.CommitDto;
import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.entity.Commit;
import org.junit.Before;
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

	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Date startDateTime;
	private Date endDateTime;
	private static final String SORT_BY_ADDITIONS = "additionSum";
	private static final String SORT_BY_DELETIONS = "deletionSum";

	@Autowired
	CommitRepository commitRepository;

	@Autowired
	TestEntityManager entityManager;

	@Before
	public void setup() throws ParseException {
		startDateTime = format.parse("2018-07-30 00:00:00");
		endDateTime = format.parse("2019-07-30 00:00:00");
	}

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
	public void testGetCommitDtoOrdyByAdditions() throws ParseException {
		List<CommitDto> commitStatisticList = commitRepository.findCommitGroupByAuthor(startDateTime, endDateTime,
				new Sort(Sort.Direction.DESC, SORT_BY_ADDITIONS));
		assertNotNull(commitStatisticList);
		// 总数据条数是4，根据authorName聚合后条数是3
		assertEquals(REAL_COMMIT_RECORD_NUM, commitStatisticList.size());
		CommitDto additionNumberOneCommitDto = commitStatisticList.get(0);
		CommitDto additionNumberTwoCommitDto = commitStatisticList.get(2);
		assertEquals("chengfan", additionNumberOneCommitDto.getUserName());
		assertEquals("pengxg", additionNumberTwoCommitDto.getUserName());
	}

	@Test
	public void testGetCommitDtoOrdyByDeletion() {
		List<CommitDto> commitStatisticList = commitRepository.findCommitGroupByAuthor(startDateTime, endDateTime,
				new Sort(Sort.Direction.DESC, SORT_BY_DELETIONS));
		assertNotNull(commitStatisticList);
		// 总数据条数是4，根据authorName聚合后条数是3
		assertEquals(REAL_COMMIT_RECORD_NUM, commitStatisticList.size());
		CommitDto deletionsNumberOneCommitDto = commitStatisticList.get(0);
		CommitDto deletionsNumberTwoCommitDto = commitStatisticList.get(2);
		assertEquals("pengxg", deletionsNumberOneCommitDto.getUserName());
		assertEquals("caojm", deletionsNumberTwoCommitDto.getUserName());
	}

	@Test
	public void testFindCommitsGroupByTime() {
		List<CommitStatisticDto> commitDtos = commitRepository
				.findCommitStatisticDtos(startDateTime, endDateTime);
		assertEquals(2, commitDtos.size());
	}
}