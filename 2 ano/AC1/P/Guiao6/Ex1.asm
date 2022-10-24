.data

.eqv print_string, 4
.eqv print_char, 11
.eqv size, 3
str1: .asciiz "Array" 
str2: .asciiz "de"
str3: .asciiz "ponteiros"
str4: .asciiz "\n"
array: .word str1,str2,str3


.text
.globl main
main:

	la $t1, array # p = &array[0]
	li $t0, 0 		#i = 0
for:
	bge $t0, size, endfor
	sll $t2, $t0, 2 	#tmp = i * 4
	addu $t2, $t2, $t1 	#tmp = &array[0] + tmp (=) tmp = & array[i]
	
	lw $a0, 0($t2)
	li $v0, 4
	syscall

	la $a0, str4 #print_char("\n")
	li $v0, 11
	syscall
	
	addi $t0, $t0, 1
	j for
endfor: 
jr $ra
