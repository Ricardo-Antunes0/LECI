.data
.text
.globl strcat
strcat:

addiu $sp, $sp, -16
sw $ra, 0($sp)
sw $s0, 4($sp)
sw $s1, 8($sp)
sw $s2, 12($sp)

move $s0, $a0	#p
move $s1, $a1	#src
move $s2, $a0	#dst para return 


while:
	lb $t0, 0($s0)
	beq $t0, '\0', endw
	addi $s0, $s0, 1	#p++
	j while
endw:
	move $a0, $s0
	move $a1, $s1
	jal strcpy
	
	move $v0, $s2	#return dst
	
lw $ra, 0($sp)
lw $s0, 4($sp)
lw $s1, 8($sp)
lw $s2, 12($sp)
addiu $sp, $sp, 16

jr $ra