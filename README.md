# About the project
My project is a real estate registry. The buildings have different 
##
# Build with
- Java
- Java spring boot
- JPA
- SQL
- H2

# Getting started
This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.
run the bash file to contanerisate the api

## Prerequisites
This is an example of how to list things you need to use the software and how to install them.

- npm
```shell
npm install npm@latest -g
```

## Installation
Below is an example of how you can instruct your audience on installing and setting up your app.
This template doesn't rely on any external dependencies or services.

- Clone the repo
  `git@github.com:CodecoolGlobal/el-proyecte-grande-sprint-4-java-Teszike99.git`
- Install NPM packages
  `npm install`

# Usage
- The first page you see if you open the app is a home page where you can read the main logic of this project.
- There is a navbar in top with some buttons where you can change the pages.
- In the profit calculator page you can see match pairs with the odds and you can calculate the
  profit if you give the amount how many money would you like to invest.
- The fix profit matches show the match pairs where the profit is fix. But its just a few percent profit.

# Contributing
Contributing is for show how we used version control.

- We used branches and did pull requests.
- Committed everything file by file.
- We worked with different sprints in this project.

```mermaid
sequenceDiagram
participant room
participant building
participant role
participant person
note left of room: CRUD
note left of building: CRUD
note left of role: CRUD
note left of person: CRUD


room->>building: add to building
role->>building: add to building
role->>person: add to person
```
baba
```mermaid
sequenceDiagram
participant Alice
participant Bob
Alice->>John: Hello John, how are you?
loop Healthcheck
John->>John: Fight against hypochondria
end
Note right of John: Rational thoughts <br/>prevail!
John-->>Alice: Great!
John->>Bob: How about you?
Bob-->>John: Jolly good!
```