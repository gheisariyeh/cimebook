# Activity Model

## Purpose

Represent an outdoor activity offering displayed in CimeBook.

An activity is not a scheduled session. Dates, capacity, guides,
and bookings are outside the scope of this increment.

## Fields

| Field | Java type | Constraints |
| --- | --- | --- |
| id | Long | Generated identifier; null before persistence |
| title | String | Required, non-blank, maximum 150 characters |
| description | String | Required, non-blank, maximum 2000 characters |
| category | ActivityCategory | Required enum value |
| difficulty | ActivityDifficulty | Required enum value |
| duration | String | Required, non-blank, maximum 50 characters |
| price | BigDecimal | Required, non-negative, maximum 2 fractional digits and 6 integer digits |
| image | String | Required, non-blank, maximum 500 characters |

All prices are expressed in EUR.

Duration is display text, such as "4h" or "Half-day".
It is not used for duration calculations in this increment.

The image field stores a local image path, not binary image data.

Titles are not required to be unique.

## Categories

| Java value | API and frontend label |
| --- | --- |
| HIKING | Hiking |
| CLIMBING | Climbing |
| CANYONING | Canyoning |

## Difficulty Levels

| Java value | API and frontend label |
| --- | --- |
| BEGINNER | Beginner |
| INTERMEDIATE | Intermediate |
| ADVANCED | Advanced |

The response mapping converts enum values to the labels above
to preserve compatibility with the frontend.

## Persistence Direction

Activity will also serve as the JPA entity for this increment.
A separate response DTO will define the public API representation.

Enums will be stored by name rather than by numeric position.

The activity entity and its persistence mapping have been implemented.

## Existing Examples

| Title | Category | Difficulty | Duration | Price in EUR |
| --- | --- | --- | --- | --- |
| Hiking at Semnoz | HIKING | BEGINNER | 4h | 45.00 |
| Climbing in Talloires | CLIMBING | INTERMEDIATE | 3h | 60.00 |
| Canyoning near Angon | CANYONING | INTERMEDIATE | Half-day | 75.00 |

## Out of Scope

- Scheduled sessions and availability
- Booking capacity
- Guide ownership
- Customer accounts
- Bookings
- Multiple currencies
- Image uploads
