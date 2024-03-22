library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

-------------------------------------------------------------------
-- Reset D Flip-flop model with active-high aynchronous reset input.--
-------------------------------------------------------------------
entity d_flip_flop_reset is
    port(
            d,clk,reset : in std_logic;
            q: out std_logic
    );
end d_flip_flop_reset;

architecture behaviour of d_flip_flop_reset is
begin
            dff_reset: process(d,clk,reset) 
            begin
                if reset = '1' then
                    q <= '0';
                elsif (falling_edge(clk)) then
                    q <= d;
                end if;
        end process dff_reset;
            
end architecture;        