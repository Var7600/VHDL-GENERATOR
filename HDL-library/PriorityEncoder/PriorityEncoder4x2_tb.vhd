library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity PriorityEncoder4x2_tb is
end entity PriorityEncoder4x2_tb;

architecture rtl of PriorityEncoder4x2_tb is
    
   
    
    component PriorityEncoder4_2 is
        port(
            enable     :in  std_logic;                      --  Enable for the encoder
            encoder_in :in  std_logic_vector (3 downto 0); --  4-bit Input
            encoder_out :out std_logic_vector (2 downto 0)   --  4 bit binary Output     
        );
    end component PriorityEncoder4_2;
    
    signal enable : std_logic;
    signal input : std_logic_vector(3 downto 0);
    signal output : std_logic_vector(2 downto 0);
    
begin

    u1: PriorityEncoder4_2
    port map
    (
        enable => enable,
        encoder_in => input,
        encoder_out => output
    );
    
    auto_test : process is 
    begin
        ------------------------------
        -- test basic encoder behaviour
        --------------------------------
        report "starting test ..." ;
        
        enable <= '1';
        wait for 5 ns ;
        
        input <= "0000" ;
        wait for 5 ns ;
        -- test 1 no stimulus        
        assert output = "--0" report "test to fail: test 1" severity Note;
        
        input <= "0001";
        wait for 5 ns ;
        -- test 2 
        assert output = "001" report "fail test 2" severity error;
        
        -- test 3
        input <= "0010";
        wait for 5 ns ;
        assert output="001" report integer'image(to_integer(unsigned(output))) & " fail test 3" severity error;
        
        -- test 4
        input <= "0100";
        wait for 5 ns;
        assert output="101" report "fail test 4" severity error;
        
        -- test 5
        input <= "1000";
        wait for 5 ns;
        assert output="111" report "fail test 5" severity error;
        
        ------------------------ 
        -- test priority encoder
        ------------------------
        input <= "1001";
        wait for 5 ns ;
        assert output="111" report integer'image(to_integer(unsigned(output))) & " fail test 6" severity error;
        
        input <= "0110";
        wait for 5 ns ;
        assert output ="101" report "fail test 7 " severity warning;
        
        report "test finished OK!!!";
        
        wait ;
    end process auto_test ;

end architecture rtl; 