package org.chengfan.gitlab.shower.dto;

import java.util.Date;

/**
 * commit统计DTO,用于绘制commit趋势图,分两种,一种是项目提交的趋势图,一种是个人提交趋势图.
 */
public class CommitStatisticDto {

    /**
     * 提交的时间,精确到某天
     */
    private Date createAt;
    /**
     * 当天总新增代码.
     */
    private int additions;
    /**
     * 当天总删除代码.
     */
    private int deletions;
    /**
     * 当天的总提交数;
     */
    private int commitCount;
}
