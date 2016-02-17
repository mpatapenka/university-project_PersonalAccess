package org.diploma.personalaccess.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an user role ('ADMIN', 'USER', etc).
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(name = "name", length = 50)
    @NotEmpty
    @Length(max = 50)
    private String name;

    @Column(name = "code", length = 20)
    @NotEmpty
    @Length(max = 20)
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("name", getName())
                .append("code", getCode())
                .toString();
    }

}
