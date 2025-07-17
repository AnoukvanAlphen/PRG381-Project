BC Wellness Portal 

Milestone 1

This milestone focuses on the login and registrastion of the BC Student Wellness Portal. The portal is built using Java Servlets and JSP, and this step ensures the development environment and project structure are correctly configured.

Setup 

1. Importing Libraries

Make sure that all required .jar files are added to your Apache NetBeans project’s library:

    Go to:
    Right-click on Project > Properties > Libraries > Compile tab > Add JAR/Folder

    jars used:

       - jbcrypt-0.4.jar

       - jstl.jar

       - postgresql-42.7.7.jar

       - taglibs-standard-impl-1.2.5.jar

       - taglibs-standard-spec-1.2.5.jar


2. Ensuring Correct Project Structure

Make sure the following folder structure is in place:

/ProjectRoot
├── /web
│   └── /WEB-INF
    
       

    If the compiler does not auto-generate the WEB-INF folder under /web, create it manually.


Milestone 2


# BC Student Wellness Management System
Milestone 2 – Desktop Application (Java Swing + JavaDB)
Programming 37(8)1 | Project 2025 

---

## Project Description

This desktop application is part of the BC Student Wellness Management System developed for Belgium Campus. It allows students and staff to manage wellness-related services including:

- Booking appointments with counselors
- Managing counselor availability
- Submitting and viewing student feedback

This application is built using **Core Java**, follows the **MVC (Model-View-Controller)** architecture, and uses **JavaDB (Derby)** for data persistence. It includes full CRUD functionality for Appointments, Counselors, and Feedback entries.

---

## Project Structure

- `dash.main.Main` – The main entry point of the dashboard GUI
- `view.pages` – Swing GUI panels for Appointments, Counselors, Feedback
- `controller` – Logic for menu interactions and form switching
- `model` – Model classes including user details, appointments, and feedback
- `db.DatabaseConnection` – JavaDB (Derby) database connection and table creation
- `view.login.frmLoginPage` – Login form used after logout or first entry

---

## Core Features

### Appointments
- Add new appointments (with student/counselor selection, date, time)
- View upcoming appointments
- Update appointment details (reschedule)
- Cancel appointments
- Real-time availability check based on counselor schedule and current bookings

### Counselors
- Add new counselor records with specialization and availability
- View list of all counselors
- Update counselor details
- Remove counselors
- Automatically reflects current status (Available / Booked) in GUI

### Feedback
- Submit feedback with rating and comments
- View and manage feedback history
- Edit or delete feedback records

---

##  GUI Design (Java Swing)

- A central dashboard with cards and a left-side menu
- Tab-based navigation to switch between Appointments, Counselors, and Feedback
- Styled tables with color-coded status
- Input validation (e.g., no empty fields)
- Confirmation dialogs for deletions
- Error handling with database connection alerts

---

## Technologies Used

- **Java SE 8+**
- **Swing GUI**
- **JavaDB (Derby) Embedded**
- **MVC Architecture**
- **JDBC** for database operations

---

## Database Tables (Auto-Created on First Run)

### `appointments`
- `id` (Primary Key)
- `name`, `surname`
- `counselor`
- `date` (DATE)
- `time` (TIME)
- `status` (Available, Booked, Cancelled)

### `counselors`
- `id` (Primary Key)
- `name`, `surname`
- `specialization`
- `availability` (e.g., "Monday 09:00-17:00")

### 🗂 `feedback`
- `id` (Primary Key)
- `name`, `surname`
- `email`
- `rating` (1–5)
- `comments`

---

## Logout Functionality

The application includes a Logout menu option that:
- Returns the user to the login screen (frmLoginPage)
- OR completely exits the application (`System.exit(0)`)

---

## How to Run

1. Ensure Java 8 or later is installed.
2. Open the project in IntelliJ IDEA.
3. Run the `Main.java` class from `dash.main`.
4. JavaDB tables will auto-create on launch.
5. Login form will appear on logout or initial execution.

---

## Notes

- GitHub was used for version control and collaboration.
- All functionalities are demonstrated and verified as per rubric guidelines.
- Project follows all principles of Object-Oriented Programming (OOP).

---
