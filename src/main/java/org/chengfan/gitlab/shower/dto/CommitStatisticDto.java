package org.chengfan.gitlab.shower.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 根据时间做过汇聚的commit统计DTO,用于绘制commit趋势图,分两种,一种是项目提交的趋势图,一种是个人提交趋势图.
 */
@AllArgsConstructor
@Data
public class CommitStatisticDto {

	/**
	 * 提交的时间,精确到某天
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date createAt;
	/**
	 * 当天总新增代码.
	 */
	private long additions;
	/**
	 * 当天总删除代码.
	 */
	private long deletions;
	/**
	 * 当天的总提交数;
	 */
	private long commitCount;

}
