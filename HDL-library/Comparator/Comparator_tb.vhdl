-- Testbench created online at:
--   https://www.doulos.com/knowhow/perl/vhdl-testbench-creation-using-perl/
-- Copyright Doulos Ltd

library IEEE;
use IEEE.Std_logic_1164.all;
use IEEE.Numeric_Std.all;

entity Comparator_tb is
end;

architecture bench of Comparator_tb is

  component Comparator is
     GENERIC ( DataWidth       : natural;
               twosComplement : bit );
     PORT ( dataA         : IN  std_logic_vector( (DataWidth - 1) DOWNTO 0 );
            dataB         : IN  std_logic_vector( (DataWidth - 1) DOWNTO 0 );
            aEqualsB      : OUT std_logic;
            aGreaterThanB : OUT std_logic;
            aLessThanB    : OUT std_logic );
  end component;
   constant DataWidth : natural := 4 ;  
  signal dataA: std_logic_vector( (DataWidth - 1) DOWNTO 0 );
  signal dataB: std_logic_vector( (DataWidth - 1) DOWNTO 0 );
  signal aEqualsB: std_logic;
  signal aGreaterThanB: std_logic;
  signal aLessThanB: std_logic ;
 
   signal twosComplement : bit := '0';
    

begin

  -- Insert values for generic parameters !!
    uut: Comparator generic map ( DataWidth      => DataWidth,
        twosComplement => twosComplement )
                     port map ( dataA          => dataA,
                                dataB          => dataB,
                                aEqualsB       => aEqualsB,
                                aGreaterThanB  => aGreaterThanB,
                                aLessThanB     => aLessThanB );

  stimulus: process
  begin
  
    -- Put initialisation code here
        dataA <= "0000";
        wait for 5 ns ;
        dataB <= "0000";
        wait for 5 ns ;
        dataA <= "0010";
        wait for 5 ns ;
        dataB <= "0001";
        wait for 5 ns ;
        dataA <= "1000";
        wait for 5 ns ;
        dataB <= "0100";
        wait for 5 ns ;
        dataA <= "1100";
        wait for 5 ns ;
        dataB <= "0100";
        wait for 5 ns ;
        dataA <= "1100";
        wait for 5 ns ;
        dataB <= "1110";
        wait for 5 ns ;
        
        -- 
        --        signed values
        --
        
        twosComplement <= '1';
        dataA <= "1111";
        wait for 5 ns ;
        dataB <= "0100";
        wait for 5 ns ;
        dataA <= "1100";
        wait for 5 ns ;
        dataB <= "0100";
        wait for 5 ns ;
        
        


    -- Put test bench stimulus code here

    wait;
  end process;


end;