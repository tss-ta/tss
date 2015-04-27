@echo off
setlocal
set PGPASSWORD=<password>
"C:\PostgreSQL\9.3\bin\psql.exe" -h localhost -U postgres -d postgres -f %~dp0\create_user_tables.sql
"C:\PostgreSQL\9.3\bin\psql.exe" -h localhost -U postgres -d postgres -f %~dp0\fill_user_tables.sql
pause
endlocal