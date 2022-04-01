# backend/Dockerfile
# 사용한 openjdk 버전에 맞는 값을 입력해주세요.
FROM openjdk:8-jdk-alpine

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src


RUN chmod +x ./gradlew
RUN ./gradlew build

ARG JAR_FILE=target/*.jar 

# jar 파일 경로는 직접 입력해주세요.
COPY ${JAR_FILE} app.jar


# 배포용 properties 실행 명령어
ENTRYPOINT ["java","-jar","app.jar","--spring.config.name=application"]


# 만약 배포용 properties를 사용하지 않는다면
# Default properties 실행 명령어
# ENTRYPOINT ["java","-jar","app.jar"]
