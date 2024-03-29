# MailBox Java Project

**MailBox** is a Java-based application that provides a secure and user-friendly mailbox experience. Leveraging technologies such as Socket for communication, Swing for the client-side interface, and MySQL for database management, this project ensures seamless user interaction and efficient data storage.

## Features

- **User Authentication:** Secure login system for user authentication.
- **Email Management:** Access and organize emails through an intuitive client-side interface.

- **Real-time Updates:** Utilizes Socket for real-time communication with the server.

- **Data Persistence:** MySQL integration for robust data storage.

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/Mededdahby/Mail-box.git
   cd MailBox-Java-Project
   ```

2. **Database Setup:**

   - Set up a MySQL database and configure the connection details in `src/main/resources/application.properties`.

3. **Run the Server:**

   ```bash
   java -jar server.jar
   ```

4. **Run the Client:**
   ```bash
   java -jar client.jar
   ```

## Usage

1. **User Authentication:**

   - Log in securely with your credentials.

2. **Mailbox Interface:**

   - Explore and manage your emails using the intuitive Swing-based interface.

3. **Real-time Updates:**
   - Experience real-time communication and updates via Socket integration.

## Screenshots

| About                           | Login                           |
| ------------------------------- | ------------------------------- |
| ![About](Screenshots/about.png) | ![Login](Screenshots/login.png) |

| Register                              | Add Mail                         |
| ------------------------------------- | -------------------------------- |
| ![Register](Screenshots/register.png) | ![Add Mail](Screenshots/add.png) |

## License

© med eddahby

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

🚀 Happy mailing! 📧🔒💻
