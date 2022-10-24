.data
.eqv read_int, 5
.eqv print_string, 4
.eqv print_int10, 1
.eqv print_char, 11
str1: .asciiz "Digite ate 20 inteiros(zero para terminar): "
str2: .asciiz "Máximo/minimo sao: "
.text
.globl main
main:
li $t1,0
li $t2, 0x7FFFFFFF
li $t3, 0x80000000

la $a0, str1
li $v0, 4
syscall

do:
li $v0, 5
syscall
move $t0, $v0
if: beq $t0, 0, endif
if1: ble $t0, $t3, if2
move $t3, $t0
if2:bge $t0, $t2, endif
move $t2, $t0
endif: addi $t1, $t1, 1
j while

while: bge $t1, 20, endwhile
bne $t0, 0, do

endwhile:

la $a0, str2
li $v0, 4
syscall

move $a0, $t3
li $v0, 1
syscall

li $a0, ':'
li $v0, 11
syscall

move $a0, $t2
li $v0, 1
syscall
jr $ra
