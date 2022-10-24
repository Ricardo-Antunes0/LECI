.data

.eqv read_string, 8
.eqv print_int10, 1
.eqv size 20
str: .space size

# num: $t0 
# i: $t1 
# str: $t2 
# str+i: $t3 
# str[i]: $t4
.text
.globl main
main:
li $t0, 0	#num
li $t1, 0	#i

la $a0, str  # endereco do 1º elemento
la $a1, size #
li $v0, 8    # Read_int(str, size)
syscall


while:  la $t2, str 
	addu $t3, $t2, $t1
	lb $t4, 0($t3)
	beq $t4, '\0', endWhile
	
	if: blt $t4, '0', endif
	    bgt $t4, '9', endif
	    addi $t0, $t0, 1
	    
	endif:	addi $t1, $t1, 1
		j while
	
endWhile:
	li $a0, $t0
	li $v0, 1 #print_int(num)
	syscall	
	jr $ra
	
			

