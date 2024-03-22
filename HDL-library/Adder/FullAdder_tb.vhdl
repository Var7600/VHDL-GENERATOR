library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity FullAdder_tb is
end entity FullAdder_tb;

architecture behaviour of FullAdder_tb is
    
    component FullAdder is
        port(
            a,b,carry_in : in     std_logic;
            sum,carry_out: out std_logic 
        );
    end component FullAdder;
    
    signal a,b,carry_in,carry_out,sum : std_logic ;
    
begin
    
    dut: FullAdder
    port map
    (
        a => a,
        b => b,
        sum => sum,
        carry_in => carry_in,
        carry_out => carry_out
    );
    process is
  begin
    a <= '0';
    b <= '0';
    carry_in <= '0';
    wait for 10 ns;
    
     a <= '1';
    b <= '0';
    carry_in <= '0';
    wait for 10 ns;
    
    a <= '0';
    b <= '1';
    carry_in <= '0';
    wait for 10 ns;
    
    a <= '1';
    b <= '0';
    carry_in <= '1';
    wait for 10 ns;
    
    a <= '1';
    b <= '1';
    carry_in <= '1';
    wait for 10 ns;
    
    wait ;
  end process;

end architecture behaviour; 