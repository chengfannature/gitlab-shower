package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chengfan
 */
@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    /**
     * 获取最后一次同步的时间记录
     *
     * @return 最后一次同步记录
     */
    Record findFirstByOrderBySyncDateDesc();
}
