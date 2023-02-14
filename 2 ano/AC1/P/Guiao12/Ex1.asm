	.data
	.eqv MAX_STUDENTS, 4
	.align 2	# n era necessarios porque .eqv n ocupa espaço
st_array: .space 176	# cada elementos do array ocupa 44 bytes
	.align 2
media:	.space 4	# static float media ->  significa que ta guardado no segmento de dados, ou seja , na mem
str1:	.asciiz "N. mec: "
str2:	.asciiz "Primeiro Nome: "
str3:	.asciiz "Ultimo nome: "
str4:	.asciiz "Nota: "
str5:	.asciiz "\nMedia: "

max_grade:	.float -20.0
sum:		.float 0.0
	.text
	.globl main
main:

	addiu $sp, $sp, -4
	sw $ra,0($sp)
	
	la $a0, st_array
	li $a1, MAX_STUDENTS
	jal read_data
	
	la $a0, st_array
	li $a1, MAX_STUDENTS
	la $a2, media
	jal max
	
	move $t0, $v0	# pmax = max(..)
	
	la $a0, str5
	li $v0, 4		# print_string("\nMedia: ")
	syscall
	
	la $t1, media		# &media
	
	l.s $f12, 0($t1) 	# *media
	li $v0, 2
	syscall
	
	move $a0, $t0	# pmax
	jal print_student
	
	li $v0, 0	# return 0
				
	lw $ra,0($sp)
	addiu $sp, $sp, 4
	
	jr $ra


read_data:
	li $t0, 0	# i = 0
	la $t1, st_array
	move $t9, $a1
	
for_read:
	bge $t0, $t9, endfor_read
	
	la $a0, str1
	li $v0, 4
	syscall	

	li $v0, 5
	syscall
	
	mul $t2, $t0, 44	# i * 44
	addu $t2, $t1, $t2	# &st_array[i]
	sw $v0, 0($t2)		# st[i].id_number = read_int();
	
	la $a0, str2		# "Primeiro Nome: "
	li $v0, 4
	syscall
	
	addiu $a0, $t2, 4	# $a0 = &st[i].first_name  
	li $a1, 17		#
	li $v0, 8
	syscall
	
	la $a0, str3
	li $v0, 4
	syscall
	
	addiu $a0, $t2, 22	# $a0 = &st[i].last_name
	li $a1, 14
	li $v0, 8
	syscall
	
	la $a0, str4
	li $v0, 4
	syscall
	
	li $v0,6	# print_float
	syscall
	s.s $f0, 40($t2)	# &st[i].grade	-> que está no st[0]+ 40 bytes
	

	addi $t0, $t0, 1	# i++
	j for_read
endfor_read:	
	jr $ra	
#####################################################################
max:	
	move $t0, $a0	# p = st
	mul $t1, $a1, 44	# ns * 44	
	addu $t1, $t0, $t1	# st+ns 
	l.s $f2, sum		# $f2 = 0.0
	l.s $f4, max_grade
for_max:
	bge $t0, $t1, endfor_max

	l.s $f6,40($t0)
	add.s $f2, $f2, $f6
if_max:	
	c.le.s $f6, $f4	
	bc1t endif_max
	
	mov.s $f4, $f6
	move $v0, $t0	# pmax = p
endif_max:
	addi $t0, $t0, 44	# p++
	j for_max
endfor_max:
	mtc1 $a1, $f4
	cvt.s.w $f4, $f4
	
	div.s $f6, $f2, $f4	# sum / ns
	s.s $f6, 0($a2)

	jr $ra

#############################################################################
print_student:
	move $t0, $a0 # &p
	
	lw $a0, 0($t0)
	li $v0, 36
	syscall
	
	addiu $a0, $t0, 4
	li $v0, 4
	syscall
	
	addiu $a0, $t0, 22
	li $v0, 4
	syscall
	
	l.s $f12,40($t0)
	li $v0, 2
	syscall
	
	jr $ra