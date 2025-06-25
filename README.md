# 📝 Journal Application

A full-featured **Spring Boot** application for managing journal entries for the user based on their roles, sending scheduled email notifications, and integrating external services like weather APIs.
Designed with modular architecture and best practices for clean and scalable development.

---

## 🚀 Features

- ✍️ **Journal Entry Management** — Create, read, update, and delete journal entries for the user and stored in MongoDB.
- 📧 **Email Notification System** — Uses Gmail SMTP to send reminders or alerts.
- 🌤️ **Weather API Integration** — Enrich journal entries with live weather data.
- ⚙️ **Task Scheduling** — Periodic tasks with Spring Scheduler (e.g., daily email reminders).
- ⚡ **Redis Caching** — Boosts performance with in-memory caching.
- 🔐 **Modular Architecture** — Clean separation of controller, service, repository, config, and utility layers.

---

## 🧰 Tech Stack

- **Backend**: Java, Spring Boot
- **Database**: MongoDB (Cloud)
- **Cache**: Redis
- **Email**: Gmail SMTP
- **API Integration**: Weather API
- **Scheduling**: Spring Task Scheduler

---

⚙️ Configuration for prop.yml

spring:
  data:
    mongodb:
      uri: YOUR_MONGODB_URI
      database: journaldB
  mail:
    host: smtp.gmail.com
    port: 587
    username: YOUR_EMAIL
    password: YOUR_APP_PASSWORD
    protocol: smtp
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

redis:
  host: localhost
  port: 6379

weather_api_key: YOUR_API_KEY

---

▶️ Run the App

Prerequisites:
Java 8+

MongoDB Atlas account

Redis server

Gmail App Password

Weather API Key (e.g., OpenWeather)
