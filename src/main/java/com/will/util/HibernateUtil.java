package com.will.util;

import com.will.model.Match;
import com.will.model.Player;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

@UtilityClass
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static final String KEY_DRIVER_CLASS = "connection.driver_class";
    private static final String KEY_CONNECTION_URL = "hibernate.connection.url";
    private static final String KEY_CONNECTION_USERNAME = "hibernate.connection.username";
    private static final String KEY_CONNECTION_PASSWORD = "hibernate.connection.password";
    private static final String KEY_SHOW_SQL = "hibernate.show_sql";
    private static final String KEY_FORMAT_SQL = "hibernate.format_sql";
    private static final String KEY_DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String KEY_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
    private static final String KEY_SQL_COMMENTS = "hibernate.use_sql_comments";

    private static Properties loadProperties() {
        Properties properties = new Properties();
        // Hibernate JDBC Properties
        properties.setProperty(KEY_DRIVER_CLASS, PropertiesUtil.get(KEY_DRIVER_CLASS));
        properties.setProperty(KEY_CONNECTION_URL, PropertiesUtil.get(KEY_CONNECTION_URL));
        properties.setProperty(KEY_CONNECTION_USERNAME, PropertiesUtil.get(KEY_CONNECTION_USERNAME));
        properties.setProperty(KEY_CONNECTION_PASSWORD, PropertiesUtil.get(KEY_CONNECTION_PASSWORD));
        // Hibernate Configuration Properties
        properties.setProperty(KEY_SHOW_SQL, PropertiesUtil.get(KEY_SHOW_SQL));
        properties.setProperty(KEY_FORMAT_SQL, PropertiesUtil.get(KEY_FORMAT_SQL));
        properties.setProperty(KEY_DDL_AUTO, PropertiesUtil.get(KEY_DDL_AUTO));
        properties.setProperty(KEY_SQL_COMMENTS, PropertiesUtil.get(KEY_SQL_COMMENTS));

        properties.setProperty(KEY_SESSION_CONTEXT_CLASS, PropertiesUtil.get(KEY_SESSION_CONTEXT_CLASS));

        return properties;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Match.class);
        configuration.addProperties(loadProperties());

        // We need ServiceRegistry in order to obtain more control over hibernate (custom connection pool, caching etc...)
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
