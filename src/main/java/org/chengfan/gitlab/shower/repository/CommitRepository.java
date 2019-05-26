package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.dto.CommitDto;
import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.entity.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Integer> {
	/**
	 * 获取某个author的所有提交.
	 *
	 * @param authorName commit作者
	 * @return 改作者的所有提交
	 */
	List<Commit> findByAuthorName(String authorName);

	/**
	 * 获取最后的提交,用于在插入新数据时做比较,避免重复插入数据
	 *
	 * @return 当前数据库时间最晚的提交
	 */
	Commit findFirstByOrderByCreatedAt();


	/**
	 * 获取每个人在某段时间内的总体积数，总代码新增代码数，总删除代码数
	 *
	 * @param startTime 统计的开始时间
	 * @param endTime   统计的结束时间
	 * @param orderBy   排序字段
	 * @return 统计列表
	 */
	@Query(value = "select new org.chengfan.gitlab.shower.dto.CommitDto(authorName, " +
			" authorEmail, count(authorName), SUM(additions), SUM(deletions)) " +
			" from Commit" +
			" where createdAt between ?1 and ?2" +
			" group by authorName" +
			" order by additions desc ")
	List<CommitDto> findCommitGroupByAuthor(Date startTime,
											Date endTime,
											String orderBy);


	@Query(value = "select new org.chengfan.gitlab.shower.dto.CommitStatisticDto(createdAt, " +
			" SUM(additions), SUM(deletions),count(createdAt)) " +
			" from Commit" +
			" group by createdAt" +
			" order by additions desc ")
	List<CommitStatisticDto> findCommitsGroupByTime();


}
