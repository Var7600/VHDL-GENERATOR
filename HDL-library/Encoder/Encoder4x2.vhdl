library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity Encoder4x2 is
    port(
        input : in std_logic_vector(3 downto 0);
        enable: in std_logic;
        output : out STD_LOGIC_VECTOR(1 downto 0)       
    );
end entity Encoder4x2;

architecture behaviour of Encoder4x2 is
    
begin
    
    encoder:process(enable,input) is begin
        if enable = '1' then
            case input is
            when "0001" =>
                output <= "00";
            when "0010" =>
                output <= "01" ;
            when "0100" =>
                output <= "10";
            when "1000" =>
                output <= "11";
            when others => 
                output <= (others => 'X');
            end case;
        end if;
        
    end process encoder;


end architecture behaviour;