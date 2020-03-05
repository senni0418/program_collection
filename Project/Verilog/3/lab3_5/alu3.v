module alu3(X, leds, Clock, Resetn);
	input [2:0]X;
	input Clock, Resetn;
	wire [2:0]w1, w2;
	wire [3:0]w3;
	output [0:6]leds;
	
	register reg1(X, Clock, Resetn, w1);
	adder add1(0, w1, w2, w3[2:0], w3[3]);
	register reg2(w3[2:0], Clock, Resetn, w2);
	//adder add2(w3[3], w1, w2, w3[2:0], w3[3]);
	hex7seg hex(w3, leds);
	
endmodule 