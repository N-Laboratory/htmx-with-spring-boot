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

HtmxとSpring bootの学習用としてこのプロジェクトを作成しました。

このプロジェクトでは以下の機能を実装しています。
* ユーザーの検索
* ユーザーの作成
* ユーザーの編集
* ユーザーの削除

このプロジェクトはフロントエンド（Htmx）とバックエンド（Spring Boot）の2つのプロジェクトで構成されています。
```sh
project_root
├── frontend (Htmx)
└── backend (Spring boot)
```

## 目次

1. [デモ](#デモ)
1. [プロジェクト概要](#プロジェクト概要)
1. [起動方法](#起動方法)
1. [テスト実施](#テスト実施)
1. [Swagger](#swagger)

## デモ
### ユーザー作成
![create_user](https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/1ebf2977-8216-44b8-abfb-7e4743e8dde8)

### ユーザー更新
![update_user](https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/46f047b1-830a-462c-be8b-491e32fb1a92)

### ユーザー削除
![delete_user](https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/c147453e-d47b-4ac8-8c0a-44c61c386b05)

## プロジェクト概要
各プロジェクトの概要は以下の通りです。

フロントエンド
* Htmx
  *  入力内容の検証
  *  入力フォームの送信
* TailwindCSS
  *  画面のデザイン・レイアウト作成
* FlowBite
  *  モーダルの表示/非表示の制御
* Vitest・Puppeteer
  *  E2Eテスト実装

バックエンド
* Spring Boot・Java
  *  ユーザー検索
  *  ユーザー作成
  *  ユーザー更新
  *  ユーザー削除
  *  ユーザー作成モーダル表示
  *  ユーザー更新モーダル表示
  *  ユーザー削除モーダル表示
* JUnit
  *  単体テスト実装

各プロジェクトで使用している主要なライブラリ・フレームワークのバージョンは以下の通りです。

フロントエンド
* Htmx: 1.9.12
* TailwindCSS: 3.4.3
* FlowBite: 2.3.0
* Vitest: 1.5.3
* Puppeteer: 22.7.1

バックエンド
* Spring Boot: 3.2.0
* Java: 21.0.1
* Gradle: 8.5
* JUnit: 5.10.2
* MyBatis: 3.5.14

## 起動方法
### 概要
このプロジェクトのディレクトリ構成は以下になっています。

データベースはインメモリーデータベースを採用しているため別途環境を構築する必要はありません。

```sh
project_root
├── frontend (Htmx)
└── backend (Spring boot)
```
### フロントエンド起動
```bash
$ cd frontend
$ npm ci
$ npm run start:fe
```

### バックエンド起動
### Linux / Mac
```bash
$ cd frontend
$ npm run build:be:linux
$ npm run start:be
```

### Windows
```bash
$ cd frontend
$ npm run build:be:windows
$ npm run start:be
```
フロントエンドとバックエンドの起動後に以下のURLにアクセスします。

http://localhost:3000/src/

## テスト実施
### E2Eテスト実施
```bash
$ cd frontend
$ npm run start:fe
$ npm run start:be
$ npm run test:fe
```

### バックエンド単体テスト実施
### Linux / Mac
```bash
$ cd frontend
$ npm run test:be:linux
```

### Windows
```bash
$ cd frontend
$ npm run test:be:windows
```

## Swagger
Swaggerを用いてAPI定義を作成しています。

API定義を参照するにはバックエンドを起動後に以下のURLにアクセスします。

http://localhost:8080/swagger-ui/index.html

<img width="1085" alt="swagger" src="https://github.com/N-Laboratory/htmx-with-spring-boot/assets/42198184/e7511701-8347-437b-b454-c38174584d3a">