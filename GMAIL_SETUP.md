# Gmail API Setup Guide

This guide explains how to set up and configure the Gmail API for the Email Notification Service.

## Step 1: Create a Google Cloud Project

1. Go to the [Google Cloud Console](https://console.cloud.google.com/)
2. Click on the project dropdown at the top of the page
3. Click "New Project"
4. Enter a project name and click "Create"

## Step 2: Enable the Gmail API

1. In your new project, go to the [API Library](https://console.cloud.google.com/apis/library)
2. Search for "Gmail API"
3. Click on "Gmail API"
4. Click "Enable"

## Step 3: Configure OAuth Consent Screen

1. Go to "APIs & Services" > "OAuth consent screen"
2. Select "External" user type (unless you have a Google Workspace organization)
3. Fill in the required information:
   - App name
   - User support email
   - Developer contact information
4. Click "Save and Continue"
5. Under "Scopes", add the following scope:
   - `https://www.googleapis.com/auth/gmail.readonly`
6. Click "Save and Continue"
7. Add test users if you're using external user type
8. Click "Save and Continue"

## Step 4: Create OAuth 2.0 Credentials

1. Go to "APIs & Services" > "Credentials"
2. Click "Create Credentials" > "OAuth client ID"
3. Choose "Desktop app" as the application type
4. Enter a name for your OAuth client
5. Click "Create"
6. Add the following redirect URI:
   ```
   http://localhost:8888/Callback
   ```
7. Click "Save"
8. Download the client configuration file

## Step 5: Configure the Application

1. Rename the downloaded OAuth client configuration file to `credentials.json`
2. Place `credentials.json` in the `src/main/resources/` directory of the project

## Step 6: First Run

When you first run the application:
1. The application will open your default web browser
2. You'll be prompted to sign in to your Google account
3. Grant the requested permissions to the application
4. The application will receive an authorization code and automatically exchange it for access tokens
5. These tokens will be stored in the `tokens` directory for future use

## Troubleshooting

### Common Issues

1. **"Error: redirect_uri_mismatch"**
   - Make sure the redirect URI in Google Cloud Console exactly matches `http://localhost:8888/Callback`
   - Check that you're using the correct `credentials.json` file

2. **"Error: invalid_scope"**
   - Verify that the Gmail API is enabled in your Google Cloud project
   - Check that the OAuth consent screen is properly configured with the required scope

3. **"Error: access_denied"**
   - Make sure you're using the correct Google account
   - Verify that you've added your email as a test user if using external user type

### Token Storage

- Access tokens are stored in the `tokens` directory
- If you experience authentication issues, you can delete the `tokens` directory to force a new authentication flow
