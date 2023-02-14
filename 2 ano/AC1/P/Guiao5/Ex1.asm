	.data
	.eqv size, 5
str: 	.asciiz "\nIntroduza um numero: "
	.align 2
lista:	.space 20
	.text
	.globl main
main:

	li $t0, 0	# i = 0
	la $t1, lista	# &lista[0]
for:	bge $t0, size, endfor
	
	la $a0, str
	li $v0, 4
	syscall
	
	li $v0, 5	#read_int()
	syscall	
	
	sll $t2, $t0, 2		# i * 4
	add $t2, $t1, $t2	#lista[i]
	
	sw $v0, 0($t2)		# lista[i]= read_int()
	
	addi $t0, $t0, 1
	j for
endfor:
	jr $ra

