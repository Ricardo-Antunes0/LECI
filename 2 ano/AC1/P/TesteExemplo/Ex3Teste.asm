.data
.eqv N, 35
.eqv read_int, 5
.eqv print_string, 4
.eqv print_int10, 1
.align 2
a: .space 140
B: .space 140
.text
.globl main
main:
#	n_even: $t0
#	n_odd: $t1
#	p1: $t2
#	p2: $t3
#	pultimo: $t4
#	*p1: $t5
#	*p2: $t6

li $t0, 0
li $t1, 0
la $t2, a
li $t9, N
sll $t4, $t9, 2
addu $t4, $t4, $t2
for: bge $t2, $t4, endfor
li $v0, 5
syscall

sw $v0, 0($t2)
addi $t2, $t2, 4
j for

endfor:
la $t2, a
la $t3, B

for1: bge $t2, $t4, endfor1
lw $t5, 0($t2)
rem $t7, $t5, 2
if:	
beq $t7, 0, else
sw $t5, 4($t3)
addi $t1, $t1, 1
addi $t3, $t3, 4
j endif

else: addi $t0, $t0, 1

endif:
addi $t2, $t2, 4

j for1

endfor1:
addu $t8, $t3, $t1
la $t3, B
for2:
bge $t3, $t8, endfor2
lw $a0, 0($t3)
li $v0, 1
syscall

addi $t3, $t3, 4
j for2

endfor2: 
jr $ra
