package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.entity.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Integer> {
	List<Commit> findByAuthorName(String authorName);
}
