library ieee;
use ieee.std_logic_1164. all;
use ieee.numeric_std.all;

entity Demux1_8 is
    generic(DataWidth: integer := 8);
    port (
            input : in std_logic_vector(DataWidth-1 downto 0);
            sel : in std_logic_vector(DataWidth-1 downto 0);
            output0,output1,output2,output3,output4,output5,output6,output7 : out std_logic_vector(DataWidth-1 downto 0)
        ) ;
    end Demux1_8;
    
architecture behaviour of Demux1_8 is
begin
process (input,sel) is begin
    case sel is
    when "000" =>
           output0 <= input ;
    when "001" =>
            output1 <= input;
    when "010" =>
            output2 <= input;
    when "011" =>
            output3 <= input;
    when "100" =>
            output4 <= input;        
    when "101" =>
            output5 <= input;
    when "110" =>
            output6 <= input;
    when "111" =>
            output7 <= input;
    when others =>
            output0 <= (others => '0');
            output1 <= (others => '0');
            output2 <= (others => '0');
            output3 <= (others => '0');
            output4 <= (others => '0');
            output5 <= (others => '0');
            output6 <= (others => '0');
            output7 <= (others => '0');
    end case;
end process;
end behaviour ;






