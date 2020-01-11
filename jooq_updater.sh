#!/usr/bin/env bash

# Fix relative file names
cd $(dirname $0)
# Run JOOQ generator using Exec Maven Plugin
mvn exec:java -f jooq-pom.xml