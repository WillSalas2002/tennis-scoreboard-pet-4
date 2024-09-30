package com.will.repository;

import com.will.model.Player;
import com.will.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

@Slf4j
public class PlayerRepository {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    public Optional<Player> findByName(String name) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Player player = session
                    .createQuery("FROM Player p WHERE p.name =: name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();
            log.info("Fetching a player with name {}", name);
            return Optional.ofNullable(player);
        }
    }

    public Player save(Player player) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            if (player.getId() == null) {
                log.info("Error happened when trying to save player: {}", player);
                throw new RuntimeException("Couldn't save player");
            }
            log.info("Successfully saved player: {}", player);
        }
        return player;
    }
}
