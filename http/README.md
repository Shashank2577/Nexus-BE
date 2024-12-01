# OAuth2 Authentication Endpoints

This document describes the OAuth2 authentication endpoints for both Google and Microsoft authentication flows.

## Google OAuth2 Authentication

### 1. Initiate Google Login
```http
GET /oauth2/gmail/login
```
This endpoint redirects to Google's authentication page.

### 2. Google OAuth2 Callback
```http
GET /oauth2/callback?code={code}&state={state}
```
Google will redirect back to this endpoint after successful authentication.

Required Query Parameters:
- `code`: Authorization code provided by Google
- `state`: State parameter for security validation

### Google OAuth2 Scopes
- `https://www.googleapis.com/auth/gmail.readonly`: Read Gmail messages
- `https://www.googleapis.com/auth/gmail.modify`: Modify Gmail messages

## Microsoft OAuth2 Authentication

### 1. Initiate Microsoft Login
```http
GET /oauth2/microsoft/login
```
This endpoint redirects to Microsoft's authentication page.

### 2. Microsoft OAuth2 Callback
```http
GET /oauth2/callback/microsoft?code={code}&state={state}
```
Microsoft will redirect back to this endpoint after successful authentication.

Required Query Parameters:
- `code`: Authorization code provided by Microsoft
- `state`: State parameter for security validation

### Microsoft OAuth2 Scopes
- `https://graph.microsoft.com/Mail.Read`: Read Outlook messages
- `https://graph.microsoft.com/Mail.Send`: Send emails through Outlook
- `https://graph.microsoft.com/User.Read`: Read user profile information

## Environment Setup

### Required Environment Variables
```json
{
    "dev": {
        "google_client_id": "your-google-client-id",
        "google_client_secret": "your-google-client-secret",
        "microsoft_client_id": "your-microsoft-client-id",
        "microsoft_client_secret": "your-microsoft-client-secret",
        "base_url": "http://localhost:8080"
    }
}
```

## Authentication Flow

1. **Initiate Authentication**
   - User clicks login button or visits login endpoint
   - Application redirects to provider's authentication page

2. **User Authentication**
   - User logs in with their credentials on provider's page
   - User grants requested permissions

3. **Callback Processing**
   - Provider redirects back to application with auth code
   - Application exchanges code for access token
   - Access token is stored for future API requests

4. **Using the Access Token**
   - Include token in Authorization header:
   ```http
   Authorization: Bearer {access_token}
   ```

## Error Responses

Common error responses you might encounter:

```json
{
    "error": "invalid_request",
    "error_description": "Missing required parameter: client_id"
}
```

```json
{
    "error": "invalid_grant",
    "error_description": "Invalid authorization code"
}
```

## Security Considerations

1. Always use HTTPS in production
2. Validate state parameter to prevent CSRF attacks
3. Store client secrets securely
4. Never expose tokens in URLs or logs
5. Implement token refresh mechanism
6. Set appropriate token expiration times
