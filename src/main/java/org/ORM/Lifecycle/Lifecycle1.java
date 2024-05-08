package org.ORM.Lifecycle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Lifecycle1 {
    public static void main(String[] args) {
        Configuration conn = new Configuration().configure().addAnnotatedClass(Laptop.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Laptop laptop = new Laptop("Dell", "Latitude"); // transient state.
            session.save(laptop); // laptop becomes persistent by using create statement
            laptop.setModel("Latitude 101"); // This makes an update statement

            transaction.commit(); // Commit the transaction

            session.evict(laptop); // Detaching the object from the session

            laptop.setBrand("Acer"); // Change brand after detachment
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction if an exception occurs
            }
            e.printStackTrace();
        } finally {
            session.close(); // Close the session
        }
    }
}
