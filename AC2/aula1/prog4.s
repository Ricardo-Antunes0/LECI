.data
str1: .asciiz "\nIntroduza um inteiro (sinal e módulo): "
str2: .asciiz "\nValor em base 10 (signed): "
str3: .asciiz "\nValor em base 2: "
str4: .asciiz "\nValor em base 16: "
str5: .asciiz "\nValor em base 10 (unsigned): "
str6: .asciiz "\nValor em base 10 (unsigned), formatado: "
.text
.globl main
main:


while:
la $a0, str1
li $v0, 8
syscall
li $v0, 5
syscall
move $t0, $v0    # value = readINt10()
##base 10
la $a0, str2
li $v0, 8
syscall

move $a0, $t0
li $v0, 7
syscall
##

##base 2
la $a0, str3
li $v0, 8
syscall

move $a0, $t0
li $a1, 2
li $v0, 6
syscall

##
##base 16
la $a0, str4
li $v0, 8
syscall

move $a0, $t0
li $a1, 16
li $v0, 6
syscall

##
##base  10 unsigned
la $a0, str5
li $v0, 8
syscall

move $a0, $t0
li $a1, 10
li $v0, 6
syscall

##
##base 10 unsgined formatado
la $a0, str6
li $v0, 8
syscall

move $a0, $t0
li $t1, 5
li $t2, 10
sll $t1, $t1, 16    # 5 << 16
or $t2, $t2, $t1    # 10 o
move $a1, $t2
li $v0, 6
syscall

##

li $v0, 0
jr $ra

