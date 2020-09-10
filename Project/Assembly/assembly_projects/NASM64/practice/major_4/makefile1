all: first endian fact subprog1 subprog2 lineargs lineargs1 loop1 loop2 loop3 loop4

simple_io.o: simple_io.asm
	nasm -felf64 -o simple_io.o simple_io.asm
first.o: first.asm simple_io.inc
	nasm -felf64 -o first.o first.asm
first: driver.c first.o simple_io.o
	gcc -o first driver.c first.o simple_io.o
endian.o: endian.asm simple_io.inc
	nasm -felf64 -o endian.o endian.asm
endian: driver.c endian.o simple_io.o
	gcc -o endian driver.c endian.o simple_io.o
fact.o: fact.asm simple_io.inc
	nasm -felf64 -o fact.o fact.asm
fact: driver.c fact.o simple_io.o
	gcc -o fact driver.c fact.o simple_io.o
subprog1.o: subprog1.asm simple_io.inc
	nasm -felf64 -o subprog1.o subprog1.asm
subprog1: driver.c subprog1.o simple_io.o
	gcc -o subprog1 driver.c subprog1.o simple_io.o
subprog2.o: subprog2.asm simple_io.inc
	nasm -felf64 -o subprog2.o subprog2.asm
subprog2: driver.c subprog2.o simple_io.o
	gcc -o subprog2 driver.c subprog2.o simple_io.o
lineargs.o: lineargs.asm simple_io.inc
	nasm -felf64 -o lineargs.o lineargs.asm
lineargs: driver.c lineargs.o simple_io.o
	gcc -o lineargs driver.c lineargs.o simple_io.o
lineargs1.o: lineargs1.asm simple_io.inc
	nasm -felf64 -o lineargs1.o lineargs1.asm
lineargs1: driver.c lineargs1.o simple_io.o
	gcc -o lineargs1 driver.c lineargs1.o simple_io.o
loop1.o: loop1.asm simple_io.inc
	nasm -felf64 -o loop1.o loop1.asm
loop1: driver.c loop1.o simple_io.o
	gcc -o loop1 driver.c loop1.o simple_io.o
loop2.o: loop2.asm simple_io.inc
	nasm -felf64 -o loop2.o loop2.asm
loop2: driver.c loop2.o simple_io.o
	gcc -o loop2 driver.c loop2.o simple_io.o
loop3.o: loop3.asm simple_io.inc
	nasm -felf64 -o loop3.o loop3.asm
loop3: driver.c loop3.o simple_io.o
	gcc -o loop3 driver.c loop3.o simple_io.o
loop4.o: loop4.asm simple_io.inc
	nasm -felf64 -o loop4.o loop4.asm
loop4: driver.c loop4.o simple_io.o
	gcc -o loop4 driver.c loop4.o simple_io.o

clean:
	rm *.o first fact endian subprog1 subprog2 lineargs lineargs1 loop1 loop2 loop3 loop4
