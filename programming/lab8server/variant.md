# Лаба 8 `5910148`

Доработать программу из лабораторной работы №7 следующим образом:

Заменить консольный клиент на клиент с графическим интерфейсом пользователя (GUI).
В функционал клиента должно входить:

    Окно с авторизацией/регистрацией.
    Отображение текущего пользователя.
    Таблица, отображающая все объекты из коллекции
        Каждое поле объекта - отдельная колонка таблицы.
        Строки таблицы можно фильтровать/сортировать по значениям любой из колонок. Сортировку и фильтрацию значений столбцов реализовать с помощью Streams API.
    Поддержка всех команд из предыдущих лабораторных работ.
    Область, визуализирующую объекты коллекции
        Объекты должны быть нарисованы с помощью графических примитивов с использованием Graphics, Canvas или аналогичных средств графической библиотеки.
        При визуализации использовать данные о координатах и размерах объекта.
        Объекты от разных пользователей должны быть нарисованы разными цветами.
        При нажатии на объект должна выводиться информация об этом объекте.
        При добавлении/удалении/изменении объекта, он должен автоматически появиться/исчезнуть/измениться  на области как владельца, так и всех других клиентов. 
        При отрисовке объекта должна воспроизводиться согласованная с преподавателем анимация.
    Возможность редактирования отдельных полей любого из объектов (принадлежащего пользователю). Переход к редактированию объекта возможен из таблицы с общим списком объектов и из области с визуализацией объекта.
    Возможность удаления выбранного объекта (даже если команды remove ранее не было).


Вопросы к защите лабораторной работы:

1. Компоненты пользовательского интерфейса. Иерархия компонентов.
2. Базовые классы Component, Container, JComponent.
3. Менеджеры компоновки.
4. Модель обработки событий. Класс-слушатель и класс-событие.
5. Технология JavaFX. Особенности архитектуры, отличия от AWT / Swing.
6. Интернационализация. Локализация. Хранение локализованных ресурсов.
7. Форматирование локализованных числовых данных, текста, даты и времени. Классы NumberFormat, DateFormat, MessageFormat, ChoiceFormat.