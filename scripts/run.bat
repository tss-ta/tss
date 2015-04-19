@echo off
setlocal
set PGPASSWORD=<your_password>
"C:\Program Files\PostgreSQL\9.2\bin\psql.exe" -h <host_name ("localhost" for local environment)> -U postgres -d <db_name (default: "postgres")> -f %~dp0\create_user_tables.sql
pause
endlocal