# Library app

1. Получение списка книг (настроил пагинацию по названию книги по возрастанию)
   http://localhost:8085/books
2. Получение информации по определенной книге
   http://localhost:8085/books/{id}
3. Добавление информации о книге
Post запрос
   http://localhost:8085/book
json:
{
"bookName": "Понедельник начинается в субботу",
"authors": [
{
"authorId": 333,
"authorFullName": "Аркадий Натанович Стругацкий"
},
{
"authorId": 444,
"authorFullName": "Борис Натанович Стругацкий"
}
]
}
4. Получение списка авторов (настроил пагинацию по имени автора по возрастанию)
   http://localhost:8085/authors
5. Поиск информации о книге в википедии
   http://localhost:8085/books/{id}/wiki
6. Сборка в jar - gradle bootJar. Запуск из консоли ./Library java -jar -Dfile.encoding=UTF-8 library.jar (без указания кодировки у меня она ломалась при запросах).
7. Сборка в Docker:
   docker build -t library . - создаем образ Docker
   docker-compose up -d - запускаем docker-compose (для работы с postgre)
