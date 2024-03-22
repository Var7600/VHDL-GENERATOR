library IEEE;
use IEEE.Std_logic_1164.all;
use IEEE.Numeric_Std.all;

entity Demux1x4_tb is
end;

architecture testbench of Demux1x4_tb is
    
 component Demux1_4 is
      generic(DataWidth: integer := 8);
      port(
          input : in std_logic_vector(DataWidth-1 downto 0);
          sel : in std_logic_vector(1 downto 0);
          output0,output1,output2,output3 : out std_logic_vector(DataWidth-1 downto 0)
      );
  end component;
    
  constant DataWidth : integer := 4;  
  signal input: std_logic_vector(DataWidth-1 downto 0);
  signal sel: std_logic_vector(1 downto 0);
  signal output0,output1,output2,output3: std_logic_vector(DataWidth-1 downto 0) ;

begin

  -- Insert values for generic parameters !!
  dut : Demux1_4 
  generic map ( DataWidth =>  DataWidth )
                   port map ( input     => input,
                              sel       => sel,
                              output0   => output0,
                              output1   => output1,
                              output2   => output2,
                              output3   => output3 );

  stimulus: process
  begin
     
            input <= "0001";
            wait for 5 ns;
            sel <= "00" ;
            wait for 5 ns ;
            
            input <= "1000";
            wait for 5 ns;
            sel <= "01";
            wait for 5 ns;
            
            input <= "1111";
            wait for 5 ns;
            sel <="10";
            wait for 5 ns ;
            
            input <= "0010";
            wait for 5 ns;
            sel <= "11";
            wait for 5 ns;
            
             wait ;
      

  end process;


end architecture testbench;


