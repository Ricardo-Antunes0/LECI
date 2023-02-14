	.data
str:	.asciiz "Arquitetura de computadores I"
	.text
	.globl main
main:
	addiu $sp, $sp, -4
	sw $ra, 0($sp)
	
	la $a0, str	# $a0 = &str[0]
	jal strlen
	
	move $a0, $v0
	li $v0, 1
	syscall
	
	li $v0, 0	# return 0
	
	lw $ra, 0($sp)
	addiu $sp, $sp, 4
	jr $ra	
			
strlen:	
	li $v0, 0	# len = 0	
while:	lb $t2, 0($a0)	# $t2 = *s
	beq $t2, '\0', endwhile
	addi $v0, $v0, 1	# len++
	addiu $a0, $a0, 1	# &s++
	j while
endwhile:
	jr $ra
