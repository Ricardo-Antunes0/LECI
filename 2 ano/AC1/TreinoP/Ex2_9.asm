.data
d1: .double 5.0
d2: .double 9.0
d3: .double 32.0
.text
.globl f2c
f2c:

mtc1 $a0, $f2
l.d $f4, d3

sub.d $f0, $f2, $f4

l.d $f6, d1
l.d $f8, d2
div.d $f6, $f6, $f8
mul.d $f0, $f6, $f0

jr $ra
