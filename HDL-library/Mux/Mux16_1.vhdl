library ieee;
use ieee.std_logic_1164.ALL;
use ieee.numeric_std.all;

entity MUX16_1 is
    generic (DataWidth : natural := 8);
    Port ( input0 : in std_logic_vector (DataWidth-1 downto 0);
           input1 : in std_logic_vector (DataWidth-1 downto 0);
           input2 : in std_logic_vector (DataWidth-1 downto 0);
           input3 : in std_logic_vector (DataWidth-1 downto 0);
           input4 : in std_logic_vector (DataWidth-1 downto 0);
           input5 : in std_logic_vector (DataWidth-1 downto 0);
           input6 : in std_logic_vector (DataWidth-1 downto 0);
           input7 : in std_logic_vector (DataWidth-1 downto 0);
           input8 : in std_logic_vector (DataWidth-1 downto 0);
           input9 : in std_logic_vector (DataWidth-1 downto 0);
           input10 : in std_logic_vector (DataWidth-1 downto 0);
           input11 : in std_logic_vector (DataWidth-1 downto 0);
           input12 : in std_logic_vector (DataWidth-1 downto 0);
           input13 : in std_logic_vector (DataWidth-1 downto 0);
           input14 : in std_logic_vector (DataWidth-1 downto 0);
           input15 : in std_logic_vector (DataWidth-1 downto 0);
           sel : in std_logic_vector (3 downto 0);
           output : out std_logic_vector(DataWidth-1 downto 0)
           );
end MUX16_1;

architecture behaviour of MUX16_1 is
begin
    process(sel,input0,input1,input2,input3,input4,input5,
    input6,input7,input8,input9,input10,input11,input12,input13,input14,input15) is
    begin
        case sel is
            when "0000" =>
                output <= input0;
            when "0001" =>
                output <= input1;
            when "0010" =>
                output <= input2;
            when "0011" =>
                output <= input3;
            when "0100" =>
                output <= input4;
            when "0101" =>
                output <= input5;
            when "0110" =>
                output <= input6;
            when "0111" =>
                output <= input7;
            when "1000" =>
                output <= input8;
            when "1001" =>
                output <= input9;
            when "1010" =>
                output <= input10;
            when "1011" =>
                output <= input11;
            when "1100" =>
                output <= input12;
            when "1101" =>
                output <= input13;
            when "1110" =>
                output <= input14;
            when "1111" =>
                output <= input15;
            when others =>
                output <= (others => '0');
        end case;
    end process;
   
end behaviour;
