.data
.eqv size, 3
.eqv print_string, 4 
.eqv print_char,11
str1: .asciiz "Array"
str2: .asciiz "de"
str3: .asciiz "ponteiros"
str4: .asciiz "\n "

array: .word str1, str2, str3
.text
.globl main
main:	li $t0, 0	# i = 0
	for:	bge $t0, size, endfor
		la $t2, array		# $t2 = & array[0]
		sll $t1, $t0, 2		# tmp = i * 4
		add $t1, $t1, $t2	#array[i] = array[0] + tmp
		
		lw $a0, 0($t1)		# $a0 = * array[i]
		li $v0, 4		
		syscall
		
		la $a0, str4
		li $v0, 11
		syscall
		
		addi $t0, $t0, 1
		j for
		
	endfor:	jr $ra

	