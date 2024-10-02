package com.will.listener;

import com.will.util.HibernateUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
@WebListener
public class DatabaseInitListener implements ServletContextListener {

    private final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String creationScript = readSqlScriptsFromInputStream("creation.sql");
        String seedingScript = readSqlScriptsFromInputStream("initialization.sql");

        executeSqlScript(creationScript);
        executeSqlScript(seedingScript);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.closeSessionFactory();
    }

    private void executeSqlScript(String creationScript) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            int rows = session.createNativeQuery(creationScript, Integer.class).executeUpdate();
            session.getTransaction().commit();
            if (rows > 0) {
                log.info("Script has been executed successfully affected rows {}", rows);
            }
        } catch (RuntimeException e) {
            log.error("Failed to execute script: {}", creationScript);
            throw new RuntimeException(e);
        }
    }

    private String readSqlScriptsFromInputStream(String fileName) {
        StringBuilder sqlScript = new StringBuilder();
        log.info("Reading data from file {}", fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sqlScript.append(line);
            }
            return sqlScript.toString();
        } catch (IOException e) {
            log.error("Error reading SQL script from file {}: {}", fileName, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private InputStream getInputStream(String fileName) {
        log.info("Getting inputStream for file {}", fileName);
        return DatabaseInitListener.class.getClassLoader().getResourceAsStream("./scripts/" + fileName);
    }
}
