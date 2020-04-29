module lab2(x3, x2, x1, x0, f0, f1, f2, f3, f4, f5, f6, x4, x5, x6, x7, a, b, c, d, e, f, g);
		input x3, x2, x1, x0, x4, x5, x6, x7;
		output f0, f1, f2, f3, f4, f5, f6, a, b, c, d, e, f, g;
		
		assign f0 = (~x3 & ~x2 & ~x1 & x0) | (~x3 & x2 & ~x1 & ~x0) | (x3 & x2 & ~x1 & x0) | (x3 & ~x2 & x1 & x0);
		assign f1 = (~x3 & x2 & ~x1 & x0) | (x3 & x2 & ~x0) | (x2 & x1 & ~x0) | (x3 & x1 & x0);
		assign f2 = (~x3 & ~x2 & x1 & ~x0) | (x3 & x2 & x1) | (x3 & x2 & ~x0);
		assign f3 = (~x3 & x2 & ~x1 & ~x0) | (~x3 & ~x2 & ~x1 & x0) | (x2 & x1 & x0) | (x3 & ~x2 & x1 & ~x0);
		assign f4 = (~x3 | ~x2) & (~x3 | ~x1) & (x2 | x0) & (~x1 | x0);
		assign f5 = (~x2 | x0 | ~x1) & (x3 | ~x2 | x1) & (x1 | x0) & (~x3 | x2) & (~x3 | ~x1);
		assign f6 = (x3 | ~x2 | x1) & (~x3 | x2) & (~x3 | ~x0) & (~x1 | x0) & (x3 | x2 | ~x1);

		assign a = ~x4 & ~x6 & x7;
		assign b = (~x6 | x7) & (x4 | x5);
		assign c = ~x5 & ~x6 & ~x7;
		assign d = (x4 | ~x5) & (x5 | x6 | x7);
		assign e = x5 & x6 & x7;
		assign f = ~x4 & ~x5 & ~x6 & ~x7;
		assign g = (~x4 & x5 & ~x7) | (~x5 & x6 & x7);
		
endmodule