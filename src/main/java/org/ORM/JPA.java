package org.ORM;

import org.ORM.Hql.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Student s1 = em.find(Student.class, 4);
        System.out.println(s1);

        // Create a new student
        Student student = new Student();
        student.setName("John Doe");
        student.setMarks(20);

        // Begin a transaction
        em.getTransaction().begin();

        // Save the student object to the database
        em.persist(student);

        // Commit the transaction
        em.getTransaction().commit();

        Student s = em.find(Student.class, 4);
        System.out.println(s);

        // Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}


