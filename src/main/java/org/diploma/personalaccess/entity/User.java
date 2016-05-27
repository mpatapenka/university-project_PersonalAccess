package org.diploma.personalaccess.entity;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Simple JavaBean domain object representing an user.
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", nullable = false, length = 50)
    @NotEmpty
    @Length(min = 4, max = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 25)
    @NotEmpty
    @Length(min = 4, max = 25)
    private String password;

    /**
     * Default: all profiles are active, not implemented in database
     */
    @Transient
    private boolean active = true;

    @Expose
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserIndex> userIndexes = new HashSet<>();


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<UserIndex> getUserIndexes() {
        return userIndexes;
    }

    public void setUserIndexes(Set<UserIndex> userIndexes) {
        this.userIndexes = userIndexes;
    }


    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive();
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(getRole());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return new EqualsBuilder()
                .append(username, user.username)
                .append(form, user.form)
                .append(role, user.role)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("form", getForm())
                .append("role", getRole())
                .toString();
    }

}
