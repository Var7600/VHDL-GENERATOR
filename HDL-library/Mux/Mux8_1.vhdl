library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity MUX8_1 is
    generic (DataWidth : natural := 8);
    Port ( input0,input1,input2,input3,input4,input5,input6,input7 : in std_logic_vector(DataWidth-1 downto 0);    
           sel : in STD_LOGIC_VECTOR (2 downto 0);
           output : out std_logic_vector(DataWidth-1 downto 0)
    );
end MUX8_1;

architecture behaviour of MUX8_1 is
begin
    process(sel,input0,input1,input2,input3,input4,input5,input6,input7) is begin
        
    case sel is
        when "000" =>
            output <= input0;
        when "001" =>
            output <= input1;
        when "010" =>
            output <= input2;
        when "011" =>
            output <= input3;
        when "100" =>
            output <= input4;
        when "101" =>
            output <= input5;
        when "110" =>
            output <= input6;
        when "111" =>
            output <= input7;
        when others =>
            output <= (others => '0');
    end case;
   end process;
end behaviour;
