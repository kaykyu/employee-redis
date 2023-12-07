package kq.practice.ssf19.model;

import java.io.Serializable;

public class Employee implements Serializable {
    
    private Integer id;
    private String name;

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "%s: %s".formatted(this.id, this.name);
    }
}
