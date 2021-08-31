# sum-service
Demo project for center-for-enterprise-technologies  
Необходимо разработать веб-сервис на Java с использованием Gradle, Spring Boot, Hibernate, JUnit.

Сборка проекта:
    В качестве среды разработки рекомендуется использовать IntelliJ IDEA.
    Проект должен собираться при помощи Gradle с использованием самописной! команды installSumService, выполняющей сборку сервиса в jar-файл с именем sum_service.jar
    Сервис должен запускаться следующим образом:
        java -jar sum_service.jar

Структура БД:
    Единственная таблица, содержащая 2 столбца - столбец для хранения строк и столбец для хранения целых чисел.

API сервиса:
    Все запросы являются POST-запросами.
    Данные в запросе и ответе представлены в JSON-формате.
    Каждый ответ содержит поля code и description - код ответа и текстовое описание кода ответа.
    Необходимо предусмотреть обработку ошибок (например, попытка добавить значение с именем, которое уже есть в базе) и добавить дополнительные коды ответа с описанием для таких ситуаций.

1) Добавление пары имя-значение в базу.
    Relative URL:
        /add
    Request:
        {
            "name": "test",
            "value": 1
        }
    Response:
        {
            "code": 0,
            "description": "OK"
        }

2) Удаление пары имя-значение из базы по имени
    Relative URL:
        /remove
    Request:
        {
            "name": "test"
        }
    Response:
        {
            "code": 0,
            "description": "OK"
        }

3) Получение суммы двух чисел, идентифицируемых их именами
    Relative URL:
        /sum
    Request:
        {
            "first": "test",
            "second": "test"
        }
    Response:
        {
            "sum": 2,
            "code": 0,
            "description": "OK"
        }

Тестирование:
    Необходимо покрыть код тестами с использованием JUnit.
