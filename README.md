<h1 align="center">HTMX + Spring Boot + TailwindCSS</h1>
<p align="center">
  <img src="https://img.shields.io/badge/-Swagger-black.svg?logo=Swagger&style=flat">
  <img src="https://img.shields.io/badge/-Htmx-grey.svg?logo=Htmx&style=flat">
  <img src="https://img.shields.io/badge/-Java-007396.svg?logo=Java&style=flat">
  <img src="https://img.shields.io/badge/-Spring Boot-lightyellow.svg?logo=Spring Boot&style=flat">
  <img src="https://img.shields.io/badge/-MyBatis-grey.svg?logo=MyBatis&style=flat">
  <img src="https://img.shields.io/badge/-JUnit5-F36D00.svg?logo=JUnit5&style=flat">
  <img src="https://img.shields.io/badge/-TailWind CSS-white.svg?logo=tailwind css&style=flat">
  <img src="https://img.shields.io/badge/-Windows-0078D6.svg?logo=windows&style=flat">
  <img src="https://img.shields.io/badge/-Mac-grey.svg?logo=macos&style=flat">
  <img src="https://img.shields.io/badge/-Linux-black.svg?logo=linux&style=flat">
  <img src="https://img.shields.io/badge/-VSCode-007ACC.svg?logo=visualstudiocode&style=flat">
  <a href="https://twitter.com/NL4boratory" target="_blank">
    <img alt="Twitter: N-LAB" src="https://img.shields.io/twitter/follow/NL4boratory.svg?style=social" />
  </a>
  <a href="https://github.com/N-Laboratory" target="_blank">
    <img src="https://img.shields.io/badge/-FollowMyAccount-grey.svg?logo=github&style=flat">
  </a>
</p>
<a href="https://github.com/N-Laboratory/htmx-with-spring-boot/blob/main/README-JP.md" target="_blank">
  日本語版 README はこちら
</a>

I created this project for learning Htmx and Spring boot.

This project implements the following features.
* Search user
* Create user
* Update user
* Delete user

This project consists of two projects: front-end (Htmx) and back-end (Spring Boot).
```sh
project_root
├── frontend (Htmx)
└── backend (Spring boot)
```
## Contents

1. [Demo](#demo)
1. [Project Overview](#project-overview)
1. [How to use](#how-to-use)
1. [Run test](#run-test)
1. [Swagger](#swagger)

## Demo
### Create User
![create_user](https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/1ebf2977-8216-44b8-abfb-7e4743e8dde8)

### Update user
![update_user](https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/46f047b1-830a-462c-be8b-491e32fb1a92)

### Delete user

![delete_user](https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/c147453e-d47b-4ac8-8c0a-44c61c386b05)

## Project Overview
Front-end
* Htmx
  *  Verification of input values
  *  Submit form
* TailwindCSS
  *  Screen design/layout creation
* FlowBite
  *  Show/hide modal control
* Vitest・Puppeteer
  *  Implement E2E test

Back-end
* Spring Boot・Java
  *  Search user
  *  Create user
  *  Update user
  *  Delete user
  *  display user creation modal
  *  display user update modal
  *  display user deletion modal
* JUnit
  *  Implement unit test

The versions of major libraries/frameworks used in each project are as follows.

Front-end
* Htmx: 1.9.12
* TailwindCSS: 3.4.3
* FlowBite: 2.3.0
* Vitest: 1.5.3
* Puppeteer: 22.7.1

Back-end
* Spring Boot: 3.2.0
* Java: 21.0.1
* Gradle: 8.5
* JUnit: 5.10.2
* MyBatis: 3.5.14

## How to use
This project consists of two projects: front-end (Htmx) and back-end (Spring Boot).

The database uses an in-memory database, so there is no need to build an environment.

```sh
project_root
├── frontend (Htmx)
└── backend (Spring boot)
```
### Run front-end
```bash
$ cd frontend
$ npm ci
$ npm run start:fe
```

### Run back-end
#### Linux / Mac
```bash
$ cd frontend
$ npm run build:be:linux
$ npm run start:be
```

#### Windows
```bash
$ cd frontend
$ npm run build:be:windows
$ npm run start:be
```
Access the following URL after starting the front-end and back-end.

http://localhost:3000/src/

## Run test
### Run E2E test
```bash
$ cd frontend
$ npm run start:fe
$ npm run start:be
$ npm run test:fe
```

### Run back-end unit test
#### Linux / Mac
```bash
$ cd frontend
$ npm run test:be:linux
```

#### Windows
```bash
$ cd frontend
$ npm run test:be:windows
```

## Swagger
API definitions are created using Swagger.

To see the API definition, access the following URL after starting the back-end.

http://localhost:8080/swagger-ui/index.html

<img width="1085" alt="swagger" src="https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/e7511701-8347-437b-b454-c38174584d3a">
