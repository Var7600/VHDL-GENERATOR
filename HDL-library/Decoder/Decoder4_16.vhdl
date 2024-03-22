library IEEE;
use IEEE.STD_logic_1164.all;

entity decoder4_16 is
    port(
          enable : in std_logic ;
         input: in std_logic_vector(3 downto 0);
         output: out std_logic_vector(15 downto 0));
end decoder4_16;

architecture behaviour of decoder4_16 is
begin
    process(enable,input) is begin
         if(enable = '1') then
            output(0) <= (not input(3)) and (not input(2)) and (not input(1)) and (not input(0));
            output(1) <= (not input(3)) and (not input(2)) and (not input(1)) and input(0);
            output(2) <= (not input(3)) and (not input(2)) and input(1) and (not input(0));
            output(3) <= (not input(3)) and (not input(2)) and input(1) and input(0);
            output(4) <= (not input(3)) and input(2) and (not input(1)) and (not input(0));
            output(5) <= (not input(3)) and input(2) and (not input(1)) and input(0);
            output(6) <= (not input(3)) and input(2) and input(1) and (not input(0));
            output(7) <= (not input(3)) and input(2) and input(1) and input(0);
            output(8) <= input(3) and (not input(2)) and (not input(1)) and (not input(0));
            output(9) <= input(3) and (not input(2)) and (not input(1)) and input(0);
            output(10)<= input(3) and (not input(2)) and input(1) and (not input(0));
            output(11)<= input(3) and (not input(2)) and input(1) and input(0);
            output(12) <= input(3) and input(2) and (not input(1)) and (not input(0));
            output(13) <= input(3) and input(2) and (not input(1)) and input(0);
            output(14) <= input(3) and input(2) and input(1) and (not input(0));
            output(15) <=  input(3) and  input(2) and  input(1) and  input(0);
        else
            output <= (others => '0') ;
        end if;
    end process ;
end Behaviour;