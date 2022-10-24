.data

.eqv size, 4
.eqv print_int10,1

array: .word 7692, 23, 5, 234

.text
.globl main
main:	li $t3, 0 #Soma = 0
	la $t0, array # p = & array[0]
	li $t4, size
	sub $t4, $t4, 1
	sll $t4, $t4, 2 #Size ja é Size = 4 x 4 logo 16 endereços
	addu $t1, $t0, $t4 #pultimo = &array[0] + o numero de endereços ate chegar ao ultimo
	#sabendo que cada int ocupada 4 endereços

	while: beq $t0, $t1, endwhile
		lw $t2, 0($t0)
		addu $t3, $t3, $t2
		addi $t0, $t0, 1
		j while
	
	endwhile:
		move $a0, $t3
		li $v0, print_int10
		syscall
		jr $ra
		
		
		
		