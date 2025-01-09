# Project: Spring Social Media Blog API

## Project Description

I completed this project as part of Revature's pre-training program. It's a backend for a hypothetical social media app that manages users’ accounts and any messages that they submit to the application. This project leverages the Java Spring framework, which allows for automatic dependency injection and configuration of many features, including data persitence, endpoints and conventional data manipulation logic (CRUD operations). This backend delivers the data needed to display information for accounts and messages, such as processing actions like logins, registrations, message creations, message updates, and message deletions.

## Technologies Used

- Java
- Spring Framework
- Spring Web
- Spring Data JPA

## Features

The API allows a user to

1. create a new Account on the endpoint POST localhost:8080/register. The request body contains a representation of a JSON Account, but does not contain an accountId. If all the required conditions are met, the response status is 200 OK, and the new account is persisted to the database.

2. verify login on the endpoint POST localhost:8080/login. The request body will contain a JSON representation of an Account. If the username and password provided exist together in the database, the response body contains a JSON representation of that account.

3. create new messages. This can be done by making an HTTP POST request at localhost:8080/messages with the message details in the request body.

4. retrieve all messages from the database using a get request at the /messages endpoint.

5. retrieve a message by its message id by making a get request to the /messages/{id} endpoint.

6. delete a message by its message id by issuing a delete request to the /messages/{id} endpoint.

7. update a message text by message id by using a patch request at the /messages/{id} endpoint

8. retrieve all messages posted by a particular user (account id) by issuing a get request to /accounts/{id}/messages

9. retrieve all accounts in the database by making a get request at the /accounts endpoint.

#### TODO list

Another functionality that could be added is hooking this backend to a front end by using technology like React or vanilla JavaScript.

## Getting Started

To clone this project use the following command and url:

git clone https://github.com/MarkBahr/MarkBahr-pep-spring-project.git

If using the eclipse IDE for development, you will have to import this as a Maven project. This can be done using these steps:

- Open an Eclipse workspace
- From the File menu, select Import.
- Expand Maven and select Existing Maven Projects.
- Click Next
- Browse for the root directory of the project
- Click Finish

## Usage

To run this project

- Navigate to src/main/java/com.example
- Right click on SocialMediaApp.java and select Run As Java Application

Then make http requests to http://localhost:8080 using a tool like PostMan or by using a browser on your local machine.
See the following GIF for an example.

<img src=Http-requests-spring-project.gif alt="Making HTTP requests on PostMan">
