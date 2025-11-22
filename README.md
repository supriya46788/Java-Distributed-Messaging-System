# Java Distributed Messaging System

A multi-threaded, socket-based messaging system built using Java. This project demonstrates concepts of networking, concurrency, thread synchronization, and distributed systems by enabling multiple clients to communicate with a central server in real time.

---

## 🚀 Overview

This project implements a **Client-Server Messaging System** where:

* Multiple clients connect to a central server.
* Each client runs in its own thread.
* The server handles concurrent communication using Java multithreading.
* Clients can send messages that the server broadcasts to all connected clients.

The system demonstrates how large-scale messaging platforms manage real-time communication at a basic level.

---

## 🧠 Key Features

* Multi-threaded server handling multiple clients.
* Real-time message broadcasting.
* Thread-safe shared message handling.
* Graceful client connection and disconnection.
* TCP/IP communication using Java Sockets.
* Command-based client functionality.
* Scalable architecture for adding more features.

---

## 🎯 Objectives

This project was built to strengthen understanding of:

* Networking (TCP/IP)
* Concurrency and Java Threads
* Synchronization and shared data management
* Distributed communication systems
* Operating system concepts applied through Java

---

## 🏗️ Architecture

The system uses a simple distributed design:

```
                 +-----------------------+
                 |        SERVER         |
                 |  Multi-threaded Core  |
                 +-----------+-----------+
                             |
     ---------------------------------------------------
     |                    |                     |       
+------------+     +-------------+      +--------------+
|  CLIENT 1  |     |  CLIENT 2   |      |  CLIENT 3    |
+------------+     +-------------+      +--------------+
```

---

## 📂 Project Structure

```
Distributed-Messaging-System/
│
├── src/
│   ├── server/
│   │   ├── Server.java
│   │   └── ClientHandler.java
│   └── client/
│       └── Client.java
│
├── README.md
```

---

## ⚙️ How It Works

### 1. Server Side

* Starts on a fixed port.
* Waits for clients using `ServerSocket`.
* Creates a new `ClientHandler` thread for every client.
* Maintains a list of connected clients.
* Broadcasts messages to all clients.

### 2. Client Side

* Connects to server using `Socket`.
* Sends username on connection.
* Runs two threads:

  * One for sending messages.
  * One for receiving messages.

---

## 🧩 Technologies Used

* **Java** (Core)
* **Sockets** (Networking)
* **Multithreading** (Concurrency)
* **TCP/IP Protocols**
* **Linux/Unix** for testing

---

## 📘 Future Enhancements

* Private messaging
* Message history storage
* Authentication system
* GUI-based chat client
* Distributed server cluster
* Load balancing support

---

