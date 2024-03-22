library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity PriorityEncoder8_3_tb is
end entity PriorityEncoder8_3_tb;

architecture behaviour of PriorityEncoder8_3_tb is
    
   
    
    component PriorityEncoder8_3 is
        port(
            enable     :in  std_logic;                      --  Enable for the encoder
            input :in  std_logic_vector (7 downto 0); --  4-bit Input
            output :out std_logic_vector (2 downto 0)   --  4 bit binary Output     
        );
    end component PriorityEncoder8_3;
    
    signal enable : std_logic;
    signal input : std_logic_vector(7 downto 0);
    signal output : std_logic_vector(2 downto 0);
    
begin

    u1: PriorityEncoder8_3
    port map
    (
        enable => enable,
        input => input,
        output => output
    );
    
    auto_test : process is 
    begin
        ------------------------------
        -- test basic encoder behaviour
        --------------------------------
       
        
        enable <= '1';
        wait for 5 ns ;
        
        input <= "00000000" ;
        wait for 5 ns ;
       
        
        input <= "00000001";
        wait for 5 ns ;
      
        
        -- test 3
        input <= "00000010";
        wait for 5 ns ;
        
        
        -- test 4
        input <= "00000100";
        wait for 5 ns;
        
        
        -- test 5
        input <= "00001000";
        wait for 5 ns;
       
        
        ------------------------ 
        -- test priority encoder
        ------------------------
        input <= "00001001";
        wait for 5 ns ;
       
        
        input <= "00000110";
        wait for 5 ns ;
       
        input <= "10000000";
        wait for 5 ns;
        
        input <= "11000000";
        wait for 5 ns;
        
        input <= "01000000";
        wait for 5 ns;
        
      
        
        wait ;
    end process auto_test ;

end architecture behaviour; 