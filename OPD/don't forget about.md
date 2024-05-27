# БЛя

## ВУ:

Для ВУ 0-3: `(номер ВУ) * 2 + 1`

```text
ВУ-0: #0, #1
ВУ-1: #2, #3
ВУ-2: #4, #5
ВУ-3: #6, #7
ВУ-4: #8, #9, #A, #B
ВУ-5: #C, #D, #E, #F
```

Не забывай про:
- `AND #0x40` для проверки готовности
- `SWAB` при вводе старшего байта

#### Пример с ВУ-2

```asm
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
```

## Сложение

Не забывай про:
- `ADC`
- `LD #0xFF` при сложении с отрицательным числом


```asm
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
```
