package org.chengfan.gitlab.shower.dto;

import lombok.Data;

@Data
public class ContributorDto {
    private int id;
    private String userName;
    private String email;
    /**
     * 用户总提交数
     */
    private int commitCount;

    /**
     * 用户总新增代码量
     */
    private int additionSum;
    /**
     * 用户总删除代码量
     */
    private int deletionSum;
}
