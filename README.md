# Spring Boot Questions Management System

A comprehensive web application built with Spring Boot 3.5.6 for managing question banks and categories. This
application provides both web interface and REST API endpoints for creating, reading, updating, and deleting questions
and categories.

## 🚀 Features

### Core Functionality

- **Category Management**: Create, edit, delete, and view question categories
- **Question Management**: Add, edit, delete, and view questions with answers
- **REST API**: RESTful endpoints for programmatic access
- **Web Interface**: User-friendly Thymeleaf-based UI
- **Security Integration**: Spring Security with authentication and authorization

### Authentication & Authorization

- **Form-based Authentication**: Traditional login with username/password
- **OAuth2 Support**: Ready for Okta integration (configurable)
- **Role-based Access Control**: Admin and User roles with different permissions
- **In-memory User Management**: Pre-configured test users

### Database Support

- **H2 Database**: In-memory database for development and testing
- **PostgreSQL Support**: Production-ready database configuration
- **JPA/Hibernate**: Object-relational mapping with automatic schema generation
- **Database Console**: H2 console available at `/h2-console`

## 🛠 Technology Stack

- **Framework**: Spring Boot 3.5.6
- **Java Version**: Java 21
- **Database**: H2 (development), PostgreSQL (production)
- **ORM**: Spring Data JPA with Hibernate
- **Security**: Spring Security 6.x
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Testing**: JUnit 5, Spring Boot Test

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- PostgreSQL (for production use)

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/hendisantika/springboot-questions.git
cd springboot-questions
```

### 2. Run with H2 Database (Development)

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 3. Run with PostgreSQL (Production)

Set up environment variables:

```bash
export JDBC_DATABASE_URL="jdbc:postgresql://localhost:5432/questions_db"
export JDBC_DATABASE_USERNAME="your_username"
export JDBC_DATABASE_PASSWORD="your_password"
export OKTA_OAUTH_ISSUER="https://your-okta-domain/oauth2/default"
export OKTA_CLIENT_ID="your_client_id"
export OKTA_CLIENT_SECRET="your_client_secret"
```

Then run:

```bash
mvn spring-boot:run
```

### 4. Access the Application

- **Web Interface**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console (dev profile only)
- **API Endpoints**: http://localhost:8080/api/*

## 🔐 Default Users

When running in development mode, the following users are available:

| Username | Password | Role  |
|----------|----------|-------|
| admin    | admin    | ADMIN |
| user     | pass     | USER  |

## 📚 API Endpoints

### Categories

- `GET /api/categories` - Retrieve all categories
- Web interface at `/category`

### Questions

- `GET /api/questions` - Retrieve all questions and answers
- Web interface at `/questions`

## 🗄 Database Schema

### Category Table

```sql
CREATE TABLE category
(
    category_id     serial PRIMARY KEY,
    subject         VARCHAR(255) NOT NULL,
    exam_type       VARCHAR(255),
    created_ts      TIMESTAMP    NOT NULL,
    last_updated_ts TIMESTAMP    NOT NULL
);
```

### Question Answers Table

```sql
CREATE TABLE question_answers
(
    question_id     serial PRIMARY KEY,
    category_id     INTEGER REFERENCES category (category_id),
    question        text,
    answer          text,
    created_ts      TIMESTAMP NOT NULL,
    last_updated_ts TIMESTAMP NOT NULL
);
```

### User Info Table

```sql
CREATE TABLE user_info
(
    user_id  serial PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    pwd      VARCHAR(255) NOT NULL,
    roles    VARCHAR(255)
);
```

## 🔧 Configuration

### Application Profiles

#### Development Profile (`application-dev.properties`)

```properties
spring.datasource.url=jdbc:h2:mem:devdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```

#### Production Profile (`application.properties`)

```properties
spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_DATABASE_USERNAME}
spring.datasource.password=${JDBC_DATABASE_PASSWORD}
spring.datasource.driverClassName=org.postgresql.Driver
```

## 🧪 Testing

Run tests with:

```bash
mvn test
```

The test configuration uses H2 in-memory database and mock OAuth2 settings.

## 🎨 Web Interface

The application provides a complete web interface with:

- **Home Page** (`/`): Welcome page with navigation
- **Login Page** (`/login`): Authentication form
- **Categories Page** (`/category`): Manage question categories
- **Questions Page** (`/questions`): Manage questions and answers
- **Add/Edit Forms**: User-friendly forms for data entry

## 🔒 Security Configuration

- **Public Endpoints**: `/api/**`, `/h2-console/**`, `/login`
- **Admin Only**: `/category/**`, `/questions/**`
- **Authenticated**: All other endpoints
- **CSRF Protection**: Disabled for API endpoints
- **Frame Options**: Configured for H2 console access

## 🚀 Deployment

### Docker Support

```dockerfile
FROM openjdk:21-jre-slim
COPY target/springboot-questions-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Environment Variables

- `JDBC_DATABASE_URL`: Database connection URL
- `JDBC_DATABASE_USERNAME`: Database username
- `JDBC_DATABASE_PASSWORD`: Database password
- `OKTA_OAUTH_ISSUER`: Okta OAuth2 issuer URL
- `OKTA_CLIENT_ID`: Okta client ID
- `OKTA_CLIENT_SECRET`: Okta client secret

## 📄 Project Structure

```
src/
├── main/
│   ├── java/com/hendisantika/springbootquestions/
│   │   ├── config/
│   │   │   ├── DbInitializer.java
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── QuestionBankAPIController.java
│   │   │   └── QuestionBankUIController.java
│   │   ├── entity/
│   │   │   ├── Category.java
│   │   │   ├── QuestionAnswers.java
│   │   │   └── UserInfo.java
│   │   ├── repository/
│   │   │   ├── CategoryRepository.java
│   │   │   ├── QuestionAnswersRepository.java
│   │   │   └── UserInfoRepository.java
│   │   ├── service/
│   │   │   ├── IQuestionBankService.java
│   │   │   ├── QuestionBankService.java
│   │   │   ├── UserDetailsImpl.java
│   │   │   └── UserDetailsServiceImpl.java
│   │   └── SpringbootQuestionsApplication.java
│   └── resources/
│       ├── templates/
│       │   ├── addCategory.html
│       │   ├── addQuestion.html
│       │   ├── category.html
│       │   ├── editCategory.html
│       │   ├── editQuestion.html
│       │   ├── header.html
│       │   ├── index.html
│       │   ├── login.html
│       │   └── questions.html
│       └── application.properties
└── test/
    └── java/com/hendisantika/springbootquestions/
        └── SpringbootQuestionsApplicationTests.java
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34
- GitHub: [@hendisantika](https://github.com/hendisantika)

## 🎯 Future Enhancements

- [ ] Question import/export functionality
- [ ] Advanced search and filtering
- [ ] Question difficulty levels
- [ ] Quiz generation from question banks
- [ ] Multiple choice question support
- [ ] User progress tracking
- [ ] Email notifications
- [ ] Advanced reporting and analytics

## 📞 Support

For support and questions, please:

1. Check the documentation above
2. Search existing issues on GitHub
3. Create a new issue with detailed description
4. Contact the author via email or Telegram

---

**Note**: This application is ready for production use with proper environment configuration. The H2 database setup is
intended for development and testing purposes only.