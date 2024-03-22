library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity Demux_tb is
end entity Demux_tb;

architecture rtl of Demux_tb is
    
    
    component Demux1_2 is
        generic(DataWidth : integer := 8);
        port(
            input : in  std_logic_vector(DataWidth-1 downto 0);
            output0,output1 : out std_logic_vector(DataWidth-1 downto 0);
            sel : in std_logic
        );
    end component Demux1_2;
    
    constant DataWidth : integer := 4 ;
    signal  output0,output1 :  std_logic_vector(DataWidth-1 downto 0);
    signal input : std_logic_vector(DataWidth-1 downto 0); 
    signal sel : std_logic ;
    
begin

    
    dut: Demux1_2
    generic map (DataWidth => DataWidth)
    port map
    (
        input => input,
         sel => sel,
        output0 => output0,
        output1 => output1
       
    );
    
    simul : process is begin
            input <= "0001";
            wait for 5 ns;
            sel <= '0' ;
            wait for 5 ns ;
            input <= "1000";
            wait for 5 ns;
            sel <= '1';
            wait for 5 ns;
             wait ;
        end process;

end architecture rtl; 