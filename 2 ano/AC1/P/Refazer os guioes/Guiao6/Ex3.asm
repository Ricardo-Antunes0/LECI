.data
.eqv size, 3
.eqv print_char, 11
.eqv print_str, 4
.eqv print_int10, 1
str1: .asciiz "Array"
str2: .asciiz "de"
str3: .asciiz "ponteiros"
str5: .asciiz "\nString #"
str6: .asciiz ": "
array: .word str1,str2,str3
.text
.globl main
main:	li $t0, 0	# i = 0
	for: 	bge $t0, size, endfor
		la $a0, str5
		li $v0, 4
		syscall
		
		move $a0, $t0
		li $v0, 1
		syscall
		
		la $a0, str6
		li $v0, 4
		syscall
		
		li $t2, 0	# j = 0
		
		while:	la $t1, array		# $t3 = & array[0]
			sll $t4, $t0, 2		# tmp = i * 4
			addu $t4, $t4, $t1	# &array[i] = &array[0] + (i*4)
			lw $t4, 0 ($t4)		# array[i] = &array[i][0]
			addu $t4, $t4, $t2	#array[i][j]
			lb $t4, 0($t4)
			beq $t4, '\0', endwhile
			move $a0, $t4
			li $v0, 11
			syscall
			
			li $a0, '-'
			li $v0, 11
			syscall
			
			addi $t2, $t2, 1	#j++
			j while
			
		endwhile: 	addi $t0, $t0, 1
				j for
			
	endfor: jr $ra

