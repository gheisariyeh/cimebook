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
- Automated integration tests for reading and creating activities

The frontend is served by Spring Boot and fetches the activity list from
the same origin. Search and category filtering remain in the browser.
A valid activity creation request returns `201 Created` with the created
activity and a `Location` header. Invalid input returns `400 Bad Request`.

Authentication, guide management, bookings, and activity editing and
deletion are not implemented yet. Activity creation currently has no
authentication and is intended for local development.

## Next Increment

The first activity API increment covered read endpoints and frontend
integration. Activity creation was added in a subsequent increment.
The next feature will be selected from the remaining MVP work.

See [Activity API Increment](docs/activity-api-scope.md) for the scope
and acceptance criteria of the original read-only increment.

## Documentation

- [Project idea](docs/project-idea.md)
- [Full MVP](docs/mvp.md)
- [Roadmap](docs/roadmap.md)
- [Activity API increment](docs/activity-api-scope.md)

## Author

Afsaneh Gheisariyeh
