library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity Decoder2_4_tb is
end entity Decoder2_4_tb;

architecture behaviour of Decoder2_4_tb is
    -- component
    component Decoder2x4 is 
        port(
        input : in std_logic_vector(1 downto 0);
        enable : in std_logic;
        output : out std_logic_vector(3 downto 0)
    );
    end component;
    -- signals
    signal input : std_logic_vector(1 downto 0);
    signal enable : std_logic;
    signal output: std_logic_vector(3 downto 0);
    
begin
    -- map component 
    dut: Decoder2x4 
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
            input <= "00" ;
            wait for 10 ns;
            input <= "10";
            wait for 10 ns;
            
            
            -- test enable is 1
            enable <= '1' ;
            wait for 10 ns ;
            input <= "00";
            wait for 10 ns ;
            input <= "01";
            wait for 10 ns ;
            input <= "10";
            wait for 10 ns ;
            input <= "11";
            wait for 10 ns ;
            
            wait ;
    end process;   
end behaviour; 