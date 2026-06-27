🏦 Virtual Banking System

A full-stack role-based virtual banking system that simulates real-world banking operations with separate interfaces for users and administrators. This project demonstrates secure authentication, transaction management, and administrative control using modern web technologies.

Features

👤 User Side

🔐 Secure Login & Signup system

🧑‍💼 Role-based access (User/Admin)

💰 Dashboard displaying current bank balance

💸 Deposit money functionality

🏧 Withdraw money

🔁 Transfer funds between accounts

📒 Passbook page showing transaction history

👤 Update user profile

🚪 Logout functionality

🛠 Admin Side

➕ Add new users

📋 View all registered users

❌ Delete users

🔍 View detailed user information

🧾 System history log (tracks all actions performed in the system)

📊 Admin dashboard with complete user overview

🧠 System Highlights

Role-based authentication ensures secure access control
Real-time transaction updates
Structured backend using REST APIs
Clean and user-friendly interface
Separation of concerns (Frontend + Backend + Database)

Tech Stack

Frontend
* HTML
* CSS
* JavaScript

Backend
* Spring Boot (Java)

Database
* MySQL

Screenshots
Example:
<img width="1053" height="858" alt="image" src="https://github.com/user-attachments/assets/24249252-034c-4821-a253-030b48126c17" />
<img width="1445" height="779" alt="image" src="https://github.com/user-attachments/assets/caec0ecd-95a5-4424-ba72-931436a2fb36" />
<img width="1019" height="839" alt="image" src="https://github.com/user-attachments/assets/f030b3a3-e8e3-45fe-b039-11630fd5ae20" />
<img width="1454" height="858" alt="image" src="https://github.com/user-attachments/assets/b8544722-f03e-47be-aaf2-56b84ea1b4ae" />
<img width="644" height="832" alt="image" src="https://github.com/user-attachments/assets/6cc6b7cc-3ff3-4073-aab9-194eb8cc692f" />
<img width="1255" height="591" alt="image" src="https://github.com/user-attachments/assets/e7083c89-da47-4351-a0b5-d5d6552afa8a" />




https://vbs-rqq0.onrender.com/login.html

📂 Project Structure

virtual-banking-system/

│── backend/ (Spring Boot)

│── frontend/ (HTML, CSS, JS)

│── database/

## 🚀 Getting Started

### Prerequisites

Before running the project, make sure you have the following installed:

* Java 17 or above
* Maven
* MySQL Server
* IntelliJ IDEA (Recommended) or any Java IDE
* Git

---

## 📥 Clone the Repository

```bash
git clone https://github.com/Akshay1code/virtual-banking-system.git
cd virtual-banking-system
```

---

## 🗄️ Create the Database

Open MySQL and create a database:

```sql
CREATE DATABASE yourDBName;
```

---

## ⚙️ Configure Database

Navigate to:

```text
src/main/resources/application.properties
```

Update the database configuration according to your local MySQL setup:

```properties
spring.application.name=vbs
# ===============================
# DATABASE CONFIGURATION
# ===============================
spring.datasource.url=jdbc:mysql://localhost:3306/yourDbName
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ===============================
# JPA / HIBERNATE CONFIGURATION
# ===============================
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ===============================
# DIALECT
# ===============================
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# ===============================
# SERVER
# ===============================
server.port=8081
```

> Replace `YOUR_USERNAME` and `YOUR_PASSWORD` with your local MySQL credentials.

---

## Environment Variables

Create a `.env` file in the project root and add the following:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/spring222
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=YOUR_PASSWORD

MYSQL_ROOT_PASSWORD=YOUR_PASSWORD
MYSQL_DATABASE=spring222
```

## ▶️ Run the Application

### Using IntelliJ IDEA

1. Open the project.
2. Wait for Maven dependencies to download.
3. Locate `VbsApplication.java`.
4. Click the **Run ▶️** button.

OR

### Using Maven

```bash
mvn spring-boot:run
```

---

## 🌐 Access the Application

Once the application starts successfully, open your browser and visit:

```text
https://vbs-rqq0.onrender.com/login.html
```

The Virtual Banking System should now be running on your local machine.



🔮 Future Improvements

* Make UI fully desktop oriented.

* Add analytics dashboard for admin

* Integrate real payment gateway simulation
  
* transactions in passbook up to date 

👨‍💻 Author

   Akshay Menon

GitHub: https://github.com/Akshay1code
