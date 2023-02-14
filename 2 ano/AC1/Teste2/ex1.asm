	.data
	.eqv SIZE, 20
fla:	.space 80
str1:	.asciiz "Invalid argc"
	.text
	.globl main
main:
# Mapa de registos
# argc: $s0
# argv: $s1
# i   : $s2
# p   : $s3
# fla -> $s4

print:	
	addiu $sp, $sp, -20
	sw $ra, 0($sp)
	sw $s0, 4($sp)	#argc
	sw $s1, 8($sp)	# argv
	sw $s2, 12($sp)	# i
	sw $s3, 16($sp)	# p

	move $s0, $a0	# $s0 = argc
	move $s1, $a1	# $s1 = &argv[0]
	li $s2, 0	# i = 0
	la $s3, fla	# p = &fla[0]

if:	ble $s0, 1, else
	bgt $s0, SIZE, else
for:	
	bge $s2, $s0, endfor
	sll $t0, $s2, 2		# i * 4
	addu $t0, $s1, $t0	# &argv[i]
	lw $a0, 0($t0)		# argumento -> * argv[i]
	li $a1, 10	
	jal tof			# tof(argv[i], 10)
	s.s $f0,0($s3)		# *p = tof(argv[i], 10)

	addi $s2, $s2, 1	# i++
	addi $s3, $s3, 4	# p++
endfor:	
	la $a0, fla
	move $a1, $s0
	jal aver
	mov.s $f12, $f0
	li $v0, 2
	j endif
else:	
	la $a0, str1
	li $v0, 4
	syscall
endif:
	la $v0, fla
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	lw $s3, 16($sp)
	addiu $sp, $sp, 20
	jr $ra