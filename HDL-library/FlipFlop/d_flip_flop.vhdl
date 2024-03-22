library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity d_flip_flop is
    port(
        d,clk : in     std_logic;
        q : out std_logic 
    );
end entity d_flip_flop;

architecture behaviour of d_flip_flop is
    
begin

    dff: process(d,clk)
    begin
        if rising_edge(clk) then
            q <= d ;
        end if;
    end process dff;

end architecture behaviour;