package org.chengfan.gitlab.shower.service.impl;

import org.chengfan.gitlab.shower.dto.ContributorDto;
import org.chengfan.gitlab.shower.service.ContributorService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ContributorServiceImpl implements ContributorService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ContributorDto> getDtoContributors(String orderByProperty) {
        Sort.Direction orderDirection = Sort.Direction.DESC;

        String sql = "  SELECT \n" +
                "    u.id,\n" +
                "    u.username,\n" +
                "    u.email,\n" +
                "    COUNT(additions) AS commit,\n" +
                "    SUM(additions) AS addition,\n" +
                "    SUM(deletions) AS deletion\n" +
                "FROM\n" +
                "    commit c\n" +
                "        RIGHT JOIN\n" +
                "    user u ON u.username = c.author_name\n";
        sql += " " + "GROUP BY";
        sql += " " + "author_name";
        sql += " " + " ORDER BY ";
        sql += " " + orderByProperty + " ";
        sql += orderDirection;
        Query query = entityManager.createNativeQuery(sql);

        return (List<ContributorDto>) query.getResultList();
    }
}
