package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.dto.NoteDto;
import org.chengfan.gitlab.shower.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    Note findFirstByOrderByCreatedAt();

    /**
     * 统计一段时间内用户的评论总数
     *
     * @param startTime 统计开始时间
     * @param endTime   统计结束时间
     * @return 统计结果
     */
    @Query(value = "select new org.chengfan.gitlab.shower.dto.NoteDto(authorName, count(body))" +
            " from Note" +
            " where createdAt between ?1 and ?2" +
            " group by authorName")
    List<NoteDto> countNoteGroupByAuthorName(Date startTime, Date endTime);
}
