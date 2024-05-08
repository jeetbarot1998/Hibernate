package org.ORM.OneToMany;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LaptopO2M {

    @Id
    private int id;
    private String name;

    @ManyToOne
    private StudentO2M studento2m;

    public StudentO2M getStudent() {
        return studento2m;
    }

    public void setStudent(StudentO2M student) {
        this.studento2m = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
