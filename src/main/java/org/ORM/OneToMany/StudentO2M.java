package org.ORM.OneToMany;


import org.ORM.OneToOne.LaptopO2O;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudentO2M {

    @Id
    private int rollNo;
    private String name;
    private int marks;

    @OneToMany(mappedBy = "studento2m")
    private List<LaptopO2M> laptops = new ArrayList<>();

    public List<LaptopO2M> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopO2M> laptops) {
        this.laptops = laptops;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                ", laptop=" + laptops +
                '}';
    }
}

