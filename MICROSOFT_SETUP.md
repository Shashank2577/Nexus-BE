# Microsoft Graph API Setup Guide

This guide explains how to set up and configure Microsoft Graph API for the Email Notification Service.

## Step 1: Register Application in Azure Active Directory

1. Sign in to the [Azure Portal](https://portal.azure.com/)
2. Navigate to "Azure Active Directory"
3. Select "App registrations" in the left sidebar
4. Click "New registration"
5. Fill in the application details:
   - Name: Email Notification Service
   - Supported account types: Choose based on your requirements
     - Single tenant: Only accounts in your organization
     - Multitenant: Accounts in any organizational directory
   - Redirect URI: (Web) http://localhost:8080/login/oauth2/code/microsoft
6. Click "Register"

## Step 2: Configure API Permissions

1. In your app registration, go to "API permissions"
2. Click "Add a permission"
3. Select "Microsoft Graph"
4. Choose "Application permissions"
5. Add the following permissions:
   - Mail.Read
   - Mail.ReadBasic
6. Click "Add permissions"
7. Click "Grant admin consent" (requires admin privileges)

## Step 3: Create Client Secret

1. Go to "Certificates & secrets"
2. Click "New client secret"
3. Add a description and choose an expiration period
4. Click "Add"
5. **IMPORTANT**: Note down the generated secret value immediately
   - You won't be able to see it again after leaving the page
   Note down these values (DO NOT commit them):
   - Client ID
   - Client Secret
   - Tenant ID

## Step 4: Get Required Credentials

Collect the following information from your app registration:
1. Application (client) ID from the "Overview" page
2. Directory (tenant) ID from the "Overview" page
3. Client secret value you created

## Step 5: Configure Environment Variables

Set the following environment variables:

```bash
# Windows (Command Prompt)
set AZURE_CLIENT_ID=your_client_id
set AZURE_CLIENT_SECRET=your_client_secret
set AZURE_TENANT_ID=your_tenant_id

# Windows (PowerShell)
$env:AZURE_CLIENT_ID="your_client_id"
$env:AZURE_CLIENT_SECRET="your_client_secret"
$env:AZURE_TENANT_ID="your_tenant_id"
```

For permanent configuration:
1. Open Windows System Properties
2. Click "Environment Variables"
3. Add the variables under "System variables"

## Step 6: Configure Authentication Settings

1. In your app registration, go to "Authentication"
2. Under "Platform configurations", click "Add a platform"
3. Select "Web"
4. Add the redirect URI if not already added
5. Under "Implicit grant and hybrid flows":
   - Check "ID tokens"
   - Check "Access tokens"
6. Click "Configure"

## Troubleshooting

### Common Issues

1. **"AADSTS7000215: Invalid client secret provided"**
   - Verify that the client secret hasn't expired
   - Check that the secret is correctly set in environment variables
   - Create a new client secret if necessary

2. **"Insufficient permissions"**
   - Verify that all required permissions are added
   - Ensure admin consent has been granted
   - Check that the permissions are of type "Application" not "Delegated"

3. **"Invalid tenant ID"**
   - Confirm you're using the correct tenant ID
   - Verify the account you're using belongs to the correct tenant

### Security Best Practices

1. **Client Secret Management**
   - Never commit client secrets to source control
   - Rotate client secrets periodically
   - Use Azure Key Vault in production environments

2. **Permission Scope**
   - Only request necessary permissions
   - Use least-privilege access principles
   - Regularly review and remove unused permissions
