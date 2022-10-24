.data
str: .asciiz "Arquitetura de computadores I"
.eqv print_int10, 1

.text
.globl main
main:
addiu $sp, $sp, -4
sw $ra, 0($sp)

la $a0, str
jal strlen
move $a0, $v0
li $v0, 1
syscall

li $v0, 0

lw $ra, 0($sp)
addiu $sp, $sp, 4

jr $ra


strlen: 
	li $t0, 0
	while:
		lb $t1, 0($a0)
		beq $t1, '\0', endw
		addi $t0, $t0, 1
		addi $a0, $a0, 1
		j while
	endw:
	move $v0, $t0
	jr $ra
