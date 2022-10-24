.data
.eqv size, 3
.eqv print_str, 4
.eqv print_char, 11
str1: .asciiz "Array"
str2: .asciiz "de"
str3: .asciiz "ponteiros"
str4: .asciiz "\n "
array: .word str1,str2,str3
.text
.globl main
main:	la $t0, array		# p = & array[0]
	li $t1, size		# size = $t1
	sll $t1, $t1, 2		# tmp = size * 4
	add $t2, $t1, $t0	# pultimo = &array[0] + (size*4) = array[size]
	
	for: 	bge $t0, $t2, endfor
		lw $a0, 0($t0)
		li $v0, 4
		syscall
		
		la $a0, str4
		li $v0, 11
		syscall
		
		addi $t0, $t0, 4
		j for
		
	endfor: jr $ra