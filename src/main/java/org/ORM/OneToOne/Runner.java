package org.ORM.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Runner {

    public static void main(String[] args) {
        m1();
    }

    static void m1(){
        LaptopO2O l = new LaptopO2O();
        l.setId(101);
        l.setName("Dell");
        StudentO2O s = new StudentO2O();
        s.setRollNo(1);
        s.setName("Abc");
        s.setMarks(10);
        s.setLaptop(l);
        Configuration config = new Configuration().configure().addAnnotatedClass(StudentO2O.class).addAnnotatedClass(LaptopO2O.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(l);
        session.save(s);
        tx.commit();
        System.out.println(s);
    }

}
