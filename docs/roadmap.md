# CimeBook Roadmap

## Current Progress

The responsive homepage, dynamic activity cards, category filters,
and text search are implemented. Activity data comes from a Spring Boot
API backed by file-based H2. The API supports listing, retrieving,
creating, updating, and deleting activities. The homepage uses the read
endpoint; write operations have no browser interface or authentication yet.

Automated integration tests cover reading, creation, and updating.
Deletion has been checked manually with an API client.

Separate activity detail pages, guide profile pages, and booking
forms are not implemented yet.

## First Backend Increment: Activity API

The first backend integration for activity browsing covers:

1. Define the activity API scope. (Documented)
2. Set up a Spring Boot application. (Implemented)
3. Add the activity model and H2 persistence. (Implemented)
4. Implement and check activity read endpoints. (Manually checked)
5. Connect the existing JavaScript frontend to the API. (Manually checked)

This first increment was completed. Creation, updating, and deletion
were implemented in separate, later increments.

See [Activity API Increment](activity-api-scope.md)
for the detailed scope.

The version sections below describe the broader project direction.
Their order and grouping may be revised as the project progresses.

## v0.1 - Static Homepage

Create a simple responsive homepage with HTML and CSS.

## v0.2 - Static Pages

Add activity list, activity details, guide profile and booking form pages.

## v0.3 - JavaScript Interactions

Add filters, search and booking form validation.

## v0.4 - Java Domain Model

Create the main Java classes: Activity, Guide, Client and Booking.

## v0.5 - Database Design

Design the SQL database schema.

## v1.0 - Spring Boot API

Create the REST API with Spring Boot.

## v1.1 - Security

Add authentication, authorization and user roles.

## v2.0 - Angular Front-end

Create the Angular application.

## v2.1 - Full Stack Integration

Connect Angular with the Spring Boot API.

## v2.2 - Docker and Deployment

Dockerize the application and prepare deployment.

## v3.0 - Final Portfolio Version

Polish the project for GitHub, CV and interviews.
