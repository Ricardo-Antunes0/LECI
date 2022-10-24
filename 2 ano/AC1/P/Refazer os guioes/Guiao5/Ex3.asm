.data

.eqv size, 10
.eqv TRUE, 1
.eqv FALSE, 0
.eqv print_string, 4
.eqv print_int10, 1
.eqv read_int, 5
str1: .asciiz ", "
str2: .asciiz "Elements of the list: \n"
str3: .asciiz "Add an integer to the list: \n"

.align 2
lista: .space 40
	
.text
.globl main
main:	li $t0, 0			# i = 0

	la $a0, str3
	li $v0, 4
	syscall

	for:	la $t1, lista		# p =  & lista[0]
		bge $t0, size, endfor
		li $v0, 5		# $v0 = read_int()
		syscall
		
		sll $t2, $t0, 2		# $t3 = i * 4
		addu $t2, $t2, $t1	# lista[i] = lista[0] + (i*4)
		sw $v0, 0($t2)		# Guardar o valor lido no reg $t3, ou seja, lista[i] 
		addi $t0, $t0, 1	# i++
		j for
		
	endfor: la $t6, lista
	 	li $t1, size
		sub $t8, $t1, 1		
	do:	li $t4, FALSE		#houveTroca = FALSE
		li $t3, 0
		for1:	bge $t3, $t8,  while
			la $t2, lista
			if:	sll $t7, $t3, 2		# tmp = i * 4
				addu $t7, $t7, $t2	# lista[i]
				lw $t8, 0($t7)
				lw $t9, 4($t7)
				ble $t8, $t9, endif
				
				sw $t9, 0($t7)		#lista[i] = lista[i+1]
				sw $t8, 4($t7)		#lista[i+1] = lista[i] 
				li $t4, TRUE		#houveTroca = TRUE	
			endif:	addi $t3, $t3, 1	# i++
				j for1
	while:	beq $t4, 1, do
		
		la $a0, str2
		li $v0, 4
		syscall
			
		li $t0, 0	# i = 0	
										
		for2:	la $t2, lista		# p = & lista[0]
			bge $t0, $t1, endfor2	
			sll $t3, $t0, 2		# tmp = i * 4
			addu $t3, $t3, $t2	# lista[i] = lista[0] + i*4
			
			lw $a0, 0($t3)
			li $v0, 1
			syscall 
	
			la $a0, str1
			li $v0, 4
			syscall
			
			addi $t0, $t0, 1
			j for2
			
		endfor2: 	jr $ra
