.data

.eqv size 10
.eqv print_int10, 1
.eqv print_string, 4

lista: .word 8,-4, 3, 5, 124, -15, 87, 9, 27, 15
str1: .asciiz "\nConteudo do array:\n"
str2: .asciiz ": "

.text
.globl main
main: 

# p = $t0
# *p = $t1
# lista + size = $t2

la $a0, str1
li $v0, 4
syscall

for: 	la $t0, lista
	li $t2, size
	sll $t2, $t2, 2
	addu $t2, $t2, $t0
	bge $t0, $t2, endfor
	
	lw $t1, 0($t0)
	
	move $a0, $t1
	li $v0, 1
	syscall
	
	la $a0, str2
	li $v0, 5
	syscall
	
	addi $t0, $t0, 1
	j for 
	
endfor:
	jr $ra	