	.data
	.eqv size, 3
str:	.asciiz "\nString #"
str1:	.asciiz "Array"
str2:	.asciiz "De"
str3:	.asciiz "Ponteiros"
str4:	.asciiz ": "

array:	.word str1,str2,str3
	.text
	.globl main
main:

	li $t0, 0	# i = 0
for:	bge $t0, size, endfor
	
	la $a0,str
	li $v0, 4
	syscall

	move $a0, $t0	# $a0 = i
	li $v0, 1	#print_int10(i)
	syscall

	la $a0, str4
	li $v0, 4
	syscall
	li $t1, 0		# j  = 0
while:	la $t3, array		#  $t3 = &array[0]
	sll $t4, $t0, 2		# i * 4
	addu $t3, $t3, $t4	# &array[i]
	lw $t3, 0($t3)		# &array[i]
	addu $t3, $t3, $t1	# &array[i][j]
	lb $t2, 0($t3)
	
	beq $t2, '\0', endwhile
	move $a0, $t2	
	li $v0, 11
	syscall	
	
	li $a0, '-'
	li $v0, 11
	syscall
	addi $t1, $t1, 1	# j ++
	j while
endwhile:
	addi $t0, $t0, 1
	j for

endfor:	jr $ra


