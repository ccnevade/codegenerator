# DataSource settings: set here your own configurations for the database
# connection. In this example we have "netgloo_blog" as database name and
# "root" as username and password.
spring.datasource.url = jdbc:mysql://localhost:3306/TABLENAME
spring.datasource.username = USERNAME
spring.datasource.password = PASSWORD

# Keep the connection alive if idle for a long time (needed in production)
spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1

# Show or not log for each sql query
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto = update
