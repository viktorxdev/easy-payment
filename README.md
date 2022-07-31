# easy-payment
Сервис работает в связке с микросервисом https://github.com/viktorxdev/easy-backend

Для локального запуска необходимо поднять два докер-контейнера: 
1) для запуска кафки:
- скопируйте и сохраните приведенный ниже текс в docker-compose.yml файл 

```
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.0.1
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  broker:
    image: confluentinc/cp-kafka:7.0.1
    container_name: broker
    ports:
    # To learn about configuring Kafka for access across networks see
    # https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/
      - "9092:9092"
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_INTERNAL://broker:29092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
 ```      
- из директории с сохранненым файлом выполните команду ```docker-compose up -d```
2) для запуска бд постгре выполните команды:
- ```docker pull postgres```
- ```docker run --name easy_payment -p 5432:5432 -e POSTGRES_USER=easy -e POSTGRES_PASSWORD=easy -d postgres```


ВНИМАНИЕ! сервис работает на порту 10101, если нужен другой, укажите нужный в application.yaml в свойстве server.port,
либо запустите проект командой с указанием порта ```./gradlew bootRun --args='--server.port=ВАШ ПОРТ'```

Проверить работоспособность можно с помощью запроса
```
POST localhost:10101/api/v1/pay
{
    "accountId": 1,
    "amount": "1.1"
}
```

Для удобства локального запуска все пароли прописаны в application.yaml, не повторяйте такое на проде!

  
