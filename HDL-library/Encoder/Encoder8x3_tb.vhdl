library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity Encoder8x3_tb is
end entity Encoder8x3_tb;

architecture testbench of Encoder8x3_tb is
    
    component Encoder8x3 is
        port ( 
                input : in std_logic_vector(7 downto 0);
                enable : in STD_LOGIC;
                output : out STD_LOGIC_VECTOR(2 downto 0)   
        );                
    end component Encoder8x3;
    
    signal input : STD_LOGIC_VECTOR(7 downto 0);
    signal enable : std_logic;
    signal output : std_logic_vector(2 downto 0);
    
begin
    
    dut: Encoder8x3 
    port map (
        input => input,
        enable => enable,
        output => output
    ) ;
    
    auto_test : process is 
    begin
        
        report "starting test ..." ;
        
        enable <= '1';
        wait for 5 ns;
        
        input <= "00000000" ;
        wait for 5 ns ;
        -- test 1 no stimulus        
        assert output = "000" report "to fail test 1" severity Note;
        
        input <= "00000001";
        wait for 5 ns ;
        -- test 2 
        assert output = "000" report "fail test 2" severity error;
        
        -- test 3
        input <= "00000010";
        wait for 5 ns ;
        assert output="001" report "fail test 3" severity error;
        
        -- test 4
        input <= "00000100";
        wait for 5 ns;
        assert output="010" report "fail test 4" severity error;
        
        -- test 5
        input <= "00001000";
        wait for 5 ns;
        assert output="011" report "fail test 5" severity error;
        
        -- test unexpected value
        input <= "00001001";
        wait for 5 ns ;
        assert output="XXX" report "fail test 6" severity error;
        
        input <= "00000110";
        wait for 5 ns ;
        assert output /="00" report "fail test 7 " severity warning;
        
        report "test finished OK!!!";
        
        wait ;
    end process auto_test ;
    
    


end architecture testbench;