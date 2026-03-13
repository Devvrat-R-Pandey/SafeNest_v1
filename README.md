# SafeNest – Women Safety Alert and Response System

A console-based Java application that provides a basic safety alert system for women.
Women can raise emergency alerts and nearby volunteers can view, filter, and respond to them.

---

## 📁 Project Structure

```
SafeNest_v1/
└── src/
    ├── main/
    │   └── SafeNestApp.java          # Entry point – main menu
    ├── model/
    │   ├── Person.java               # Abstract base class
    │   ├── AlertSender.java          # Woman raising the alert
    │   ├── Volunteer.java            # Volunteer responder
    │   └── Alert.java                # Alert entity
    ├── exception/
    │   ├── AlertNotFoundException.java
    │   └── InvalidInputException.java
    ├── repository/
    │   └── AlertRepository.java      # Array-based in-memory storage
    ├── service/
    │   ├── AlertService.java         # Interface
    │   └── AlertServiceImpl.java     # Business logic
    ├── ui/
    │   ├── AlertSenderMenu.java      # Alert Sender console menu
    │   └── VolunteerMenu.java        # Volunteer console menu
    └── util/
        └── StringUtil.java           # String helper methods
```

---

## 👥 User Roles

### 🚨 Alert Sender
- Raise a new safety alert with name, location, and message
- Receives a unique Alert ID on successful submission

### 🦸 Volunteer Responder
- View all alerts
- Filter alerts by location
- Respond to an alert (assigns volunteer + adds note)
- Update alert status

---

## 🔄 Alert Statuses

| Status | Meaning |
|---|---|
| `RAISED` | Alert just submitted |
| `IN_PROGRESS` | Volunteer is responding |
| `RESOLVED` | Situation handled |
| `DISMISSED` | Alert closed |

---

## ▶️ How to Run

1. Open the project in **IntelliJ IDEA**
2. Mark `src/` as **Sources Root**  
   *(Right-click `src` → Mark Directory as → Sources Root)*
3. Run `SafeNestApp.java` inside `src/main/`

---

## 🧠 Concepts Demonstrated

- **OOP** – Abstract class, inheritance, polymorphism, encapsulation
- **Arrays** – `Alert[]` for in-memory storage
- **Linear Search** – Find alert by ID
- **Bubble Sort** – Sort alerts by location A-Z
- **Exception Handling** – Custom exceptions, try-catch-finally
- **String Manipulation** – StringBuilder, StringBuffer
- **Interface** – `AlertService` contract
- **Constructor Overloading** – `Volunteer`, `AlertSender`
- **`super` / `this`** – Used in all subclass constructors

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **IDE:** IntelliJ IDEA
- **Storage:** In-memory (arrays)
- **Build:** Plain IntelliJ (no Maven)
