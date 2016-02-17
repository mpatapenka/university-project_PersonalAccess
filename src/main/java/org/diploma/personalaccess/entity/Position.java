package org.diploma.personalaccess.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position extends BaseEntity {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
    private Set<Form> forms = new HashSet<>();

    @ManyToMany(mappedBy = "availablePositions")
    private Set<Index> availableIndexes = new HashSet<>();

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
