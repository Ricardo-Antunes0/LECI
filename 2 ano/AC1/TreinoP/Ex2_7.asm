.data
.text
.globl strrev
strrev:
addiu $sp, $sp, -12
sw $ra,0($sp)
sw $s0, 4($sp)
sw $s1, 8($sp)
sw $s2, 12($sp)

move $s0, $a0	#*p1
move $s1, $a0	#*p2
move $s2, $a0

while: lb $t2, 0($s1)
	beq $t2, '\0', endw
	addi $s1, $s1, 1	# &p2++
	j while
endw:
addi $s1, $s1, -1

while1: bge $s0, $s1, endw1
	move $a0, $s0
	move $a1, $s1
	jal exchange
	addi $s0, $s0, 1
	addi $s1, $s1, 1
	j while1
endw1:
	move $v0, $s2
	
	
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	addiu $sp, $sp, 12
	jr $ra
