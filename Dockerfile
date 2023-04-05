FROM openjdk:17

EXPOSE 8080
ADD target/bumbleBee.war bumbleBee.war
ENTRYPOINT ["java","-war","/bumbleBee.war"]

ARG APP_NAME=bumbleBee
ARG APP_VERSION=0.0.7