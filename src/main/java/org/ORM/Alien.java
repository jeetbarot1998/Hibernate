package org.ORM;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "MyAlienTable")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Alien {

    @Id
    private int aid;

    @Column(name = "alien_name")
    private String name;

//    @Transient //Temporary data
    private String color;

    private AlienName alienFullName;

    public AlienName getAlienFullName() {
        return alienFullName;
    }

    public void setAlienFullName(AlienName alienFullName) {
        this.alienFullName = alienFullName;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", alienFullName=" + alienFullName +
                '}';
    }
}
