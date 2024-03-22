library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity PriorityEncoder4x2 is
    port(
        enable     :in  std_logic;                      --  Enable for the encoder
        encoder_in :in  std_logic_vector (3 downto 0); --  4-bit Input
        encoder_out :out std_logic_vector (2 downto 0)   --  3 bit binary Output (bit at position 0(least significant) if set to 1 that indicate more than one input is active/high 
    );
end entity PriorityEncoder4x2;

architecture behaviour of PriorityEncoder4x2 is
begin
   process (enable, encoder_in) begin
        if (enable = '1') then
            if (encoder_in = "0000") then
                encoder_out <= "--0";
            elsif (encoder_in = "1---") then
                encoder_out <= "111";
            elsif (encoder_in = "01--") then
                encoder_out <= "101";
            elsif (encoder_in = "001-") then
                encoder_out <= "011";
            elsif (encoder_in = "0001") then
                encoder_out <= "001";
            end if;
        end if;
    end process;

end architecture behaviour;