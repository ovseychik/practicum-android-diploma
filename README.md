# Проект Get a job!

##### приложение для поиска вакансий в использованием Head Hunter API.
-----

#### для связи telegram: @koritin84

-----

#### совместимость: Android 8.0 (min SDK 26)

-----

#### Версия языка kotlin.android' version '1.9.21''
Gradle JDK version '17.0.9'
#### Зависимости: Room, coroutines, viewpager2, fragment, glide, gson, retrofit2,

-----
### Технологии:
Git, XML, JSON, Kotlin, Room, MVVM, Android SDK, Single Activity, Fragments,  Retrofit2, ConstraintLayout  RecyclerView, SharedPreferences, Permissions, Gradle, Coroutines, Flow,  LiveData Jetpack Navigation Component, Koin, Glide

-----
### Инструкция по установке

1. Откройте Android studio
2. Нажмите кнопку "Get from VCS"
3. В поле "URL:" вставте ссылку [github](https://github.com/AlexanderKorytin/practicum-android-diploma.git) на этот проект и нажмите "clone"
4. при необходимости скачайте нужную версию Gradle JDK

### Инструкция по эксплуатации

1 При запуске приложения откроется экран показанный на следующем изображении

![Screenshot_20240319-200705](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/7322daf3-cef5-4dbc-a841-21f610aa4002)

Здесь можно ввести запрос с названием вакансии и спустя некоторое время будет выполнен поисковый запрос и в результате на экране отобразиться список вакансий (см. изображение ниже) или сообщение об ошибке, нажатию иконки настроек в верхнем правом углу перейти на экран настроек (см п. 2)


![Screenshot_20240319-200921](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/51c662ee-9f0b-448a-ace6-985d5c3503d4)

по нажатию на вакансию из списка будет осуществлен переход на экран деталей вакансий (см п. 3), а по длительному нажатию вакансия будет добавлена в список избранных

2 Экраны настроек 

![Screenshot_20240319-200735](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/e7907f55-4307-43bf-b695-20bc86c3f875) пустые ![Screenshot_20240319-200854](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/cba9b6af-7a0d-4e4c-bcb5-c3e03fa9da54) заполненые  ![Screenshot_20240319-200753](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/8dfd7992-1931-4c9f-8596-a0bf1e5f5b53) выбор места работы ![Screenshot_20240319-200804](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/b657712f-62f8-49b8-ba09-2cdac156a675) выбор региона ![Screenshot_20240319-200758](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/46b5beb8-bfee-471a-b88a-47a61619d80a) выбор страны ![Screenshot_20240319-200742](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/96fc4df6-eed8-43d4-bedc-7969064f6535) выбор отрасли

На данных экранах можно выбрать:

- место работы (страна, регион) - при выборе региона страна выставляется автоматически (если не была выбрана ранее)

- отрасль

- ввести требуемый уровень з/п

- выбрать опцию не показывать без зарплаты

По нажатию кнопки применить - будет осуществлен возврат на экран поиска и новые настройки применяться сразу к не пустому зпросу. По возврату на экран поиска по back настройки будут применены только к следующему запросу. Нажатие кнопки очистить - осчистит настройки.

3 Экран деталей вакансии

Для отобранения детале сначала осуществляется запрос в сеть и при ошибки сети пповеряется - находиться ли вакансия в списке избраннхых и если находиться - детали получаются из локальной базы данных.

![Screenshot_20240319-203125](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/ce3accee-2469-4dae-ae9a-dd263492c57b)

Здесь можно просматреть детали выбранной вакансии, позвонить по указанному номеру телефона, написать на почту, добавить вакансию в список избранных.

4. Экран избранного

![Screenshot_20240319-200717](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/880f8cf0-574d-4209-a855-f86d6190cc5d)

Здесь по нажатию на вакансию будет осуществлен переход на экран деталей (см п. 3), а по длительному нажатию будет вызван диалог с предложением удалить вакансию из списка избранных.

   
6. Экран о команде

![Screenshot_20240319-200724](https://github.com/AlexanderKorytin/practicum-android-diploma/assets/124441554/a1ce6160-9ed4-41f7-8b5f-c4fe83fdaf12)


