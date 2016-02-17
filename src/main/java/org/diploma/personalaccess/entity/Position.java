package org.diploma.personalaccess.entity;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an position of employee in university.
 *
 * @author Maksim Patapenka
 */
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

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("name", getName())
                .toString();
    }

}
