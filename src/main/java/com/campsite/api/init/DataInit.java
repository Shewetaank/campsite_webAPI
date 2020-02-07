package com.campsite.api.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.campsite.api.dao.CampsiteRegistrationDAO;
import com.campsite.api.entity.CampsiteRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

    private CampsiteRegistrationDAO campsiteRegistrationDAO;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(CampsiteRegistrationDAO campsiteRegistrationDAO) {
        this.campsiteRegistrationDAO = campsiteRegistrationDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        getCampsiteData();
    }

    private void getCampsiteData() {
        long count = campsiteRegistrationDAO.count();

        if (count == 0) {
            CampsiteRegistration p1 = new CampsiteRegistration();

            p1.setUserId("ERS345SA");
            p1.setEmail("shewetaank.indora@gmail.com");
            p1.setFirstName("Shewetaank");
            p1.setLastName("Indora");
            p1.setFromDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            p1.setToDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

            campsiteRegistrationDAO.save(p1);
        }
    }

}