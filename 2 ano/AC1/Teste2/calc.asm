	.data
sum:	.double 0.0
k1:	.double 0.035
	.text
	.globl main
main:

#Mapa de registos:
#val	: 	$f12
#array  :	$a0
# fx	: 	$t0
# k	:	$t1
# sum	: 	$f0
# aux	: 	$f2
calc:
	li $t0, 1	# fx = 1
	li $t1, 0	# k = 0
	l.d $f0, sum	# $f0 = sum = 0.0
	l.d $f8, k1
	
do:	
	addi $t2, $t1, 1	# k + 1
	mul $t0, $t0, $t1	# fx *= (k+1)
	
	sll $t2, $t1, 3		# k * 8
	addu $t2, $a0, $t2	# &array[k]
	l.d $f2, 0($t2)		# array[k]
	
	mtc1 $t0, $f4		# $f4 = fx
	cvt.d.w $f4, $f4	# $f4 = (double) fx
	div.d $f2, $f2, $f4	# aux
	add.d $f0, $f0, $f2	# sum = sum + aux
	
	s.d $f0, 0($t2)		# array[k] = sum
	addi $t1, $t1, 1	# k++
while:	c.le.d $f2, $f8		#
	bc1f do
	
	cvt.w.d $f0, $f0	# (int) sum
	mfc1 $v0, $f0		# $v0 = sum
	jr $ra