.data

.eqv read_int, 5
.eqv print_string, 4 
.eqv sizex4, 20
.eqv size, 5

lista: .space sizex4
str: .asciiz "\nIntroduza um numero: "
 
.text
.globl main
main:

for: 	li $t0, 0
	bge $t0, size, endfor
	
	la $a0, str
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall 
	
	la $t1, lista
	sll $t2, $t0, 2
	addu $t3, $t2, $t1 # i + ofsset e guardo no $t3
	sw $v0, 0($t3)
	
	addi $t0, $t0, 1
	j for
	
endfor:
	jr $ra