	.data
str1:	.asciiz "I serodatupmoC ed arutetiuqrA"
str2:	.space 31
str3:	.asciiz "String too long: "
str4:	.asciiz "\n"	
	.text
	.globl main
main:

	addiu $sp, $sp, -4
	sw $ra,0($sp)
	
if:
	la $a0, str1
	jal strlen
	bgt $v0, 30, else
	
	la $a0, str2
	la $a1, str1
	jal strcpy
	la $a0, str2
	li $v0, 4
	syscall
	la $a0, str4
	li $v0, 4
	syscall
	la $a0, str2
	jal strrev
	move $a0, $v0
	li $v0, 4
	syscall
	li $v0, 0	# exit_value = 0
	j endif	
else:	
	la $a0, str3
	li $v0, 4
	syscall
	
	la $a0, str1
	jal strlen
	move $a0, $v0
	li $v0, 1
	syscall
	li $v0, -1
endif:
	lw $ra,0($sp)
	addiu $sp, $sp, 4
	
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
	
######################	
strrev:
	addiu $sp, $sp, -16
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	
	
	move $s0, $a0	# str
	move $s1, $a0	# p1
	move $s2, $a0	# p2
	
while_strrev:	
	lb $t0, 0($s2)	# *p2
	beq $t0, '\0', endwhile_strrev
	addiu $s2, $s2, 1	#p2++
	j while_strrev
endwhile_strrev:
	addiu $s2, $s2, -1	# p2--

while1_strrev:
	bge $s1, $s2, endwhile1_strrev
	
	move $a0, $s1
	move $a1, $s2
	jal exchange
	addiu $s1, $s1, 1	#p1++
	addiu $s2, $s2, -1	#p2--
	j while1_strrev
endwhile1_strrev:
	
	move $v0, $s0	# return str
	
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	addiu $sp, $sp, 16
	
	jr $ra
	
	
exchange:
	
	lb $t0, 0($a0)	# *c1
	lb $t1, 0($a1)	# *c2
	
	sb $t1, 0($a0)
	sb $t0, 0($a1)
	
	jr $ra
	
###################
strlen:	
	li $v0, 0	# len = 0
while_strlen:
	lb $t0, 0($a0)	# *s
	beq $t0, '\0', endwhile_strlen
	addiu $v0, $v0, 1	# len++
	addiu $a0, $a0, 1	# s++
	j while_strlen	
endwhile_strlen:
	jr $ra




