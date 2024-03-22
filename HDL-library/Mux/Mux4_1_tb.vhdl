library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity Mux4_1_tb is

end entity Mux4_1_tb;

architecture testbench of Mux4_1_tb is
    -- component
    COMPONENT Mux4_1 IS
        generic (
            DataWidth :  natural := 8
        ); port (
            input0    : in std_logic_vector(DataWidth - 1 downto 0);
            input1    : in std_logic_vector(DataWidth - 1 downto 0);
            input2    : in std_logic_vector(DataWidth - 1 downto 0);
            input3    : in std_logic_vector(DataWidth - 1 downto 0);
            sel       : in std_logic_vector(1 downto 0);
            output    : out std_logic_vector(DataWidth - 1 downto 0)
        );
    end component Mux4_1;
    -- signals
    constant DataWidth : natural := 4;
    signal input0,input1,input2,input3,output : std_logic_vector(DataWidth-1 downto 0);
    signal sel : std_logic_vector(1 downto 0);
    
begin
    i_Mux4_1_map: Mux4_1 generic map (DataWidth => DataWidth)
    port map  (
            input0 => input0,
            input1 => input1,
            input2 => input2,
            input3 => input3,
            sel => sel,
            output => output
        );
        auto_process: process is 
            begin
                report "Starting test ...";
                
                sel <= "00";
                wait for 10 ns ;
                input0 <= "1000";
                wait for 10 ns ;
                
                 -- test 1
                assert(output = input0)
                report "fail test 1"
                severity error;
                
                -- test 2
                sel <= "01";
                wait for 10 ns ;
                input1 <= "0001";
                wait for 10 ns ;
                
                -- test 3
                assert (output = input1) report "fail test 2" severity error;
                
                sel <= "10";
                wait for 10 ns;
                input2 <= "1111";
                wait for 10 ns ;
                
                 -- test 4
                assert (output = input2) report "fail test 3" severity error;
                
                sel <= "11";
                wait for 10 ns;
                input3 <= "0011";
                wait for 10 ns;
                
                -- test 5
                assert (output = input3)  report "fail test 4" severity error;
                
                report "Test finished Okay!";
                
                wait ;
        end process;

end testbench;