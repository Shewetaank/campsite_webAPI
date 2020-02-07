package com.campsite.api.mapper;

import com.campsite.api.client.ClientBookingObject;
import com.campsite.api.client.User;
import com.campsite.api.entity.CampsiteRegistration;

import java.util.ArrayList;
import java.util.List;

public class CampsiteRegistrationMapper {

    public static List<ClientBookingObject> getCampsiteRegistrationMapper(Iterable<com.campsite.api.entity.CampsiteRegistration> campsiteRegistrations) {
        List<ClientBookingObject> clientBookingObjectList = new ArrayList<>();
        campsiteRegistrations.forEach(r -> {
            clientBookingObjectList.add(new ClientBookingObject() {{
                setUser(new User() {{
                    setId(r.getUserId());
                    setEmail(r.getEmail());
                    setFirstName(r.getFirstName());
                    setLastName(r.getLastName());
                }});
                setFromDate(r.getFromDate());
                setToDate(r.getToDate());
                setBookingNumber(r.getBookingNumber());
            }});
        });
        return clientBookingObjectList;
    }

    public static CampsiteRegistration getRegistrationFromClientBookingObject(ClientBookingObject clientBookingObject) {
        CampsiteRegistration request = new CampsiteRegistration();
        request.setUserId(clientBookingObject.getUser().getId());
        request.setFirstName(clientBookingObject.getUser().getFirstName());
        request.setLastName(clientBookingObject.getUser().getLastName());
        request.setEmail(clientBookingObject.getUser().getEmail());
        request.setFromDate(new java.sql.Date(clientBookingObject.getFromDate().getTime()));
        request.setToDate(new java.sql.Date(clientBookingObject.getToDate().getTime()));
        return request;
    }

    public static ClientBookingObject getClientBookingFromRegistration(CampsiteRegistration campsiteRegistration) {
        return new ClientBookingObject() {{
            setUser(new User() {{
                setId(campsiteRegistration.getUserId());
                setFirstName(campsiteRegistration.getFirstName());
                setLastName(campsiteRegistration.getLastName());
                setEmail(campsiteRegistration.getEmail());
            }});
            setBookingNumber(campsiteRegistration.getBookingNumber());
            setFromDate(campsiteRegistration.getFromDate());
            setToDate(campsiteRegistration.getToDate());
        }};
    }
}
