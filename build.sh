#!/bin/bash

# ./build_test_project.sh
mvn clean install
mvn cobertura:cobertura
md5sum src/test/resources/Test1/target/classes/istic/fr/prog_test/Point.class