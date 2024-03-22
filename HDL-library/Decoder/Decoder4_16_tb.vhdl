library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity Decoder4_16_tb is
end entity Decoder4_16_tb;

architecture behaviour of Decoder4_16_tb is
    -- component
    component Decoder4_16 is 
        port(
        input : in std_logic_vector(3 downto 0);
        enable : in std_logic;
        output : out std_logic_vector(15 downto 0)
    );
    end component;
    -- signals
    signal input : std_logic_vector(3 downto 0);
    signal enable : std_logic;
    signal output: std_logic_vector(15 downto 0);
    
begin
    -- map component 
    dut: Decoder4_16 
        port map(
            input => input,
            enable => enable,
            output => output
        );
    -- test simulation
    sim_time_proc: process
    begin
            -- test enable is zero     
            enable <= '0' ;
            wait for 10 ns;
            input <= "0000" ;
            wait for 10 ns;
            input <= "0010";
            wait for 10 ns;
            
            
            -- test enable is 1
            enable <= '1' ;
            wait for 10 ns ;
            input <= "0000";
            wait for 10 ns ;
            input <= "0001";
            wait for 10 ns ;
            input <= "0010";
            wait for 10 ns ;
            input <= "0011";
            wait for 10 ns ;
            input <= "0100";
            wait for 10 ns;
            input <= "0101";
            wait for 10 ns;
            input <= "0110";
            wait for 10 ns;
            input <= "0111";
            wait for 10 ns;
            input <= "1000";
            wait for 10 ns;
            input <= "1001";
            wait for 10 ns;
            input <= "1010";
            wait for 10 ns;
            input <= "1011";
            wait for 10 ns;
            input <= "1100";
            wait for 10 ns;
            input <= "1101";
            wait for 10 ns;
            input <= "1110";
            wait for 10 ns;
            input <= "1111";
            wait for 10 ns;
            
            
            wait ;
    end process;   
end behaviour; 