<h1 align="center">HTMX + Spring Boot + TailwindCSS</h1>
<p align="center">
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
* Create User
* Update user
* Delete user

This project consists of two projects: front-end (Htmx) and back-end (Spring Boot).
```sh
project_root
├── frontend (Htmx)
└── backend (Spring boot)
```


## DEMO
### Create User
<img src="https://private-user-images.githubusercontent.com/42198184/328012431-5f7a1135-5498-4f90-9da4-046672c853a7.gif?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTQ5MDcxOTIsIm5iZiI6MTcxNDkwNjg5MiwicGF0aCI6Ii80MjE5ODE4NC8zMjgwMTI0MzEtNWY3YTExMzUtNTQ5OC00ZjkwLTlkYTQtMDQ2NjcyYzg1M2E3LmdpZj9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA1MDUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNTA1VDExMDEzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTBjMTE2YjliZTk4ZjY4MWMzYzgyNjQ2MTQyNjJkMmI1OThmOWFiN2Q0NTZkNGUzOWFhMzJhODJhMjVmN2Q2MzImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.DE33_E0rWF1SarPPyTyRmZ14WZypW3sXf8_6XUygteM">

### Update user
<img src="https://private-user-images.githubusercontent.com/42198184/328012435-b24fdd5c-9466-4468-b72e-8db6ff5480cd.gif?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTQ5MDcxOTIsIm5iZiI6MTcxNDkwNjg5MiwicGF0aCI6Ii80MjE5ODE4NC8zMjgwMTI0MzUtYjI0ZmRkNWMtOTQ2Ni00NDY4LWI3MmUtOGRiNmZmNTQ4MGNkLmdpZj9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA1MDUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNTA1VDExMDEzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTc2ZGYxODk1MGM1YTBjNmM0ZWNiY2ZhNDhkZjFmYmZiNTg0MDc2MTJmNWE4OGNhMjU5ZmUyMGI5ZjgwNzdkZTkmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.yd4aCuA7K3HO0oemfnwysRXCdsPm6FV3tl5WCoInEHA">

### Delete user
<img src="https://private-user-images.githubusercontent.com/42198184/328012433-59762be8-d475-4e1b-95cb-348346475c5f.gif?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTQ5MDcxOTIsIm5iZiI6MTcxNDkwNjg5MiwicGF0aCI6Ii80MjE5ODE4NC8zMjgwMTI0MzMtNTk3NjJiZTgtZDQ3NS00ZTFiLTk1Y2ItMzQ4MzQ2NDc1YzVmLmdpZj9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA1MDUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNTA1VDExMDEzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTYwNWE0ZWY5NDQxZGFjNTJkOTM0MTQwMjEzYmFiMzg2Mjk5ZGJmZjcyNmYwMzFmMzdiYTc2ZDQ5NTlkOGQwYzUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.kdVrpAP6p1ZWUdwVpERM6nxmTXUtXj6AICEbUi9WXHU">

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