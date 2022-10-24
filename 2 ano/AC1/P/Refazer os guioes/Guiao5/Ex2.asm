.data
.eqv size, 10
.eqv print_string, 4
.eqv print_int10, 1
str1: .asciiz "\nConteudo do array:\n"
str2: .asciiz "; "

lista: .word -8, -4, 3, 5, 124, -15, 87, 9, 27, 15

.text
.globl main
main:	la $a0, str1
	li $v0, 4
	syscall
	
	la $t0, lista
	li $t1, size		# $t1 = size
	sll $t1, $t1, 2		# $t1 = size * 4
	add $t1, $t1, $t0
	for:	bge $t0, $t1, endfor
		
		lw $t3, 0($t0)
		move $a0, $t3
		li $v0, 1
		syscall
		
		la $a0, str2
		li $v0, 4
		syscall
		
		addi $t0, $t0, 4
		j for
	endfor:
	jr $ra