# 1. Формы представления информации

## Аналоговая (непрерывная) информация

- **Описание**: Представление с помощью аналоговой величины (аналога).
- **Характеристики**:
  - Бесконечное количество значений в ограниченном диапазоне.
  - Отсутствие разрывов между значениями.
  - Один сигнал пропорционален величине.
- **Примеры**:
  - Живописное полотно (непрерывное изменение цвета).
  - Виниловая пластинка (непрерывное изменение звуковой дорожки).

## Дискретная (цифровая) информация

- **Описание**: Представление с помощью набора напряжений, соответствующих цифрам.
- **Характеристики**:
  - Конечное количество значений.
  - Несколько сигналов, каждый из которых соответствует одной цифре.
- **Примеры**:
  - Изображение, напечатанное струйным принтером (точки разного цвета).
  - Аудиокомпакт-диск (дорожка с различной отражающей способностью).

## Способы представления дискретной информации

### Системы счисления в вычислительной технике

- **Двоичная система** (2СС)
  - Пример позиционной системы.
  - Применяется в ЭВМ.
  - Термины: бит, байт, слово.

- **Восьмеричная система** (8СС)
  - Упрощает запись и чтение чисел человеком.
  - Сокращает трудоёмкость ручной обработки кодов.

- **Десятичная система** (10СС)
  - Привычная для человека, но не оптимальная для ЭВМ.

- **Шестнадцатеричная система** (16СС)
  - Упрощает ручную обработку чисел.

- **Двоично-десятичная система** (2-10СС)
  - Используется в цифровых устройствах с десятичными индикаторами.
  - Десятичные цифры (0-9) представлены 4-разрядными двоичными комбинациями (0000-1001).
  - Две двоично-десятичные цифры составляют 1 байт (значения от 0 до 99).

# 2. Представление чисел с фиксированной точкой

## Прямой код

- **Описание**: Используется для представления целых двоичных чисел без знака.
- **Диапазон**: 
  - 16-разрядное слово: от (0000 0000 0000 0000)₂ = 0 до (1111 1111 1111 1111)₂ = 65535.
- **Особенности**:
  - Фиксированная запятая после младшего бита слова.
  - Применяется для целых положительных чисел и нуля.

## Числа со знаком

- **Описание**: Для представления положительных и отрицательных чисел.
- **Формат**:
  - Старший бит определяет знак числа: 0 - положительное, 1 - отрицательное.
- **Коды**:
  - **Прямой код**: Совпадает с двоичным представлением для положительных чисел.
  - **Обратный код**: Инвертирование прямого кода модуля числа для отрицательных чисел.
  - **Дополнительный код**: Обратный код плюс единица для отрицательных чисел.

## Дополнительный код

- **Преимущества**:
  - Упрощает операции сложения и вычитания.
  - Позволяет использовать одну и ту же схему сумматора для знаковых и беззнаковых чисел.

## Битовые признаки

- **Перенос (C - Carry)**:
  - Признак выхода за границы разрядной сетки для беззнаковых чисел.
  - Пример: 
```
    1000 0000 0000 0000
  + 1000 0000 0000 0000
  ---------------------
  1 0000 0000 0000 0000
```
  - Результат: 0 (перенос в 17-й разряд).

- **Переполнение (Overflow)**:
  - Признак переполнения разрядной сетки для знаковых чисел.
  - Разные знаки слагаемых или совпадение знаков слагаемых со знаком суммы указывают на корректность результата.
  - В противном случае - сигнал переполнения.

- **Отрицательный результат (N)**:
  - Признак отрицательного результата при знаковом представлении.
  - Выставляется, если в старшем разряде числа в дополнительном коде находится 1.

- **Нулевой результат (Z)**:
  - Признак нулевого результата.
  - Выставляется, если все разряды числа равны 0.

# 3. Представление символьных и строковых данных

## Кодовые таблицы

### ASCII (7-битная)
- **Порядковые номера**: 0-255
- **Коды символов**:
  - 0-31: `00000000` – `00011111` — Управляющие символы (управление выводом текста, подача звукового сигнала, разметка текста и т.п.).
  - 32-127: `00100000` – `01111111` — Строчные и прописные буквы латинского алфавита, десятичные цифры, знаки препинания, скобки, коммерческие и другие символы (Символ 32 – пробел).
  - 128-255: `10000000` – `11111111` — Кодовая страница для нелатинского алфавита.

### KOI-8 (8-битная)
- Восьмибитовая кодовая страница, совместимая с ASCII.
- Русские буквы находятся в верхней части таблицы, соответствуя фонетическим аналогам в английском алфавите из нижней части таблицы.
- Пример: "Русский Текст" → "rUSSKIJ tEKST".

### ISO-8859-5 (8-битная)
- 8-битная кодовая страница для кириллицы из семейства ISO-8859.
- Нижняя часть таблицы полностью соответствует ASCII.
- Недостаток: отсутствие символов (тире, кавычки-ёлочки, градус), поэтому в России почти не использовалась.

