package org.chengfan.gitlab.shower.dto;

import lombok.*;

/**
 * Commit dto 描述了提交数和总提交代码量的统计数据.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitDto {
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
    private long commitCount;

    /**
     * 用户总新增代码量
     */
    private long additionSum;
    /**
     * 用户总删除代码量
     */
    private long deletionSum;
}
