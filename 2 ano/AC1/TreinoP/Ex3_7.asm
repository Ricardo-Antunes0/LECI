.data

.text
.globl strcpy
strcpy:

move $t0, $a0

li $t1, -1

move $t2, $a0
move $t3, $a1

do: 	addi $t1, $t1, 1	
	addu $t2, $t2, $t1	# dst[i]
	addu $t3, $t3, $t1	#src[i]
	sb $t3, 0($t2)
while: lb $t4, 0($t3)	#*src[i]
	bne $t4, '\0', do
move $v0, $t0
jr $ra