

VEV - Dynamic testing
=====================

- Pierre Gaudichon
- Timothée Merlet-Thomazeau
- Ahmed Nomane


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

Il faut recompiler le projet de teste avant chaque lancement du programme :

    ./build_test_project.sh

Lancer le projet avec IntelliJ (la compilation avec maven ne marche pas).
    
Lancer les tests du projet (sans lancer les tests du projet ressource) :

    mvn clean -Dtest=AppTest test


## Plan

- Compiler le projet ressource
  + projet maven -> Ok, prototype
  + compiler le projet programmatiquement ? -> pour l'instant script bash
  + exécuter les tests programmatiquement ? -> fait
- Ajouter des logs a chaque endroit important
  + method -> fait
  + block -> *en cours*
  + constructor -> fait
  + Faire un meilleur logger, pas System.out.println -> Fait
  + Noter précisement l'endroit des logs.
- Lire les logs pour générer un rapport
  + code coverage (par projet, classe, méthode)
  + Compter le nombre d'éxecution de chaque ligne de code


## Problème

- https://stackoverflow.com/questions/12533588/changing-part-of-method-body-with-javassist
- https://jboss-javassist.github.io/javassist/tutorial/tutorial3.html#intro


## Rapport

A report describing the functionalities of your tool, what problem does it solve, and both the relevence of the solution and its accuracy. A section about how you assessed this accuracy and test your tool overall is expected. You should also mention the problems you have encontered during the developpment and how you solved them. Guidelines concerning this report can be [can be found here](http://people.rennes.inria.fr/Benoit.Baudry/wp-content/uploads/2013/09/Instructions_for_report.pdf)

(voir Instruction_for_report.pdf)

[Google Drive](https://docs.google.com/document/d/1Eq1y1_AFh_T2HYzo27jMtxkTGtQECQDQ8dcQJHb8dvY/edit?usp=sharing)
(que en lecture, pas d'édit sur un repo github publique, envoyer un message pour avoir un accès en écriture)


## TODO

- Rapport
  - liste d'intro, vérifier si tjr a jours
  - finir intro (pt 3 et 4 du sujet)