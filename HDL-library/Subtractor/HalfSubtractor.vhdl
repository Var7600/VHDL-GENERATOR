------------------------------------------
-- VHDL Code for the 2-bit Comparator
-- function HalfSubtractor
-- A - B 
-----------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 


entity HalfSubtractor is
    port(
        a,b: in std_logic ;
        difference,borrow: out STD_LOGIC
    );
end entity HalfSubtractor;

architecture behaviour of HalfSubtractor is
    
begin
    difference <= (not(a) and b) or (a and not(b)) ;
    borrow <= not(a) and b ;
end architecture behaviour;