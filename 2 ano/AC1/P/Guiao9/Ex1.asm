.data
k1: .float 2.59375
k2: .float 0.0
.eqv read_int, 5
.eqv print_float, 2
.text
.globl main
main:

do:
	li $v0, 5
	syscall
	move $t0, $v0	# val = read_int()

	mtc1 $t0, $f2	# float(val)

	l.s $f4, k1	# $f4 = 2.59375
	mul.s $f12, $f2, $f4	#res = ...

	li $v0, 2	#print_float(res)
	syscall
	
	l.s $f6, k2
while: 	c.eq.s $f12, $f6
	bc1f do
	
l.s $f0, 0($0)

jr $ra
