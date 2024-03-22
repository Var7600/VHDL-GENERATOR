library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity Mux2_1 is 
generic (DataWidth : natural := 8);
        port(
        input0,input1 : in std_logic_vector(DataWidth-1 downto 0);
        output : out std_logic_vector(DataWidth-1 downto 0);
        sel : in std_logic
    );
end entity Mux2_1;

architecture behaviour of Mux2_1 is
begin
    with sel select
    output <= input0 when '0',
    input1 when '1',
    (others => '0') when others;
end behaviour;  