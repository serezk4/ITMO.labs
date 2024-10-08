**Вопросы**

1. **Какую оболочку используете вы?**

   Я использую оболочку **bash** (Bourne Again Shell).

2. **Как поменять оболочку, которая используется по умолчанию?**

   Вы можете изменить оболочку по умолчанию с помощью команды `chsh` (change shell):

   ```bash
   chsh -s /bin/bash
   ```

   После этого потребуется ввести ваш пароль. Изменения вступят в силу после следующего входа в систему.

3. **Чем особенна оболочка `sh` и почему мы редко её используем напрямую?**

   Оболочка `sh` (Bourne Shell) является стандартной оболочкой UNIX и предоставляет базовый функционал без многих современных расширений. Мы редко используем её напрямую, потому что современные оболочки, такие как `bash` или `zsh`, предлагают больше возможностей и удобств для интерактивной работы и написания скриптов.

4. **Для каких целей сейчас в основном используется `sh`?**

   `sh` в основном используется для написания скриптов, которые должны быть максимально совместимы между различными UNIX-системами. Она обеспечивает наибольшую портативность скриптов.

5. **Как вывести результат выполнения команды в файл?**

   Вы можете перенаправить стандартный вывод команды в файл с помощью символа `>`:

   ```bash
   команда > файл.txt
   ```

   Чтобы добавить вывод в конец файла без перезаписи, используйте `>>`:

   ```bash
   команда >> файл.txt
   ```

6. **Что такое дерево процессов?**

   Дерево процессов — это иерархическая структура всех процессов, запущенных в системе. Каждый процесс может иметь дочерние процессы, порожденные им, и эта структура отображает отношения между ними.

7. **Что такое `stdin`, `stdout`, `stderr`?**

   - **`stdin`** (Standard Input) — стандартный поток ввода, по умолчанию принимает данные с клавиатуры.
   - **`stdout`** (Standard Output) — стандартный поток вывода, по умолчанию выводит данные на экран.
   - **`stderr`** (Standard Error) — стандартный поток ошибок, используется для вывода сообщений об ошибках, по умолчанию выводит данные на экран.

8. **Как соединить выход одной программы со входом другой?**

   Используя конвейер (pipe) `|`:

   ```bash
   команда1 | команда2
   ```

   Вывод `команда1` передается на вход `команда2`.

9. **Когда целесообразно использовать `stdout`, а когда — `stderr`?**

   - **`stdout`** следует использовать для вывода обычных результатов работы программы.
   - **`stderr`** используется для вывода сообщений об ошибках или предупреждений.

   Это позволяет отделить нормальный вывод от сообщений об ошибках, что удобно при перенаправлении потоков.

---

### Задание 0.5

Используя регулярные выражения и команду `grep`, выполните следующие действия над файлом `test`.

**Содержимое файла `test`:**

```text
a
hello
world
123
0x1F
abc
0xABcd

```

1. **Найти строчки строго из одного (любого) символа.**

   ```bash
   grep -E '^.$' test
   ```

   **Объяснение:**

   - `^` — начало строки.
   - `.` — любой один символ.
   - `$` — конец строки.
   - Ключ `-E` используется для включения расширенных регулярных выражений.

2. **Найти строчки, в которых есть как минимум одна цифра.**

   ```bash
   grep '[0-9]' test
   ```

   **Объяснение:**

   - `[0-9]` — любой символ от 0 до 9.

3. **Найти строчки, являющиеся числами в шестнадцатеричном формате C, например `0x1F`, `0xABcd`.**

   ```bash
   grep -E '^0x[0-9a-fA-F]+$' test
   ```

   **Объяснение:**

   - `^0x` — строка начинается с `0x`.
   - `[0-9a-fA-F]+` — один или более шестнадцатеричных символов.
   - `$` — конец строки.

4. **Найти строчки, где есть слово из трёх букв.**

   ```bash
   grep -E '\b[a-zA-Z]{3}\b' test
   ```

   **Объяснение:**

   - `\b` — граница слова.
   - `[a-zA-Z]{3}` — три буквы (независимо от регистра).

5. **Найти пустые строчки.**

   ```bash
   grep '^$' test
   ```

   **Объяснение:**

   - Строка начинается и сразу же заканчивается, то есть она пустая.

6. **Инвертировать результаты поиска `grep` с использованием ключей.**

   Например, чтобы найти все строки, **не** содержащие слово `hello`:

   ```bash
   grep -v 'hello' test
   ```

   **Объяснение:**

   - Ключ `-v` инвертирует результат поиска, выводя строки, не соответствующие шаблону.

