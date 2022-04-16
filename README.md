# MPAI Store COAP Server

## Resources
- test
- large
- config/aif/demo

## Compile
Before to compile this project, compile also the dependency: https://github.com/PelionIoT/java-coap 
`mvn clean install -DskipTests`

## Run
`mvn spring-boot:run -Dspring-boot.run.arguments="--mpai.store.host=$IP_ADDRESS"`

or

`java -Dmpai.store.host=$IP_ADDRESS -jar coap-server-0.0.1-SNAPSHOT.jar`

