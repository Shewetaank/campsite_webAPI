package com.campsite.api.service;

import com.campsite.api.client.ClientBookingObject;
import com.campsite.api.dao.CampsiteRegistrationDAO;
import com.campsite.api.entity.CampsiteRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static com.campsite.api.mapper.CampsiteRegistrationMapper.*;

@Service
@Transactional
public class CampsiteRegistrationService {

    @Autowired
    private CampsiteRegistrationDAO campsiteRegistrationDAO;

    public List<ClientBookingObject> getCampsiteRegistration() {
        Iterable<com.campsite.api.entity.CampsiteRegistration> campsiteRegistrations = campsiteRegistrationDAO.findAll();
        return getCampsiteRegistrationMapper(campsiteRegistrations);
    }

    public ClientBookingObject campsiteRegistration(ClientBookingObject clientBookingObject) throws Exception {
        if (validateBookingDateRanges(clientBookingObject)) {
            if (clientBookingObject.getBookingNumber() != null) {
                Iterable<com.campsite.api.entity.CampsiteRegistration> existingBookings = campsiteRegistrationDAO
                        .findByBookingNumber(clientBookingObject.getBookingNumber());

                CampsiteRegistration existingBooking = existingBookings.iterator().next();
                existingBooking.setEmail(clientBookingObject.getUser().getEmail());
                existingBooking.setBookingNumber(clientBookingObject.getBookingNumber());
                existingBooking.setFirstName(clientBookingObject.getUser().getFirstName());
                existingBooking.setLastName(clientBookingObject.getUser().getLastName());
                existingBooking.setFromDate(new java.sql.Date(clientBookingObject.getFromDate().getTime()));
                existingBooking.setToDate(new java.sql.Date(clientBookingObject.getToDate().getTime()));

                return getClientBookingFromRegistration(campsiteRegistrationDAO.save(existingBooking));
            } else {
                CampsiteRegistration campsiteRegistration = campsiteRegistrationDAO.save(getRegistrationFromClientBookingObject(clientBookingObject));
                return getClientBookingFromRegistration(campsiteRegistration);
            }
        } else {
            throw new Exception("Cannot create booking because the dates overlap with an existing booking. " +
                    "Please refresh the page to check the latest available dates.");
        }
    }

    public ClientBookingObject campsiteRegistrationForAnonymousUser(ClientBookingObject clientBookingObject) throws Exception {
        if (validateBookingDateRanges(clientBookingObject)) {
            if (clientBookingObject.getBookingNumber() != null) {
                Iterable<com.campsite.api.entity.CampsiteRegistration> existingBookings = campsiteRegistrationDAO
                        .findByBookingNumber(clientBookingObject.getBookingNumber());

                CampsiteRegistration existingBooking = existingBookings.iterator().next();
                existingBooking.setEmail(clientBookingObject.getUser().getEmail());
                existingBooking.setBookingNumber(clientBookingObject.getBookingNumber());
                existingBooking.setFirstName(clientBookingObject.getUser().getFirstName());
                existingBooking.setLastName(clientBookingObject.getUser().getLastName());
                existingBooking.setFromDate(new java.sql.Date(clientBookingObject.getFromDate().getTime()));
                existingBooking.setToDate(new java.sql.Date(clientBookingObject.getToDate().getTime()));

                return getClientBookingFromRegistration(campsiteRegistrationDAO.save(existingBooking));
            } else {
                CampsiteRegistration campsiteRegistration = campsiteRegistrationDAO.save(getRegistrationFromClientBookingObject(clientBookingObject));
                return getClientBookingFromRegistration(campsiteRegistration);
            }
        } else {
            throw new Exception("Cannot create booking because the dates overlap with an existing booking. " +
                    "Please refresh the page to check the latest available dates.");
        }
    }

    public List<ClientBookingObject> getCampsiteRegistration(String bookingNumber) {
        Iterable<com.campsite.api.entity.CampsiteRegistration> campsiteRegistrations = campsiteRegistrationDAO.findByBookingNumber(bookingNumber);
        return getCampsiteRegistrationMapper(campsiteRegistrations);
    }

    public Long deleteCampsiteRegistrationForAnonymousUser(String bookingNumber) {
        return campsiteRegistrationDAO.deleteByBookingNumber(bookingNumber);
    }

    public Long deleteCampsiteRegistration(String bookingNumber) {
        return campsiteRegistrationDAO.deleteByBookingNumber(bookingNumber);
    }

    private boolean validateBookingDateRanges(ClientBookingObject clientBookingObject) {
        boolean valid = true;
        Iterable<com.campsite.api.entity.CampsiteRegistration> campsiteRegistrations = campsiteRegistrationDAO.findAll();
        for (CampsiteRegistration r : campsiteRegistrations) {
            if (overlap(new java.sql.Date(clientBookingObject.getFromDate().getTime()),
                    new java.sql.Date(clientBookingObject.getToDate().getTime()),
                    r.getFromDate(), r.getToDate())) {
                valid = false;
            }
        }
        return valid;
    }

    private boolean overlap(Date start1, Date end1, Date start2, Date end2) {
        return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime();
    }
}
