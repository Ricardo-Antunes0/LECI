.data
.eqv size, 5
.eqv TRUE, 1
.eqv FALSE, 0
.eqv print_string, 4
.eqv print_int10, 1
.eqv read_int, 5
str1: .asciiz ", "
str2: .asciiz "Elements of the list: \n"
str3: .asciiz "Add an integer to the list: \n"
.align 2
lista: .space 20
.text
.globl main
main:	
	# i = $t0
	# size = $t1
	# &lista[0] = $t2
	# i * 4 = $t3
	#houve_troca = $t4
	#p = $t5
	# pultimo = $t6
	# * p = $t8
	# *(p+1) = $t9

	li $t0, 0			# i = 0
	li $t1, size
	
	la $a0, str3
	li $v0, 4
	syscall

	for:	la $t2, lista
		bge $t0, $t1, endfor
		li $v0, 5		# $v0 = read_int()
		syscall
		
		sll $t3, $t0, 2		# $t3 = i * 4
		addu $t3, $t3, $t2	# lista[i] = lista[0] + (i*4)
		sw $v0, 0($t3)		# Guardar o valor lido no reg $t3, ou seja, lista[i] 
		addi $t0, $t0, 1	# i++
		j for
		
	endfor: 	sll $t7, $t1, 2		#size * 4
			subu $t7 $t7, 1		#size - 1
			add $t6, $t1, $t7	# pultimo
		
	do:	li $t4, FALSE	#houveTroca = false
		la $t2, lista	# $t2 = &lista[0]
		for1: 	bge $t2, $t6, while
			if:	lw $t8, 0($t2)	#*p
				lw $t9, 4($t2)	#*(p+1)
				ble $t8, $t9 endif
				
				sw $t9, 0($t2)
				sw $t8, 4($t2)
				li $t4, TRUE
			endif:
				addi $t2, $t2, 4 #p++
				j for1
		
	while: beq $t4, TRUE, do	
	
	li $t0, 0
	for2:	la $t2, lista	# p = & lista[0]
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
	endfor2: jr $ra	