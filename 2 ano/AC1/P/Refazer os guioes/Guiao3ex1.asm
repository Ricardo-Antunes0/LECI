.data

.eqv print_string, 4
.eqv print_int10, 1
.eqv read_int, 5

str1: .asciiz "Introduza um numero: "
str2: .asciiz "Valor ignorado\n"
str3: .asciiz "A soma dos positivos e': "
.text
.globl main
main:
	for: 	li $t0, 0  #i = 0
		li $t1, 0  #soma = 0
		bge $t0, 5, endfor
		
		la $a0, str1 	 #print_string(str1)
		li $v0, 4	
		syscall
		
		li $v0, 5	#Read_int()	
		syscall		#Value = read_int()
		move $t2, $v0
		
		if: ble $t2, 0 else
			addu $t2, $t2, $t1
			j endIf
		else:
			la $a0, str2
			li $v0, 4 	#Print_String(str2)
			syscall
		endIf:
			addi $t0, $t0, 1
			j for
		
	endfor:
		la $a0, str3
		li $v0, 4
		syscall
		
		move $a0, $t1
		li $v0, 1
		syscall
		
		jr $ra