# What's Cooking?

## Description
What's Cooking is a test-driven recipe REST API with a Java Spring Boot backend and a React frontend. The API allows its users to discover a range of recipes that are stored in a PostgreSQL database, a  user can edit, update and delete an existing recipe and create their own recipe to share with other users. The API has a controller, service, repository layers along with 3 JPA entities for a recipe, ingredient and instruction object. The API also implements two software design patterns, the Builder and Adapter patterns.

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

This project consisted of two sprints that each lasted just over a week. At the end of each sprint, I held a retrospective where I was able to evaluate my processes, analyse my backlog, check if I had reached the milestones I had created in my sprint planning session, identify what practises were successful and where improvements could be made. Although, this was a solo project I conducted regular stand-ups to remind myself of what issues I had completed, what features I was working on at the time, update my issue board and address any blockers I may be experiencing.

### Planning and Design
To organise this project I created a GitLab Issues Board which consisted of 5 lists: Open, Task, Work in Progress, Blocked and Closed. The user stories were stored in the open list, the backlog was held  in the task list, the issues being worked on at the time were kept in the work in progress list and blockers in the blocked list. Once an issue had been completed it was moved into the closed list.

Milestones were created for each user story and assigned to issues. This help track progress and set a time period for each feature. I also organised issues using labels to separate the issues that were to be completed in the first sprint and the second sprint.


<img src="https://recipeapi-images.s3.eu-west-2.amazonaws.com/issueBoard.png" alt="project-gitlab-issue-board" width="800"/>


To visualise the structure of this application, I created a diagram to understand how the controller, service, repository, model layers and the database interact with each other.


<img src="https://recipeapi-images.s3.eu-west-2.amazonaws.com/RecipeRestAPI-diagram.png" alt="project-diagram" width="800">


### Implementation

####  Design Patterns
The design patterns used to build this REST API was the Builder pattern (creational) and the Service Layer pattern (architechural).

The Builder pattern was used to create a Recipe object. I opted for this pattern because it allowed me to initially build a recipe object that only included certain attributes and without removing the immutable nature of the object and creating another constructor, I could then add more attributes to the object.

The Service Layer pattern was used to build the service layer of this API. I created a service interface which held the CRUD methods of the API. I then created a service implementation class that implemented the interface and inherited its methods. This classed added functionality to each method and communicated with the Jpa repository layer, to ensure the correct data was retrieved from the postgreSQL database. This pattern introduces loose coupling and abstraction to the API and increases the reusability and scalabilty of the code.

#### Database
This application currently uses a PostgreSQL relational database.

The database contains 3 tables: Recipes, Ingredients and Instructions. The Recipes and Ingredients tables contain a One-to-Many relationship. This means one record in the Recipes table is associated with more than one record in the Ingredients table. This relationship also exists with the Recipe and Instructions tables. The tables are joined by creating a Foreign Key column in the child table which holds a reference to the Primary Key found in the parent table (Recipes).

Here is a diagram of the database schema:

<img src="https://recipeapi-images.s3.eu-west-2.amazonaws.com/DatabaseSchema.png" alt="project-database-schema" width="800"/>

### Testing
This application was created using a test-driven development approach, following the red, green and refactor method. Test were created using Spring Boot, JUnit 5, Mockito, MockMVC, Java Persistence API(JPA), AssertJ Library and Jackson Testers.

A Recipe controller test was created to test its communication with the client and the service layer. Unit tests were created for each REST endpoint: GET, POST, PUT and DELETE to verify the correct HTTP status code was found in the response and to verify the response payload contained the correct JSON body.

A Recipe Service Implementation test was also created to test the implementation of the business logic, while the data access (repository) layer was mocked to test the service layer in isolation. The test verified the service layer's CRUD (Create, Read, Update and Delete) methods were functioning correctly.

The test also used the Behaviour Driven Development process of given-when-then where the given stage was the preparation stage of the test where objects were created and manipulated for the test. The when stage is where the behaviour of the test was introduced and the then stage is the verification part of the test. The expected and actual results are compared and validate if they are equal and different methods are checked to see if they were invoked correctly.

### Reflection
By creating this API, I was able to learn how to implement architectural and creational Software Design Patterns and the benefits they can bring to your code. I gained a better understanding on functional programming and was able to successfully use the Stream API and Lambda Functions. This project was my first experience working with the One-to-Many mapping and although I had to overcome a few hurdles for it work successfully, I now have a strong understanding of it. I've also been able to develop my knowledge on Behaviour Driven Development, data structures and the Java syntax. The use of a GitLab Issues board was very helpful as it allowed me to track my progress, stay organised and complete features and issues within my planned Milestones. 

As I mentioned before I did experience a few hurdles when working on the One-to-Many relationship of my database tables. By using the incorrect fetch type annotation, I was rendering duplicates of my instructions when I fetched a recipe object in my React app. I learnt that by using the FetchType.EAGER on my One-to-Many relationships, I had created a [Cartesian Product](https://quebit.com/askquebit/quebit-products/exploring-the-sql-cartesian-join/#:~:text=In%20SQL%20Server%2C%20the%20cartesian,defined%20between%20the%20two%20tables) which was creating duplicates because the fetch type was creating a relationship between my two child tables. With the help of online resources such as Stack Overflow and my helpful technical mentor I was able to rectify this issue by using the FetchType.LAZY annotation.  

Furthermore, to prepare my REST API for containerisation and deployment. I decided to create a cloud database for my local PostgreSQL database using the Amazon Relational Database Service, but I wasn't able to use the getByIngredient and DeleteById methods using that database and decided to use a local database instead. So, my next steps will include learning how to use the Amazon Relational Database Service successfully.   

If I could improve this project, I would use git branching more. So, for every feature I would create a new branch and if I needed multiple versions of feature, I would also create a new branch. So that If I needed to refer to the branches again I would have them readily available. This API was test driven and I used BDD and the red-green-refactor approach. As this was my first time creating a spring boot REST API, my tests were initially quite lengthy as I wanted to make sure that all the functionality was working correctly once my tests had passed. I made sure to refactor my test but now completing this project I can see the benefits of refactoring my test further and understand what information is useful and not in the given stage of my tests. 


## Prerequisites
This project uses the Java programming language and Node.js runtime environment.

#### Java

To run this application please make sure you have Java 17 LTS (or higher) installed locally.

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

If you don't have Node installed, you will need to download nvm - the Node Version Manager first. You can find the installation instructions for the latest version on the [nvm GitHub repo](https://github.com/nvm-sh/nvm#installing-and-updating).

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

## Open API Documentation
An Open API doc for this REST API can be found [here](openapi.yaml). It can be used to test the APIs endpoints directly on the GitLab.com website.

<img src="https://recipeapi-images.s3.eu-west-2.amazonaws.com/Openapi.png"  alt="OpenApi-swagger-ui" width="800"/>

## Roadmap
In the future I would like to implement the following features:

- [ ] Use the AWS api to allow users to upload images to their recipe
- [ ] Build containers with Jib
- [ ] Deploy the application using Heroku
- [ ] User can create an account and sign in
- [ ] User can view only the recipes they have created
- [ ] User can only delete recipes they have created

