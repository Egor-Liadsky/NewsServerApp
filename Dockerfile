FROM openjdk:11
EXPOSE 8081:8081
RUN mkdir /app
COPY ./build/libs/*-all.jar /app/NewsApp-0.0.1.jar
ENTRYPOINT ["java","-jar","/app/NewsApp-0.0.1.jar"]