FROM openjdk:11
EXPOSE 8080
VOLUME /db
ADD /db/Articles.mv.db /db/Articles.mv.db
ADD target/news-portal-0.0.1-SNAPSHOT.jar news-portal-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/news-portal-0.0.1-SNAPSHOT.jar"]

