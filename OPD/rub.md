## Суммирование каждого k-ого элемента массива в 32-ух разрядную сумму, вводимого с ВУ-2. Элементы — 14-ти разрядные числа

```ASM
ORG 0x20

ARR_LENGTH:    WORD ?  ; Длина вводимого массива
CURR_ELEM:    WORD 0  ; Текущий элемент
ELEMS_LEFT:    WORD ?  ; Сколько элементов осталось (счетчик цикла)
SUM_0_15:    WORD 0  ; Результат/сумма (младшие 2 байта)
SUM_16_31:    WORD 0  ; Результат/сумма (старшие 2 байта)
INDEX_CHECK:    WORD 0  ; Счетчик, который обнуляется каждый k-ый индекс (для суммы k-ых элементов)
INDEX_INDENT:  WORD ?  ; k - число, которому кратны индексы суммируемых элементов
COMPARISON_MASK:  WORD 0x2000 ; Маска для проверки знака 14-ти разрядного числа
NEGATIVE_MASK:  WORD 0xC000 ; Маска для очищения "мусора" в старших разрядах и получения отрицательного числа
POSITIVE_MASK:  WORD 0x1FFF  ; Маска для очищения "мусора" в старших разрядах и получения положительного числа

START:  CLA    ; Загружаем исходные данные в обновляемые ячейки и обнуляем ячейки результата
  ST $SUM_0_15
  ST $SUM_16_31
  LD $ARR_LENGTH
  ST $ELEMS_LEFT

MAIN_LOOP:  CLA    ; Основной цикл программы
  ST $CURR_ELEM  ; Обнуляем ячейку с предыдущим считанным элементом
  CALL WORD_INPUT  ; Вызов подпрограммы ввода числа
  LD $INDEX_CHECK  ; Проверка на равенство счетчика числу k
  INC
  CMP $INDEX_INDENT
  BEQ FUNCTION  ; Если счетчик равен k - переходим к функции суммирования
  ST $INDEX_CHECK  ; Иначе - сохраняем новое значение счетчика и продолжаем цикл
LOOP_END:  LOOP ELEMS_LEFT
  JUMP MAIN_LOOP
  HLT

WORD_INPUT:      ; Подпрограмма для ввода числа с ВУ-2 (сначала старший байт, затем младший)
SPIN_LOOP_1:  IN 5    ; Спин-луп
  AND #0x40
  BEQ SPIN_LOOP_1
  IN 4    ; Ввод старшего байта
  SWAB
  ST $CURR_ELEM
SPIN_LOOP_2:  IN 5    ; Спин-луп
  AND #0x40
  BEQ SPIN_LOOP_2
  IN 4    ; Ввод младшего байта
  ADD $CURR_ELEM
  ST $CURR_ELEM
  RET

FUNCTION:  CLA
  ST $INDEX_CHECK  ; Обнуляем счетчик элементов
  LD $CURR_ELEM  ; Загружаем только что считанный элемент
  AND $COMPARISON_MASK  ; Проверяем знак числа с помощью маски и переходим к нужной подфункции суммирования
  BEQ POS_ELEM
  JUMP NEG_ELEM

POS_ELEM:  LD $CURR_ELEM  ; Подфункция для суммирования с положительным числом
  AND $POSITIVE_MASK
  ADD $SUM_0_15
  ST $SUM_0_15
  CLA
  ADC $SUM_16_31
  ST $SUM_16_31
  JUMP LOOP_END

NEG_ELEM:  LD $CURR_ELEM  ; Подфункция для суммирования с отрицательным числом
  OR $NEGATIVE_MASK
  ADD $SUM_0_15
  ST $SUM_0_15
  LD #0xFF
  ADC $SUM_16_31
  ST $SUM_16_31
  JUMP LOOP_END
```

## Суммирование каждого элемента двумерного массива размерностью [7][7] с индексом i с шагом 3, индексом j с шагом 2 и при условии, что элемент кратен 4, состоящего из 20-разрядных элементов, с формированием 32-разрядного результата
Формат хранения элементов в массиве: мл_байты_0; ст_байты_0; мл_байты_1; ст_байты_1; ...

