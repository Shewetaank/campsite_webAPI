package com.campsite.api.mapper;

import com.campsite.api.client.ClientBookingObject;
import com.campsite.api.client.User;
import com.campsite.api.entity.CampsiteRegistration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampsiteRegistrationMapper {

    public static List<ClientBookingObject> getCampsiteRegistrationMapper(ResultSet resultSet) throws SQLException {
        List<ClientBookingObject> clientBookingObjects = new ArrayList<>();
        while (resultSet.next()) {
            clientBookingObjects.add(new ClientBookingObject() {{
                setUser(new User() {{
                    setId(resultSet.getString("userId"));
                    setEmail(resultSet.getString("email"));
                }});
                setFromDate(resultSet.getDate("fromDate"));
                setToDate(resultSet.getDate("toDate"));
                setBookingNumber(resultSet.getString("bookingNumber"));
            }});
        }
        return clientBookingObjects;
    }

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

    public static ClientBookingObject getSingleCampsiteRegistrationMapper(ResultSet resultSet) throws SQLException {
        ClientBookingObject clientBookingObject = new ClientBookingObject();
        while(resultSet.next()) {
            clientBookingObject.setUser(new User() {{
                setId(resultSet.getString(2));
                setFirstName(resultSet.getString(3));
                setLastName(resultSet.getString(4));
                setEmail(resultSet.getString(6));
            }});
            clientBookingObject.setFromDate(resultSet.getDate(7));
            clientBookingObject.setToDate(resultSet.getDate(8));
            clientBookingObject.setBookingNumber(resultSet.getString(5));
            return clientBookingObject;
        }
        return null;
    }
}
