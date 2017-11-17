

VEV - Dynamic testing
=====================

- Pierre Gaudichon
- Timothée Merlet-Thomazeau
- Ahmed


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
- [Tuto (modify classFile)](https://jboss-javassist.github.io/javassist/tutorial/tutorial3.html#intro)


## Build

    mvn clean install


## Plan

- Compiler le projet ressource
  + projet maven
  + compiler le projet programmatiquement ? -> pour l'instant script bash
  + exécuter les tests programmatiquement ? -> fait
- Ajouter des logs a chaque endroit important
  + branche, class, methode, ... -> que debut methode
  + Noter précisement l'endroit des logs.
- Lire les logs pour générer un rapport
  + code coverage (par projet, classe, méthode)
  + Compter le nombre d'éxecution de chaque ligne de code


## javac

    ./build_test_project.sh


## Problème

- https://stackoverflow.com/questions/12533588/changing-part-of-method-body-with-javassist
- https://jboss-javassist.github.io/javassist/tutorial/tutorial3.html#intro