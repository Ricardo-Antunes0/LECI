.data
str1: .asciiz "Introduza um numero: "
str2: .asciiz "Valor ignorado\n"
str3: .asciiz "A soma dos positivos e': "
.eqv print_string, 4
.eqv read_int, 5
.eqv print_int10,1 
.text
.globl main
main:	li $t2, 0		# i = 0
	li $t0, 0		# soma = 0

for: 	bge $t2, 5, endfor
 	
 	la $a0, str1
 	li $v0, 4
 	syscall
 	
 	li $v0, 5
 	syscall
 	move $t1, $v0 
 	
 	if:	ble $t1, 0, else
 		add $t0, $t0, $t1
 		j endif
 	
 	else:	la $a0, str2
 		li $v0, 4
 		syscall
 	endif:
 		addi $t2, $t2, 1
 		j for
 		
endfor:	la $a0, str3
	li $v0, 4
	syscall
	
	move $a0, $t0
	li $v0, 1
	syscall
	jr $ra