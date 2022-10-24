.data
.eqv size, 20
.eqv read_string, 8
.eqv print_int10, 1 

str: .space 20
 
.text
.globl main
main:	la $a0, str
	li $a1, size
	li $v0, 8
	syscall
		
	li $t0, 0 #num
	li $t1, 0 #i		
	
	while:	la $t2, str
		addu $t3, $t2, $t1 # $t3 = str+i = &str[i]
		lb $t4, 0($t3)
		beq $t4, '\0', endwhile
	 	if: 	blt $t4, '0', endif
	 		bgt $t4, '9', endif
	 		addi $t0, $t0, 1
	 	endif:
	 		addi $t1, $t1, 1
	 		j while
	  endwhile:
		move $a0, $t0
		li $v0, 1
		syscall 
		jr $ra