---

**Вопросы**

1. **Наследуются ли переменные окружения в дочерних процессах? Если да, то все ли?**

   Да, переменные окружения наследуются всеми дочерними процессами. Однако наследуются только те переменные, которые были экспортированы с помощью команды `export`.

2. **Зачем нужен `export` при запуске программ?**

   Команда `export` делает переменную доступной для дочерних процессов. Без `export` переменная будет доступна только в текущей оболочке и не будет видна дочерним процессам.

3. **Как установить значение переменной окружения и запустить программу?**

   Можно установить переменную и сразу запустить программу в одной строке:

   ```bash
   VAR=value программа
   ```

   Или сначала экспортировать переменную, а затем запустить программу:

   ```bash
   export VAR=value
   программа
   ```

---

**Вопросы**

1. **Что значит параметр `-f elf64`?**

   Параметр `-f elf64` указывает ассемблеру `nasm`, что нужно создать объектный файл в формате ELF для 64-битной архитектуры.

2. **Что значит параметр `-g`?**

   Параметр `-g` включает генерацию отладочной информации в объектном файле, что позволяет использовать отладчики (например, `gdb`) для пошаговой отладки программы.

3. **Что такое отладчик?**

   Отладчик — это инструмент, который позволяет разработчику выполнять программу пошагово, устанавливать точки останова, просматривать и изменять значения переменных и регистров, исследовать поведение программы.

---

**Вопрос**

**В чем смысл вызова операции исключающего или регистра с самим собой?**

Инструкция `xor r, r` (где `r` — регистр) обнуляет регистр `r`. Это быстрый и эффективный способ установить регистр в ноль. Используется часто вместо `mov r, 0`, так как может быть быстрее и компактнее по размеру кода.

---

**Вопросы**

1. **Что такое секция? Зачем они нужны?**

   Секция в ассемблерном коде — это область программы, содержащая определённый тип данных или кода. Основные секции:

   - `.text` — содержит исполняемый код.
   - `.data` — содержит инициализированные данные.
   - `.bss` — содержит неинициализированные данные.

   Секции помогают компоновщику правильно разместить код и данные в памяти и управлять правами доступа.

2. **Что такое метка (label)?**

   Метка — это именованная позиция в коде, к которой можно обращаться для переходов или ссылок на данные. Метки облегчают управление потоком выполнения и обращение к данным.

3. **Что означает инструкция `xor r, r`, где `r` — любой регистр?**

   Инструкция `xor r, r` обнуляет регистр `r` путем выполнения операции XOR регистра с самим собой. Результатом всегда будет ноль.

4. **Найдите страницу, соответствующую инструкции `mov` в Intel Software Developer Manual и просмотрите её.**

   Инструкция `mov` используется для копирования данных между регистрами, памятью и непосредственными значениями. В Intel SDM подробно описаны все варианты использования `mov`, включая разрешённые операнды и особенности.

---

**Вопрос**

**Что значит параметр `-g` при компиляции?**

Параметр `-g` при компиляции включает генерацию отладочной информации в объектном файле, что позволяет отладчику сопоставлять машинный код с исходным кодом и облегчает процесс отладки.

---

**Вопрос**

**В чём разница между `nexti`, `stepi`? Что такое `step over`?**

- `stepi` (`si`) — выполняет одну машинную инструкцию, заходя внутрь вызываемых функций.
- `nexti` (`ni`) — выполняет одну машинную инструкцию, но не заходит внутрь функций; если встречается вызов функции, то она выполняется целиком, и управление возвращается на следующую инструкцию после вызова.
- `step over` — термин, означающий "перешагнуть" через вызов функции, не заходя внутрь неё (аналогично `nexti`).

---

**Вопрос**

**Прочитайте `help x`.**

Команда `x` в `gdb` используется для отображения содержимого памяти по указанному адресу. Формат команды:

```gdb
x /[количество][формат][размер] адрес
```

- `количество` — количество элементов для отображения.
- `формат` — формат вывода (`x` — шестнадцатеричный, `d` — десятичный, `c` — символы и т.д.).
- `размер` — размер элементов (`b` — байт, `h` — 2 байта, `w` — 4 байта, `g` — 8 байт).
- `адрес` — адрес памяти для отображения.

---

### Задание 3

**Запустите программу `Hello, world!` в `gdb` и выполните её по шагам. Проследите за изменениями регистра `rip` и обратите внимание на значение `rax` после системного вызова `write`.**

