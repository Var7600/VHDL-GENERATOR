------------------------------------------
-- VHDL Code for the 3-bit Comparator
-- function FullSubtractor
-- A - B 
-----------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 


entity FullSubtractor is
    port(
        a,b,c: in std_logic ;
        difference,borrow: out STD_LOGIC
    );
end entity FullSubtractor;

architecture behaviour of FullSubtractor is
    
begin
    
    difference <= (not(a) and not(b) and c) or (not(a) and b and not(c)) or (a and not(b) and not(c)) or (a and b and c);
    borrow <= (not(a) and c) or (not(a) and b) or b;
    
end architecture behaviour;