.data
.eqv SIZE, 8
.eqv SIZE2, 4
.eqv print_int10, 1
.eqv print_char, 1
.eqv print_string, 4
str1: .asciiz "Result is: "
val: .word 8, 4, 15, -1987, 327, -9, 27, 16
.text
.globl main
main:
li $t0, 0
do:
la $t2, val	# &val[0] = $t2
sll $t3, $t0, 2	# tmp = i*4
addu $t3, $t3, $t2	# & val[i]
lw $t4, 0($t3)
lw $t5, 4($t3)

sw $t5, 0($t3)
sw $t4, 4($t3)

addi $t0, $t0, 1

while: blt $t0, SIZE2, do

la $a0, str1
li $v0, 4
syscall

li $t0, 0

do1:
sll $t3, $t0, 2
addu $t3, $t2, $t3
lw $a0, 4($t3)
li $v0, 1
syscall

li $a0, ','
li $v0, 11
syscall

addi $t0, $t0, 1

while1: blt $t0, SIZE, do1
jr $ra
