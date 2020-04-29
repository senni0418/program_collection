module comblock(compare,enter,change,clock,reset,clock1,bit1,bit2);
	input compare, enter, change,clock,reset;
	output clock1,bit1,bit2;
	reg [2:0]y,Y;
	parameter init = 3'b000,wrong1 = 3'b001,wrong2 = 3'b010,open = 3'b100,ready=3'b101,load = 3'b110;
	always@(compare,enter,change,y)
	begin 
		case(y)
		init: if(compare == 0 && ((enter == 1 && change==0) || (enter == 0 && change==1))) Y = wrong1;
			else if (compare == 1 && enter == 1 && change==0) Y = open;
			else if (compare == 1 && enter == 0 && change==1) Y = ready;
			else Y = init;
		wrong1: if(compare == 1 && enter == 1 && change==0) Y = open;
			else if(compare == 1 && enter == 0 && change==1) Y = ready;
			else if(compare == 0 && ((enter == 1 && change==0) || (enter == 0 && change==1))) Y = wrong2;
			else Y = wrong1;
		wrong2: Y = wrong2;
		open: if (compare == 0 && ((enter == 1 && change==0) || (enter == 0 && change==1))) Y = wrong1;
			else if (compare == 1 && enter == 0 && change==1) Y = ready;
			else if (compare == 1 && enter == 1 && change==0) Y = init;
			else Y = open;
		ready: if ((enter == 1 && change==0) || (enter == 0 && change==1)) Y = load;
			else Y = ready;
		load: Y= init;
		default:Y = 3'bxxx;
		endcase
	end
	always@(posedge clock,negedge reset)
	begin
		if(reset == 0)
			y <= init;
		else
			y <= Y;
	end
	assign clock1 = (y == load);
   assign bit1 = (y == wrong2 || y == ready || y == load);
	assign bit2 = (y == open || y == ready || y == load);
endmodule	