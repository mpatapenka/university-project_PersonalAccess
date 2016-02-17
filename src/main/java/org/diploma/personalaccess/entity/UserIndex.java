package org.diploma.personalaccess.entity;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Simple JavaBean domain object representing an user work indexes.
 *
 * @author Maksim Patapenka
 */
@Entity
@Table(name = "user_index")
public class UserIndex extends BaseEntity {

    @Column(name = "self_estimate")
    private int selfEstimate;

    @Column(name = "lead_estimate")
    private int leadEstimate;

    @Column(name = "fill_date")
    private Date fillDate;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "index_id")
    private Index index;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    public int getSelfEstimate() {
        return selfEstimate;
    }

    public void setSelfEstimate(int selfEstimate) {
        this.selfEstimate = selfEstimate;
    }

    public int getLeadEstimate() {
        return leadEstimate;
    }

    public void setLeadEstimate(int leadEstimate) {
        this.leadEstimate = leadEstimate;
    }

    public Date getFillDate() {
        return fillDate;
    }

    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", getId())
                .append("selfEstimate", getSelfEstimate())
                .append("leadEstimate", getLeadEstimate())
                .append("fillDate", getFillDate())
                .append("description", getDescription())
                .append("index", getIndex())
                .append("user", getUser())
                .append("document", getDocument())
                .toString();
    }

}
