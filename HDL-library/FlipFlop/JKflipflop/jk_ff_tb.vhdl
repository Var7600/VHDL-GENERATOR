library ieee;
use ieee.std_logic_1164.all;

entity jk_ff_tb is
end jk_ff_tb;

architecture testbench of jk_ff_tb is

component jk_flip_flop is
    port(j, k, clk, rst : in std_logic;
        q, not_q : out std_logic
    );
end component;

signal j, k, clk, rst : std_logic;
signal q, not_q : std_logic;

begin
    uut: jk_flip_flop port map(
    j => j,
    k => k,
    clk => clk,
    rst => rst,
    q => q,
    not_q => not_q);

    clock: process
    begin
    clk <= '1';
    wait for 10 ns;
    clk <= '0';
    wait for 10 ns;
    end process;
    
    jk: process
    begin
    j <= '0';
    k <= '0';
    rst <= '0';
    wait for 20 ns;
    
    j <= '0';
    k <= '1';
    rst <= '0';
    wait for 20 ns;
    
    j <= '1';
    k <= '0';
    rst <= '0';
    wait for 20 ns;
    
    j <= '1';
    k <= '1';
    rst <= '0';
    wait for 20 ns;
    
    j <= '1';
    k <= '1';
    rst <= '0';
    wait for 20 ns;
    
    j <= '0';
    k <= '0';
    rst <= '0';
    wait for 20 ns;
    
    j <= '0';
    k <= '0';
    rst <= '1';
    wait for 20 ns;
    end process;
end testbench;