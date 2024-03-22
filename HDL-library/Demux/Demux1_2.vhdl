library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity Demux1_2 is
    generic(DataWidth : integer := 8);
    port(
        input: in std_logic_vector(DataWidth-1 downto 0);
        sel: in std_logic;
        output0,output1: out std_logic_vector(DataWidth-1 downto 0)
    );
end entity Demux1_2;

architecture behaviour of Demux1_2 is
   
begin

    demux: process(input,sel)
    begin
        case sel is
            when '0' =>
                output0 <= input;
            when '1'=>
                output1 <= input;
            when others =>
                output0 <= (others => '0');
                output1 <= (others => '0');
        end case;
        
       
    end process demux;

end architecture behaviour;