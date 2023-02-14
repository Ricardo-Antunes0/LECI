	.data
str:	.asciiz ", "
	.text
	.globl main
main:


insert:
if:
	ble $a2, $a3, else
	li $v0, 1		# return 1
	j endif
else:

addiu $t0, $a3, -1	# i = size - 1
for:	blt $t0, $a2, endfor
	sll $t1, $t0, 2		# i * 4
	
	addu $t1, $a0, $t1	#&array[i]
	lw $t2, 0($t1)		# *array[i]
	sw $t2, 4($t1)		# array[i+1]= array[i]	
	addiu $t0, $t0, -1	# i--
	j for
endfor:
	sll $t0, $a2, 2		# pos * 4
	addu $t0, $a0, $t0	# &array[pos]
	sw $a1, 0($t0)
	

	li $v0, 0	# return 0	

endif:	jr $ra




print_array:
	move $t0, $a0		# a
	sll $t1, $a1, 2		# n * 4
	addu $t2, $t0, $t1	# p
	
for_print:	
	bge $t0, $t2, endfor_print
	lw $a0, 0($t0)	# $a0 = *a
	li $v0, 1
	syscall
	la $a0, str
	li $v0, 4
	syscall
	
	addiu $t0, $t0, 4	# a++
	j for_print
endfor_print:	jr $ra