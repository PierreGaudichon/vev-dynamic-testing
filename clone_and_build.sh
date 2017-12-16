#!/usr/bin/env bash

# Creates the example folder.
mkdir examples
cd examples


# Clone all the repo we want to test.
git clone https://github.com/apache/commons-cli.git


# Build all project (should be maven projects).
for project in * ; do
    ( cd $project && mvn install )
done
