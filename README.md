# 🎉 Event-Service (NextEvent Project)

A microservice responsible for managing events within the NextEvent system.  
It exposes a RESTful JSON API that allows clients to create, update, retrieve, and delete event records via the API Gateway.

---

## 👤 Student Information

- **Student Name:** Sherul Dhanushka Fernando
- **Student Number:** 2301691014
- **Slack Handle:** https://ijse-eca-hdse-69-70.slack.com/team/U0AEH8NS9DW
- **GCP Project ID:** project-0ae0d75b-3979-4ebf-be9

---

## 📝 About

The **Event-Service** is responsible for handling all event-related operations in the NextEvent system.

It allows:

- Creating new events
- Viewing event details
- Updating event information
- Deleting events

All requests are routed through the **API Gateway**, and the service registers itself with the **Service-Registry (Eureka)**.

---

## 🛠 Tech Stack

| Technology | Details |
|---|---|
| Java | 25 |
| Spring Boot | 4.0.3 |
| Spring Cloud | 2025.1.0 |
| Spring Data MongoDB | NoSQL persistence layer |
| MongoDB | Document database (port `13500`) |
| MapStruct | DTO ↔ Document mapping |
| Lombok | Boilerplate reduction |
| Spring Validation | Bean validation |
| Eureka Client | Service registration & discovery |
| Config Client | Fetches config from Config-Server |
| Spring Boot Actuator | Health & management endpoints |

---

## 🌐 Service Details

| Property | Value |
|---|---|
| Port | `8002` |
| Artifact ID | `Event-Service` |
| Group ID | `lk.ijse.eca` |
| Database | MongoDB — `localhost:13500` / database `eca` |

---

## 📡 API Endpoints

Base path: `/api/v1/events`

| Method | Path | Description | Content-Type |
|---|---|---|---|
| `POST` | `/api/v1/events` | Create a new event | `application/json` |
| `GET` | `/api/v1/events` | Get all events | — |
| `GET` | `/api/v1/events/{eventId}` | Get event by ID | — |
| `PUT` | `/api/v1/events/{eventId}` | Update event | `application/json` |
| `DELETE` | `/api/v1/events/{eventId}` | Delete event | — |

---

## 📌 Event ID Format

- Example: `EVT001`, `EVENT100`
- Must be **unique**
- Cannot be changed after creation

---

## 📥 Sample Request Body

> Requests must use `Content-Type: application/json`

### ➕ POST `/api/v1/events`

```json
{
  "eventId": "EVT001",
  "description": "Tech Conference 2026"
}
```

**PUT** `/api/v1/events/{eventId}`

```json
{
  "description": "Musical show"
}
```

**Sample response:**

```json
{
  "eventId": "EVT001",
  "description": "Tech Conference 2026"
}
```

## Getting Started

Follow the lecture guidelines, refer to the lecture video for more information and how to get started correctly.

> **Prerequisites:** Config-Server, Service-Registry, and Api-Gateway must be running. A MongoDB instance must be accessible on port `13500` with credentials `root / mongodb`.

**Startup order:**
1. Config-Server (`9000`)
2. Service-Registry (`9001`)
3. Api-Gateway (`7000`)
4. **Program-Service** (`8001`)

```bash
./mvnw spring-boot:run
```

## Testing

A Postman collection is available for testing the API endpoints:

**Program Service:** [Open Collection](https://sherul.postman.co/workspace/classroom~67e69d15-9d52-4dc5-b136-621917174743/collection/40383343-f31980a0-d56c-4142-af12-46625f10feab?action=share&creator=40383343)

## Need Help?

If you encounter any issues, feel free to reach out and start a discussion via the Slack workspace.
