module lab2_i(x3,x2,x1,x0,f0,f1,f2,f3,f4,f5,f6);

	input x3, x2, x1, x0;
	output f0, f1, f2, f3, f4, f5, f6;

	assign f0 = (~x3 & ~x2 & ~x1 & x0) | (~x3 & x2 & ~x1 & ~x0) | (x3 & x2 & ~x1 & x0) | (x3 & ~x2 & x1 & x0);
	assign f1 = (~x3 & x2 & ~x1 & x0) | (x3 & x2 & ~x0) | (x2 & x1 & ~x0) | (x3 & x1 & x0);
	assign f2 = (~x3 & ~x2 & x1 & ~x0) | (x3 & x2 & x1) | (x3 & x2 & ~x0);
assign f3 = (~x3 & x2 & ~x1 & ~x0) | (~x3 & ~x2 & ~x1 & x0) | (x2 & x1 & x0) | (x3 & ~x2 & x1 & ~x0);
assign f4 = (~x3 | ~x2) & (~x3 | ~x1) & (x2 | x0) & (~x1 | x0);
assign f5 = (~x2 | x0) & (x1 | x0) & (~x3 | x2) & (~x3 | ~x1);
assign f6 = (x3 | ~x2 | ~x1) & (~x3 | x2) & (~x3 | ~x0) & (~x1 | x0) & (x2 | ~x1);

endmodule