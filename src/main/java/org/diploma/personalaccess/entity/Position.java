package org.diploma.personalaccess.entity;

import com.google.gson.annotations.Expose;
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

    @Expose
    @Column(name = "name", nullable = false, length = 255)
    @NotEmpty
    @Length(max = 255)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
    private Set<Form> forms = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "availablePositions")
    private Set<Index> availableIndexes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dependency",
            joinColumns = {@JoinColumn(name = "position_sub_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "position_lead_id", nullable = false)})
    private Set<Position> leads = new HashSet<>();

    @ManyToMany(mappedBy = "leads")
    private Set<Position> subs = new HashSet<>();


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

    public Set<Position> getLeads() {
        return leads;
    }

    public void setLeads(Set<Position> leads) {
        this.leads = leads;
    }

    public Set<Position> getSubs() {
        return subs;
    }

    public void setSubs(Set<Position> subs) {
        this.subs = subs;
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
                .append("subs", getSubs())
                .toString();
    }

}
