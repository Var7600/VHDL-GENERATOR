library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity SegmentDisplay is
   port(
        hex : in std_logic_vector(3 downto 0);
        decimal_point : in std_logic  ;
        segment : out std_logic_vector(7 downto 0)
    );
end entity SegmentDisplay;

architecture rtl of SegmentDisplay is
    
begin
process(hex,decimal_point) is begin
        case hex is
        when "0000" =>
                segment(6 downto 0) <= "1111110"; -- 0
        when "0001" =>
                segment(6 downto 0) <= "0110000"; -- 1
        when "0010" =>
                segment(6 downto 0) <= "1101101" ; -- 2
        when "0011" =>
                segment(6 downto 0) <= "1111001"; -- 3
        when "0100" =>
                segment(6 downto 0) <= "0110011" ; -- 4
        when "0101" =>
                segment(6 downto 0) <= "1011011" ; -- 5
        when "0110" =>
                segment(6 downto 0) <= "1011111" ; -- 6
        when "0111" =>
                segment(6 downto 0) <= "1110000" ; -- 7
        when "1000" =>
                segment(6 downto 0) <= "1111111";  -- 8
        when "1001" =>
                segment(6 downto 0) <= "1111011" ; -- 9
        when "1010" =>
                segment(6 downto 0) <= "1110111" ; -- a
        when "1011" =>
                segment(6 downto 0) <= "0011111";  -- b
        when "1100" =>
                segment(6 downto 0) <= "1001110" ; -- c
        when "1101" =>
                segment(6 downto 0) <= "0111101" ; -- d
        when "1110" =>
                segment(6 downto 0) <= "1001111" ;  -- e
        when "1111" =>
                segment(6 downto 0) <= "1000111" ;  -- f
        when others =>
                segment(6 downto 0) <= "0000000" ;
        end case ;
        segment(7) <= decimal_point ;
    end process ;
   

end architecture rtl;