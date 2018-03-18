# Site-traffic-statistic
REST-сервис для сбора статистики посещаемости сайта

# Инструкция по сборке и запуску (Linux).

### Для запуска необходимо:
* Maven
* Redis
* PostgreSql

### Подключение к СУБД
Параметры подключения находятся в /src/main/resources/application.properties

По дефолту:
1. <b>PostgreSql</b> 
- Username=dz-test
- Password=test
- Db=test

2. <b>Redis</b> 
- Host=localhost
- Port=6379
- без пароля

### Создание базы данных и пользователя Postgres 
```
sudo su - postgres
createuser dz-test --pwprompt
createdb test --owner dz-test
```

## Сборка и запуск

```
git clone https://github.com/TheFreaky/Site-traffic-statistic.git
cd Site-traffic-statistic/
mvn clean package
cd target/
java -jar test-task-0.1.jar
```

## API

#### Создание события посещения сайта пользователем

Request: POST "/visit?userId={userId}&pageId={pageId}"

* userId - идентификатор пользователя
* pageId - идентификатор страницы

Response: 
```
{
  "visitsCount" : количество посещений за текущие сутки,
  "uniqueUserCount" : количество уникальных пользователей за текущие сутки
}
```

#### Получение статистики за произвольный период

Request: GET "/stats?start={start}&end={end}"

* start - начало периода в формате yyyy-MM-dd
* end - конец периода в формате yyyy-MM-dd

Response: 
```
{
  "visitsCount" : количество посещений за данный период,
  "uniqueUserCount" : количество уникальных пользователей за данный период,
  "regularUserCount" : количество постоянных пользователей за данный период
}
```
