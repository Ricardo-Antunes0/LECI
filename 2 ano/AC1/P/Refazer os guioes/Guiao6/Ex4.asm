.data

.eqv print_string, 4 
.eqv print_int10, 1
str1:	.asciiz "Nr. de parametros: "
str2:	.asciiz "\nP"
str3:	.asciiz ": "
.align 2
.text
.globl main
main:	li $t0, 0
	move $t1, $a0
	move $t2, $a1
	
	la $a0, str1
	li $v0, 4
	syscall
	
	move $a0, $t1
	li $v0, 1
	syscall
	
for: 	bge $t0, $t1, endfor
	
	la $a0, str2
	li $v0, 4
	syscall
	
	move $a0, $t0
	li $v0, 1
	syscall
	
	la $a0, str3
	li $v0, 4
	syscall
	
	sll $t4, $t0, 2
	addu $t3, $t2, $t4
	lw $t3, 0 ($t3)
	
	move $a0, $t4
	li $v0, 4
	syscall
	
	addi $t0, $t0, 1
	j for
endfor: li $v0, 0
	jr $ra