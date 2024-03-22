

library IEEE;

use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD. all;

entity Encoder8x3 is

    port (
        input: in std_logic_vector (7 downto 0);
        enable : in STD_LOGIC;
        output: out std_logic_vector (2 downto 0)
    );

end Encoder8x3;

architecture behaviour of Encoder8x3 is

begin

    process (enable,input) is
    begin
        if enable = '1' then
            case input is
        
                when "00000001" => output <= "000";
            
                when "00000010" => output <= "001";
            
                when "00000100" => output <= "010";
            
                when "00001000" => output <= "011";
            
                when "00010000" => output <= "100";
            
                when "00100000" => output <= "101";
            
                when "01000000" => output <= "110";
            
                when "10000000" => output <= "111";
            
                when others => output <= "XXX";
        
            end case ;
        end if;
        

    end process ;

end behaviour;
