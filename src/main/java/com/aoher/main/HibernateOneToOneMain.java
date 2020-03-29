package com.aoher.main;

import com.aoher.model.Customer;
import com.aoher.model.Txn;
import com.aoher.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HibernateOneToOneMain {

    private static Logger log = LoggerFactory.getLogger(HibernateOneToOneMain.class);

    public static void main(String[] args) {
        Txn txn = buildDemoTransaction();

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
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
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            Txn txn = session.get(Txn.class, id);
            tx.commit();
            log.info(String.format(" === === Transaction Details= %s", txn));
        } catch (Exception e) {
            log.error(String.format("Exception occured. %s", e.getMessage()));
        } finally {
            if (sessionFactory != null && !sessionFactory.isClosed()) {
                log.info("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }

    private static Txn buildDemoTransaction() {
        Txn txn = new Txn();
        txn.setDate(new Date());
        txn.setTotal(100);

        Customer customer = new Customer();
        customer.setAddress("Bangalore, India");
        customer.setEmail("pankaj@gmail.com");
        customer.setName("Pankaj Kumar");

        txn.setCustomer(customer);
        customer.setTxn(txn);
        return txn;
    }
}
