	.data
num:	.float 2.59375
num1:	.float 0.0
	.text
	.globl main
main:
	l.s $f4, num1   # 0.0
	l.s $f2, num	# $f2 = 2.59375
	# ou fazia assim
	# la $t1, num1
	# l.s $f2, 0($t1)	# carregar o conteudo que ta no endereço do $t1
do:	
	li $v0, 5
	syscall
	move $t0, $v0	# val = read_int()
	
	mtc1 $t0, $f0		# $f0 = (int)val
	cvt.s.w $f0, $f0	# $f0 = (float)val
	mul.s $f0, $f0, $f2	# res = (float)(val) * 2.5973
	
	mov.s $f12, $f0		# $f12 = val
	li $v0, 2
	syscall
	
while:	c.eq.s $f0, $f4	# TRUE se forem igual -> 1
	bc1f do

	li $v0, 0
	jr $ra