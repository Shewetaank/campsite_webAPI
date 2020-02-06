package com.campsite.api.repository;

public class RegistrationSQLCommands {

    public static String updateCommand =  "UPDATE CAMPSITEREGISTRATION SET firstName = '%s', lastName = '%s', email = " +
            "'%s', fromDate = '%s', toDate = '%s' WHERE bookingNumber = '%s';";

    public static String insertCommand = "INSERT INTO CAMPSITEREGISTRATION(userId, firstName, lastName, email, fromDate, toDate)" +
            "values ('%s','%s','%s','%s','%s','%s')";

    public static String searchByBookingNUmnber = "SELECT bookingNumber from CAMPSITEREGISTRATION where id = %s";

    public static String searchByBookingNumber = "SELECT * FROM CAMPSITEREGISTRATION WHERE bookingNumber = '%s'";

    public static String getAllBookings = "SELECT * FROM CAMPSITEREGISTRATION;";

    public static String deleteBooking = "DELETE FROM CAMPSITEREGISTRATION wHERE bookingNumber = '%s';";
}
