.data
str1: .asciiz "Introduza um numero: "
str2: .asciiz "O valor em binario e': "
.eqv print_string, 4
.eqv read_int, 5
.eqv print_char, 11

.text
.globl main

main:

lui $t0, 0 #value
lui $t1, 0 # bit
lui $t2, 0 # i 

la $a0, str1
li $v0, 4
syscall

li $v0, 5
syscall
move $t0, $v0

la $a0, str2
li $v0, 4
syscall

for: 
	bge $t2, 32, endFor
	andi $t0, $t0, 0x80000000
	if: beq $t1, 0, else
		li $a0, '1'
		li $v0, 11
		syscall
		j endIf
	else:
		li $a0, 0
		li $v0, 11
		syscall
	endIf:
	sll $t0, $t0, 1
	addi $t2, $t2, 1 
	j for  

endFor:
jr $ra