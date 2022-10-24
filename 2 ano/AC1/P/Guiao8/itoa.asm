
itoa:
	addiu $sp, $sp, -20	#Colocar espaço na pilha
	sw $ra, 0($sp)		#guardar o $ra na pilha
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	
	move $s0, $a0		# s0 = n
	move $s1, $a1		# s1 = b
	move $s2, $a2		# s2 = s
	move $s3, $a2		# s3 = s
	
	
	do:	
		rem $t0, $s0, $s1	# digit = n % b
		divu $s0, $s0, $s1	# n = n/b
		move $a0, $t0 		# $a0 = digit
		jal toascii 		# toascii(digit)
		sb $v0, 0($s3)		# *p == toascii(digit)	
		addi $s3, $s3, 1	# *p++
	while3: bgtz $s0, do
		sb $s0, 0($s3)		# *P = '\0'
		move $a0, $s2			
		jal strrev		# strrev(s)
	
		move $v0, $s2		# return s
		
		lw $ra, 0($sp)		#ir buscar o $ra
		lw $s0, 4($sp)
		lw $s1, 8($sp)
		lw $s2, 12($sp)
		lw $s3, 16($sp)
		addiu $sp, $sp, 20	
		jr $ra
	
	
		 	
		

		
