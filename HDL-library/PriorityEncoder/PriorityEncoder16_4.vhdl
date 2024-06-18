-------------------------------------------------------
-- Design Name : PriorityEncoder16_4
-- File Name   : PriorityEncoder16_4.vhdl
-- Function    : Pri Encoder using when-else
-------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;

entity PriorityEncoder16_4 is
    port (
        enable     :in  std_logic;                      --  Enable for the encoder
        encoder_in :in  std_logic_vector (15 downto 0); --  16-bit Input
        binary_out :out std_logic_vector (3 downto 0)   --  4 bit binary Output

    );
end PriorityEncoder16_4;

architecture behaviour of PriorityEncoder16_4 is

begin

 binary_out <= "0000" when enable = '0' else
                "0001" when encoder_in( 1 ) = '1' else
                "0010" when encoder_in( 2 ) = '1' else
                "0011" when encoder_in( 3 ) = '1' else
                "0100" when encoder_in( 4 ) = '1' else
                "0101" when encoder_in( 5 ) = '1' else
                "0110" when encoder_in( 6 ) = '1' else
                "0111" when encoder_in( 7 ) = '1' else
                "1000" when encoder_in( 8 ) = '1' else
                "1001" when encoder_in( 9 ) = '1' else
                "1010" when encoder_in( 10 ) = '1' else
                "1011" when encoder_in( 11 ) = '1' else
                "1100" when encoder_in( 12 ) = '1' else
                "1101" when encoder_in( 13 ) = '1' else
                "1110" when encoder_in( 14 ) = '1' else
                "1111" when encoder_in( 15 ) = '1' else
                "0000"; 
end behaviour;
