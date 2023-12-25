import time

import addition_1.addition1
import addition_2.addition2
import addition_3.addition3
import main.main

print('### speed tests ###')

# task main

start = time.time_ns()

main.main.generate()

result = time.time_ns() - start
print('main: ', result / 1000000, 'ms')

# task 1

start = time.time_ns()
addition_1.addition1.generate()
result = time.time_ns() - start
print('task addition 1: ', result / 1000000, 'ms')

# task 2

start = time.time_ns()
addition_2.addition2.generate()
result = time.time_ns() - start
print('task addition 2: ', result / 1000000, 'ms')

# task 3

start = time.time_ns()
addition_3.addition3.generate()
result = time.time_ns() - start
print('task addition 3: ', result / 1000000, 'ms')
