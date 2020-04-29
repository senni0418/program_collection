module display(sw,light);
	input [0:1] sw;
	output reg [0:6] light;
	
	always @(sw)
	begin
		case(sw)
			0: light = 7'b1111110;
			1: light = 7'b0000001;
			2: light = 7'b0001000;
			3: light = 7'b1101010;
		endcase
	end
endmodule 