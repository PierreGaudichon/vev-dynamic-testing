#!/usr/bin/env bash

cd src/test/resources/Test1/
mvn clean install
md5sum target/classes/istic/fr/prog_test/Point.class