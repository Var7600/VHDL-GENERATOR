library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity jk_flip_flop is
    port(
        j,k,clk,rst : in STD_LOGIC ;
        q,not_q : out STD_LOGIC 
    );
end jk_flip_flop;

architecture behaviour of jk_flip_flop is
begin
    process(clk,rst)
    variable qn : STD_LOGIC ;
    begin
        if ( rst = '1') then
            qn := '0';
        elsif (clk'event and clk='1') then
            if (j = '0' and k='0') then
                qn := qn ;
            elsif ( j = '0' and k='1') then
                qn := '0';
            elsif (j = '1' and k = '0') then
                qn := '1';
            elsif ( j = '1' and k = '1') then
                qn := not qn ;
            else
                null ;
            end if;
       end if;        
       q <= qn ;
       not_q <= not qn;
    end process;

end architecture behaviour;