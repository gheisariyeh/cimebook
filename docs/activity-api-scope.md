# Activity API Increment

## Goal

Replace the hard-coded activity data in the frontend with data
provided by a Spring Boot REST API and stored in a database.

Preserve the existing activity cards, text search, and category filters.

## Current Implementation

- The homepage uses HTML, CSS, and JavaScript.
- One Spring Boot backend serves the activity API on port 8082.
- The three initial activities are stored in file-based H2; the
  initializer skips insertion when the table already contains data.
- GET /activities and GET /activities/{id} provide read-only access.
- The frontend loads the list with fetch() and renders activity cards.
- applyFilters() combines text search and category filtering in the browser.
- The frontend is served from the backend's static resources.

## Implementation Scope

- One Spring Boot application in the backend directory, using Java 21 and Maven.
- Spring Data JPA and H2 in file mode for activity persistence.
- Three initial activities, inserted only when no activities exist.
- Read-only endpoints for the activity list and one activity.
- Existing HTML, CSS, and JavaScript served by Spring Boot.
- Activity cards loaded with fetch(); search and category filters remain in the browser.

## Initial Activity Data

Each activity will contain:

- id
- title
- description
- category
- difficulty
- duration
- price
- image

The image field will contain an image path, not the image file itself.

See [Activity Model](activity-model.md) for field types and constraints.

## Read Endpoints

| Method | Path | Purpose | Success response |
| --- | --- | --- | --- |
| GET | /activities | Retrieve all activities | 200 OK with a JSON array |
| GET | /activities/{id} | Retrieve one activity | 200 OK with a JSON object |

An empty activity list returns 200 OK with [].

A request for a nonexistent activity returns 404 Not Found.

## Acceptance Criteria

The endpoint responses and initial data were checked manually during
development. Automated integration tests for the read endpoints were
added later. A separate documented test report is not part of this increment.

### Backend

- The application starts successfully.
- The three existing activities are available in the database.
- Both read endpoints return the expected data and status codes.
- Activity data survives application restarts.
- Restarting the application does not duplicate initial data.

### Frontend Integration

- Activity cards use data received from the API.
- Text search remains case-insensitive.
- Search and category filtering work together.
- No matching results, loading, request failure, and empty data have
  distinct messages.
- Images display correctly.
- The layout remains usable on mobile and desktop.

## Out of Scope

- Registration and login
- User roles and permissions
- Guide profile management
- Bookings and payments
- Favorites
- Activity creation, editing, and deletion
- Angular migration
- Deployment

## Relationship to the Full MVP

This increment is an intermediate step toward the full MVP described
in mvp.md.

It delivers activity browsing backed by persistent data.
It does not deliver the complete booking platform.

## Implementation Progress

1. Documentation for this increment was drafted before implementation.
2. The Spring Boot application, activity model, and persistence were implemented.
3. The read endpoints were implemented and checked manually.
4. The existing frontend was connected to the API and checked in the browser.
5. Documentation was updated and reviewed for the read-only increment.

Creation, updating, and deletion were implemented in later increments;
they remain outside the scope of this original increment.
