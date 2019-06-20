package org.chengfan.gitlab.shower.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * 按照贡献者来统计所有贡献数据
 *
 *
 */
@Setter
@Getter
public class ContributorStatsticDto {


	public ContributorStatsticDto(ContributionDto contributionDto, List<CommitStatisticDto> commitStatisticDto) {
		this.contributionDto = contributionDto;
		this.commitStatisticDto = commitStatisticDto;
	}

	private ContributionDto contributionDto;

	private List<CommitStatisticDto> commitStatisticDto;

}
