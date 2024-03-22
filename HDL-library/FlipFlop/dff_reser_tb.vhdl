library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity dff_reset is
end entity dff_reset;

architecture test of dff_reset is
    
    -- component
    component d_flip_flop_reset is
        port(
            d,clk,reset : in std_logic;
            q : out std_logic
        );
   end component;
       
   -- singal 
    signal clk,d,q,reset : std_logic;
    
begin
    -- port map
    dut: d_flip_flop_reset port map
    (
        clk => clk,
        reset => reset,
        d => d,
        q => q
    );
    
    sim_reset: process
    begin
        reset <= '0';
        wait for 5 ns;
        reset <= '1';
        wait for 5 ns;
        end process sim_reset;

    sim_time_proc: process
    begin
        d <= '0';
        wait for 40 ns;
        d <= '1';
        wait for 40 ns;
    end process sim_time_proc;

    clk_proc: process
    begin
        clk <= '0';
        wait for 10 ns;
        clk <= '1';
        wait for 10 ns;
    end process clk_proc;
    
   

end architecture test; 