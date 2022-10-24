.data
.eqv size, 3
.eqv print_str, 4
.eqv print_char, 11
.eqv print_int10, 1
str1: .asciiz "Array" 
str2: .asciiz "de"
str3: .asciiz "ponteiros"
str4: .asciiz "\nString #"
str6: .asciiz ": "
.align 2
array: .word str1,str2,str3

.text
.globl main
main:
li $t0, 0


for: 				# i = 0
	bge $t0,size, endfor
	
	la $a0, str4
	li $v0, 4
	syscall
	
	move $a0, $t0
	li $v0, 1
	syscall
	
	la $a0, str6
	li $v0, 4
	syscall
	
	li $t1, 0			# j = 0
	while:	la $t3, array		# $t3 = & array[0]
		sll $t2, $t0, 2		# $t2 = i * 4
		addu $t3, $t3, $t2	# $t3 = &array[0] + (i*4)
		lw $t3, 0($t3)		#$t3 = *array[i] = & array[i][0]
		addu $t3, $t3, $t1
		lb $t3, 0($t3) 
		beq $t3,'\0' , endwhile
		
		move $a0, $t3		#print_char(array[i][j]); 
		li $v0, 11
		syscall 
	
		li $a0, '-'		#print_char('-'); 
		li $v0, 11
		syscall
		
		addi $t1, $t1, 1
		j while
		
	endwhile:
		addi $t0, $t0, 1
		j for
		
endfor:
jr $ra
