# Campsite_webAPI

* [Introduction](#introduction)
* [RESTful URLs](#restful-urls)
* [Record limits](#record-limits)
* [Request & Response Examples](#request--response-examples)

## Introduction

This document gives a high level details of the campsite web API. The web API is a spring boot application that uses h2 db to save the campsite registration
details. The campsite registration table is defined as Entity in the code and hibernate creates the table during the App launch and creates the table with initial data.

## RESTful URLs

The web API has two controllers one for handling the campsite registration CRUD operations and another to manage Users in Okta. The application will run on port 8081
(configured in application.properties file). You can also access h2 db with URL - http://localhost:8081/h2-console.

Use the following values to connect to the h2 db-
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:campsitedb
User Name: sa
Password:

Other base Urls for the campsite registration and User is-

http://localhost:8081/campsiteregistration
http://localhost:8081/registerUser

## Request & Response

The request for the campsite registration from the web App sends user's first name, last name, email address and the date range (minimum of 1 day to a maximum
of 3 days). The request object for the registration looks like-

    {
       "user": {
          "firstName": "test",
          "lastName": "test lastname",
          "email": "test@gmail.com"
       },
       "fromDate": "Tue Feb 18 00:00:00 EST 2020",
       "toDate": "Thu Feb 20 00:00:00 EST 2020"
    }

The response will look like-

    {
       "user": {
          "firstName": "test",
          "lastName": "test lastname",
          "email": "test@gmail.com"
       },
       "bookingNumber": "DBCF22DB", // auto-generated value with fixed length of 8 alpha-numeric characters
       "fromDate": "2020-02-18",
       "toDate": "2020-02-20"
    }

Please check the CampsiteController class for list of other methods. There are separate methods for anonymous and registered user but can be
merged to one.

### GET /campsiteRegistration/get

Example: http://localhost:8081/campsiteRegistration/get

Response body:

    [
       {
          "user": {
             "id": null,
             "firstName": "Shewetaank",
             "lastName": "Indora",
             "email": "shewetaank.indora@gmail.com",
             "password": null
          },
          "bookingNumber": "F1F3EA7C",
          "fromDate": 1581224400000,
          "toDate": 1581224400000
       },
       {
          "user": {
             "id": "00u16vdsciykcByfs4x6",
             "firstName": "Shewetaank",
             "lastName": "Indora",
             "email": "shewetaank.indora@gmail.com",
             "password": null
          },
          "bookingNumber": "74A8F945",
          "fromDate": 1581397200000,
          "toDate": 1581570000000
       },
       {
          "user": {
             "id": "00u16vdsciykcByfs4x6",
             "firstName": "Shewetaank",
             "lastName": "Indora",
             "email": "shewetaank.indora@gmail.com",
             "password": null
          },
          "bookingNumber": "5C2A7D46",
          "fromDate": 1582693200000,
          "toDate": 1582866000000
       }
    ]

### GET /campsiteRegistration/anonymous/{bookingNumber}

Response Body:

    [
       {
          "user": {
             "id": null,
             "firstName": "Shewetaank",
             "lastName": "Indora",
             "email": "shewetaank.indora@gmail.com",
             "password": null
          },
          "bookingNumber": "D03097B5",
          "fromDate": 1581397200000,
          "toDate": 1581570000000
       }
    ]

Besides this there are also methods in UserController to get the info and register user which uses OktaClientConfig class can be found in the config package
(Some Urls are hrd coded in OktaClientConfig & OktaOAuth2WebSecurityConfigurerAdapter but can be moved and these are not part of the scope of the challange)

The Web API also starts with a single entry to the data base defined in the DataInit class. If you setup initial data setup there and try it out.
Also, while saving info to db the service queries the table first to check if there is overlapping dates in the range provided by the user.
If there is the save operation to the db will fail.

There are also validator classes to validate the info provided by the user and will fails to save the database in case of exception. Please check validation package
for more details.