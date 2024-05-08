package org.ORM.Hql;

import org.ORM.Alien;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
//        hql1();
//        UsingHqlToGetCertainColumns();
        UsingNativeSQLQuery();
    }

    static void UsingNativeSQLQuery(){
        Configuration conn = new Configuration().configure().addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session1 = sf.openSession();
        session1.beginTransaction();
        SQLQuery query = session1.createSQLQuery("select rollNo, name from Student");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List students = query.list();
        for(Object o : students){
            Map m = (Map) o;
            System.out.println(m.get("name") + " : " +m.get("rollNo"));
        }
        session1.getTransaction().commit();
        session1.close();
    }

    static void UsingHqlToGetCertainColumns(){
        Configuration conn = new Configuration().configure().addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session1 = sf.openSession();
        int mark = 30;
        session1.beginTransaction();
        Query q = session1.createQuery(" select rollNo, name from Student s where s.marks> :mark");
        q.setParameter("mark", mark);
        List<Object[]> students = (List<Object[]>) q.list();
        for(Object[] s : students){
            System.out.println(s[0] + " " + s[1]);
        }
        session1.getTransaction().commit();
        session1.close();
    }

    static void hql1(){
        Configuration conn = new Configuration().configure().addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
        SessionFactory sf = conn.buildSessionFactory(reg);
        Session session1 = sf.openSession();
        session1.beginTransaction();
//        Random r = new Random();
//
//        for(int i = 0 ; i<50; i++){
//            Student s = new Student();
//            s.setMarks(r.nextInt(100));
//            s.setRollNo(i);
//            s.setName("Name " + i);
//            session1.save(s);
//        }

        Query q = session1.createQuery("from Student");
        List <Student> students = q.list();
        List<Student> collect = students.stream().filter(st -> st.getMarks() > 50).collect(Collectors.toList());
        System.out.println(collect);
        session1.getTransaction().commit();
        session1.close();
    }
}
