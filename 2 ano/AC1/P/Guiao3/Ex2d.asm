.data
str1: .asciiz "Introduza um numero: "
str2: .asciiz "O valor em binario e': "
.eqv print_string, 4
.eqv read_int, 5
.eqv print_char, 11

.text
.globl main

main:
li $t5, 0 #flag = 0
lui $t0, 0 #value
lui $t1, 0 # bit
lui $t2, 0 # i 

la $a0, str1
li $v0, 4 #Print_string(Introduza um numero)
syscall

li $v0, 5
syscall
move $t0, $v0 #value = read_int()

la $a0, str2
li $v0, 4
syscall

for: 
	bge $t2, 32, endFor

	andi $t3, $t1, 0x80000000 #(value & 0x80000000)
	srl $t1, $t0, 31 #bit = (value & 0x80000000) >> 31;
	
	if: bne $t5, 1, if2 
	    beq $t1, 0, endIf 
	
	addi $t4, $t1, 0x30 # 0x30 + bit
	move $a0, $t4  #argumento � (0x30 + bit)
	li $v0, 11  #Print_char()
	syscall

endFor:
jr $ra