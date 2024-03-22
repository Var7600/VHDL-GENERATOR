library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity HalfAdder_tb is
end entity HalfAdder_tb;

architecture behaviour of HalfAdder_tb is
    
    component HalfAdder is
        port(
            a,b : in     std_logic;
            sum,carry : out std_logic 
        );
    end component HalfAdder;
    
    signal a,b,carry,sum : std_logic ;
    
begin
    
    dut: HalfAdder
    port map
    (
        a => a,
        b => b,
        sum => sum,
        carry => carry
    );
    process is
  begin
    a <= '0';
    b <= '0';
    wait for 10 ns;
    a <= '0';
    b <= '1';
    wait for 10 ns;
    a <= '1';
    b <= '0';
    wait for 10 ns;
    a <= '1';
    b <= '1';
    wait for 10 ns;
    
    wait ;
  end process;

end architecture behaviour; 