#num: $t0
#i: $t1
#str: $t2
#str+i: $t3
#str[i]: $t4
.data
.eqv read_string, 8
.eqv print_int10, 1
.eqv SIZE, 20
str: .space SIZE
.text
.globl main
main:

li $t0, 0
li $t1, 0

la $a0, str
li $a1, SIZE
li $v0, 8
syscall #Read_int()


while: la $t2, str
       addu $t3, $t2, $t1
       lb $t4, 0($t3)
       beq $t4, '\0', endwhile
       
       if: blt $t4, '0', endif
       if2: bgt $t4, '9', endif
       addi $t0, $t0, 1
	
       endif:
       addi $t1, $t1, 1
       j while
	
       endwhile:
       move $a0, $t0
       li $v0, 1
       syscall

	jr $ra
