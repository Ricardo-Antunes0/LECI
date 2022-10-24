.data
.eqv size, 20
.eqv read_string, 8
.eqv print_int10, 1
str: .space size
# num: $t0 
# p: $t1 
# *p: $t2
.text
.globl main
main:

li $t0, 0  #num = 0

la $a0, str
li $a1, size
li $v0, 8
syscall

la $t1, str

while: 	lb $t2, 0($t1) #*P
	beq $t2, '\0', endWhile
	
	if: 	blt $t2, '0', endif
		bgt $t2, '9', endif
		addi $t0, $t0, 1
	endif:
		addiu $t1, $t1, 1
		j while
		
endWhile:
	
	move $a0, $t0 #argumento da funcao = num
	li $v0, 1 #print_int(Num)
	syscall
	jr $ra
	
