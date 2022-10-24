.data
str1: .asciiz "Introduza um numero: "
str2: .asciiz "\nO valor em binário e': "
.eqv  read_int, 5
.eqv print_string, 4
.eqv print_char, 11
.text
.globl main
main:
	la $a0, str1
	li $v0, 4
	syscall

	li $v0, 5
	syscall
	move $t0, $v0	

	la $a0, str2
	li $v0, 4
	syscall

	li $t2, 0  #i
	li $t3, 0  # flag
	#$t0 value
	#$t1 bit
	
do:
	srl $t1, $t0, 31
	if: 	beq $t3, 1, then
		beqz $t1, endif	
		
		then: 	li $t3, 1
		
		rem $t5, $t2, 4
		if2: bnez $t5, end
			li $a0, ' '
			li $v0, 11
			syscall
		end:	
			addi $t1, $t1, 0x30
			move $a0, $t1
			li $v0, 11
			syscall
	endif:		
		sll $t0, $t0, 1
		addi $t2, $t2, 1
		
while:	blt $t2, 32, do
	jr $ra
