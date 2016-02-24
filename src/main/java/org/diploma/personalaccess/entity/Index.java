package org.diploma.personalaccess.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an work index of employee.
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "_index")
public class Index extends BaseEntity {

    @Column(name = "name", length = 400)
    @NotEmpty
    @Length(max = 400)
    private String name;

    @Column(name = "estimate")
    @Min(0)
    private int estimate;

    @Column(name = "multiplier")
    @Min(1)
    private int multiplier;

    @Column(name = "work_name", length = 50)
    @Length(max = 50)
    private String workName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "index")
    private Set<UserIndex> userIndexes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "available_index",
            joinColumns = {@JoinColumn(name = "index_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "position_id", referencedColumnName = "id")})
    private Set<Position> availablePositions = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Set<UserIndex> getUserIndexes() {
        return userIndexes;
    }

    public void setUserIndexes(Set<UserIndex> userIndexes) {
        this.userIndexes = userIndexes;
    }

    public Set<Position> getAvailablePositions() {
        return availablePositions;
    }

    public void setAvailablePositions(Set<Position> availablePositions) {
        this.availablePositions = availablePositions;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("name", getName())
                .append("estimate", getEstimate())
                .append("multiplier", getMultiplier())
                .append("workName", getWorkName())
                .toString();
    }

}
