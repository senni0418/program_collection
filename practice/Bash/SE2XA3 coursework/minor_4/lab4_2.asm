%include "simple_io.inc"

global asm_main

SECTION .data
;x: dq 5
x: dq 7

SECTION .text

asm_main:
	enter 0,0
	saveregs
   mov	rax, [x]
	call	print_int
	mov	al,'*'
	call	print_char
	mov	rax, [x]
	call	print_int
	mov	al,' '
	call	print_char
	mov	al,'+'
	call	print_char
	mov	al,' '
	call	print_char
	mov	al,'3'
	call	print_char
	mov	al,'*'
	call	print_char
	mov	rax, [x]
	call	print_int
	mov	al,' '
	call	print_char
	mov	al,'-'
	call	print_char
	mov	al,' '
	call	print_char
	mov	rax, [x]
	call	print_int
	mov	al,' '
	call	print_char
	mov	al,'='
	call	print_char
	mov	al,' '
	call	print_char
;;;;;;;;now compute the result
	mov	rax, [x]
	mul	rax
	mov	rbx, rax 	; rbx = x*x
	mov	rcx, qword 3
	mov	rax, [x]
	mul	rcx
	mov	rcx, rax	; rcx = 3*x
	add	rbx, rcx	; rbx = x*x+3*x
	sub	rbx, qword 5	; rbx = x*x+3*x-5
	mov	rax, rbx
	call	print_int
	call	print_nl
	
	restoregs
	leave
	ret
