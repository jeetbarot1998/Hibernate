package org.ORM.ManyToMany;


import org.ORM.OneToMany.StudentO2M;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LaptopM2M {

    @Id
    private int id;
    private String name;

    @ManyToMany
    private List<StudentM2M> studentm2m = new ArrayList<>();

    public List<StudentM2M> getStudentm2m() {
        return studentm2m;
    }

    public void setStudentm2m(List<StudentM2M> studentm2m) {
        this.studentm2m = studentm2m;
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
