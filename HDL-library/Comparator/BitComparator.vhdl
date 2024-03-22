--==============================================================================                                                                                                      
--== Component : BitComparator                                                ==
--== compare two bits a and b if a == b then a_equals_b=1 ,                    ==
--== if a > b then a_greater_than_b=1 , if a < b then a_less_than_b           ==
--==============================================================================

LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.numeric_std.all;


ENTITY BitComparator IS

   PORT ( a         : IN  std_logic;
          b         : IN  std_logic;
          a_equals_b      : OUT std_logic;
          a_greater_than_b : OUT std_logic;
          a_less_than_b    : OUT std_logic );
          
END ENTITY BitComparator;

ARCHITECTURE behaviour OF BitComparator IS 

BEGIN

   a_equals_b <= a XNOR b;
   a_less_than_b <= NOT(a) AND b ;
   a_greater_than_b <= a AND not(b) ; 

END behaviour;