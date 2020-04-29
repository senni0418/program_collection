module lab4(enter,change,sw,clock,reset,light);
	input enter,change,clock,reset;
	input [0:3] sw;
	wire [0:3] sw1;
	wire [0:1] sw2;
	wire clock1;
	output [0:6]light;
	
	inputCon in1(clock,enter,1,pulse_enter);
	inputCon in2(clock,change,1,pulse_change);
	register r1(sw,clock1,reset,sw1);
	compare c1(sw,sw1,res);
	comblock co1(res,pulse_enter,pulse_change,clock,reset,clock1,sw2[0],sw2[1]);
	display d1(sw2,light);
endmodule 