### Windows-1251 (8-битная)
- Стандартная 8-битная кодировка для русских версий Microsoft Windows до 10-й версии.
- Преимущества:
  - Наличие практически всех символов, используемых в русской типографике.
- Недостатки:
  - Код `0xFF` (255) для строчной буквы "я" совпадает со служебным символом в других кодировках.
  - Отсутствуют символы псевдографики, имеющиеся в CP866 и KOI8.

### UTF-8 (8-битная)
- 8-битная стандартизированная кодировка текста для хранения символов Unicode.
- Совместимость с ASCII: 7-битные символы отображаются как есть, остальные — как мусор.
- Преимущество: для текста, содержащего латинские буквы и простейшие знаки препинания, UTF-8 экономит объём по сравнению с UTF-16.
- Кодирование:
  - 1 байт: `0aaa aaaa` (7-битный символ ASCII).
  - 2 байта: `110x xxxx 10xx xxxx`.
  - 3 байта: `1110 xxxx 10xx xxxx 10xx xxxx`.
  - 4 байта: `1111 0xxx 10xx xxxx 10xx xxxx 10xx xxxx`.
  - 5 байт: `1111 10xx 10xx xxxx 10xx xxxx 10xx xxxx 10xx xxxx`.
  - 6 байт: `1111 110x 10xx xxxx 10xx xxxx 10xx xxxx 10xx xxxx 10xx xxxx`.

### UTF-16
- Символы кодируются двухбайтовыми словами с использованием всех возможных диапазонов значений (от `0000₁₆` до `FFFF₁₆`).
- Можно кодировать символы Unicode, сдвигая символ в 8-й диапазонах `0000₁₆...D7FF₁₆` и `E000₁₆...10FFFF₁₆`.
- Диапазон `D800₁₆...DFFF₁₆` используется для кодирования суррогатных пар.
- Символы Unicode до `FFFF₁₆` записываются как 16-битные слова.
- Символы в диапазоне `10000₁₆...10FFFF₁₆` (больше 16 бит) кодируются парой 16-битных слов.
- Старшие 10 бит (лидирующее слово), младшие 10 бит (последующее слово).
- Пример:
  - 1 байт: `0aaa aaaa`.
  - 2 байта: `110x xxxx 10xx xxxx`.
  - 3 байта: `1110 xxxx 10xx xxxx 10xx xxxx`.
  - 4 байта: `1111 0xxx 10xx xxxx 10xx xxxx 10xx xxxx`.

# 4. Базовые элементы вычислительной техники

## Ячейки памяти
- **Описание**: Минимальный адресуемый элемент запоминающего устройства.
- **Адресация**: Имеют адрес (порядковый номер), доступный для команд процессора.
- **Состояния**:
  - Заряженный/разряженный конденсатор.
  - Проводящее/непроводящее состояние транзистора.
- **Типы**:
  - **SRAM (static random access memory)**: Сохраняет состояние без регенерации, используется в кэшах.
  - **DRAM (dynamic random access memory)**: Требует постоянной подзарядки, используется для оперативной памяти.

## Регистры
- **Описание**: Память внутри процессора для хранения адресов и промежуточных результатов.
- **Характеристика**: Количество битов, которые могут храниться в регистре.

## Шины
- **Описание**: Электрическая цепь, соединяющая регистры и устройства ЭВМ.
- **Состав**: Параллельные провода для передачи данных, синхронизации и управления.
- **Типы**:
  - Однонаправленная.
  - Двунаправленная.

## Вентильные схемы
- **Описание**: Электронные ключевые схемы для управления потоком информации.
- **Функция**: Управляющий сигнал разрешает или запрещает прохождение данных.

## Тактовый генератор
- **Описание**: Устройство для генерации электрических импульсов заданной частоты.
- **Функция**: Синхронизация процессов передачи информации.

## Логические схемы
- **Описание**: Совокупность логических элементов для выполнения логических операций.
- **Пример**: Простое устройство для выполнения одной логической операции.

## Триггеры
- **Описание**: Устройства с двумя устойчивыми состояниями, переключаемыми внешними сигналами.
- **Типы**:
  - **RS-триггер**: Вход S устанавливает выход Q в единичное состояние, вход R сбрасывает Q в нулевое.
  - **D-триггер**: Хранение одного бита данных, объединенные входы установки и сброса.
  - **T-триггер**: Счетный триггер, изменяющий состояние при поступлении импульса.

## Счетчики
- **Описание**: Устройства для подсчета импульсов, выводящие двоичный код.
- **Основной параметр**: Модуль счета — максимальное число сигналов для подсчета.
- **Пример**: Четырехразрядный счетчик на T-триггерах.

## Сумматоры
- **Описание**: Устройства для преобразования информационных сигналов в сигнал, эквивалентный их сумме.
