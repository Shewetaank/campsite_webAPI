package com.campsite.api.mapper;

import com.campsite.api.pojos.CampsiteRegistration;
import com.campsite.api.pojos.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampsiteRegistrationMapper {

    public static List<CampsiteRegistration> getCampsiteRegistrationMapper(ResultSet resultSet) throws SQLException {
        List<CampsiteRegistration> campsiteRegistrations = new ArrayList<>();
        while (resultSet.next()) {
            campsiteRegistrations.add(new CampsiteRegistration() {{
                setUser(new User() {{
                    setId(resultSet.getString("userId"));
                    setEmail(resultSet.getString("email"));
                }});
                setFromDate(resultSet.getDate("fromDate"));
                setToDate(resultSet.getDate("toDate"));
                setBookingNumber(resultSet.getString("bookingNumber"));
            }});
        }
        return campsiteRegistrations;
    }

    public static CampsiteRegistration getSingleCampsiteRegistrationMapper(ResultSet resultSet) throws SQLException {
        CampsiteRegistration campsiteRegistration = new CampsiteRegistration();
        while(resultSet.next()) {
            campsiteRegistration.setUser(new User() {{
                setId(resultSet.getString(2));
                setFirstName(resultSet.getString(3));
                setLastName(resultSet.getString(4));
                setEmail(resultSet.getString(6));
            }});
            campsiteRegistration.setFromDate(resultSet.getDate(7));
            campsiteRegistration.setToDate(resultSet.getDate(8));
            campsiteRegistration.setBookingNumber(resultSet.getString(5));
            return campsiteRegistration;
        }
        return null;
    }
}
