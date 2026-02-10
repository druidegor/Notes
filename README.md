# NotesApp

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat\&logo=android\&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat\&logo=kotlin\&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-brightgreen)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean-orange)

NotesApp — приложение для создания и хранения заметок с локальным сохранением данных.
Проект реализован с использованием современного Android-стека и архитектурного подхода MVVM + Clean Architecture.

---

## Функциональность

* Создание заметок
* Редактирование существующих заметок
* Удаление заметок
* Локальное хранение данных
* Сохранение изображений во внутреннее хранилище
* Автоматическое обновление списка при изменениях

---

## Архитектура

Проект построен с разделением на слои:

* Presentation — UI на Jetpack Compose + ViewModel
* Domain — бизнес-логика и use cases
* Data — работа с базой данных и репозитории

Такой подход упрощает масштабирование и поддержку кода.

---

## Технологии

* Kotlin
* Jetpack Compose
* MVVM
* Clean Architecture
* Hilt (Dependency Injection)
* Room (локальная база данных)
* Coroutines + Flow
* Coil (загрузка изображений)
* Splash Screen API
* Internal Storage (сохранение изображений)

---

## Запуск проекта

1. Клонировать репозиторий:

```
git clone https://github.com/druidegor/Notes.git
```

2. Открыть проект в Android Studio
3. Собрать и запустить на эмуляторе или устройстве

---

## Особенности реализации

* Dependency Injection через Hilt
* Реактивное обновление UI через Flow
* Сохранение изображений заметок во внутреннее хранилище
* Загрузка изображений с помощью Coil
* Чёткое разделение слоёв (data / domain / presentation)
* Простая и понятная структура проекта
