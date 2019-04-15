package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.dto.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Integer> {
	@Query("select SUM(additions) from Commit  where author_name = ?1")
	Integer getAdditonsSum(String authorName);

	@Query("select SUM(deletions) from Commit  where author_name = ?1")
	Integer getDeletionsSum(String authorName);
}
