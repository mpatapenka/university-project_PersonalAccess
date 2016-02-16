package org.diploma.personalaccess.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
    private Set<Form> forms = new HashSet<>();

    @ManyToMany(mappedBy = "availablePositions")
    private Set<Index> availableIndexes = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Index> getAvailableIndexes() {
        return availableIndexes;
    }

    public void setAvailableIndexes(Set<Index> availableIndexes) {
        this.availableIndexes = availableIndexes;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }

}
