@echo off
echo Setting up environment variables...

for /f "tokens=*" %%a in ('type .env ^| findstr /v "#"') do (
    set "%%a"
)

echo Environment variables set successfully!
echo Starting the server...

call mvn spring-boot:run
