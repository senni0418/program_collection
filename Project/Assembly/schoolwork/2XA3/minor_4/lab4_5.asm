%include "simple_io.inc"
global  asm_main

SECTION .data

peg1: dq 0,0,0,0,0,0,3,4,9
peg2: dq 0,0,0,0,0,0,0,2,9
peg3: dq 0,0,0,0,0,0,0,1,9

SECTION .bss
N: resq 3

SECTION .text

line1:   ; subroutine line1 expects one address A on stack
         ; it uses three numbers, N1 stored at  address A, 
         ; N2 stored at address address A+72, and 
         ; N3 stored at address A+144
         ; all three numbers N1, N2, and N3 are expected to be
         ; OK and in the range 0..9
         ;
; line1 displays a line that is composed like this
; 12-N1 dots, N1 pluses, one |, N1 pluses, 12-N1 dots
; continued the same for N2 and continued the same for N3
;
; for instance if N1=9, N2=9, N3=9 the display will look likes this 
;...+++++++++|+++++++++......+++++++++|+++++++++......+++++++++|+++++++++...
;
; for instance if N1=4, N2=2, N3=1 the display will look likes this 
;........++++|++++..................++|++.....................+|+...........
;
; for instance if N1=3, N2=0, N3=0 the display will look likes this 
;.........+++|+++.....................|........................|............
;

   enter	0,0                  ; setup routine
   saveregs                   ; save all registers
   mov	rax, [rbp+16]        ; address of N1
   mov	r12, qword [rax]     ; r12=N1
	mov	qword [N], r12       ; store it in N[0]
   add	rax, qword 72        ; address of N2
   mov   r12, qword [rax]     ; r12=N2
	mov	qword [N+8], r12     ; store it in N[1] 
   add   rax, qword 72        ; address of N3
   mov 	r12, qword [rax]     ; r12=N3
	mov	qword [N+16], r12		; store it in N[2]

   ;; we do a loop 3x for N[0], then N[1], then N[2]
   ;; rbx controls the loop
   ;; r12 takes on N values
   mov	rbx, qword 0
	mov	r12, N
	LOOP1:
		cmp	rbx, qword 2
		ja		LOOP1_END
		;; [r12] is N
      ;;;;;;  print 12-[r12] . ;;;;;;;;;;;;;;;;;
			mov	r13, qword 12
			sub	r13, qword [r12]
         ;; so we print r13 .
         ;; we use r14 to count the loop
			mov	r14, qword 0
			DOT:
				cmp	r14, r13
				jae	DOT_END
				mov	al, '.'
				call	print_char
            inc	r14
				jmp	DOT
		   DOT_END:
      ;;;;;;;;;  print [r12] + ;;;;;;;;;;;;;;;;;
			mov	r13, qword [r12]
         ;; so we print r13 +
         ;; we use r14 to count the loop
			mov	r14, qword 0
			PLUS:
				cmp	r14, r13
				jae	PLUS_END
				mov	al, '+'
				call	print_char
            inc	r14
				jmp	PLUS
			PLUS_END:
      ;;;;;;;;;  print | ;;;;;;;;;;;;;;;;;;;;;;;
      mov	al, '|'
		call	print_char
      ;;;;;;;;;  print [r12] + ;;;;;;;;;;;;;;;;;
			mov	r13, qword [r12]
         ;; so we print r13 +
         ;; we use r14 to count the loop
			mov	r14, qword 0
			PPLUS:
				cmp	r14, r13
				jae	PPLUS_END
				mov	al, '+'
				call	print_char
            inc	r14
				jmp	PPLUS
			PPLUS_END:
      ;;;;;;  print 12-[r12] . ;;;;;;;;;;;;;;;;;
			mov	r13, qword 12
			sub	r13, qword [r12]
         ;; so we print r13 .
         ;; we use r14 to count the loop
			mov	r14, qword 0
			DDOT:
				cmp	r14, r13
				jae	DDOT_END
				mov	al, '.'
				call	print_char
            inc	r14
				jmp	DDOT
		   DDOT_END:
		;; increment the counter
		inc 	rbx
		;; increment the index for N
		add 	r12, qword 8
		jmp 	LOOP1
	LOOP1_END:
      call	print_nl

   restoregs
   leave
   ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
asm_main:
   enter	0,0             ; setup routine
   saveregs              ; save all registers

   mov	rbx, peg1        
   add 	rbx, qword 64   ; this does 9, 9, 9
   ;add 	rbx, qword 56   ; this does 4, 2, 1
   ;add 	rbx, qword 48   ; this does 3, 0, 0

	push 	rbx			    ; push the address of N1, N2, N3
	call	line1
	add	rsp, 8          ; clean stack   
	
   
   restoregs             ; restore all registers
   leave                     
   ret
