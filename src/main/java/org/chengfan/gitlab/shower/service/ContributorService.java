package org.chengfan.gitlab.shower.service;

import org.chengfan.gitlab.shower.dto.ContributionDto;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

/**
 * @author chengfan
 */
public interface ContributorService {
	/**
	 * 获取所有贡献者的提交信息.
	 *
	 * @param sort      排序参数,可以根据commitCount or additionSum or deletionSum
	 *                  排序
	 * @param startTime 统计开始时间
	 * @param endTime   统计结束时间
	 * @return 贡献者信息列表
	 */
	List<ContributionDto> getDtoContributors(Sort sort, Date startTime, Date endTime);
}
