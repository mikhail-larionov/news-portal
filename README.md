Для запуска потребуется:
- maven версии 3.8.3
- java 11
- docker

Запуск:
Переходим в корневую папку проекта, 
вводим в консоль команду:
mvnw source:jar
далее:
docker build -t server:1.0 .

далее:

docker run -p 9090:8080 server:1.0  
