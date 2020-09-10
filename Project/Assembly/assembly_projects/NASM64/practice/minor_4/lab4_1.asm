%include "simple_io.inc"

global asm_main


SECTION .data
;x: dq 4
x: dq 5

SECTION .text

asm_main:
	enter	0,0
	saveregs

   mov	rax,	[x]
	call	print_int
	mov	al,' '
	call	print_char
	mov	al,'*'
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
	mov	rax, [x]
   mul	rax
	call	print_int
	call	print_nl

	restoregs
	leave
	ret
