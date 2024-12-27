--------------------------------------------------------
-- VHDL code for n-bit adder     
--
-- function of adder:
-- A plus B to get n-bit sum and 1 bit carry    
-- we may use generic statement to set the parameter 
-- n(DataWidth) of the adder.                            
--------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

--------------------------------------------------------

entity ADDER is
generic( n: natural := 8 );
port(    
        a:    in std_logic_vector(n-1 downto 0);
    b:    in std_logic_vector(n-1 downto 0);
    carry:    out std_logic;
    sum:    out std_logic_vector(n-1 downto 0)
);

end entity ADDER;

--------------------------------------------------------

architecture behv of ADDER is

-- define a temporary signal to store the result 

signal result: std_logic_vector(n downto 0);
 
begin                      
 
    -- the 3rd bit should be carry
   
    result <= ('0' & a)+('0' & b);
    sum <= result(n-1 downto 0);
    carry <= result(n);

end behv;

--------------------------------------------------------