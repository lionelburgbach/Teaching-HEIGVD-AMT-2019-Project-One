FROM jboss/wildfly:17.0.1.Final

MAINTAINER Lionel Burgbacher <lionel.burgbacher@heig-vd.ch>

COPY src/projectOne.war /opt/jboss/wildfly/standalone/deployments
COPY src/mysql-connector-java-5.1.48-bin.jar /opt/jboss/wildfly/standalone/deployments
COPY src/standalone.xml /opt/jboss/wildfly/standalone/configuration/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

