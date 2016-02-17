package org.diploma.personalaccess.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserIndex> userIndexes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dependency",
            joinColumns = {@JoinColumn(name = "user_sub_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_lead_id", nullable = false)})
    private Set<User> leads = new HashSet<>();

    @ManyToMany(mappedBy = "leads")
    private Set<User> subs = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<User> getLeads() {
        return leads;
    }

    public void setLeads(Set<User> leads) {
        this.leads = leads;
    }

    public Set<User> getSubs() {
        return subs;
    }

    public void setSubs(Set<User> subs) {
        this.subs = subs;
    }

}
