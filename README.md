# Incident Management System

Full-stack assignment implementation.

## Stack
- Java 17
- Spring Boot 3.5.x
- Spring Security + JWT
- Spring Data JPA / Hibernate
- MySQL
- React + Vite
- REST API

## Backend
1. Create a MySQL database:
   `CREATE DATABASE incident_management;`
2. Update `backend/src/main/resources/application.properties` with your MySQL username/password.
3. Import `backend` into Eclipse as an existing Maven project.
4. Run `IncidentManagementApplication`.
5. Backend: http://localhost:8080

## Frontend
```bash
cd frontend
npm install
npm run dev
```
Frontend: http://localhost:5173

## API
- POST /api/auth/register
- POST /api/auth/login
- POST /api/auth/forgot-password
- POST /api/auth/reset-password
- GET /api/users/me
- POST /api/incidents
- GET /api/incidents
- GET /api/incidents/{incidentId}
- PUT /api/incidents/{incidentId}
- DELETE /api/incidents/{incidentId}
- GET /api/incidents/search?incidentId=...

## Demo PIN lookup
For the assignment, the backend contains a small sample PIN mapping and a deterministic fallback:
- 110001 -> New Delhi / India
- 400001 -> Mumbai / India
- 560001 -> Bengaluru / India
- 500001 -> Hyderabad / India
- 700001 -> Kolkata / India

The frontend automatically calls `/api/users/pincode/{pincode}`.

## Security rules
- JWT authentication.
- Users can only access their own incidents.
- Closed incidents cannot be edited or deleted.
- Incident ID is unique at both application and database level.
- Passwords are BCrypt encoded.
