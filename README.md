# UserDb

UserDb is a simple Java application that demonstrates the use of command pattern to manage user data in a database. The application uses H2 database for storage and includes unit tests with JUnit and Mockito.

## Features

- Add user to the database
- Print all users in format: `id, guid, name`
- Delete all users

## Running the Application

```sh
mvn exec:java -Dexec.mainClass="com.kuruc.Main"