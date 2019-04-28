package org.chengfan.gitlab.shower.dto;

import lombok.Data;

@Data
public class ContributorDto {
    /**
     * 用户id,此id为gitlab分配
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户邮箱
     */
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

    /**
     * 总评论数
     */
    private int noteSum;
}
