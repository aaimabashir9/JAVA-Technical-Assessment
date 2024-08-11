FROM gradle:8-alpine as build
WORKDIR /payment
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src
RUN gradle bootJar

FROM amazoncorretto:21-alpine as package
WORKDIR /payment
COPY --from=build /payment/build/libs/payment-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
