----------------------------
-- Desing : Priority Encoder 4x2
-- Function : if more than one input is active/high the priority is given to the most significant bit to the least significant bit.
----------------------------




library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity PriorityEncoder4_2 is
    port(
        enable     :in  std_logic;                      --  Enable for the encoder
        encoder_in :in  std_logic_vector (3 downto 0); --  4-bit Input
        encoder_out :out std_logic_vector (2 downto 0)   --  2 bit binary Output (bit at position 0(least significant) if set to 1 that indicate more than one input is active/high 
    );
end entity PriorityEncoder4_2;

architecture behaviour of PriorityEncoder4_2 is
begin
  prio_enco4_2: process (enable, encoder_in) begin
        if (enable = '1') then
            if (encoder_in(3) = '1') then
                encoder_out <= "111";
            elsif (encoder_in(2) = '1') then
                encoder_out <= "101";
            elsif (encoder_in(1) = '1') then
                encoder_out <= "001";
            elsif (encoder_in(0) = '1') then
                encoder_out <= "001";
            else
                encoder_out <= "000";
            end if;
        end if;
    end process prio_enco4_2;

end architecture behaviour;