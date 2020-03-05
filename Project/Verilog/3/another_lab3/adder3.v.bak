module adder(carryin, X, Y, S, carryout);
	input carryin;
	input [2:0]X, Y;
	output [2:0]S;
	output carryout;
	wire [2:1]C;
	
	fulladd stage0(carryin, X[0], Y[0], S[0], C[1]);
	fulladd stage1(C[1], X[1], Y[1], S[1], C[2]);
	fulladd stage2(.Cout(carryout), .s(S[2]), .y(Y[2]), .x(X[2]), .Cin(C[2]));
	
endmodule 


module fulladd(Cin, x, y, s, Cout);
	input Cin, x, y;
	output s, Cout;
	
	assign s = x ^ y ^ Cin;
	assign Cout = (x & y) | (Cin & x) | (Cin & y);
	
endmodule 