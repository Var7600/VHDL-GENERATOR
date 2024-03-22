library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity Demux1_4 is 
    generic(DataWidth: integer := 8);
    port(
        input : in std_logic_vector(DataWidth-1 downto 0);
        sel : in std_logic_vector(1 downto 0);
        output0,output1,output2,output3 : out std_logic_vector(DataWidth-1 downto 0)
    );
end entity Demux1_4;

architecture behaviour of Demux1_4 is
    begin
    process(input,sel) is begin
        case sel is
            when "00" =>
            output0 <= input;
          
            when "01" =>
                output1 <= input;
               
            when "10" =>
                output2 <= input;
               
           when "11" =>
                output3 <= input;
                
            when others =>
                 output0 <= (others => '0');
                 output1 <= (others => '0');
                 output2 <= (others => '0');
                 output3 <= (others => '0');
        end case;
    end process;
end architecture behaviour;

    