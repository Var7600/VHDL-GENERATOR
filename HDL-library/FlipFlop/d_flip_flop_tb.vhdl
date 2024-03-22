library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity d_flip_flop_tb is
end entity d_flip_flop_tb;

architecture testbench of d_flip_flop_tb is
    
    -- component
    component d_flip_flop is
        port(
            d,clk : in std_logic;
            q : out std_logic
        );
   end component;
       
   -- singal 
    signal clk,d,q : std_logic;
    
begin
    -- port map
    dut: d_flip_flop port map
    (
        clk => clk,
        d => d,
        q => q
    );
    
    

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
    
   

end architecture testbench; 