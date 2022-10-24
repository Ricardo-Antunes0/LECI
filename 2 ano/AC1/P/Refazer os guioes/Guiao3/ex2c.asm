.data

str1: .asciiz "Introduza um numero: "
str2: .asciiz "\nO valor em binário e': "
.eqv  read_int, 5
.eqv print_string, 4
.eqv print_char, 11
.text
.globl main
main:
	la $a0, str1
	li $v0, 4
	syscall

	li $v0, 5
	syscall
	move $t0, $v0	

	la $a0, str2
	li $v0, 4
	syscall

	li $t2, 0
	
for: 	bge $t2, 32, endfor
	
	
	rem $t5, $t2, 4
	bnez $t5, x
	li $a0, ' '
	li $v0, 11
	syscall
x: 	andi $t6, $t0,0x80000000
	srl $t1, $t6, 31
	
	addi $t1, $t1, 0x30
	
	move $a0, $t1
	li $v0, 11
	syscall
			
	sll $t0, $t0, 1
	addi $t2, $t2, 1
		j for
endfor:	jr $ra