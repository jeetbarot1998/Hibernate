package org.ORM.OneToOne;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class StudentO2O {

    @Id
    private int rollNo;
    private String name;
    private int marks;

    @OneToOne
    private LaptopO2O laptop;

    public LaptopO2O getLaptop() {
        return laptop;
    }

    public void setLaptop(LaptopO2O laptop) {
        this.laptop = laptop;
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
                ", laptop=" + laptop +
                '}';
    }
}

