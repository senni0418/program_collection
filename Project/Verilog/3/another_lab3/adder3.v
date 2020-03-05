module adder3(carryin, x0, x1, x2, y0, y1, y2, s0, s1, s2, carryout);
	input carryin, x0, x1, x2, y0, y1, y2;
	output carryout, s0, s1, s2;
	wire c1, c2;
	
	fulladd stage0(carryin, x0, y0, s0, c1);
	fulladd stage1(c1, x1, y1, s1, c2);
	fulladd stage2(.Cout(carryout), .s(s2), .y(y2), .x(x2), .Cin(c2));
	
endmodule 


module fulladd(Cin, x, y, s, Cout);
	input Cin, x, y;
	output s, Cout;
	
	assign s = x ^ y ^ Cin;
	assign Cout = (x & y) | (Cin & x) | (Cin & y);
	
endmodule 