CFLAGS=-Wall -O2 -ansi
fib: main_fib.o fib.o bmp.o timing.o
	$(CXX) -o fib $?
runall:
	./fib 7 10 10 10 100 100 fib7.bmp
	./fib 9 10 10 10 300 300 fib9.bmp
	./fib 25 10 10 10 10000 10000 fib25.bmp
	./fib 26 10 10 10 20000 20000 fib26.bmp

clean:
	@rm -rf fib *.o *bmp 
