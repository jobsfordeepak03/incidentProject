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
2. Update `backend/src/main/resources/application.properties`
   with your MySQL username/password. (default --- root/root in application.properties file)
4. Import `backend` into Eclipse as an existing Maven project.
5. Run `IncidentManagementApplication`.
6. Backend: http://localhost:8080

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
Provide the PIN and auto-select the City , State, and Country.


The frontend automatically calls `/api/users/pincode/{pincode}`.

## Security rules
- JWT authentication. (in application.properties file)
- Users can only access their own incidents.
- Closed incidents cannot be edited or deleted.
- Incident ID is unique at both application and database level.
- Passwords are BCrypt encoded.




<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/4b12e63b-acbe-4248-9f48-6f48bc0dd85d" />


<img width="1440" height="900" alt="Screenshot 2026-08-21 at 6 12 46 PM" src="https://github.com/user-attachments/assets/73f76192-12f0-4f13-bffa-4636824fa40a" />

<img width="1440" height="900" alt="Screenshot 2026-08-21 at 6 13 06 PM" src="https://github.com/user-attachments/assets/35bd78f2-4463-4f8c-b7d7-c02d7033b4bf" />

<img width="1440" height="900" alt="Screenshot 2026-08-21 at 6 15 09 PM" src="https://github.com/user-attachments/assets/30bf4ba5-37e1-48a9-a4e2-34a9681d17c4" />



<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/178ce378-2a86-4586-9e08-64e1c21cca32" />

<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/d3a17472-ca9b-4500-bda2-c3b407ad549f" />

<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/01bef265-1096-4a04-8b76-3727dac5daec" />