```asm
ORG 0x10
ArrAddr: WORD 0x300
CurElemAddr: WORD 0
flat_dimension: WORD 7 ; размер
linearIndexMaxBorder: WORD 0 ; макс граница линейного индекса
count: WORD ? ; счётчик 
indexI: WORD 0
indexJ: WORD 0
indexJMaxBorder: WORD ? ; фактическая граница для индекса J (т.к храним мл_байт, ст_байт в элементе)
step_i: WORD 3
step_j: WORD 2
step_i_f: WORD ? ; шаг для первого случая
step_j_f: WORD ?
step_j_actual: WORD ? ; шаг для корректного прохода по элементам в строке
indexLinear: WORD 0
sum1: WORD 0
sum2: WORD 0

Start:
CLA
ST sum1
ST sum2
LD step_i
DEC
ST step_i_f
LD step_j
ASL
ST step_j_actual ; фактический шаг это step * 2
SUB #2
ST step_j_f ; но для первого случая step * 2 - 2
LD flat_dimension ; count = 7 = flat_dimension
ST count
ASL
ST indexJMaxBorder; граница у j = 7*2 = 14
LD #0

square: ; расчёт для дальнейшей проверки границ лин.индекса (2*7*7)
ADD flat_dimension ; (=7)
LOOP count ; (count = 7)
JUMP square
ASL
ST linearIndexMaxBorder

calcIndexes_f: ; индексы для первого прохода
LD step_i_f 
ST indexI
LD step_j_f
ST indexJ
JUMP calcLinearIndex

; //////////////////////

OlegSheyko:

calcIndexes: ; добавление шага к индексам (смещение)
LD indexJ
CMP indexJMaxBorder ; сначала проверяем, что мы обошли все элементы в строке (j)
BPL inc_i
ADD step_j_actual ; если нет, то добавляем шаг
ST indexJ
JUMP calcLinearIndex

inc_i: ; иначе ставим в позицию для первого случая !!!
LD step_j_f 
ST indexJ

LD indexI ; увеличиваем i
ADD step_i
ST indexI

checking: ; проверка что i не вышел за границы 
LD indexI
CMP flat_dimension
BMI calcLinearIndex
HLT


; //////////////////////

calcLinearIndex: ; короче тут типа линейный индекс вычисляется как 7*i + j
CLA
ST linearIndex
LD indexI
BEQ add_j

add_i:
LD linearIndex
ADD flat_dimension
ST linearIndex

LD indexI
DEC
ST indexI
BNE add_i

ASL
ST linearIndex

add_j:
LD linearIndex
ADD indexJ
ST linearIndex
CMP linearIndexMaxBorder ; проверка чтобы он не вышел за границы
BMI elemFinder
HLT

; //////////////////////

elemFinder: ; представляем двумерный массив как единую строку и смещаемся по ней
LD ArrAddr
ADD linearIndex
ST CurElemAddr

; //////////////////////

summing:
LD (CurElemAddr)+

checkingMultiplicity: 
ROR
BCS return
ROR
BCS return
ROL
ROL

ADD sum1 ; складывание младшего байта
ST sum1

summing2:
LD (CurElemAddr)
ADC #0 ; сложение с битом С, потому что он будет потерян при расширении знака старшего байта
ExtendingSign:
ASL
ASL
ASL
ASL
SXTB
ASR
ASR
ASR
ASR
ADD sum2
return:
LD CurElemAddr ; возвращение указателя к младшему байту
DEC
ST CurElemAddr
JUMP OlegSheyko

; //////////////////////

ORG 0x300
Array:
WORD 0;   i = 0, j = 0
WORD 0;
...
WORD 0;   i = 0, j = 13
WORD 0;

WORD 0;   i = 1, j = 0
WORD 0;
...
WORD 0;   i = 1, j = 13
WORD 0;
```

## Суммирование каждого k-го элемента массива (если он кратен 4) (ввод k с ВУ-2), состоящего из 20-разрядных элементов, с формированием 32-разрядного результата
Формат хранения элементов в массиве: мл_байты_0; ст_байты_0; мл_байты_1; ст_байты_1; ...

```asm
ORG 0x10
ArrAddr: WORD 0x300
CurElemAddr: WORD 0
step: WORD 0
arrayIndex: WORD 0
ArrLength: WORD 30
sum1: WORD 0
sum2: WORD 0

Start:
LD #0
ST sum1
ST sum2
LD ArrLength ; увеличение границы для проверки индекса
ASL
ST ArrLength
Spin0:
IN 5
AND #0x40
BEQ Spin0
IN 4
ASL ; умножаю шаг на 2
ST step

SUB #2 ; вычитаю 2, чтобы верно обработать первый по порядку k-ый элемент
ST arrayIndex
JUMP Skip

OlegSheyko:
LD arrayIndex
ADD step
ST arrayIndex
Checking:
CMP ArrLength
BMI Skip
HLT
Skip:
LD ArrAddr
ADD arrayIndex
ST CurElemAddr

summing:
LD (CurElemAddr)+

checkingMultiplicity:
ROR
BCS return
ROR
BCS return
ROL
ROL

ADD sum1 ; складывание младшего байта
ST sum1

summing2:
LD (CurElemAddr)
ADC #0 ; сложение с битом С, потому что он будет потерян при расширении знака старшего байта
ExtendingSign:
ASL
ASL
ASL
ASL
SXTB
ASR
ASR
ASR
ASR
ADD sum2
return:
LD CurElemAddr ; возвращение указателя к младшему байту
DEC
ST CurElemAddr
JUMP OlegSheyko


ORG 0x300
Array:
WORD 0;
WORD 0; 1 элемент
WORD 0;
WORD 0; 2 элемент
...
WORD 0;
WORD 0; 30й элемент
```

