XMPP PING (Java)
======

Small application create to check connectivity between two components XMPP. 

## Pre Requirements

Install Java
```bash
apt-get install openjdk-8-jdk
```

Install maven
```bash
apt-get install maven
```
Install git
```bash
apt-get install git
```

## Instalation
```bash
cd xmpp-ping-java
mvn install
```
## Configuration

Create the "configuration.properties" using the example "configuration.properties.example"
configuration.properties
```
xmpp_own_component_jid=
xmpp_own_component_password=
xmpp_server_ip=
xmpp_server_port=

xmpp_component_to_ping=

period_to_ping=
```
Create the "log4j.conf" using the example "log4j.conf.example"
log4j.conf
```
log4j.appender.file.File=xmpp-ping.log
```

## Run
```
cd {xmpp-ping-java}/bin
bash start-ping
```

## Check
Open the file log and verify if the ping is ok or not. 
