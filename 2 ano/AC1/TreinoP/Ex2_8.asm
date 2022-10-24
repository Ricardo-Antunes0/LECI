.data
.text
.globl itoa
itoa:

addiu $sp, $sp, -16
sw $ra, 0($sp)
sw $s0, 4($sp)
sw $s1, 8($sp)
sw $s2, 12($sp)

move $s0, $a2	# p = *s
move $s1, $a0	#n
move $s2, $a1	# b
move $t3, $a2

do:
	
	rem $t1, $s1, $s2	#$t1 = digit
	divu $s1, $s1, $s2
	move $a0, $t1
	jal toascii
	sb $v0, 0($s0)
	addi $s0, $s0, 1
while: bgt $s1, $s0, do

li $t0, '\0'
sb $t0, 0($s0)

move $a0, $t3	# $t3 = s
jal strrev
move $v0, $t3

lw $ra,0($sp)
lw $s0, 4($sp)
lw $s1, 8($sp)
lw $s2, 12($sp)
addiu $sp, $sp, 16
jr $ra
	
