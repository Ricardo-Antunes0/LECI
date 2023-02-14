	.data
str1:	.asciiz "Arquitetura de "
str2:	.space 50
str3:	.asciiz "\n"
str4:	.asciiz "Computadores I"
	.text
	.globl main
main:
	
	addiu $sp, $sp, -4
	sw $ra,0($sp)
		
	
	la $a0, str2
	la $a1, str1	# strcpy(str2, str1); 
	jal strcpy
	
	la $a0, str2
	li $v0, 4	# print_string(str2);
	syscall
	
	la $a0, str3
	li $v0, 4	# print_string("\n"); 
	syscall
	
	la $a0, str2
	la $a1, str4
	jal strcat
	move $a0, $v0
	li $v0, 4	# print_string( strcat(str2, "Computadores I") ); 
	syscall
	
	li $v0, 0 	#return 0
	
	lw $ra,0($sp)
	addiu $sp, $sp, 4	# EPILOGO
	
	jr $ra

	
strcat:
	
	addiu $sp, $sp, -8
	sw $ra,0($sp)
	sw $s0,4($sp)
	
	move $s0, $a0 	# $s0 = dst
	
while_strcat:	lb $t0, 0($a0)	# *p
	beq $t0, '\0', endwhile_strcat
	addiu $a0, $a0, 1	# p++
	j while_strcat
endwhile_strcat:

	jal strcpy
	
	move $v0, $s0	# return dst
	
	lw $ra,0($sp)
	lw $s0,4($sp)
	addiu $sp, $sp, 8

	jr $ra
	
	
strcpy:	
	li $t0, 0		# i = 0
do:
	addu $t1, $a0, $t0 	# &dst[i]
	addu $t2, $a1, $t0	# &src[i]
	
	lb $t2, 0($t2)		# *src[i]
	sb $t2, 0($t1)		
	
	addiu $t0, $t0, 1	# i++
while_strcpy:	bne $t2, '\0', do
	move $v0, $a0
	jr $ra