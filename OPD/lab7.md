![Смешной кот за ноутбуком](35fbbb691a405a1df9904e2471091b73.jpg)

# Лабораторная работа 4

## Вариант `98353`

Синтезировать цикл исполнения для выданных преподавателем команд. Разработать тестовые программы, которые проверяют
каждую из синтезированных команд. Загрузить в микропрограммную память БЭВМ циклы исполнения синтезированных команд,
загрузить в основную память БЭВМ тестовые программы. Проверить и отладить разработанные тестовые программы и
микропрограммы.

**ADDHL М** - Сложить старший байт AC и младший байт заданной ячейки памяти, результат поместить в М, установить
признаки N/Z/V/C\
Код операции - $9...$\
Тестовая программа должна начинаться с адреса $\textbf{048B}_{16}$

## Выполнение работы

### Текст программы

| Адрес | Микрокоманда                                                       | Описание                                                              | Комментарий                                                                                     |
|-------|--------------------------------------------------------------------|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| E0    | 00<ins>2</ins>00080<ins>1</ins>0                                   | HTOH(<ins>AC</ins>) → <ins>BR</ins>                                   | Взять старший байт AC и поместить его в BR (используем BR, чтобы операция не могла изменить AC) |
| E1    | 000<ins>1</ins>00100<ins>1</ins>                                   | LTOL(<ins>DR</ins>) → <ins>DR</ins>                                   | Взять младший байт DR и поместить его в DR                                                      |
| E2    | 000<ins>1</ins> <span style="color:green">E</span>090<ins>21</ins> | __BR__ + __DR__ → __DR__, <span style="color:green">N, Z, V, C</span> | Результат операции сложения BR и DR поместить в DR, установить флаги N, Z, V, C                 |
| E3    | 80<span style="color:orange">55</span>1010<ins>4</ins>0            | GOTO STORE @ <span style="color:orange">55</span>                     | Безусловный переход на метку STORE @ 55                                                         |

### Таблица трассировки микропрограммы

##### Содержимое памяти и регистров процессора после выборки микрокоманды (test1)

| МР до выборки<br/>MK | MR         | IP  | CR   | AR  | DR   | SP  | BR   | AC   | NZVC | СчМК |
|----------------------|------------|-----|------|-----|------|-----|------|------|------|------|
| E0                   | 0020008010 | 497 | 9470 | 470 | 0BFF | 7FF | FF00 | FF00 | 1000 | E1   |
| E1                   | 0001001001 | 497 | 9470 | 470 | 00FF | 7FF | FF00 | FF00 | 1000 | E2   |
| E2                   | 0001E09021 | 497 | 9470 | 470 | FFFF | 7FF | FF00 | FF00 | 1000 | E3   |
| E3                   | 8055101040 | 497 | 9470 | 470 | 0001 | 7FF | FF00 | FF00 | 1000 | 55   |

##### Содержимое памяти и регистров процессора после выборки микрокоманды (test3)

| МР до выборки<br/>MK | MK         | MR         | IP  | CR   | AR  | DR   | SP  | BR   | AC   | NZVC | СчМК |
|----------------------|------------|------------|-----|------|-----|------|-----|------|------|------|------|
| E0                   | 0020008010 | 0020008010 | 4AF | 9472 | 472 | 0078 | 7FF | 3C00 | 3C00 | 0000 | E1   |
| E1                   | 0001001001 | 0001001001 | 4AF | 9472 | 472 | 0078 | 7FF | 3C00 | 3C00 | 0000 | E2   |
| E2                   | 0001E09021 | 0001E09021 | 4AF | 9472 | 472 | 3C78 | 7FF | 3C00 | 3C00 | 0000 | E3   |
| E3                   | 8055101040 | 8055101040 | 4AF | 9472 | 472 | 3C78 | 7FF | 3C00 | 3C00 | 0000 | 55   |

### Тестовая программа

```asm
ORG 0x470
ARG1: WORD 0xFFFF
ARG2: WORD 0x0000
ARG3: WORD 0x3C78

CHECK1: WORD 0x0
CHECK2: WORD 0x0
CHECK3: WORD 0x0

FINAL: WORD 0x0

CORRECT_RES1: WORD 0xFFFF 
CORRECT_RES2: WORD 0x0000
CORRECT_RES3: WORD 0x3C78

ORG 0x48B
START:
	CLA
	CALL TEST1
	CALL TEST2
	CALL TEST3
	LD #0x1
	AND CHECK1
	AND CHECK2
	AND CHECK3
	ST FINAL
STOP: HLT

TEST1:
	LD ARG1
	WORD 0x9470; ADDHL 470 - адрес ARG1
	BMI negative_is_set
	JUMP exit
	negative_is_set: LD ARG1
	CMP CORRECT_RES1
	BEQ set_number1_is_correct
	JUMP exit
	set_number1_is_correct: LD CHECK1
	INC
	ST CHECK1
	exit: RET

TEST2:
	LD ARG2
	WORD 0x9471
	BEQ zero_is_set
	JUMP exit
	zero_is_set: LD ARG2
	CMP CORRECT_RES2
	BEQ set_number2_is_correct
	JUMP exit
	set_number2_is_correct: LD CHECK2
	INC
	ST CHECK2
	JUMP exit

TEST3:
	LD ARG3
	WORD 0x9472
	BVC overflow_is_set
	JUMP exit
	overflow_is_set: LD ARG3
	CMP CORRECT_RES3
	BEQ set_number3_is_correct
	JUMP exit
	set_number3_is_correct: LD CHECK3
	INC
	ST CHECK3
    JUMP exit
```

### Методика проверки

0. Запустить микропрограмму
1. Загрузить тестовую программу в память
2. Запустить основную программу с адреса $\textbf{048B}_{16}$ в режиме `работа`
3. Дождаться останова
4. Проверить значение ячейки памяти с номером $\textbf{479}_{16}$:\
   4.1. Если значение 0x1 - все тесты выполнены успешно.  
    4.2. Также, необходимо проверить значения ячеек ARG[1-3]: ARG1 должно быть равно $FFFF$, ARG2 – $0000$, ARG3 – $3C78$

## Вывод

В ходе выполнения лабораторной работы я изучил алгоритм синтеза собственной команды БЭВМ с использованием микропрограмм, а также методику проверки разработанной программы.