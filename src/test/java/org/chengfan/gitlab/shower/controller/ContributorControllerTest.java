package org.chengfan.gitlab.shower.controller;

import com.google.common.collect.ImmutableList;
import org.chengfan.gitlab.shower.dto.CommitDto;
import org.chengfan.gitlab.shower.dto.ContributionDto;
import org.chengfan.gitlab.shower.service.CommitService;
import org.chengfan.gitlab.shower.service.ContributorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ContributorController.class)
public class ContributorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ContributorService contributorService;

	@MockBean
	CommitService commitService;

	private ContributionDto contributionDto;

	@Before
	public void setup() {
		contributionDto = buildContributionDto();
		when(contributorService.getDtoContributors(any(Sort.class), any(Date.class), any(Date.class)))
				.thenReturn(ImmutableList.of(contributionDto));
	}


	@Test
	public void testGetHistoryContributors() throws Exception {
		mockMvc.perform(get("/contributions/history?orderBy=additions")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].userName", is(contributionDto.getUserName())));
	}

	@Test
	public void testGetContributorsByDate() throws Exception {
		mockMvc.perform(get("/contributions/byDate?orderBy=additions&&startTime=2018-07-30&&endTime=2019-07-30")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].userName", is(contributionDto.getUserName())));
	}

	@Test
	public void showContributorDetail() {
	}

	private ContributionDto buildContributionDto() {
		CommitDto.CommitDtoBuilder builder = CommitDto.builder();
		builder.userName("chengfan");
		builder.email("chengfan@huawei.net.cn");
		builder.additionSum(1000);
		builder.deletionSum(10);
		builder.commitCount(20);
		CommitDto commitDto = builder.build();
		return new ContributionDto(commitDto, 30);
	}
}