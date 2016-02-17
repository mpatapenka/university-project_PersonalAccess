package org.diploma.personalaccess.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an approve document.
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "document")
public class Document extends BaseEntity {

    @Column(name = "name", length = 255)
    @NotEmpty
    @Length(max = 255)
    private String name;

    @Column(name = "system_name", length = 255)
    @Length(max = 255)
    private String systemName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private Set<UserIndex> userIndexes = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Set<UserIndex> getUserIndexes() {
        return userIndexes;
    }

    public void setUserIndexes(Set<UserIndex> userIndexes) {
        this.userIndexes = userIndexes;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("name", getName())
                .append("systemName", getSystemName())
                .toString();
    }

}
