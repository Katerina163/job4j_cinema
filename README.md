<h3 align="center">В проекте реализован сайт кинотеатра.</h3>

<p align="justify">Для хранения данных использован язык SQL, все скрипты для создания/заполнения таблиц сохранены в db/scripts, которые можно запустить через Liquibase, чьи настройки лежат в db.
Архитектура разбита на слои: repository – для работы с базой данных,  services – для бизнес логики и controllers для работы с клиентами; model описывает доменные объекты, dto предназначен для передачи данных между слоями контроллеров и сервисов, filter реализует запрет неавторизированным пользователям покупать билеты.</p>

<p align="justify">Для реализации серверной части приложения использованы Spring Boot, Liquibase, Sql2o, PostgreSQL. Пользовательская часть использует Thymeleaf, Bootstrap.</p>

<h2 align="right">Внешний вид страниц:</h2>
1. Главная страница с плывущими облаками
<img src="https://user-images.githubusercontent.com/79073032/233982428-ea17fa1d-48b8-4bf9-b039-44a09d549cb4.png" width="450"/>
2. Расписание фильмов
<img src="https://user-images.githubusercontent.com/79073032/233983150-694342aa-88ac-466f-b332-ed5862291396.png" width="450"/>
3. Постеры с фильмами, находящимися в прокате
<img src="https://user-images.githubusercontent.com/79073032/233983419-b237044a-70e1-4fa5-935d-656ecadbd929.png" width="450"/>
4. Страница регистрации
<img src="https://user-images.githubusercontent.com/79073032/233983696-e36e8c48-314f-46de-b893-10ad812a5b8f.png" width="450"/>
5. Страница авторизации
<img src="https://user-images.githubusercontent.com/79073032/233983784-a4c1a89c-18dd-4906-9d38-ca1e2b30cb77.png" width="450"/>
Для зарегистрированных и авторизированных пользователей выведено имя в левом углу (вместо страницы регистрации)
<img src="https://user-images.githubusercontent.com/79073032/233984161-ad34b72a-8dfe-4a74-97f4-1164dbd4b635.png" width="450"/>
6. Страница покупки билета, где для выбора ряда и места служат выпадающие списки.
<img src="https://user-images.githubusercontent.com/79073032/233984430-0860eda3-13c7-4a0f-b923-6bcc005be8fe.png" width="450"/>
7. Страница успешной покупки билета. На данную страницу (и страницу выбора билета) могут попасть только зарегистрированные пользователи.
<img src="https://user-images.githubusercontent.com/79073032/233984626-cbe93e46-b498-4f49-8379-f9786b9050fa.png" width="450"/>
8. Страница с ошибками (например, если пользователь захотел купить уже  купленный билет)
<img src="https://user-images.githubusercontent.com/79073032/233985146-95dca195-3645-43a0-9d9d-612db68e347a.png" width="450"/>
