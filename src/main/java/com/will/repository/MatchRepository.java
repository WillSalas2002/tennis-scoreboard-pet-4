package com.will.repository;

import com.will.model.Match;
import com.will.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class MatchRepository {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    public int getTotalMatchesCount(String filter) {

        try (Session session = SESSION_FACTORY.openSession()) {

            StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(m.id) FROM Match m");

            includeFilterIfNotNull(filter, queryBuilder);

            Query<Long> query = session.createQuery(queryBuilder.toString(), Long.class);
            if (filter != null) {
                query.setParameter("filter", filter + "%");
            }

            int count = query.uniqueResult().intValue();
            log.info("Count of matches with filter = {} is {}", filter, count);

            return count;
        }
    }

    public List<Match> findAll(int limit, int offset, String filter) {
        try (Session session = SESSION_FACTORY.openSession()) {
            StringBuilder queryBuilder = new StringBuilder("""
                    SELECT m
                            FROM Match m
                            JOIN FETCH m.player1
                            JOIN FETCH m.player2
                            JOIN FETCH m.winner
                    """);

            includeFilterIfNotNull(filter, queryBuilder);

            Query<Match> query = session
                    .createQuery(queryBuilder.toString(), Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit);

            setParameterFilterIfNotNull(filter, query);
            log.info("Fetching matches with pagination (limit = {}, offset = {}) and/or filter = {}", limit, offset, filter);

            return query.getResultList();
        }
    }

    public void save(Match match) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
            if (match.getId() == null) {
                log.info("Error happened when trying to save match: {}", match);
                throw new RuntimeException("Couldn't save match");
            }
            log.info("Successfully saved match: {}", match);
        }
    }

    private void includeFilterIfNotNull(String filter, StringBuilder queryBuilder) {
        if (filter != null) {
            queryBuilder.append(" WHERE m.player1.name LIKE :filter OR m.player2.name LIKE :filter");
        }
    }

    private void setParameterFilterIfNotNull(String filter, Query<Match> query) {
        if (filter != null) {
            query.setParameter("filter", filter + "%");
        }
    }
}
