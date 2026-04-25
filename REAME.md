# Java Console Chat System

A modular, object-oriented console chat application built with Java (JDK 8+). This project demonstrates core OOP concepts: encapsulation, inheritance via user types, polymorphism (different message behaviors), and separation of concerns across multiple classes.

## Features

- **Create users** – choose between Admin and Regular user types.
- **Send direct messages** – select sender and recipient from existing users.
- **View messages** – see all messages (direct + broadcast) for a specific user.
- **Broadcast (Admin only)** – extra challenge feature: admins can send a message visible to every user.
- **Interactive menu** – runs in a loop until user chooses exit.
- **Input validation** – prevents empty messages, duplicate users, etc.

## OOP Highlights

- `User` class with private fields and enum `UserType`
- `Message` class supporting two constructors (direct vs broadcast)
- `ChatSystem` manager class handling collections and business logic
- `ChatApp` main class with menu and user I/O

## How to Run

1. Clone the repository.
2. Open in IntelliJ IDEA (or any Java IDE).
3. Ensure all `.java` files are in the `src` folder.
4. Run `ChatApp.java` (or `Main.java` if renamed).

## Requirements

- JDK 8 or higher
- IntelliJ IDEA / Eclipse / any Java compiler