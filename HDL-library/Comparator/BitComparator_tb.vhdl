-- Testbench created online at:
--   https://www.doulos.com/knowhow/perl/vhdl-testbench-creation-using-perl/
-- Copyright Doulos Ltd

library IEEE;
use IEEE.Std_logic_1164.all;
use IEEE.Numeric_Std.all;

entity BitComparator_tb is
end;

architecture bench of BitComparator_tb is

  component BitComparator
     PORT ( a         : IN  std_logic;
            b         : IN  std_logic;
            a_equals_b      : OUT std_logic;
            a_greater_than_b : OUT std_logic;
            a_less_than_b    : OUT std_logic );
  end component;

  signal a: std_logic;
  signal b: std_logic;
  signal a_equals_b: std_logic;
  signal a_greater_than_b: std_logic;
  signal a_less_than_b: std_logic ;

begin

  uut: BitComparator port map ( a                => a,
                                b                => b,
                                a_equals_b       => a_equals_b,
                                a_greater_than_b => a_greater_than_b,
                                a_less_than_b    => a_less_than_b );

  stimulus: process
  begin
  
    -- Put initialisation code here
        a <= '0';
        wait for 5 ns ;
        b <= '0';
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        a <= '1';
        wait for 5 ns;
        b <= '0'  ;
        wait for 5 ns;
        b <= '1';
        wait for 5 ns;
        
        wait ;


    -- Put test bench stimulus code here

    wait;
  end process;


end;