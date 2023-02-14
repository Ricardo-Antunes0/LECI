	.data
str1:	.asciiz "Introduza um numero"
str2: 	.asciiz "\nO valor em binario e: "	
	.text
	.globl main
main:
	li $t0, 0		# i = 0
	li $t9, 0		# flag = 0
	
	la $a0, str1
	li $v0, 4		#print_int(Introduza um numero)
	syscall	
	
	li $v0, 5
	syscall
	move $t1, $v0		# value = read_int()
	
	la $a0, str2
	li $v0, 4		# print_str(O valor em binario e: ")
	syscall
	
for:	bge $t0, 32, endfor
	srl $t2, $t1, 31	#bit = value >> 31

if: 	beq $t9, 1 then
	bnez $t2, then
	j endif
then:
	li $t9, 1		# flag = 1
	
if1: 	rem $t4, $t0, 4		# if( i% 4 == 0)
	bnez $t4, endif1
	li $a0, ' '		#print_char(' ')
	li $v0, 11
	syscall

endif1: addi $a0, $t2, 0x30	#print_Char(0x30 + bit)
	li $v0, 11
	syscall
	
endif:	sll $t1, $t1, 1		# value = value << 1
	addi $t0, $t0, 1	#i++
	j for
	
endfor:
	jr $ra
	
