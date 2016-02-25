package org.diploma.personalaccess.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an faculty of university.
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "faculty")
public class Faculty extends BaseEntity {

    @Column(name = "full_name", length = 255)
    @NotEmpty
    @Length(max = 255)
    private String fullName;

    @Column(name = "short_name", length = 15)
    @NotEmpty
    @Length(max = 15)
    private String shortName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private Set<Form> forms = new HashSet<>();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

        Faculty faculty = (Faculty) o;
        return new EqualsBuilder()
                .append(fullName, faculty.fullName)
                .append(shortName, faculty.shortName)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("fullName", getFullName())
                .append("shortName", getShortName())
                .toString();
    }

}
