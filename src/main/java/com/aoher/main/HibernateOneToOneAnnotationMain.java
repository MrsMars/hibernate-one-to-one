package com.aoher.main;

import com.aoher.model.Customer1;
import com.aoher.model.Txn1;
import com.aoher.util.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HibernateOneToOneAnnotationMain {private static Logger log = LoggerFactory.getLogger(HibernateOneToOneMain.class);

    public static void main(String[] args) {
        Txn1 txn = buildDemoTransaction();

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx;

        try {
            sessionFactory = HibernateAnnotationUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            log.info("Session created");

            tx = session.beginTransaction();
            session.save(txn);
            tx.commit();
            log.info(String.format("Transaction ID=%s", txn.getId()));

            printTransactionData(txn.getId());
        } catch (Exception e) {
            log.error(String.format("Exception occured. %s", e.getMessage()));
        } finally {
            if (session != null && !sessionFactory.isClosed()) {
                log.info("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }

    private static void printTransactionData(long id) {
        SessionFactory sessionFactory = null;
        Session session;
        Transaction tx;

        try {
            sessionFactory = HibernateAnnotationUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            Txn1 txn = session.get(Txn1.class, id);
            tx.commit();
            log.info(String.format(" === === Annotation Example. Transaction Details= %s", txn));
        } catch (Exception e) {
            log.error(String.format("Exception occured. %s", e.getMessage()));
        } finally {
            if (sessionFactory != null && !sessionFactory.isClosed()) {
                log.info("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }

    private static Txn1 buildDemoTransaction() {
        Txn1 txn = new Txn1();
        txn.setDate(new Date());
        txn.setTotal(100);

        Customer1 customer = new Customer1();
        customer.setAddress("Bangalore, India");
        customer.setEmail("pankaj@gmail.com");
        customer.setName("Pankaj Kumar");

        txn.setCustomer(customer);
        customer.setTxn(txn);
        return txn;
    }
}
