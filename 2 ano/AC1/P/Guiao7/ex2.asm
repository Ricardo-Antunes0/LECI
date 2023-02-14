	.data
str:	.asciiz "ITED - orievA ed edadisrevinU"
	.text
	.globl main
main:
	addiu $sp, $sp, -4
	sw $ra, 0($sp)
	
	la $a0, str
	jal strrev
	move $a0, $v0
	li $v0, 4
	syscall
	
	li $v0, 0
	
	lw $ra, 0($sp)
	addiu $sp, $sp, 4
	jr $ra



#$a0 -> $s0	guardar o $a0 porque pode ser alterado e vou retorna lo no final
# p1 $s1
# p2 $s2
strrev:
	addiu $sp, $sp, -16
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	
	move $s0, $a0	# $s0 = &str
	move $s1, $a0	# p1 = &str
	move $s2, $a0	# p2 = &str
	
while:	lb $t0, 0($s2)	# $t0 = *p2
	beq $t0, '\0', endwhile
	addiu $s2, $s2, 1	# p2++
	j while
endwhile:
	addiu $s2, $s2, -1	# p2--
while1:
	bge $s1, $s2, endwhile1
	move $a0, $s1		# $a0 = p1
	move $a1, $s2		# $a1 = p2
	jal exchange
	addiu $s1, $s1, 1	# p1++
	addiu $s2, $s2 -1	# p2--
	j while1
endwhile1:
	move $v0, $s0		# return str
	
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	addiu $sp, $sp, 16
	
	jr $ra

exchange:
	lb $t0, 0($a0)	# $t0 = *c1
	lb $t1, 0($a1)	# $t1 = *c2
	
	sb $t1, 0($a0)	# &c1 = *c2
	sb $t0, 0($a1)	# &c2 = *c1
	
	jr $ra
