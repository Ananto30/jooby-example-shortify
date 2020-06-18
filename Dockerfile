FROM gradle:5.6.0-jdk8 as build
WORKDIR /shortify
#COPY build.gradle build.gradle
#COPY settings.gradle settings.gradle
COPY *.gradle gradle.* gradlew ./
COPY gradle ./gradle
RUN ./gradlew --version

COPY src src
COPY conf conf
RUN ./gradlew --no-daemon shadowJar

FROM openjdk:8-jdk-slim
WORKDIR /shortify
COPY --from=build /shortify/build/libs/shortify-1.0.0-all.jar app.jar
#COPY /build/libs/shortify-1.0.0-all.jar app.jar
COPY conf conf
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
