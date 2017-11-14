

VEV - Dynamic testing
=====================

- Pierre Gaudichon
- Timothée Merlet-Thomazeau


## Deadlines

- November the 22nd 23:59 : Mi session deadline
- November the 29th 23:59 : Mostly finished deadline
- December the 22nd 23:59 : Final dealine


## Links

- [Sujet](https://github.com/Software-Testing/Project-2017-2018)
- [Javassist tutorial](http://jboss-javassist.github.io/javassist/tutorial/tutorial.html)
- [Github](https://github.com/PierreGaudichon/vev-dynamic-testing)

- [Tuto (prof)](http://www.tomsquest.com/blog/2014/01/intro-java-agent-and-bytecode-manipulation/)
- [Tuto (transform)](http://blog.xebia.fr/2008/05/02/java-agent-instrumentez-vos-classes/)


## Build

    mvn clean install
    java -javaagent:target/vev-dynamic-testing-0.0.1-SNAPSHOT.jar -jar target/vev-dynamic-testing-0.0.1-SNAPSHOT.jar
    

## Plan

- Compiler le projet ressource
  + projet maven  
  + hors de java
- Ajouter des logs a chaque endroit important
  + branche, class, methode, ...
  + Noter précisement l'endroit des logs.
- Lire les logs pour générer un rapport
  + code coverage (par projet, classe, méthode)
  + Compter le nombre d'éxecution de chaque ligne de code


## javac

javac -cp junit-4.4.jar PointTest.java Point.java
avoir Junit-4.4.jar dans le dossier
