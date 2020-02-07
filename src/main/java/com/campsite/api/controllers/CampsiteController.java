package com.campsite.api.controllers;

import com.campsite.api.client.ClientBookingObject;
import com.campsite.api.dao.CampsiteRegistrationDAO;
import com.campsite.api.service.CampsiteRegistrationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CampsiteController {

    final static Logger LOGGER = Logger.getLogger(CampsiteController.class);

    @Autowired
    private CampsiteRegistrationDAO campsiteRegistrationDAO;
    @Autowired
    private CampsiteRegistrationService campsiteRegistrationService;

    @PostMapping("/campsiteRegistration")
    public ClientBookingObject campsiteRegistration(@RequestBody ClientBookingObject clientBookingObject) {
        try {
            return campsiteRegistrationService.campsiteRegistration(clientBookingObject);
        } catch (Exception ex) {
            LOGGER.error("Error while adding campsite registration: " + clientBookingObject.toString());
            throw ex;
        }
    }

    @PostMapping("/campsiteRegistration/anonymous")
    public ClientBookingObject campsiteRegistrationForAnonymousUser(@RequestBody ClientBookingObject clientBookingObject) {
        try {
            return campsiteRegistrationService.campsiteRegistrationForAnonymousUser(clientBookingObject);
        } catch (Exception ex) {
            LOGGER.error("Error while adding campsite registration: " + clientBookingObject.toString());
            throw ex;
        }
    }

    @GetMapping("/campsiteRegistration/get")
    public List<ClientBookingObject> getCampsiteRegistration() {
        try {
            return campsiteRegistrationService.getCampsiteRegistration();
        } catch (Exception ex) {
            LOGGER.error("Error while getting the registrations from server.", ex);
            throw ex;
        }
    }

    @GetMapping("/campsiteRegistration/anonymous/{bookingNumber}")
    public List<ClientBookingObject> getCampsiteRegistration(@PathVariable String bookingNumber) {
        try {
            return campsiteRegistrationService.getCampsiteRegistration(bookingNumber);
        } catch (Exception ex) {
            LOGGER.error(String.format("Error while getting the registration by booking number: %s", bookingNumber), ex);
            throw ex;
        }
    }

    @DeleteMapping("/campsiteRegistration/anonymous/{bookingNumber}")
    public Long deleteCampsiteRegistrationForAnonymousUser(@PathVariable String bookingNumber) {
        try {
            return campsiteRegistrationService.deleteCampsiteRegistrationForAnonymousUser(bookingNumber);
        } catch (Exception ex) {
            LOGGER.error(String.format("Error while removing the registration by booking number: %s", bookingNumber), ex);
            throw ex;
        }
    }


    @DeleteMapping(value = "/campsiteRegistration/{bookingNumber}")
    public Long deleteCampsiteRegistration(@PathVariable String bookingNumber) {
        try {
            return campsiteRegistrationService.deleteCampsiteRegistration(bookingNumber);
        } catch (Exception ex) {
            LOGGER.error(String.format("Error while removing the registration by booking number: %s", bookingNumber), ex);
            throw ex;
        }
    }
}
