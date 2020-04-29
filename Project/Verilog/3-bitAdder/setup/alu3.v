module alu3(sw1, hex, Clock, Resetn);
	input [0:2] sw1;
	input Clock, Resetn;
	wire [0:2]w1, w2;
	wire [0:3]w3;
	output [0:6]hex;
	
	register reg1(sw1, Clock, Resetn, w1);
	adder3 add1(0, w1[2], w1[1], w1[0], w2[2], w2[1], w2[0], w3[3], w3[2], w3[1], w3[0]);
	register reg2(w3[1:3], Clock, Resetn, w2);
	//adder3 add2(0, w1[2], w1[1], w1[0], w2[2], w2[1], w2[0], w3[3], w3[2], w3[1], w3[0]);
	hex7seg disp(w3, hex);
	
endmodule 