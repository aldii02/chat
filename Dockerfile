FROM openjdk:11
EXPOSE 8080
ADD target/chat.jar chat.jar
ENTRYPOINT ["java","-jar","/chat.jar"]
ENV TZ="Asia/Almaty"


