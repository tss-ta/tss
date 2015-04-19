For Windows users:
1. Open run.bat
2. Change PGPASSWORD to your database password
3. Change absolute path to psql.exe if needed
4. Change hostname after "-h" (in local environment: localhost)
5. Don't change db user after "-U"!
6. Change db name after "-d" to your local db if needed (default: postgres)
7. create_user_tables.sql is needed to be in the same folder with run.bat

command example:
    set PGPASSWORD=pass
    "C:\Program Files\PostgreSQL\9.2\bin\psql.exe" -h localhost -U postgres -d postgres -f E:\develop\java_projects\tss\scripts\create_user_tables.sql
