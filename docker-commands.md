# Docker Compose Commands Guide

This document provides all the necessary commands to run the Spring Boot Questions application with PostgreSQL and
pgAdmin using Docker Compose.

## 🚀 Quick Start Commands

### 1. Start Database Services Only (Recommended for Development)

```bash
# Start PostgreSQL and pgAdmin only
docker compose up postgres pgadmin -d

# View logs
docker compose logs -f postgres pgadmin
```

### 2. Start All Services (Full Stack)

```bash
# Start all services including the Spring Boot app
docker compose --profile full-stack up -d

# View logs for all services
docker compose logs -f
```

### 3. Development Workflow

```bash
# Start database services
docker compose up postgres pgadmin -d

# Run Spring Boot app locally (connects to Docker PostgreSQL)
mvn spring-boot:run -Dspring-boot.run.profiles=docker

# Or use your IDE with 'docker' profile
```

## 🛠 Service Management Commands

### Start Services

```bash
# Start specific services
docker compose up postgres -d           # PostgreSQL only
docker compose up pgadmin -d            # pgAdmin only
docker compose up postgres pgadmin -d   # Both database services

# Start with logs in foreground
docker compose up postgres pgadmin      # Without -d to see logs
```

### Stop Services

```bash
# Stop all services
docker compose down

# Stop and remove volumes (WARNING: This will delete all data)
docker compose down -v

# Stop specific services
docker compose stop postgres
docker compose stop pgadmin
```

### View Logs

```bash
# View logs for all services
docker compose logs -f

# View logs for specific service
docker compose logs -f postgres
docker compose logs -f pgadmin
docker compose logs -f app
```

### Restart Services

```bash
# Restart all services
docker compose restart

# Restart specific service
docker compose restart postgres
```

## 🔍 Service Status and Information

### Check Service Status

```bash
# List running services
docker compose ps

# View detailed service information
docker compose top
```

### Execute Commands in Containers

```bash
# Connect to PostgreSQL database
docker compose exec postgres psql -U questions_user -d questions_db

# Access PostgreSQL shell
docker compose exec postgres bash

# View PostgreSQL logs
docker compose exec postgres tail -f /var/log/postgresql/postgresql.log
```

## 🗄 Database Management

### PostgreSQL Commands

```bash
# Connect to database
docker compose exec postgres psql -U questions_user -d questions_db

# Create database backup
docker compose exec postgres pg_dump -U questions_user questions_db > backup.sql

# Restore database from backup
docker compose exec -T postgres psql -U questions_user -d questions_db < backup.sql

# View database tables
docker compose exec postgres psql -U questions_user -d questions_db -c "\dt"
```

### Access Database via pgAdmin

1. Open browser: http://localhost:5050
2. Login credentials:
    - Email: `admin@questions.com`
    - Password: `admin123`
3. Add server connection:
    - Name: `Spring Boot Questions DB`
    - Host: `postgres` (container name)
    - Port: `5432`
    - Database: `questions_db`
    - Username: `questions_user`
    - Password: `questions_password`

## 📊 Application Access Points

| Service                      | URL                              | Credentials                         |
|------------------------------|----------------------------------|-------------------------------------|
| **Spring Boot App**          | http://localhost:8080            | admin/admin, user/pass              |
| **pgAdmin**                  | http://localhost:5050            | admin@questions.com / admin123      |
| **PostgreSQL**               | localhost:5432                   | questions_user / questions_password |
| **H2 Console** (dev profile) | http://localhost:8080/h2-console | sa / (empty)                        |

## 🔧 Environment Profiles

### Docker Profile (docker)

- Uses PostgreSQL from Docker Compose
- Full logging enabled
- Actuator endpoints exposed
- Optimal for container deployment

### Development Profile (dev)

- Uses H2 in-memory database
- H2 console enabled
- Fast startup for development
- No external dependencies

### Production Profile (default)

- Uses environment variables for database config
- Production-ready settings
- Requires external PostgreSQL

## 🐛 Troubleshooting

### Common Issues

#### PostgreSQL Connection Issues

```bash
# Check if PostgreSQL is running
docker compose ps postgres

# View PostgreSQL logs
docker compose logs postgres

# Test database connection
docker compose exec postgres pg_isready -U questions_user
```

#### pgAdmin Access Issues

```bash
# Check if pgAdmin is running
docker compose ps pgadmin

# View pgAdmin logs
docker compose logs pgadmin

# Restart pgAdmin
docker compose restart pgadmin
```

#### Application Connection Issues

```bash
# Check network connectivity
docker compose exec app ping postgres

# View application logs
docker compose logs app

# Restart application
docker compose restart app
```

### Clean Reset

```bash
# Stop all services and remove volumes
docker compose down -v

# Remove unused Docker resources
docker system prune -f

# Rebuild and start fresh
docker compose build --no-cache
docker compose up postgres pgadmin -d
```

### Volume Management

```bash
# List Docker volumes
docker volume ls

# Inspect volume details
docker volume inspect springboot-questions_postgres_data

# Backup volume data
docker run --rm -v springboot-questions_postgres_data:/data -v $(pwd):/backup alpine tar czf /backup/postgres-backup.tar.gz -C /data .

# Restore volume data
docker run --rm -v springboot-questions_postgres_data:/data -v $(pwd):/backup alpine tar xzf /backup/postgres-backup.tar.gz -C /data
```

## 📈 Monitoring and Health Checks

### Health Check URLs

```bash
# Application health
curl http://localhost:8080/actuator/health

# Database health (from within app container)
docker compose exec app curl http://localhost:8080/actuator/health/db
```

### Resource Usage

```bash
# Monitor resource usage
docker compose top

# View system resource usage
docker stats
```

## 🔄 Development Workflow Examples

### Typical Development Session

```bash
# 1. Start database services
docker compose up postgres pgadmin -d

# 2. Run application locally
mvn spring-boot:run -Dspring-boot.run.profiles=docker

# 3. Access pgAdmin for database management
# Open http://localhost:5050

# 4. Make code changes and restart app
# Ctrl+C to stop app, then run again

# 5. When done, stop services
docker compose down
```

### Full Stack Testing

```bash
# 1. Build and start all services
docker compose --profile full-stack up --build -d

# 2. Test application
curl http://localhost:8080/api/categories

# 3. View logs
docker compose logs -f app

# 4. Stop when done
docker compose down
```

### Database Development

```bash
# 1. Start only PostgreSQL
docker compose up postgres -d

# 2. Connect with your favorite database client
# Host: localhost, Port: 5432, DB: questions_db, User: questions_user

# 3. Or use pgAdmin
docker compose up pgadmin -d
# Access at http://localhost:5050

# 4. Run app locally for testing
mvn spring-boot:run -Dspring-boot.run.profiles=docker
```