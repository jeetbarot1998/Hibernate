package org.ORM.ManyToMany;

import org.ORM.OneToMany.LaptopO2M;
import org.ORM.OneToMany.StudentO2M;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class RunnerM2M {

    public static void main(String[] args) {
        m1();
    }

    static void m1(){
        LaptopM2M l = new LaptopM2M();
        l.setId(101);
        l.setName("Dell");
        StudentM2M s = new StudentM2M();
        s.setRollNo(1);
        s.setName("Abc");
        s.setMarks(10);
        s.getLaptops().add(l);
        l.getStudentm2m().add(s);

        Configuration config = new Configuration().configure().addAnnotatedClass(LaptopM2M.class).addAnnotatedClass(StudentM2M.class);
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
