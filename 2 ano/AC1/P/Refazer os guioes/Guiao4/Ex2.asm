.data
.eqv size, 20
.eqv read_string, 8
.eqv print_int10, 1 
str: .space 21
.text
.globl main
main:	la $a0, str
	li $a1, size
	li $v0, 8
	syscall
	li $t0, 0	# num = 0
	
	la $t1, str
	
	while:	lb $t2, 0($t1)	
	 	beq $t2, '\0', endwhile
		if: 	blt $t2, '0', endif
			bgt $t2, '9', endif
			addi $t0, $t0, 1
		endif:	addiu $t1, $t1, 1
			j while
	endwhile:
		move $a0, $t0
		li $v0, 1
		syscall
		jr $ra