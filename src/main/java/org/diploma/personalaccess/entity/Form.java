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
 * Simple JavaBean domain object representing an form of employee.
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "form")
public class Form extends BaseEntity {

    @Expose
    @Column(name = "first_name", length = 50)
    @NotEmpty
    @Length(max = 50)
    private String firstName;

    @Expose
    @Column(name = "last_name", length = 50)
    @NotEmpty
    @Length(max = 50)
    private String lastName;

    @Column(name = "middle_name", length = 50)
    @NotEmpty
    @Length(max = 50)
    private String middleName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "form")
    private Set<User> users = new HashSet<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Form form = (Form) o;
        return new EqualsBuilder()
                .append(firstName, form.firstName)
                .append(lastName, form.lastName)
                .append(middleName, form.middleName)
                .append(unit, form.unit)
                .append(faculty, form.faculty)
                .append(position, form.position)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("firstName", getFirstName())
                .append("lastName", getLastName())
                .append("middleName", getMiddleName())
                .append("unit", getUnit())
                .append("faculty", getFaculty())
                .append("position", getPosition())
                .toString();
    }
    
}
