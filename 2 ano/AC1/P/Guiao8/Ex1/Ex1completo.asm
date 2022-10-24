
.data
str: .asciiz "2020 e 2024 sao anos bissextos"
.eqv print_int10, 1
.text
.globl main
main:

addiu $sp, $sp, -4
sw $ra, 0($sp)


la $a0, str
jal atoi
move $a0, $v0
li $v0, 1
syscall

lw $ra, 0($sp)
addiu $sp, $sp, 4
jr $ra





atoi:	
	li $v0, 0	# res = 0

	while:
		lb $t2, 0 ($a0)	# $t2 = conteudo do ponteiro
 		blt $t2, '0', endwhile
		bgt $t2, '9', endwhile
	
		subu $t0, $t2, '0'	#digit = *s++ - '0' 
		addiu $a0, $a0, 1	# *s++
	
		mul $v0, $v0, 10
		add $v0, $v0, $t0
		j while
	endwhile:
		jr $ra
	
	