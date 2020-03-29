package com.aoher.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

    private static final String HIBERNATE_CFG = "hibernate.cfg.xml";

    private static Logger log = LoggerFactory.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(HIBERNATE_CFG);
            log.info("Hibernate Configuration loaded");

            return configuration.buildSessionFactory();
        } catch (Exception e) {
            log.error(String.format("Initial SessionFactory creation failed: %s", e));
            throw new ExceptionInInitializerError(e);
        }
    }
}
