package com.collegemanagement.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AbstractDao {
    private static SessionFactory sessionFactory;
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (AbstractDao.class) {
                if (sessionFactory == null) {
                    try {
                        sessionFactory = new Configuration().
                                configure("hibernate.cfg.xml").buildSessionFactory();
                               
                    } catch (Throwable ex) {
                        throw new ExceptionInInitializerError(ex);
                    }
                }
            }
        }
        return sessionFactory;
    }
    public Session getSession() throws HibernateException{
        return getSessionFactory().openSession();
    }
    }