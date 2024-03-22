library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity Subtractor_tb is
end entity Subtractor_tb;

architecture rtl of Subtractor_tb is
    
    component Subtractor is
    generic ( DataWidth : natural := 8);
    Port (
            a, b: in  std_logic_vector(DataWidth-1 downto 0); -- Input ports for the signed numbers to be subtracted
            result: out std_logic_vector(DataWidth-1 downto 0); -- Output port for the result
            overflow : out std_logic
    );
end component Subtractor;
    
    constant DataWidth : natural := 4 ;
    signal  a, b,result:  std_logic_vector(DataWidth-1 downto 0);
    signal carry : std_logic ;
    
begin
    
     dut: Subtractor
     generic map ( DataWidth => DataWidth )
    port map
    (
         a => a ,
         b => b ,
         result => result,
         overflow => carry
        
    );

    sim_time_proc: process
    begin
        
              
                a <= "0001";
                b <= "0000";
                wait for 10 ns ;
                
                
                
                -- test 2
                a <= "0010";
                b <= "0001";
                wait for 10 ns ;
                
                
                -- test 3
                a <= "1111";
                b <= "1110";
                wait for 10 ns ;
                
                
                 -- test 4
                a <= "0001";
                b <= "1000";
                wait for 10 ns ;
                
                
                -- test 5
                a <= "1111";
                b <= "1111";
                wait for 10 ns ;
                
                wait ;
    
   end process;

end architecture rtl; 