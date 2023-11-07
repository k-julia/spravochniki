Крапивина Юлия Игоревна, 2023 год, 4 курс, 4 группа

***
### Информация о справочниках
 Проект содержит 2 справочника: сотрудников (employee) и проектов компании (project).

  **Справочник сотрудников содержит:**
- проект, над которым работает сотрудник 
- имя сотрудника (текст)
- должность (текст)
- дата принятия на работу (дата)
- зарплата (число с фикс. запятой)


  **Справочник проектов содержит:**
- название проекта (текст)
- дата начала проекта (дата)
- бюджет проекта (целое число)

***

### Схема создания баз данных

``` postgresql
create table project
(
    id         serial primary key,
    name       varchar(100),
    start_time date,
    budget     int
);

create table employee (
    id          serial primary key,
    name        varchar(100),
    position    varchar(100),
    project_id  int,
    hiring_time date,
    salary      double precision,
    constraint fk_project
        foreign key (project_id)
            references project (id)
            on delete set null
);
```

***

### Пример заполнения баз данных

``` postgresql
insert into project (name, start_time, budget)
values ('Фитнес приложение', date('10-01-2023'), 200000);
insert into project (name, start_time, budget)
values ('Сервис онлайн покупок', date('30-10-2022'), 3000000);
insert into project (name, start_time, budget)
values ('Система для автоматизации заказов', date('25-05-2022'), 1000000);

insert into employee (name, position, project_id, hiring_time, salary)
values ('Пупкин Никита Сергеевич', 'старший разработчик', 2, date('20-05-2020'), 2000);
insert into employee (name, position, project_id, hiring_time, salary)
values ('Василюк Анастасия Витальевна', 'менеджер по работе с клиентами', 1, date('01-10-2021'), 1507.6);
insert into employee (name, position, project_id, hiring_time, salary)
values ('Анисимов Алексей Юрьевич', 'младший разработчик', 2, date('17-09-2022'), 1050.5);
```

*** 

### Инструкция по запуску и использованию

Выставить свои локальные значения следующим переменным в application.properties:
``` properties
spring.datasource.url=your value
spring.datasource.username=your value
spring.datasource.password=your value
```

Порт приложения 8080

Чтобы сортировать значения таблицы по колонке, нажмите на название колонки