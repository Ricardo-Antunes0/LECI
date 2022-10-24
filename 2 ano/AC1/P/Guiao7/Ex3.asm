.data

.eqv str_max_size, 30
.eqv print_string, 4
.eqv print_int10, 1
str1: .asciiz "I serodatupmocC ed  arutetiuqrA"
str2: .space str_max_size
str3: .asciiz "String too long"
 
.text
.globl main
main:

addiu $sp, $sp, -4
sw $ra, 0($sp)


if:	la $a0, str1
	jal strlen
	bgt $v0, str_max_size, else
	la $a0, str2
	la $a1, str1
	jal strcpy
	
	la $a0, str2
	li $v0, 4
	syscall
	
	li $a0, '\n'
	li $v0, 4
	syscall
			
	la $a0, str2
	jal strrev
	
	move $a0, $v0
	li $v0, 4
	syscall
	
	li $t3, 0 #exit_value = 0
	j endif
else:
	la $a0, str3
	li $v0, 4
	syscall
	
	la $a0, str1 
	jal strlen
	move $a0, $v0
	li $v0, 1
	syscall
	
	li $t3, -1


endif:move $v0, $t3
	
lb $ra, 0($sp)
addiu $sp, $sp, 4
jr $ra


strcpy:
	move $t2, $a0
	li $t0, -1 # i = -1
	do:
		addi $t0, $t0, 1	# i++
		addu $a0, $a0, $t0 	# $a0 = &(dst[i])
		addu $a1, $a1, $t0	# $a1 = &(src[i])
		lb $t1, 0($a1)		# $$t1 = * src[i]
		sb $t1, 0($a0)		# dst[i] = src[i]
		
	while5:   bne $t1, '\0', do
	
	move $v0, $t2
	jr $ra
		
	
strlen:					# int strlen(char *s) {
	li	$t1, 0			# 	len = 0
while:	lb $t0, 0($a0)		# 	while(*s++ != '\0')
	addiu	$a0, $a0, 1		#
	beq	$t0, '\0', endw		# 	{
	addi	$t1, $t1, 1		# 		len++;
	j	while			# 	}
endw:	move	$v0, $t1		# 	return len;
	jr 	$ra			# }
	
strrev: 					# char * strrev(char *str) {
	addiu 	$sp, $sp, -16 			# 	reserva espaço na stack
 	sw 	$ra, 0($sp) 			# 	guarda endereço de retorno
	sw 	$s0, 4($sp) 			# 	guarda valor dos registos
	sw 	$s1, 8($sp) 			# 	$s0, $s1 e $s2
	sw 	$s2, 12($sp) 			#
	move 	$s0, $a0 			# 	registo "callee-saved"
	move 	$s1, $a0 			# 	p1 = str
	move 	$s2, $a0 			# 	p2 = str
while1: lb	$t1, 0($s2)			#	$t1 = *p2;
	beq	$t1, '\0', endw1		# 	while( *p2 != '\0' ) {
	addiu	$s2, $s2, 1 			# 		p2++;
	j 	while1 				# 	}
endw1:	addiu	$s2, $s2, -1 			# 	p2--;
while2: bge	$s1, $s2, endw2			# 	while(p1 < p2) {
	move 	$a0, $s1 			#
	move 	$a1, $s2			#
 	jal 	exchange 			# 		exchange(p1,p2);
 	addiu	$s1, $s1, 1			#
 	addiu	$s2, $s2, -1			#
	j 	while2 				# 	}
endw2:	move 	$v0, $s0 			# 	return str
	lw 	$ra, 0($sp) 			# 	repõe endereço de retorno
 	lw 	$s0, 4($sp) 			# 	repõe o valor dos registos
	lw 	$s1, 8($sp)			# 	$s0, $s1 e $s2
	lw 	$s2, 12($sp) 			#
	addiu 	$sp, $sp, 16			# 	liberta espaço da stack
	jr 	$ra 				# }	termina a sub-rotina
						#
						#
exchange:					# void cxchange(char *c1, char *c2) {
	lb	$t0, 0($a0)			#	$t0 = *c1:
	lb	$t1, 0($a1)			#	$t1 = *c2;
	sb	$t0, 0($a1)
	sb	$t1, 0($a0)
	jr	$ra				# } fim do programa


