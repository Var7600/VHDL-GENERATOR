library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
 
entity FullAdder is
  port (
        a  : in std_logic;
        b  : in std_logic;
        carry_in : in std_logic;
        sum   : out std_logic;
        carry_out : out std_logic
    );
end FullAdder;
 
 
architecture behaviour of FullAdder is
   
begin
    
   sum   <= a xor b xor carry_in;
   carry_out <= ((a xor b) and carry_in) or (a and b);
 
end behaviour;