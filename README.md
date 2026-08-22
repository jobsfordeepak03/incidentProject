
============================START OF RUNNING INSTRUCTION ==================================== 

# Code / Project Structures on Local System, Incident Project after download from Git
<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/8eeab855-5ab9-441a-b27a-8f273e3a6af5" />


# Below Details for run the Incident Project 

#Commands
- install the Docker if not on testing system
- docker --version
- docker ps
- open -a Docker
- docker compose up -d mysql

<img width="2854" height="1688" alt="image" src="https://github.com/user-attachments/assets/675476dc-a161-4244-b163-0816521deda7" />

# Run the Backend Code in Eclipse
- IncidentManagementApplication.java
  
<img width="2834" height="1628" alt="image" src="https://github.com/user-attachments/assets/56133dce-6204-49d5-bb94-bd098cfcecb3" />

# Run the Frontend Code with below Commands
- npm install
- npm run dev

  <img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/6a524f7e-7b37-4109-9c42-af939303c7b6" />


# Hit the URL in browser 
- http://localhost:5173
  
<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/3bbe8190-86f1-4985-92db-86a94e61c6ae" />





========================================END OF RUNNING INSTRUCTION ==================================== 







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



<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/8cbcd37e-d6ff-4473-bc5a-0ae73b791494" />


<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/4b12e63b-acbe-4248-9f48-6f48bc0dd85d" />


<img width="1440" height="900" alt="Screenshot 2026-08-21 at 6 12 46 PM" src="https://github.com/user-attachments/assets/73f76192-12f0-4f13-bffa-4636824fa40a" />

<img width="1440" height="900" alt="Screenshot 2026-08-21 at 6 13 06 PM" src="https://github.com/user-attachments/assets/35bd78f2-4463-4f8c-b7d7-c02d7033b4bf" />

<img width="1440" height="900" alt="Screenshot 2026-08-21 at 6 15 09 PM" src="https://github.com/user-attachments/assets/30bf4ba5-37e1-48a9-a4e2-34a9681d17c4" />



<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/178ce378-2a86-4586-9e08-64e1c21cca32" />

<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/d3a17472-ca9b-4500-bda2-c3b407ad549f" />

<img width="2880" height="1800" alt="image" src="https://github.com/user-attachments/assets/01bef265-1096-4a04-8b76-3727dac5daec" />







