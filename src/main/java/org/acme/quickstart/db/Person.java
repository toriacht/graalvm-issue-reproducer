package org.acme.quickstart.db;

import javax.persistence.Entity;

import java.time.LocalDate;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public LocalDate birth;
    public Status status;

    // this will store all names in lowercase in the DB,
    // and make them uppercase in the model
    public String getName(){
        return name.toUpperCase();
    }

    public String setName(String name){
        return this.name = name.toLowerCase();
    }

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findAlive(){
        return list("status", Status.Alive);
    }

    public static void deleteStefs(){
        delete("name", "Stef");
    }
}