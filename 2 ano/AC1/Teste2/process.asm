	.data
#typedef struct
#			Align	Size	Offset 
#	char id		1	1	0
# 	double av	8	8	8
#	int ns		4	4	16
#	char smp[18]	1	18	20
# 	int sum		4	4	40
# } t_sample		1	44
sum:	.double 0.0
# nsei se é preciso isto
ts:	.space 44
	.text
	.globl main
main:

process:	# funcao terminal!!
# ts: $a0
# nval: $a1
#sum:	$f0
#n:	$t0
#k:	$t1
#acc:	$t2
#pu: 	$t3

	l.d $f0, sum
	li $t2, 0	# acc = 0

	mul $t3, $a1, 44	# nval
	addu $t3, $a0, $t3	# pu = ts + nval
for1:	bge $a0, $t3, endfor1
	li $t1, 0		# k = 0
	lw $t0, 16($a0)		# ts-> ns
for2:	bge $t1, $t0, endfor2
	addi $t4, $t1, 20	# k + 20
	addu $t4, $a0, $t4	# &ts->smp[k]
	lb $t4, 0($t4)		# ts->smp[k]
	add $t2, $t2, $t4	# acc += ts->smp[k]
	addi $t1, $t1, 1	# k++
	j for2
endfor2:
	sw $t2, 40($a0)
	mtc1 $t0, $f0
	cvt.d.w $f0, $f0	# double ts->ns
	mtc1 $t2, $f2		
	cvt.d.w $f2, $f2	# double acc
	
	div.d $f2, $f2, $f0	# acc/ts->ns
	s.d $f2, 8($a0)
	
	add.d $f0, $f0, $f2	# sum += ts->av
	
	addiu $a0, $a0, 44	# ts++
	j for1
endfor1:
	cvt.s.d $f0, $f0
	jr $ra