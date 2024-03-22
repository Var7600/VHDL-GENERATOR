-----------------------------------------------------------------------------------------------------------------------------------
-- Desing : Priority Encoder 8x3
-- Function : if more than one input is active/high the priority is given to the most significant bit to the least significant bit.
-----------------------------------------------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity PriorityEncoder8_3 is
port (
        enable: in std_logic;  --  Enable for the encoder
        input: in std_logic_vector (7 downto 0); --  8-bit Input
        output: out std_logic_vector (2 downto 0) -- 3 bit binary Output
    );
end PriorityEncoder8_3;

architecture behaviour of PriorityEncoder8_3 is
begin
    prio_enco8_3 : process(input,input) is
        begin
        if enable='1' then
            if input(7)='1'then
                output<="111" ;
            elsif input(6)='1' then
                output <= "110";
            elsif input(5)='1' then
                output <= "101";
            elsif input(4)='1' then
                output <= "100" ;
            elsif input(3)='1' then
                output <= "011";    
            elsif input(2)='1' then
                output <= "010";
            elsif input(1)='1' then
                output <= "001" ;
            elsif input(0)='1' then
                output <= "000";
            end if;
       end if;
  end process prio_enco8_3;
    
end behaviour;