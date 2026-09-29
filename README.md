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

The current implementation is a responsive homepage backed by a
Spring Boot read-only activity API and file-based H2 persistence.

Implemented features:

- Homepage sections for activities, guides, and how the platform works
- Activity cards generated from API data using JavaScript
- Category filtering
- Case-insensitive search by activity title and category
- Combined search and category filtering
- A message when no activities match the selected filters

The three initial activities are stored in H2 and exposed through
GET /activities and GET /activities/{id}. The frontend is served by
Spring Boot and fetches the activity list from the same origin.
Search and category filtering remain in the browser.

Authentication, guide management, booking, and activity write
operations are not implemented yet.

## Next Increment

Review and commit the activity API increment, then continue with
subsequent MVP features.

See [Activity API Increment](docs/activity-api-scope.md)
for this increment's scope and acceptance criteria.

## Documentation

- [Project idea](docs/project-idea.md)
- [Full MVP](docs/mvp.md)
- [Roadmap](docs/roadmap.md)
- [Activity API increment](docs/activity-api-scope.md)

## Author

Afsaneh Gheisariyeh
