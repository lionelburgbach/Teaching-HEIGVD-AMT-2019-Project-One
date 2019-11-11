#!/bin/bash
docker-compose down
docker rmi $(docker images -f "dangling=true" -q)
docker rmi topologies_db
docker rmi topologies_wildfly
