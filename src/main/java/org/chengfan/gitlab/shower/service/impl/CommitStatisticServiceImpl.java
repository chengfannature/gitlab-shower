package org.chengfan.gitlab.shower.service.impl;

import org.chengfan.gitlab.shower.dto.CommitStatisticDto;
import org.chengfan.gitlab.shower.service.CommitStatisticService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class CommitStatisticServiceImpl implements CommitStatisticService {
    @PersistenceContext
    private EntityManager entityManager;
    private static final String SQL = "";
    private static final String USERNAME_FILTER = " where username=";
    private static final String GROUP_BY_DATE = " GROUP BY DATE_FORMAT(created_at, \"%Y-%m-%d\")";


    @Override
    public List<CommitStatisticDto> getALl() {
        String sql = SQL + GROUP_BY_DATE;
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public List<CommitStatisticDto> getByUsername(String username) {
        String sql = SQL;
        sql += sql + USERNAME_FILTER + username;
        sql += GROUP_BY_DATE;
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }
}
