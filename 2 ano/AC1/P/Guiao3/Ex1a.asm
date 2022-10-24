
# Mapa de registos:
# $t0 – soma
# $t1 – value
# $t2 - i
 .data
str1: .asciiz "Introduza um numero: "
str2: .asciiz "Valor ignorado"
str3: .asciiz "A soma dos positivos e': "
 .eqv print_string,4
 .eqv read_int, 5
.text
.globl main
main:
li $t0, 0 #soma
li $t1, 0 #value
li $t2, 0 #i

for: bge $t2, 5, endFor   #Se o i >= 5 entao saio do for, se nao continuo e incremento o i
	la $a0, str1
	li $v0, print_string   #printstring ou 4, é igual
	syscall

	li $v0, 5 #Read_int	
	syscall
	move $t1, $v0 #value = read_int 
			
	if: ble $t1, 0, else #If (value > 0)
	add $t0, $t0, $t1
	j endIf 						 						
else:
la $a0, str2 
li $v0, 4
syscall
#Posso por j endIf mas nao é necesario

endIf:
addi $t2, $t2,1 
j for
	
endFor:
la $a0, str3  
li $v0, 4
syscall

move $a0, $t0 
li $v0, 1 # print_string("...")
syscall

jr $ra