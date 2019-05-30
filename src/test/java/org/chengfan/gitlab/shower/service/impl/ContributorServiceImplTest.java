package org.chengfan.gitlab.shower.service.impl;

import com.google.common.collect.ImmutableList;
import org.chengfan.gitlab.shower.dto.CommitDto;
import org.chengfan.gitlab.shower.dto.ContributionDto;
import org.chengfan.gitlab.shower.dto.NoteDto;
import org.chengfan.gitlab.shower.repository.CommitRepository;
import org.chengfan.gitlab.shower.repository.NoteRepository;
import org.chengfan.gitlab.shower.service.ContributorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContributorServiceImplTest {

	private CommitDto commitDto1 = new CommitDto("chengfan", "chengfan@greenet.net.cn", 20, 500, 100);
	private CommitDto commitDto2 = new CommitDto("pengxg", "pengxg@greenet.net.cn", 20, 500, 100);

	private NoteDto noteDto1 = new NoteDto("chengfan", 20);
	private NoteDto noteDto2 = new NoteDto("pengxg", 10);
	private ImmutableList<CommitDto> commitDtos;
	private ImmutableList<NoteDto> noteDtos;

	@TestConfiguration
	static class ContributorServiceImplContextConfiguration {
		@Bean
		public ContributorService contributorService() {
			return new ContributorServiceImpl();
		}
	}

	@Autowired
	private ContributorService contributorService;

	@MockBean
	CommitRepository commitRepository;

	@MockBean
	NoteRepository noteRepository;

	@Before
	public void setup() {
		commitDtos = ImmutableList.of(commitDto1, commitDto2);
		noteDtos = ImmutableList.of(noteDto1, noteDto2);
		Mockito.when(commitRepository.
				findCommitGroupByAuthor(any(Date.class), any(Date.class), any(Sort.class)))
				.thenReturn(commitDtos);
		Mockito.when(noteRepository.countNoteGroupByAuthorName(any(Date.class), any(Date.class)))
				.thenReturn(noteDtos);
	}

	@Test
	public void testGetDtoContributors() {
		List<ContributionDto> contributionDtos = contributorService
				.getDtoContributors(new Sort(Sort.Direction.DESC, "additions"),
						new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		assertEquals(commitDtos.size(), contributionDtos.size());
	}
}