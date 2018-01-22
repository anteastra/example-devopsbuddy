package com.anteastra.devopsbuddy.backend.persistence.domain.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Plan implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    private int id;
    private String name;

    public Plan() {
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
}
