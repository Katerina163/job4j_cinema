<h3 align="center">В проекте реализован сайт кинотеатра.</h3>

<p align="justify">Для хранения данных использован язык 
  <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/7003f462-f182-412a-9761-cc8578ddaa74" width="20"/>
  SQL, все скрипты для создания/заполнения таблиц сохранены в db/scripts, которые можно запустить через 
  <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/15421558-c247-4238-8ee5-a4c9cb8d4277" width="20"/>
  Liquibase, чьи настройки лежат в db.
Архитектура разбита на слои: repository – для работы с базой данных,  services – для бизнес логики и controllers для работы с клиентами; model описывает доменные объекты, dto предназначен для передачи данных между слоями контроллеров и сервисов, filter реализует запрет неавторизированным пользователям покупать билеты.</p>

<p align="justify">Для реализации серверной части приложения использованы <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/fa6eb85d-4d90-46fc-a993-064d59850668" width="20"/>
 Spring Boot, 
  <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/ddee7429-12b7-4247-a456-63d344e2fc7c" width="20"/>
  Liquibase, 
  <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/15421558-c247-4238-8ee5-a4c9cb8d4277" width="20"/>
  Sql2o,
  <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/d6fb7e80-d4e8-407e-978d-d849f074ec9f" width="20"/>
  PostgreSQL. Пользовательская часть использует 
  <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/e6d840f6-bd4e-42d2-a99c-36a61286b90c" width="20"/>
  Thymeleaf, 
  <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/07e9c770-b2c9-4512-8512-118ee727662b" width="20"/>
  Bootstrap.</p>

<h3>Внешний вид страниц:</h3>
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

<h3>Сборка</h3>
<div>
Осуществляется через <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/297cfa7e-e8c8-4a73-bb04-5eaec7d913e2" width="20"> Maven
</div>
  
<h3>Дальнейшее развитие проекта</h3>
<ul>
  <li>Переделать белый фон на что-то красивое</li> 
  <li>Добавить сборку через <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/6a463093-7eec-45b1-a0f8-c11c7232ece1" width="20"> Docker</li>
  <li>Дописать тесты</li>
  <li>Написать JavaDoc</li>  
</ul>

<h3>Контакты</h3> 
В случае возникновения вопросов (и предложений) по проекту, свяжитесь со мной через <img src="https://github.com/Katerina163/job4j_cinema/assets/79073032/e98c124c-0379-475b-b6f1-8ad2d9b8bcc4" width="20"> Telegram:
<div>
<a href="https://t.me/kat163n" target="_blank">
  @kat163n
</a>
</div>
