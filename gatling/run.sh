#!/bin/sh
USER_ARGS="-Dsomething=$1"
COMPILATION_CLASSPATH=`find -L ./target -maxdepth 1 -name "*.jar" -type f -exec printf :{} ';'`
java $USER_ARGS -cp $COMPILATION_CLASSPATH io.gatling.app.Gatling -s CustomerRequestSimulation


:./target/gatling-load-testing-3.8.4-jar-with-dependencies.jar:./target/gatling-load-testing-3.8.4.jar

java -Dconfig.resource=myConf.conf -jar the-jar-i-created.jar -s org.comp.pckg.DemoSimulation
