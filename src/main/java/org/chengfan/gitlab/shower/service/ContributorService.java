package org.chengfan.gitlab.shower.service;

import org.chengfan.gitlab.shower.dto.ContributorDto;

import java.util.List;

public interface ContributorService {
    /**
     * 获取所有贡献者的提交信息.
     *
     * @param orderByProperty 排序参数,可以根据commitCount or additionSum or deletionSum
     *                排序
     * @return 贡献者信息列表
     */
    List<ContributorDto> getDtoContributors(String orderByProperty);
}
