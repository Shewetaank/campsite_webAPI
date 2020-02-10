package com.campsite.api.entity;


import com.campsite.api.validators.CampsiteRegistrationAnnotation;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "CAMPSITEREGISTRATION")
@CampsiteRegistrationAnnotation
public class CampsiteRegistration {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "userId", length = 20)
    private String userId;

    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 80, nullable = false)
    private String email;

    @Column(name = "bookingNumber", length = 50, nullable = false)
    private String bookingNumber;

    @Column(name = "fromDate")
    private Date fromDate;

    @Column(name = "toDate")
    private Date toDate;

    @PrePersist
    public void prePersist() {
        if (bookingNumber == null) {
            bookingNumber = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } else {
            bookingNumber = bookingNumber.substring(0, 8).toUpperCase();
        }
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
