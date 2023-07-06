# Demo of ADB Group Work

## This is Team H&L

> Members of Team H&L:
- Li Shengyang
- Hikaru Matsuzaki

## Technology Stack

This is a `front-end` and `back-end` separated project.

> front-end:

The front-end is based on [Vue](https://vuejs.org/), modified from an open-source front-end project: [GitHub Repo](https://github.com/hai-27/vue-store).

> back-end:

The back-end is based on the [Spring Boot framework](https://spring.io/projects/spring-boot) and [ScalarDB](https://github.com/scalar-labs/scalardb), and the entire project code is original.

## How to run

### front-end：

1.Install the required dependencies: [Node.js](https://nodejs.org/)、[Vue](https://vuejs.org/)

```
$ brew install node 
$ npm install --global @vue/cli
```
   
2.Project setup

```
$ cd vue-store
$ npm install
```

3.Compiles and hot-reloads for development

```
$ npm run serve
```

### back-end：

1.Install the required dependencies：[java8](https://www.java.com/), [ScalarDB](https://github.com/scalar-labs/scalardb), [MySQL](https://www.mysql.com/), [Cassandra](https://cassandra.apache.org/_/index.html), [Gradle](https://gradle.org/)

```   
Each dependency has detailed installation and running tutorials available on their official websites or technical blogs. Therefore, I won't go into detail here.
```

2.DB Migration

```
$ cd demo
$ sh migration.sh
```

This operation will use `ScalarDB Schema Loader` to create the necessary database and table structure for the demo.

3.Run the SpringBoot project

```
gradle bootRun
```

Or just:

```
Click ▶️ on your IDEA 
```

4.Before using it formally:

Please visit `http://localhost:8080/api/support/init` to generate the necessary initialization data before using it formally.


> The suggested order is to start the backend first and then start the frontend. Once both the frontend and backend projects are successfully running, you can freely interact with the corresponding frontend pages.



## A Little Pity
Because the author has very little knowledge of frontend technologies, simple modifications and reverse engineering of the frontend-backend interaction through HTTP requests can be done. However, making changes to the frontend project's code at a technical level is quite challenging. Therefore, all communication between the frontend and backend is done using POST requests, which is extremely frustrating for a fan of RESTful practices like me.

The main focus of the demo is to showcase the functionality, so some complex validation and other logic have been omitted. As a result, there may be unexpected behavior in certain situations.

This demo is primarily for learning and communication purposes, and should not be used for commercial purposes.