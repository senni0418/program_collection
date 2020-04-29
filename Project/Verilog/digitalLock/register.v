module register(D,Clock,Resetn,Q);
	input [0:3]D;
	input Resetn,Clock;
	output reg [0:3]Q;
	always @(posedge Clock, negedge Resetn)
	if (Resetn == 0)
		Q<=4'b0110;
	else 
		Q<=D;
endmodule 