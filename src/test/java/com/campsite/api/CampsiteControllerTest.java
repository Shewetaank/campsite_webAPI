package com.campsite.api;


import com.campsite.api.client.ClientBookingObject;
import com.campsite.api.client.User;
import com.campsite.api.controllers.CampsiteController;
import com.campsite.api.dao.CampsiteRegistrationDAO;
import com.campsite.api.service.CampsiteRegistrationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.campsite.api.UUIDClass.getUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CampsiteControllerTest {

    private CampsiteRegistrationService campsiteRegistrationService;

    @Before
    public void setUp() {
        this.campsiteRegistrationService = mock(CampsiteRegistrationService.class);
    }

    @Test
    public void test_get_campsite_registrations() {
        when(campsiteRegistrationService.getCampsiteRegistration()).thenReturn(getClientBookingObjects());
        List<ClientBookingObject> clientBookingObjects = campsiteRegistrationService.getCampsiteRegistration();
        assertEquals(2, clientBookingObjects.size());
    }

    @Test
    public void test_get_campsite_registration_by_booking_number() {
        when(campsiteRegistrationService.getCampsiteRegistration(isA(String.class))).thenReturn(getClientBookingObject());
        List<ClientBookingObject> clientBookingObjects = campsiteRegistrationService.getCampsiteRegistration("TEST1234");
        assertEquals(1, clientBookingObjects.size());
        assertEquals("TEST1234", clientBookingObjects.get(0).getBookingNumber());
    }

    @Test
    public void test_delete_deleteCampsiteRegistration() {
        when(campsiteRegistrationService.deleteCampsiteRegistration(isA(String.class))).thenReturn(1L);
        Long result = campsiteRegistrationService.deleteCampsiteRegistration("TEST1234");
        assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void test_delete_deleteCampsiteRegistrationForAnonymousUser() {
        when(campsiteRegistrationService.deleteCampsiteRegistrationForAnonymousUser(isA(String.class))).thenReturn(1L);
        Long result = campsiteRegistrationService.deleteCampsiteRegistrationForAnonymousUser("TEST1234");
        assertEquals(Long.valueOf(1), result);
    }

    private List<ClientBookingObject> getClientBookingObject() {
        return new ArrayList<ClientBookingObject>(){{
            add(new ClientBookingObject() {{
                setUser(new User() {{
                    setId(getUUID());
                    setFirstName(getUUID());
                    setLastName(getUUID());
                    setEmail(getUUID());
                }});
                setBookingNumber("TEST1234");
                setFromDate(new Date());
                setToDate(new Date());
            }});
        }};
    }

    private List<ClientBookingObject> getClientBookingObjects() {
        List<ClientBookingObject> clientBookingObjects = new ArrayList<>();
        clientBookingObjects.add(new ClientBookingObject() {{
            setUser(new User() {{
                setId(getUUID());
                setFirstName(getUUID());
                setLastName(getUUID());
                setEmail(getUUID());
            }});
            setBookingNumber("E4FH5S23");
            setFromDate(new Date());
            setToDate(new Date());
        }});
        clientBookingObjects.add(new ClientBookingObject() {{
            setUser(new User() {{
                setId(getUUID());
                setFirstName(getUUID());
                setLastName(getUUID());
                setEmail(getUUID());
            }});
            setBookingNumber("E4F3HS23");
            setFromDate(new Date());
            setToDate(new Date());
        }});
        return clientBookingObjects;
    }
}
