module compare(sw,sw1,res);
	input [0:3] sw,sw1;
	output res;
	
	assign res = (sw == sw1);
	
endmodule
