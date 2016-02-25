package org.diploma.personalaccess.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an unit of university.
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "unit")
public class Unit extends BaseEntity {

    @Column(name = "name", length = 100)
    @NotEmpty
    @Length(max = 100)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Unit unit = (Unit) o;
        return new EqualsBuilder()
                .append(name, unit.name)
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
