# CarePoint Clinic Management System

A connected clinic-management application with a Spring Boot + PostgreSQL API and a React + Vite frontend. The backend you supplied has been retained; additions are limited to browser CORS support, appointment endpoints (the existing Appointment entity was not exposed through REST), and environment-based database settings.

## Project structure

- `src/main/java/...` — Spring Boot backend (existing controllers, services, repositories and entities)
- `src/main/java/.../config/CorsConfig.java` — permits the Vite development server to call the API
- `src/main/java/.../controller/AppointmentController.java` — appointment CRUD API
- `library-frontend/src/components/` — reusable React components, arranged like the supplied VS Code example
- `library-frontend/src/App.jsx` — app navigation, dashboard and CRUD screens

## 1. Database

Create a PostgreSQL database named `clinic`. Configure credentials in `src/main/resources/application.properties` or set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`. Defaults are `jdbc:postgresql://localhost:5432/clinic`, `postgres`, `postgres`. Hibernate uses `update`, so existing records are preserved.

## 2. Start the backend

From the project root in a terminal:

```powershell
.\mvnw.cmd spring-boot:run
```

API base URL: `http://localhost:8082`

## 3. Start the frontend

Open a second terminal:

```powershell
cd library-frontend
npm install
npm run dev
```

Open `http://localhost:5173`. Vite proxies `/api` calls to the backend at port 8082. Keep both terminals running.

## API endpoints

- Patients: `/api/patient` (GET, POST, GET by ID, PUT, DELETE)
- Doctors: `/api/doctor` (GET, POST, GET by ID, PUT, DELETE)
- Offices: `/api/office` (GET, POST, GET by ID, PUT, DELETE; original `/save` retained)
- Appointments: `/api/appointment` (GET, POST, PUT by ID, DELETE)

Appointment JSON example:

```json
{
  "appointmentDate": "2026-11-24",
  "reason": "General consultation",
  "status": "SCHEDULED",
  "patientId": "PATIENT-UUID",
  "doctorId": "DOCTOR-UUID"
}
```

The dashboard and records screens load live data from PostgreSQL through the API. Create patient and doctor records before scheduling an appointment.
