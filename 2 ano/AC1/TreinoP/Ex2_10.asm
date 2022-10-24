.data
d1: .double 1.0
d2: .double 0.5
d3: .double 0.0
.text
.globl sqrt
sqrt:
	
l.d $f0, d1	# $f0 = xn
li $t0, 0	 # i = 0
l.d $f2, d3	#$f2 = 0.0

mtc1 $a0, $f4	# $f4 = double val

l.d $f8, d2

if: c.le.d $f4, $f2
	bc1t else
	
	do:
		mov.d $f6, $f0	# aux = xn
		div.d $f4, $f4, $f0	# $f4 = val/xn
		add.d $f0, $f0, $f4	# $f0 = xn + val/xn
		mul.d $f0, $f0, $f8	# xn = xn + val/xn * 0.5
	while: c.eq.d $f6, $f0
		bc1f end1
		j else
		end1:
			addi $t0, $t0 1
			blt $t0, 25, do
			
else:
l.d $f0, d3
jr $ra	
		
