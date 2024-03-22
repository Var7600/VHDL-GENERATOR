library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity HalfSubtractor_tb is
end entity HalfSubtractor_tb;

architecture testbench of HalfSubtractor_tb is
    
    component HalfSubtractor is
    Port ( 
            a,b : in std_logic;
            difference,borrow : out STD_LOGIC
         );
    end component HalfSubtractor;

    signal a,b,difference,borrow : STD_LOGIC;

begin

    uut: HalfSubtractor
    port map(
        a => a, b => b, difference => difference, borrow => borrow
    );
    
    process is
    begin
        a <= '0';
        wait for 5 ns;
        b <= '0';
        wait for 5 ns;
        
        a <= '0';
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        
        a <= '1';
        wait for 5 ns;
        b <= '0';
        wait for 5 ns;
        
        a <= '1';
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        
        wait;
    
    end process ;
end architecture testbench;