**Решение:**

1. **Запуск `gdb` и загрузка программы:**

   ```bash
   gdb hello
   ```

2. **Установка точки останова на метку `_start`:**

   ```gdb
   (gdb) break _start
   ```

3. **Запуск программы:**

   ```gdb
   (gdb) run
   ```

4. **Включение режима псевдографики для удобства:**

   ```gdb
   (gdb) layout asm
   (gdb) layout regs
   ```

5. **Пошаговое выполнение программы:**

   Используйте команду `si` для выполнения одной инструкции:

   ```gdb
   (gdb) si
   ```

   После каждого шага можно проверить значение регистра `rip`:

   ```gdb
   (gdb) info registers rip
   ```

   **Объяснение:**

   Регистр `rip` (Instruction Pointer) содержит адрес следующей инструкции для выполнения. При каждом шаге значение `rip` изменяется на длину выполненной инструкции, указывая на следующую.

6. **Наблюдение за `rax` после системного вызова `write`:**

   После выполнения инструкции `syscall` для `write`, проверьте значение `rax`:

   ```gdb
   (gdb) si  # Выполняем syscall
   (gdb) print $rax
   ```

   **Объяснение:**

   После системного вызова в `rax` возвращается код возврата. Для `write` это количество записанных байт. Если вызов успешен и записано 14 байт, то `rax` будет равно 14.

---

**Задание 3.5**

**Попробуйте при отладке программы пройти назад, а не вперед.**

**Решение:**

1. **Включение записи выполнения:**

   ```gdb
   (gdb) record
   ```

2. **Выполнение нескольких шагов вперед:**

   ```gdb
   (gdb) si
   (gdb) si
   # Повторите несколько раз
   ```

3. **Шаги назад:**

   Используйте команду `reverse-stepi`:

   ```gdb
   (gdb) reverse-stepi
   ```

   **Объяснение:**

   Команда `reverse-stepi` позволяет возвращаться к предыдущей инструкции, восстанавливая предыдущее состояние регистров и памяти.

---

### Задание 4

**Пройдите по шагам следующую программу и проследите за изменением значения регистра `rax`. Объясните увиденное.**

```asm
section .text
global _start

_start:
    mov     rax, 0FFFFFFFFFFFFFFFFh  ; rax = 0xFFFFFFFFFFFFFFFF
    mov     al, 0                    ; Обнуляем младший байт
    mov     ax, 0                    ; Обнуляем младшие 2 байта
    mov     eax, 0                   ; Обнуляем младшие 4 байта

    mov     rax, 60
    xor     rdi, rdi
    syscall
```

**Решение:**

1. **Компиляция программы:**

   ```bash
   nasm -f elf64 -g -o program.o program.asm
   ld -o program program.o
   ```

2. **Запуск в `gdb`:**

   ```bash
   gdb program
   ```

3. **Установка точки останова и запуск:**

   ```gdb
   (gdb) break _start
   (gdb) run
   ```

4. **Пошаговое выполнение и наблюдение за `rax`:**

   - **Первоначальное значение:**

     ```gdb
     (gdb) si
     (gdb) info registers rax
     ```

     `rax = 0xFFFFFFFFFFFFFFFF`

   - **После `mov al, 0`:**

     ```gdb
     (gdb) si
     (gdb) info registers rax
     ```

     `rax = 0xFFFFFFFFFFFFFF00`

     **Объяснение:**

     `al` — младший байт регистра `rax`. Установив `al` в 0, мы обнуляем младший байт, старшие биты остаются неизменными.

   - **После `mov ax, 0`:**

     ```gdb
     (gdb) si
     (gdb) info registers rax
     ```

     `rax = 0xFFFFFFFFFFFF0000`

     **Объяснение:**

     `ax` — младшие 2 байта `rax`. Обнуляя `ax`, мы обнуляем младшие 2 байта.

   - **После `mov eax, 0`:**

     ```gdb
     (gdb) si
     (gdb) info registers rax
     ```

     `rax = 0x0000000000000000`

     **Объяснение:**

     При записи в `eax` (младшие 4 байта `rax`), старшие 4 байта регистра `rax` автоматически обнуляются. Это особенность архитектуры x86-64.

5. **Вывод:**

   - Запись в `al` и `ax` затрагивает только младшие байты, старшие остаются неизменными.
   - Запись в `eax` обнуляет старшую часть регистра `rax`. Это важно учитывать при программировании на ассемблере.
