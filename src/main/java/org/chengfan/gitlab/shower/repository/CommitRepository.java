package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.dto.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository  extends JpaRepository<Commit, Integer> {


}
