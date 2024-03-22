library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity FullSubtractor_tb is
end entity FullSubtractor_tb;

architecture testbench of FullSubtractor_tb is
    
    component FullSubtractor is
    Port ( 
            a,b,c : in std_logic;
            difference,borrow : out STD_LOGIC
         );
    end component FullSubtractor;

    signal a,b,c,difference,borrow : STD_LOGIC;

begin

    uut: FullSubtractor
    port map(
        a => a, b => b, c => c , difference => difference, borrow => borrow
    );
    
    process is
    begin
        a <= '0';
        wait for 5 ns;
        b <= '0';
        wait for 5 ns;
        c <= '0';
        wait for 5 ns;
        
        a <= '0';
        wait for 5 ns;
        b <= '0';
        wait for 5 ns;
        c <= '1';
        wait for 5 ns;
        
        a <= '0';
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        c <= '0';
        wait for 5 ns;
        
        a <= '0';
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        c <= '1';
        wait for 5 ns;
        
        a <= '1';
        wait for 5 ns;
        b <= '0';
        wait for 5 ns;
        c <= '0';
        wait for 5 ns;
        
        a <= '1';
        wait for 5 ns;
        b <= '0';
        wait for 5 ns;
        c <= '1';
        wait for 5 ns;
        
        a <= '1';
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        c <= '0';
        wait for 5 ns;
        
        a <= '1';
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        c <= '1';
        wait for 5 ns;
        
        
        wait;
    
    end process ;
end architecture testbench;