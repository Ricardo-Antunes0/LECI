	.data
	.eqv SIZE, 20
str: 	.space 21	#20+1 para terminador
	.text
	.globl main
main:
	li $t0, 0	# num = 0
	
	la $a0, str
	li $a1, SIZE
	li $v0, 8
	syscall

	la $t1, str	# p = &str	o meu ponteiro vai ter o endereco do array
	
while:	lb $t2, 0($t1)	# $t2 = *p (=) str[0]
	beq $t2, '\0', endwhile
	
if:	blt $t2, '0', endif
	bgt $t2, '9', endif
	addi $t0, $t0, 1	#num++
endif:
	addi $t1, $t1, 1	# p++
	j while
endwhile:
	move $a0, $t0
	li $v0, 1
	syscall
	jr $ra
