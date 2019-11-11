#!/bin/bash

mvn clean package
mv target/projectOne.war docker/dockerTrail/images/wildfly/src/