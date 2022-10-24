.data
f1: .float 2.59375
f2: .float 0.0
.text
.globl main
main:
do:

li $v0, 5
syscall
mtc1 $v0, $f2
l.s $f6, f1
cvt.s.w $f2, $f2
mul.s $f4, $f2, $f6

mov.s $f12, $f4 
li $v0, 2
syscall

while: 
l.s $f8, f2
c.eq.s $f4,$f8
bc1f do

li $v0, 0
jr $ra