## Вывод строки, введённой с ВУ-8 (клавиатура), на ВУ-5 (принтер)
```asm
org  0xAA
ptr_s:  WORD  0x2AA ; указатель на начало строки 
now_p:  WORD  0000 ; нынешний указатель на символ
f_s:  WORD  0x0A ; последний символ
now_c:  WORD  0000 ; счетчик символов

  org  0xBA
START:  IN  0xD
  AND  #0x40
  BEQ  START
  LD  #0x0
  OUT  0xC

  CLA
  ST  now_c
  ST  (ptr_s)
  LD  ptr_s
  ST  now_p
  
STEP1:  IN  0x19 ; получаем 1-й символ
  AND  #0x40
  BEQ  STEP1
  IN  0x18
  CMP  f_s
  BEQ  WRITE
  ST  (now_p)
  LD  (now_c)+

STEP2:  IN  0x19 ; получаем 2-й
  AND  #0x40
  BEQ  STEP2
  IN  0x18
  CMP  f_s
  BEQ  WRITE
  SWAB
  OR  (now_p)
  ST  (now_p)+
  LD  (now_c)+
  JUMP  STEP1
  
WRITE:  LD  ptr_s
  ST  now_p
  
WRITE1:  IN  0xD ; выводим
  AND  #0x40
  BEQ  WRITE1
  LD  (now_p)  
  OUT  0xC
  LOOP  now_c
  JUMP  WRITE2
  HLT

WRITE2:  IN  0xD ; выводим
  AND  #0x40
  BEQ  WRITE1
  LD  (now_p)+
  SWAB
  OUT  0xC
  
  LOOP  now_c
  JUMP  WRITE1
  HLT
```

## Суммирование каждого k-го элемента (ввод k с ВУ2) массива, состоящего из 10-разрядных элементов, с формированием 32-разрядного результата
```asm
ORG 0x10
ArrAddr: WORD 0x300
CurElemAddr: WORD 0
step: WORD 0
arrayIndex: WORD 0
ArrLength: WORD 30
sum1: WORD 0
sum2: WORD 0
TMP: WORD ?

Start:
LD #0
ST sum1
ST sum2
Spin0:
IN 5
AND #0x40
BEQ Spin0
IN 4
ST step

DEC
ST arrayIndex
JUMP Skip

OlegSheyko:
LD arrayIndex
ADD step
ST arrayIndex
Checking:
CMP ArrLength
BMI Skip
HLT
Skip:
LD ArrAddr
ADD arrayIndex
ST CurElemAddr

summing:
LD (CurElemAddr)

ExtendingSign:
ASR
ASR
SXTB
ASL
ASL
ST TMP
LD (CurElemAddr)
AND #0x03
ADD TMP

BLT if_neg

ADD sum1
ST sum1
CLA
ADC sum2
ST sum2
JUMP OlegSheyko

if_neg:
ADD sum1
ST sum1
LD #0xFF
ADC sum2
ST sum2
JUMP OlegSheyko


ORG 0x300
Array:
WORD 0;
WORD 0;
...
WORD 0; 30й элемент
```

## Суммирование каждого k-го элемента (ввод k с ВУ2) массива, состоящего из 16-разрядных элементов, с формированием 32-разрядного результата
```asm
ORG 0x10
ArrAddr: WORD 0x300
CurElemAddr: WORD 0
step: WORD 0
arrayIndex: WORD 0
ArrLength: WORD 30
sum1: WORD 0
sum2: WORD 0

Start:
LD #0
ST sum1
ST sum2
Spin0:
IN 5
AND #0x40
BEQ Spin0
IN 4
ST step

DEC
ST arrayIndex
JUMP Checking

OlegSheyko:
LD arrayIndex
ADD step
ST arrayIndex
Checking:
CMP ArrLength
BMI Skip
HLT
Skip:
LD ArrAddr
ADD arrayIndex
ST CurElemAddr

summing:
LD (CurElemAddr)
BLT if_neg

ADD sum1
ST sum1
CLA
ADC sum2
ST sum2
JUMP OlegSheyko

if_neg:
ADD sum1
ST sum1
LD #0xFF
ADC sum2
ST sum2
JUMP OlegSheyko


ORG 0x300
Array:
WORD 0;
WORD 0;
...
WORD 0; 30й элемент
```

## Суммирование всех элементов массива, состоящего из 16-разрядных эоементов, с формированием 32-разрядного результата

```asm
ORG 0x20
ARR_LENGTH:  WORD ?
CURR_ELEM:  WORD $ARR_1
SUM_16_31:  WORD 0
SUM_0_15:  WORD 0

START:  CLA

MAIN_LOOP:  LD $SUM_0_15
  ADD (CURR_ELEM)
  ST $SUM_0_15
  CALL SIGN_CHECK
  LOOP ARR_LENGTH
  JUMP MAIN_LOOP
  HLT

SIGN_CHECK:  LD (CURR_ELEM)+
  BMI NEGATIVE
POSITIVE:  LD $SUM_16_31
  ADC #0x0
  ST $SUM_16_31
  RET
NEGATIVE:  LD $SUM_16_31
  ADC #0xFF
  ST $SUM_16_31
  RET

ARR_1:  WORD ?
```
