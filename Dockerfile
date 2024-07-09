FROM maven as builder
WORKDIR /build
COPY mvnw pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests
FROM eclipse-temurin:21-jdk-alpine
RUN addgroup --system spring-news-group && adduser --system --ingroup spring-news-group spring-news
USER spring-news:spring-news-group
WORKDIR news
EXPOSE 3003
COPY --from=builder /build/target/*.jar /news/app.jar
ENTRYPOINT ["java","-jar","/news/app.jar"]