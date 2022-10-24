.data

.eqv read_string, 4
.eqv read_int, 5
.eqv read_string, 8
.eqv read_float, 6

.eqv stdiof, 0
.eqv stfnof, 4
.eqv stlnof, 22
.eqv stgrof, 40
.eqv stmxfn, 17
.eqv stmxln, 14 
.eqv max_students, 4
.eqv sizest, 44	#sizeof(student)

st_arr: .space 176
str1: .asciiz  "n mec"
str2: .asciiz "primeiro nome"
str3: .asciiz "ultimo nome"
str4: .asciiz "nota: "
.align 2
media: .float 
max_grade
.text
.globl main




read_data:
	move $s0, $a0
	move $s1, $a1
	
	li $s2, 0
f_rd: bge $s2, $s1,end_f_rd
	
	la $a0, str1
	li $v0, print_string
	syscall
	move $t0, $s0
	li $t2, 44
	mul $t2, $s2, $t2
	addu $t0, $t0, $t2
	li $v0, read_integer
	syscall
	sw $v0, stlnof($st0)
	
	la $a0, str2
	li $v0, print_string
	syscall
	move $t0, $s0 			# $t0 é o endereco/ponteiro para o inicio do bloco do array de estruturas- os tais 176 bytes
	li $t2, 44			#sizeof(student)
	mulu $t2, $s2, $t2		# i =  sizeof(student)
	addu $t0, $t0, $t2		#endereco inicial da estrutura que e a instancia i do array  de estruturas st_arr
	addiu $a0, $t0, stfnof		
	li $a1, stmxfn
	li $v0, read_string
	syscall 

	la $a0, str3
	li $v0, print_string
	syscall
	move $t0, $s0
	li $t2, 44			#sizeof(student)
	mulu $t2, $s2, $t2		# i =  sizeof(student)
	addu $t0, $t0, $t2		#endereco inicial da estrutura que e a instancia i do array  de estruturas st_arr
	addiu $a0, $t0, stfnof		
	li $a1, stmxln
	li $v0, read_string
	syscall 

	la $a0, str4
	li $v0, print_string
	syscall
	move $t0, $s0
	li $t2, 44			#sizeof(student)
	mulu $t2, $s2, $t2		# i =  sizeof(student)
	addu $t0, $t0, $t2		#endereco inicial da estrutura que e a instancia i do array  de estruturas st_arr
	addiu $a0, $t0, stfnof		
	li $a1, stmxln
	li $v0, read_string
	syscall
	s.s $f0, stgrof($t0)









main:
addiu $sp, $sp, -4
sw $ra, 0($sp)

la $a0, st_arr
li $a1, max_students
jal read_data

lw $ra, 0($sp)
addiu $sp, $sp, 4
jr $ra


