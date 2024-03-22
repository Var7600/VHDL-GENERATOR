library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity Mux_tb is
end entity Mux_tb;

architecture rtl of Mux_tb is
    -- constant
    constant  DataWidth : natural := 8 ;
    -- component
    component Mux2_1 is
        generic (DataWidth : natural := 8);
        port(
            input0,input1 : in std_logic_vector(DataWidth-1 downto 0);
            output : out std_logic_vector(DataWidth-1 downto 0);
            sel : in std_logic
        );       
    end component Mux2_1;
    -- signal
    signal input0,input1,output : std_logic_vector(DataWidth-1 downto 0);
    signal sel : std_logic ;
    
    
    
begin

    dut_map: Mux2_1
    generic map ( DataWidth => DataWidth)
    port map
    (
        input0 => input0,
        input1 => input1,
        sel => sel,
        output => output
    );
    
    process_simulation: process is
        begin
        wait for 10 ns;
        input0 <="00000100";
        wait for 10 ns;
        input1 <= "00001000";
        wait for 10 ns;
        sel <= '0';
        wait for 10 ns;
        sel <= '1';
        wait for 10 ns;
        wait;
    end process;

end architecture rtl;