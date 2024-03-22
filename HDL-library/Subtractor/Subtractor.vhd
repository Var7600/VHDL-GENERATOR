library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity Subtractor is
    generic ( DataWidth : natural := 8);
    Port (
            a, b: in  std_logic_vector(DataWidth-1 downto 0); -- Input ports for the signed numbers to be subtracted
            result: out std_logic_vector(DataWidth-1 downto 0); -- Output port for the result
            overflow : out std_logic
    );
end entity Subtractor;

architecture behaviour of Subtractor is
    
    -- define a temparary signal to store the result
    signal temp_result: std_logic_vector(DataWidth  downto 0);
begin

   
        -- result
        temp_result <= std_logic_vector(signed('0'& a) - signed('0' & b));
        -- sum
        result <= temp_result(DataWidth-1 downto 0);
        -- overflow
        overflow <= temp_result(DataWidth) ;
        
end architecture behaviour;
