.data
str:	.asciiz "101101"
	.text
	.globl main

main:					# int main(void) {

	addiu	$sp, $sp, -4		#	abrir a pilha 
	sw	$ra, 0($sp)		#
	la	$a0, str		#	$t0 = str;
	jal	atoi			#	atoi(str);
	move	$a0, $v0		#	$a0 = return atoi(str)
	li	$v0, 1			#
	syscall				#
	lw	$ra, 0($sp)		# 
	addiu	$sp, $sp, 4		#
	jr	$ra			# { fim do programa

atoi:
	li $v0, 0
	while: lb $t0, 0($a0)
	ble $t0, '0', endwhile
	bgt $t0, '9', endwhile

	addi $t1, $t0, -0x30
	addiu $a0, $a0, 1
	mul $v0, $v0, 10
	add $v0, $v0, $t1
	j while
endwhile:
 jr $ra
 
	

	