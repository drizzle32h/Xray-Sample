FROM tomcat:8-jdk8-openjdk
RUN apt-get update && apt-get install apt-file -y && apt-file update && apt-get install vim -y && apt-get install telnet -y 
RUN rm -f /usr/local/tomcat/webapps/ROOT
COPY target/ROOT.war /usr/local/tomcat/webapps
ENV JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"
EXPOSE 8080
ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh", "run"]
