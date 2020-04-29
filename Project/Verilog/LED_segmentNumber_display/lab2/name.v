module name(x4, x5, x6, x7, a, b, c, d, e, f, g);
		input x4, x5, x6, x7;
		output a, b, c, d, e, f, g;
		
		assign a = ~x4 & ~x6 & x7;
		assign b = (~x6 | x7) & (x4 | x5);
		assign c = ~x5 & ~x6 & ~x7;
		assign d = (x4 | ~x5) & (x5 | x6 | x7);
		assign e = x5 & x6 & x7;
		assign f = ~x4 & ~x5 & ~x6 & ~x7;
		assign g = (~x4 & x5 & ~x7) | (~x5 & x6 & x7);
		
endmodule 