.data
.eqv size, 5
str1: .asciiz "\nIntroduza um numero: "
.align 2
lista: .space 20
	.eqv print_string, 4
	.eqv read_int, 5
.text
.globl main
main:	li $t0, 0	# i = 0
		
	for:	bge $t0, size, endfor
		la $a0, str1
		li $v0, 4
		syscall
		li $v0, 5
		syscall
		
		la $t1, lista # $t1 = &lista[0] 
		sll $t2, $t0, 2 	# tmp = i * 4
		addu $t2, $t2, $t1	# p = &lista[i]
		sw $v0, 0($t2)
		
		addi $t0, $t0, 1
		j for
		
	endfor:
		jr $ra
