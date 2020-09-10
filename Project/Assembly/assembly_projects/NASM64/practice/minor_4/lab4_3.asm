%include "simple_io.inc"

global	asm_main

segment .data
str1: db	"123456",0
x: dq 1,2
c: db 'A'

segment	.bss

segment	.text

asm_main:
	enter	0,0
	saveregs
	
	mov	rax, str1
	call	print_string
	call	print_nl

 	mov	rax, qword [x]
	call	print_int
	mov	al, ' '
	call	print_char
	mov	al, ' '
	call	print_char
	mov	rax, qword [x+8]
	call	print_int
	call	print_nl
	
	mov	rax, qword 0
	mov	al, byte [c]
	call	print_char
	call	print_nl

	mov	[str1], byte 'A'
	mov	[str1+1], byte 'B'
	mov	[str1+2], byte 'C'
	mov	[str1+3], byte 0

	mov	rax, str1
	call	print_string
	call	print_nl
	
	mov	qword [x], qword 7
	mov	qword [x+8], qword 8
	
	mov	rax, qword [x]
	call	print_int
	mov	al, ' '
	call	print_char
	mov	al, ' '
	call	print_char
	mov	rax, qword [x+8]
	call	print_int
	call	print_nl
	
	mov	[c], byte 'B'
	mov	al, byte [c]
	call	print_char
	call	print_nl

	restoregs
	leave
	ret
