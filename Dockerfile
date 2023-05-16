FROM openjdk:17-jdk
ARG JAR_FILE=SpringMVCAssignment/target/*.jar
ARG YML_FILE=SpringMVCAssignment/src/main/resources/application.yml
COPY ${JAR_FILE} app.jar
COPY ${YML_FILE} application.properties
EXPOSE 1086
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=?Mfptnj123@hview2022
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3307/budgetDb?useUnicode=true&characterEncoding=UTF-8
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application","-Dspring.config.location=/","-jar","/app.jar"]