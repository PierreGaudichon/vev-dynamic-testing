

VEV - Dynamic testing
=====================

- Pierre Gaudichon
- Timothée Merlet-Thomazeau


## Links

- [Sujet](https://github.com/Software-Testing/Project-2017-2018)
- [Javassist tutorial](http://jboss-javassist.github.io/javassist/tutorial/tutorial.html)
- [Github](https://github.com/PierreGaudichon/vev-dynamic-testing)

- [Tuto (prof)](http://www.tomsquest.com/blog/2014/01/intro-java-agent-and-bytecode-manipulation/)
- [Tuto (transform)](http://blog.xebia.fr/2008/05/02/java-agent-instrumentez-vos-classes/)


## Build

    mvn clean install
    java -javaagent:target/vev-dynamic-testing-0.0.1-SNAPSHOT.jar -jar target/vev-dynamic-testing-0.0.1-SNAPSHOT.jar