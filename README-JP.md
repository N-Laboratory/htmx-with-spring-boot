<h1 align="center">HTMX + Spring Boot + TailwindCSS</h1>
<p align="center">
  <img src="https://img.shields.io/badge/-Htmx-grey.svg?logo=Htmx&style=flat">
  <img src="https://img.shields.io/badge/-Java-007396.svg?logo=Java&style=flat">
  <img src="https://img.shields.io/badge/-Spring Boot-lightyellow.svg?logo=Spring Boot&style=flat">
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
### フロントエンド環境構築
```bash
$ cd frontend
$ npm ci
```
### フロントエンド起動
```bash
$ cd frontend
$ npm run start:fe
```
### バックエンド環境構築
```bash
$ cd frontend
$ npm run build:be
```
### バックエンド起動
```bash
$ cd frontend
$ npm run start:be
```

## テスト実施
### E2Eテスト実施
```bash
$ cd frontend
$ npm run start:fe
$ npm run start:be
$ npm run test:fe
```

### バックエンド単体テスト実施
```bash
$ cd frontend
$ npm run test:be
```

## デモ
### ユーザー作成
<img src="https://user-images.githubusercontent.com/42198184/236676267-6985a216-7d7c-4913-a617-f3bfca127521.gif">

### ユーザー更新
<img src="https://user-images.githubusercontent.com/42198184/236676291-4a529a3d-127e-4015-99aa-0c63d154b650.gif">

### ユーザー削除
<img src="https://user-images.githubusercontent.com/42198184/236676293-1c76f008-88d0-44db-a0a7-3ecfbcf05dc2.gif">