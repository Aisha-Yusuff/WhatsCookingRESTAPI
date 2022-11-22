# What's Cooking?

## Description
A Recipe REST API allowing users to discover a range of recipes and share their favourite recipes.

## Features
- User can view all recipes
- User can find a recipe by searching by ingredient
- User can create a recipe
- User can update a recipe
- User can delete a recipe

## User Stories
```
As a user, 
I want to be able to retrieve all the recipes,
So that I can find a recipe to cook.
```
```
As a user, 
I want to be able to retrieve recipes with specific ingredients,
So that I can cook a recipe with ingredients I already have
```
```
As a user, 
I want to be able to create a new recipe, 
So that I can share my own recipes.
```
```
As a user, 
I want to add an image to my recipe, 
So I can share how delicious my food looks.
```
```
As a user, 
I want to be able to edit a recipe, 
So that I can update my recipes over time.
```
```
As a user, 
I want to be able to delete a recipe, 
When I no longer want to share it.
```
## Project Approach

Although, this was a solo project I conducted stand ups where I reminded myself of what issues I had completed, what features I was working on at the time and address any blockers I may be experiencing. This project consisted of two sprints that each lasted a week. At the end of each sprint, I held a retrospective where I was able to evaluate my processes, analyse my backlog, check if I had reached the milestones I had created in my sprint planning session, identify what practises were successful and where improvements could be made.

### Planning and Design
To organise this project I created a GitLab Issues Board which consisted of 5 lists: Open, Task, Work in Progress, Blocked and Closed. The user stories were stored in the open list, the backlog was held  in the task list, the issues being worked on at the time were kept in the work in progress list and blockers in the blocked list. Once an issue had been completed it was moved into the closed list.

Milestones were created for each user story and assigned to issues. This help track progress and set a time period for each feature. I also organised issues using labels to separate the issues that were to be completed in the first sprint and the second sprint.

<img src="https://recipeapi-images.s3.eu-west-2.amazonaws.com/issueBoard.png" alt="project-gitlab-issue-board" width="500"/>

To visualise the structure of this application, I created a diagram to understand how the controller, service, repository, model layers and the database interact with each other.

<img src="https://recipeapi-images.s3.eu-west-2.amazonaws.com/RecipeRestAPI-diagram.png" alt="project-diagram" width="500">

### Implementation
The design patterns used to build this REST API include the Builder pattern and the Adapter pattern.

The Builder pattern was used to create a Recipe object. I opted for this pattern because it allowed me to initially build a recipe object that only included certain attributes and without removing the immutable nature of the object and creating another constructor, I could then add more attributes to the object.

The Adapter pattern was used to build the service layer of this API. I created a service interface which acted as an adaptee. I then created a service implementation class that acted as the adaptor, the class implemented the interface and inherited its methods to implement the business logic of the API. This pattern introduced loose coupling between the client and the service interface and increased the reusability of the code.


### Testing
This application was created using a test-driven development approach, following the red, green and refactor method. Test were created using Spring Boot, JUnit 5, Mockito, MockMVC, Java Persistence API(JPA), AssertJ Library and Jackson Testers.

A Recipe controller test was created to test its communication with the client and the service layer. Unit tests were created for each REST endpoint: GET, POST, PUT and DELETE to verify the correct HTTP status code was found in the response and to verify the response payload contained the correct JSON body.

A Recipe Service Implementation test was also created to test the implementation of the business logic, while the data access (repository) layer was mocked to test the service layer in isolation. The test verified the service layer's CRUD (Create, Read, Update and Delete) methods were functioning correctly.

Error handling test were also created to validate if the wrong information was passed through the API such as an incorrect ID, then the API would return an Illegal State Exception.


## Prerequisites
This project uses the Java programming language and Node.js runtime environment.

#### Java

To run this application please make sure you have have Java 17 LTS (or higher) installed locally.

You can use the following commands to check your Java version:
```bash
javac -version

javac 17.0.4
```
```bash
java -version

openjdk version "17.0.4" 2022-07-19 LTS
OpenJDK Runtime Environment Zulu17.36+13-CA (build 17.0.4+8-LTS)
OpenJDK 64-Bit Server VM Zulu17.36+13-CA (build 17.0.4+8-LTS, mixed mode, sharing)
```

If you don't have Java installed, you can find the Oracle JDK Installation guide on the [oracle website](https://www.oracle.com/java/technologies/downloads/#jdk17).

#### Node.js

If you don't have Node installed, you will need to download nvm - the Node Version Manager first. You can find the installation instructions for the latest version on the [nvm Github repo](https://github.com/nvm-sh/nvm#installing-and-updating).

Once, you've downloaded nvm, you can install Node using the following commands:

```
nvm install node
nvm use node
```
You should then see something like this in your terminal: `Now using node v16.6.0 (npm v7.19.1)` , the version may be slightly different.


## Installation
1. Clone this repository
```
git clone git@gitlab.com:ay36/RecipeAPI.git
```

2. Open the project directory using your desired IDE

3. Go to the `com.aishayusuff.RecipeAPI` package
```
cd src/main/java/com/aishayusuff/RecipeApi
```

4. Run `RecipeApiApplication.java` to run the backend server

5. Then open a new terminal and `cd src/main/frontend`

6. Download the required dependencies by running `npm install`

7. Run the frontend server using `npm start`

This should open the application in your browser and the application is now ready for you to use.
## Images
Here is a walkthrough of the website:
(gif of the website)
- view an ingredient
- searching by ingredient
- creating an ingredient
- Deleting an ingredient

## Open API Documentation
An Open API doc for this REST API can be found here. It can be used to test the API's endpoints directly on the GitLab.com website.
(Screenshot from https://editor.swagger.io/)

## Database
This application currently uses a PostgreSQL relational database.

The database contains 3 tables: Recipes, Ingredients and Instructions. The Recipes and Ingredients tables contain a One to Many relationship. This means one record in the Recipes table is associated with more than one record in the Ingredients table. This relationship also exists with the Recipe and Instructions tables. The tables are joined by creating a Foreign Key column in the child table which holds a reference to the Primary Key found in the parent table (Recipes).

Here is a diagram of the database schema:

<img src="https://recipeapi-images.s3.eu-west-2.amazonaws.com/DatabaseSchema.png" alt="project-database-schema" width="500"/>

## Roadmap
In the future I would like to implement the following features:

- [ ] User can create an account and sign in
- [ ] User can view only the recipes they have created
- [ ] User can only delete recipes they have created
- [ ] Build containers with Jib
- [ ] Deploy the application using Heroku
