package org.diploma.personalaccess.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unit")
public class Unit extends BaseEntity {

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unit")
    private Set<Form> forms = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }

}
