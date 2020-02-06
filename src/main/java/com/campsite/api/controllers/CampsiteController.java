package com.campsite.api.controllers;

import com.campsite.api.dao.CampsiteDBDao;
import com.campsite.api.pojos.CampsiteRegistration;
import com.campsite.api.repository.RegistrationSQLCommands;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.campsite.api.mapper.CampsiteRegistrationMapper.getCampsiteRegistrationMapper;
import static com.campsite.api.mapper.CampsiteRegistrationMapper.getSingleCampsiteRegistrationMapper;
import static com.campsite.api.repository.RegistrationSQLCommands.*;
import static java.util.Objects.nonNull;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CampsiteController {

    final static Logger LOGGER = Logger.getLogger(CampsiteController.class);

    @Autowired
    private CampsiteDBDao campsiteDBDao;

    @PostMapping("/campsiteRegistration")
    public CampsiteRegistration campsiteRegistration(@RequestBody CampsiteRegistration campsiteRegistration) {
        int generatedKey = 0;
        try {
            String sql = String.format(insertCommand, campsiteRegistration.getUser().getId(),
                    campsiteRegistration.getUser().getFirstName(),
                    campsiteRegistration.getUser().getLastName(),
                    campsiteRegistration.getUser().getEmail(),
                    new java.sql.Date(campsiteRegistration.getFromDate().getTime()),
                    new java.sql.Date(campsiteRegistration.getToDate().getTime()));

            PreparedStatement preparedStatement = campsiteDBDao.getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            Statement statement = campsiteDBDao.getStatement();
            ResultSet resultSet = statement.executeQuery(String.format(searchByBookingNUmnber, generatedKey));
            if(nonNull(resultSet) && resultSet.next()) {
                campsiteRegistration.setBookingNumber(resultSet.getString(1));
            } else {
                return null;
            }
            return campsiteRegistration;
        } catch (Exception ex) {
            LOGGER.error("Error while adding campsite registration: " + campsiteRegistration.toString());
            return null;
        }
    }

    @PostMapping("/campsiteRegistration/anonymous")
    public CampsiteRegistration campsiteRegistrationForAnonymousUser(@RequestBody CampsiteRegistration campsiteRegistration) {
        int generatedKey = 0;
        try {
            if(campsiteRegistration.getBookingNumber() != null) {
                String sql = String.format(RegistrationSQLCommands.updateCommand, campsiteRegistration.getUser().getFirstName(),
                        campsiteRegistration.getUser().getLastName(),campsiteRegistration.getUser().getEmail(),
                        new java.sql.Date(campsiteRegistration.getFromDate().getTime()),
                        new java.sql.Date(campsiteRegistration.getToDate().getTime()),
                        campsiteRegistration.getBookingNumber());
                PreparedStatement preparedStatement = campsiteDBDao.getConnection().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int update = preparedStatement.executeUpdate();
                return update == 1 ? campsiteRegistration : null;
            } else {
                String sql =  String.format(insertCommand, null,
                        campsiteRegistration.getUser().getFirstName(),
                        campsiteRegistration.getUser().getLastName(),
                        campsiteRegistration.getUser().getEmail(),
                        new java.sql.Date(campsiteRegistration.getFromDate().getTime()),
                        new java.sql.Date(campsiteRegistration.getToDate().getTime()));
                PreparedStatement preparedStatement = campsiteDBDao.getConnection().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }
                Statement statement = campsiteDBDao.getStatement();
                ResultSet resultSet = statement.executeQuery(String.format(searchByBookingNUmnber, generatedKey));
                if(nonNull(resultSet) && resultSet.next()) {
                    campsiteRegistration.setBookingNumber(resultSet.getString(1));
                } else {
                    return null;
                }
                return campsiteRegistration;
            }
        } catch (Exception ex) {
            LOGGER.error("Error while adding campsite registration: " + campsiteRegistration.toString());
            return null;
        }
    }

    @GetMapping("/campsiteRegistration/get")
    public List<CampsiteRegistration> getCampsiteRegistration() {
        try {
            Statement statement = campsiteDBDao.getStatement();
            ResultSet rs = statement.executeQuery(getAllBookings);
            return getCampsiteRegistrationMapper(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/campsiteRegistration/anonymous/{bookingNumber}")
    public List<CampsiteRegistration> getCampsiteRegistration(@PathVariable String bookingNumber) {
        try {
            Statement statement = campsiteDBDao.getStatement();
            String sql =  String.format(searchByBookingNumber, bookingNumber);
            ResultSet rs = statement.executeQuery(sql);
            CampsiteRegistration singleCampsiteRegistrationMapper = getSingleCampsiteRegistrationMapper(rs);
            if(nonNull(singleCampsiteRegistrationMapper)) {
                return new ArrayList<CampsiteRegistration>() {{
                    add(singleCampsiteRegistrationMapper);
                }};
            } else {
                return new ArrayList<>();
            }
        } catch (Exception ex) {
            LOGGER.error("Error while getting the booking number");
        }
        return null;
    }

    @DeleteMapping("/campsiteRegistration/anonymous/{bookingNumber}")
    public boolean deleteCampsiteRegistrationForAnonymousUser(@PathVariable String bookingNumber) {
        try {
            return deleteBooking(bookingNumber);
        } catch (Exception ex) {
            LOGGER.error("Error while getting the booking number");
        }
        return false;
    }


    @DeleteMapping(value = "/campsiteRegistration/{bookingNumber}")
    public boolean deleteCampsiteRegistration(@PathVariable String bookingNumber) {
        try {
            return deleteBooking(bookingNumber);
        } catch (Exception ex) {
            LOGGER.error("Error while removing the booking: ", ex);
            return false;
        }
    }

    private boolean deleteBooking(String bookingNumber) throws SQLException {
        Statement statement = campsiteDBDao.getStatement();
        int rs = statement.executeUpdate(String.format(deleteBooking, bookingNumber));
        return rs == 1;
    }
}
