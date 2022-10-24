.data
.eqv size, 4
.eqv print_int10,1

array: .word 7692, 23, 5, 234
.text
.globl main

main:	li $t3, 0 	# soma = 0
	la $t0, array 	#p = array
	li $t4, size	# $t4 = size
	sub $t4, $t4, 1 # $t4 = size -1
	sll $t4, $t4, 2 # size -1 = (size -1) * 4
	add $t1, $t0, $t4 # pultimo =  &array[0] + 3*4
	
	while: 	bgt $t0, $t1, endwhile
		lw $t2, 0($t0)
		add $t3, $t3, $t2
		addi $t0, $t0, 4
		j while
	endwhile:
		move $a0, $t3
		li $v0, 1
		syscall
		jr $ra