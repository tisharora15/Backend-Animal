# RescuePaw Backend — Spring Boot + PostgreSQL

## Project Structure

```
rescuepaw-backend/
├── pom.xml
└── src/main/
    ├── java/com/rescuepaw/
    │   ├── RescuePawApplication.java       ← Entry point
    │   ├── config/
    │   │   └── SecurityConfig.java         ← CORS + BCrypt + Security
    │   ├── entity/                         ← Database tables (JPA)
    │   │   ├── User.java
    │   │   ├── Animal.java
    │   │   ├── RescueRequest.java
    │   │   ├── AdoptionApplication.java
    │   │   ├── VolunteerApplication.java
    │   │   ├── ContactMessage.java
    │   │   └── Donation.java
    │   ├── repository/                     ← Database queries (JPA)
    │   ├── dto/                            ← Request/response objects
    │   ├── service/                        ← Business logic
    │   └── controller/                     ← REST API endpoints
    └── resources/
        ├── application.properties          ← DB config
        └── data.sql                        ← Sample animal data
```

---

## Prerequisites

| Tool | Version | Download |
|------|---------|----------|
| Java JDK | 17+ | https://adoptium.net |
| Maven | 3.8+ | https://maven.apache.org |
| PostgreSQL | 14+ | https://www.postgresql.org |
| IntelliJ IDEA | Any | https://www.jetbrains.com/idea |

---

## Setup — Step by Step

### 1. Install PostgreSQL and create the database

```sql
-- Open psql or pgAdmin and run:
CREATE DATABASE rescuepaw;
```

### 2. Update application.properties

Open `src/main/resources/application.properties` and set your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rescuepaw
spring.datasource.username=postgres
spring.datasource.password=YOUR_ACTUAL_PASSWORD
```

### 3. Run the backend

**Option A — IntelliJ IDEA:**
1. Open the `rescuepaw-backend` folder in IntelliJ
2. Wait for Maven to download dependencies
3. Click the green ▶ Run button on `RescuePawApplication.java`

**Option B — Terminal:**
```bash
cd rescuepaw-backend
mvn spring-boot:run
```

You should see:
```
Started RescuePawApplication in 3.2 seconds
```

### 4. Verify it works

Open your browser and visit: http://localhost:8080/animals

You should see a JSON array of 8 animals. ✅

---

## Frontend Changes Required

### 1. Copy the new files to your React project:

| File in this folder | Copy to |
|---------------------|---------|
| `RescuePage.jsx` | `src/components/RescuePage.jsx` |
| `AuthContext.jsx` | `src/contexts/AuthContext.jsx` |

### 2. Update SignupPage.jsx

Replace the direct fetch in `handleSubmit` with the `signup` from `AuthContext`:

```jsx
import { useAuth } from "../contexts/AuthContext";
const { signup } = useAuth();

// In handleSubmit:
await signup({
  firstName: formData.firstName,
  lastName: formData.lastName,
  email: formData.email,
  phone: formData.phone,
  password: formData.password,
});
setSuccess("Account created! Please login.");
```

### 3. Update VolunteerPage.jsx form submit

Change the `fetch` URL from the placeholder to:

```js
fetch("http://localhost:8080/volunteer/apply", { ... })
```

### 4. Update ContactPage.jsx form submit

```js
fetch("http://localhost:8080/contact", { ... })
```

### 5. Update DonatePage.jsx form submit

```js
fetch("http://localhost:8080/donate", { ... body: JSON.stringify({
  amount: customAmount || selectedAmount,
  donationType: donationType === "monthly" ? "MONTHLY" : "ONE_TIME",
  donorEmail: ""   // optional
})})
```

### 6. Update AnimalDetailPage.jsx adoption form submit

```js
fetch("http://localhost:8080/adoption/apply", { ... })
```

---

## API Reference

| Method | Endpoint | Description | Called From |
|--------|----------|-------------|-------------|
| POST | `/auth/register` | Create account | SignupPage.jsx |
| POST | `/auth/login` | Login | AuthContext.jsx |
| GET | `/animals` | All available animals | AdoptPage.jsx |
| GET | `/animals/{id}` | Single animal | AnimalDetailPage.jsx |
| GET | `/animals?type=Dog` | Filter by type | AdoptPage.jsx |
| POST | `/rescue` | Submit rescue request | RescuePage.jsx |
| GET | `/rescue/track/{ref}` | Track by reference | - |
| POST | `/adoption/apply` | Apply to adopt | AnimalDetailPage.jsx |
| POST | `/volunteer/apply` | Volunteer signup | VolunteerPage.jsx |
| POST | `/contact` | Send contact message | ContactPage.jsx |
| POST | `/donate` | Record donation | DonatePage.jsx |

---

## Database Tables (auto-created by JPA)

```
users                   → registered users
animals                 → adoptable animals
rescue_requests         → rescue form submissions
adoption_applications   → adoption form submissions
volunteer_applications  → volunteer form submissions
contact_messages        → contact form messages
donations               → donation records
```

---

## Common Errors

| Error | Fix |
|-------|-----|
| `Connection refused on 5432` | PostgreSQL is not running — start it |
| `password authentication failed` | Wrong password in application.properties |
| `database "rescuepaw" does not exist` | Run `CREATE DATABASE rescuepaw;` in psql |
| `Port 8080 already in use` | Change `server.port=8081` in application.properties |
| CORS error in browser | Make sure your React runs on port 5173 or 3000 |
