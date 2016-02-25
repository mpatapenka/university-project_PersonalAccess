package org.diploma.personalaccess.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
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
    @NotEmpty
    @Length(max = 255)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
    private transient Set<Form> forms = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "availablePositions")
    private transient Set<Index> availableIndexes = new HashSet<>();

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;
        return new EqualsBuilder()
                .append(name, position.name)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("name", getName())
                .toString();
    }

}
