package org.ORM.OneToMany;

import org.ORM.OneToOne.LaptopO2O;
import org.ORM.OneToOne.StudentO2O;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class RunnerO2M {

    public static void main(String[] args) {
        m1();
    }

    static void m1(){
        LaptopO2M l = new LaptopO2M();
        l.setId(101);
        l.setName("Dell");
        StudentO2M s = new StudentO2M();
        s.setRollNo(1);
        s.setName("Abc");
        s.setMarks(10);
        s.getLaptops().add(l);
        Configuration config = new Configuration().configure().addAnnotatedClass(StudentO2M.class).addAnnotatedClass(LaptopO2M.class);
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
