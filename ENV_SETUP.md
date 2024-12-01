# Environment Setup

## Required Environment Variables

Before running the application, set up the following environment variables:

### Google OAuth2 Credentials
```
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
```

### Microsoft Azure Credentials
```
AZURE_CLIENT_ID=your_azure_client_id
AZURE_CLIENT_SECRET=your_azure_client_secret
AZURE_TENANT_ID=your_azure_tenant_id
```

## Setting Environment Variables

### Quick Start (Recommended)
1. Copy the values from your Google and Microsoft Azure dashboards into the `.env` file
2. Run `start.bat` to set environment variables and start the server

### Manual Setup

#### Windows
```powershell
setx GOOGLE_CLIENT_ID "your_google_client_id"
setx GOOGLE_CLIENT_SECRET "your_google_client_secret"
setx AZURE_CLIENT_ID "your_azure_client_id"
setx AZURE_CLIENT_SECRET "your_azure_client_secret"
setx AZURE_TENANT_ID "your_azure_tenant_id"
```

#### Linux/MacOS
Add to your ~/.bashrc or ~/.zshrc:
```bash
export GOOGLE_CLIENT_ID="your_google_client_id"
export GOOGLE_CLIENT_SECRET="your_google_client_secret"
export AZURE_CLIENT_ID="your_azure_client_id"
export AZURE_CLIENT_SECRET="your_azure_client_secret"
export AZURE_TENANT_ID="your_azure_tenant_id"
```

## Using the .env File
1. Locate the `.env` file in the project root
2. Replace the placeholder values with your actual credentials:
```
# Azure Configuration
AZURE_CLIENT_ID=your_azure_client_id_here
AZURE_CLIENT_SECRET=your_azure_client_secret_here
AZURE_TENANT_ID=your_azure_tenant_id_here

# Google Configuration
GOOGLE_CLIENT_ID=your_google_client_id_here
GOOGLE_CLIENT_SECRET=your_google_client_secret_here
```
3. Run `start.bat` to automatically set the environment variables and start the server

## Verifying Setup
1. Ensure all environment variables are set correctly
2. The application will start automatically with `start.bat`
3. Access the application at `http://localhost:8888`

## Troubleshooting
- If you get authentication errors, verify that your credentials are correctly set in the `.env` file
- If `start.bat` fails, try running it as administrator
- Make sure there are no spaces around the '=' signs in the `.env` file
