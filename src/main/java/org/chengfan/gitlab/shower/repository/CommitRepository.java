package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.entity.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
