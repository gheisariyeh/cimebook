# CimeBook

CimeBook is a full-stack web application for booking mountain activities around Annecy, France.

The project starts as a simple static website using HTML and CSS, then evolves step by step into a full-stack application using JavaScript, Java, Spring Boot, SQL, Angular, Docker and AI-related features.

## Project Goal

The goal of CimeBook is to help tourists discover and book mountain activities around Annecy, such as hiking, skiing, climbing and canyoning.

Mountain guides can manage their profiles, activities and availability.

## Tech Roadmap

- HTML / CSS
- Git / GitHub
- JavaScript
- Java / Object-Oriented Programming
- SQL Database
- Spring Boot REST API
- Angular Front-end
- Security and RGPD
- Docker and Deployment
- AI-assisted features

## Current Status

The current implementation is a responsive homepage backed by a Spring Boot
activity API and file-based H2 persistence.

Implemented features:

- Homepage sections for activities, guides, and how the platform works
- Activity cards generated from API data using JavaScript
- Category filtering and case-insensitive search in the browser
- Loading, error, and empty-result messages for the activity list
- Three initial activities stored in H2 without duplication on restart
- `GET /activities` to list activities
- `GET /activities/{id}` to retrieve one activity
- `POST /activities` to create an activity with validated input
- `PUT /activities/{id}` to replace an activity with validated input
- `DELETE /activities/{id}` to remove an activity
- Automated integration tests for reading, creating, and updating activities

The frontend is served by Spring Boot and fetches the activity list from
the same origin. Search and category filtering remain in the browser.
A valid activity creation request returns `201 Created` with the created
activity and a `Location` header. Invalid create or update input returns
`400 Bad Request`. The write endpoints are currently used through API clients;
the homepage still only reads activities.

| Method | Path | Success | Missing activity |
| --- | --- | --- | --- |
| `GET` | `/activities` | `200 OK` with an array (possibly empty) | — |
| `GET` | `/activities/{id}` | `200 OK` with an activity | `404 Not Found` |
| `POST` | `/activities` | `201 Created` with an activity and `Location` header | — |
| `PUT` | `/activities/{id}` | `200 OK` with the updated activity | `404 Not Found` |
| `DELETE` | `/activities/{id}` | `204 No Content` | `404 Not Found` |

Authentication, guide management, and bookings are not implemented yet.
Activity write endpoints currently have no authentication and are intended
for local development.

## Next Increment

The first activity API increment covered read endpoints and frontend
integration. Creation, updating, and deletion were added in later increments.
Next, document the API workflow and plan a controlled interface for managing
activities before making write operations available to users.

See [Activity API Increment](docs/activity-api-scope.md) for the scope
and acceptance criteria of the original read-only increment.

## Documentation

- [Project idea](docs/project-idea.md)
- [Full MVP](docs/mvp.md)
- [Roadmap](docs/roadmap.md)
- [Activity API increment](docs/activity-api-scope.md)

## Author

Afsaneh Gheisariyeh
