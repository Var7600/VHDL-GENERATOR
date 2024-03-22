library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity Encoder4x2_tb is
end entity Encoder4x2_tb;

architecture rtl of Encoder4x2_tb is
    
    component Encoder4x2 is
        port ( 
                input : in std_logic_vector(3 downto 0);
                enable : in std_logic;
                output : out STD_LOGIC_VECTOR(1 downto 0)   
        );                
    end component Encoder4x2;
    
    signal input : STD_LOGIC_VECTOR(3 downto 0);
    signal enable : std_logic;
    signal output : std_logic_vector(1 downto 0);
    
begin
    
    dut: Encoder4x2 
    port map (
        input => input,
        enable => enable,
        output => output
    ) ;
    
    auto_test : process is 
    begin
        
        report "starting test ..." ;
        
        enable <= '1';
        wait for 5 ns ;
        
        input <= "0000" ;
        wait for 5 ns ;
        -- test 1 no stimulus        
        assert output = "00" report "to fail test 1" severity Note;
        
        input <= "0001";
        wait for 5 ns ;
        -- test 2 
        assert output = "00" report "fail test 2" severity error;
        
        -- test 3
        input <= "0010";
        wait for 5 ns ;
        assert output="01" report "fail test 3" severity error;
        
        -- test 4
        input <= "0100";
        wait for 5 ns;
        assert output="10" report "fail test 4" severity error;
        
        -- test 5
        input <= "1000";
        wait for 5 ns;
        assert output="11" report "fail test 5" severity error;
        
        -- test unexpected value
        input <= "1001";
        wait for 5 ns ;
        assert output="XX" report "fail test 6" severity error;
        
        input <= "0110";
        wait for 5 ns ;
        assert output /="00" report "fail test 7 " severity warning;
        
        report "test finished OK!!!";
        
        wait ;
    end process auto_test ;
    
    


end architecture rtl; 