CFLAGS=-Wall -O2 -ansi
filter: filter.o
	$(CXX) -o filter $?

clean :
	@rm -rf filter *.o *.ppm
