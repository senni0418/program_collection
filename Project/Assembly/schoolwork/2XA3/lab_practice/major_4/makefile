all: proj4

simple_io.o: simple_io.asm
	nasm -felf64 -o simple_io.o simple_io.asm
proj4.o: proj4.asm simple_io.inc
	nasm -felf64 -o proj4.o proj4.asm
proj4: driver.c proj4.o simple_io.o
	gcc -o proj4 driver.c proj4.o simple_io.o

clean:
	rm *.o proj4
