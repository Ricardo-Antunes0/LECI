	.data
	.eqv size, 3
str:	.asciiz "Array"
str1:	.asciiz "De"
str2:	.asciiz "Ponteiros"

array:	.word str, str1, str2
	.text
	.globl main
main:

	la $t0, array	# p = array
	li $t1, size
	sll $t1, $t1, 2	#size * 4
	addu $t1, $t0, $t1	# pultimo = array + size
	

for:	bge $t0, $t1, endfor

	lw $a0, 0($t0)
	li $v0, 4
	syscall

	li $a0, ' '
	li $v0, 11
	syscall

	addi $t0, $t0, 4	# p++
	j for

endfor:	jr $ra


	

