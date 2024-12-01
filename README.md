# Email Notification Service

This Spring Boot application monitors incoming emails from Gmail and Microsoft accounts and exposes them through REST APIs and webhook notifications.

## Prerequisites

1. Java 11 or higher
2. Maven
3. Gmail API credentials
4. Microsoft Azure credentials

## Setup

For detailed setup instructions, see:
- [Gmail Setup Guide](GMAIL_SETUP.md)
- [Microsoft Graph Setup Guide](MICROSOFT_SETUP.md)

## Building and Running

1. Build the project:
```bash
mvn clean install
```

2. Run the application:
```bash
mvn spring-boot:run
```

## API Endpoints

### Get New Emails
```
GET /api/emails/new
```
Returns a list of new unread emails from both Gmail and Microsoft accounts.

### Start Email Monitoring
```
POST /api/emails/watch/start
```
Starts monitoring for new emails.

### Stop Email Monitoring
```
POST /api/emails/watch/stop
```
Stops monitoring for new emails.

## Authentication and User Management

### Register New User
```http
POST /api/accounts
Content-Type: application/json

{
    "username": "your_username",
    "email": "your_email@example.com",
    "password": "your_password"
}
```
Creates a new user account. The password will be securely hashed before storage.

**Response:**
- 200 OK: Account created successfully
- 400 Bad Request: Username or email already exists

### Login
```http
POST /api/auth/login
Content-Type: application/json

{
    "username": "your_username",
    "password": "your_password"
}
```
Authenticates a user and returns a JWT token.

**Response:**
- 200 OK: Login successful, returns JWT token
- 401 Unauthorized: Invalid credentials

### Using Authentication
For protected endpoints, include the JWT token in the Authorization header:
```
Authorization: Bearer your_jwt_token
```

## Testing with HTTP Files

The project includes HTTP request files in the `http` directory that you can use to test the API endpoints directly from your IDE:

- `auth.http`: Authentication-related requests (register, login)
- `emails.http`: Email-related endpoints
- `http-client.env.json`: Environment variables for different configurations

To use these files:
1. Open the `.http` files in your IDE
2. Click the "Run" button next to each request
3. After login, copy the JWT token and update it in `http-client.env.json`

### Environment Setup
The `http-client.env.json` file contains environment-specific variables:
```json
{
  "dev": {
    "auth_token": "your-jwt-token-here"
  }
}
```
Update the `auth_token` value with the JWT token received from the login endpoint.

## Email Polling

The application polls for new emails every minute when watching is enabled. You can adjust the polling interval by modifying the `@Scheduled` annotation in the service classes.
