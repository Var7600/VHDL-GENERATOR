library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity HalfAdder is
    port(
        a : in std_logic ;
        b : in std_logic ;
        sum : out std_logic ;
        carry : out std_logic 
        
    );
end entity HalfAdder;

architecture behaviour of HalfAdder is
begin
    sum <= a xor b ;
    carry <= a and b;    

end architecture behaviour;