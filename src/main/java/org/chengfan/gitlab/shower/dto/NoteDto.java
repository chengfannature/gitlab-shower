package org.chengfan.gitlab.shower.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 描述了检视意见的统计数据：某段时间的总评论数.
 *
 * @author chengfan
 */
@Setter
@Getter
@AllArgsConstructor
public class NoteDto {
    /**
     * 评论者git 用户名
     */
    private String userName;
    /**
     * 评论人在某段时间的总评论数
     */
    private long noteCount;
}
