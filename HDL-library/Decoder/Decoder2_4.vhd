library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity Decoder2_4 is
    port(
        input : in std_logic_vector(1 downto 0);
        enable : in std_logic;
        output : out std_logic_vector(3 downto 0)
    );
end  Decoder2_4;

architecture behaviour of Decoder2_4 is
    
begin
    process(enable,input) is begin
        if(enable = '1') then
            case input is
                when "00" =>
                    output <= "0001" ;
                when "01" =>
                    output <= "0010" ;
                when "10" =>
                    output <= "0100" ;
                when "11" =>
                    output <= "1000" ;
                when others =>
                    output <= "0000";
                end case;
        end if;
    end process;
    
end  behaviour;