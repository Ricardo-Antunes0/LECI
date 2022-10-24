.data

.text
.globl strlen
strlen:

li $t0, 0
move $t1, $a0
while:
	lb $t2, 0($t1)
	beq $t2, '\0', endwh
	addi $t0, $t0, 1
	addi $t1, $t1, 1
	j while
endwh:
move $v0, $t0
jr $ra
	
	