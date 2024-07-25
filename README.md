```sh
$ cd ~/workspace/
$ mvn archetype:generate -DgroupId=com.justin4u -DartifactId=user -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
$ cd user
$ mkdir -p src/main/java src/test/java src/test/resources
```


<pre>
.
├── README.md
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── justin4u
    │   │           └── user
    │   │               ├── User.java
    │   │               └── UserResource.java
    │   ├── resources
    │   │   └── META-INF
    │   │       └── persistence.xml
    │   └── webapp
    │       ├── WEB-INF
    │       │   └── web.xml
    │       └── index.jsp
    └── test
        ├── java
        └── resources

</pre>
