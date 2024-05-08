package org.ORM.ManyToMany;


import org.ORM.OneToMany.LaptopO2M;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudentM2M {

    @Id
    private int rollNo;
    private String name;
    private int marks;

//    @ManyToMany(mappedBy = "studentm2m", fetch = FetchType.EAGER) // For Eager fetching
//    IE. fetch values from joining respective tables beforehand.
    @ManyToMany(mappedBy = "studentm2m", fetch = FetchType.LAZY)
    private List<LaptopM2M> laptops = new ArrayList<>();

    public List<LaptopM2M> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopM2M> laptops) {
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

