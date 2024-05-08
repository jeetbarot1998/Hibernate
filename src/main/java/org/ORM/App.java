package org.ORM;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
//        Create();
//        GetFirstLevelCaching();
//        GetSecondLevelCaching();
        GetQueryLevelCaching();
//     SetUsingNestedObject();
    }

    static void SetUsingNestedObject(){
        class populateAlienFullName{

            AlienName populate(){
                AlienName alienName = new AlienName();
                alienName.setFname("fname");
                alienName.setMname("mname");
                alienName.setLname("lname");
                return alienName;
            }
        }

        Alien myAlien = new Alien();
        myAlien.setAid(5);
        myAlien.setColor("Purple");
        myAlien.setAlienFullName(new populateAlienFullName().populate());

        Configuration conn = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(myAlien);
        tx.commit();
        System.out.println(myAlien);
    }


    static void GetFirstLevelCaching(){
        Alien myAlien = new Alien();

        Configuration conn = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        myAlien = (Alien) session.get(Alien.class, 5);
        System.out.println(myAlien);
        myAlien = (Alien) session.get(Alien.class, 5);
        System.out.println(myAlien);
        tx.commit();
        sf.close();
    }

    static void GetQueryLevelCaching(){
        Alien myAlien = new Alien();

        Configuration conn = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session1 = sf.openSession();

        session1.beginTransaction();
        Query q1 = session1.createQuery("from Alien Where aid=5");
        q1.setCacheable(true);
        myAlien = (Alien) q1.uniqueResult();
        System.out.println(myAlien);
        session1.getTransaction().commit();
        session1.close();

        Session session2 = sf.openSession();
        session2.beginTransaction();
        Query q2 = session2.createQuery("from Alien where aid=5");
        q2.setCacheable(true);
        myAlien = (Alien) q2.uniqueResult();
        System.out.println(myAlien);
        session2.getTransaction().commit();
        session2.close();

    }

    static void GetSecondLevelCaching(){
        Alien myAlien = new Alien();

        Configuration conn = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session1 = sf.openSession();

        Transaction tx = session1.getTransaction();
        tx.begin();
        myAlien = (Alien) session1.get(Alien.class, 5);
        System.out.println(myAlien);
        Query q1 = session1.createQuery("Select * from MyAlienTable Where aid = 5");
        q1.setCacheable(true);
        myAlien = (Alien) q1.uniqueResult();
        System.out.println(myAlien);
        tx.commit();


        session1.close();

        Session session2 = sf.openSession();
        Transaction tx2 = session2.getTransaction();
        tx2.begin();
        myAlien = (Alien) session2.get(Alien.class, 5);
        System.out.println(myAlien);
        tx2.commit();
        session2.close();

    }

    static void Create(){
        Alien myAlien = new Alien();
        myAlien.setAid(3);
        myAlien.setColor("Cyan");
        myAlien.setName("MyAlien3");

        Configuration conn = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(myAlien);
        tx.commit();
        System.out.println(myAlien);
    }
}
