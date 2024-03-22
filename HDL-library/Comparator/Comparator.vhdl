--------------------------------------------------------------------------------                                                                                                                        
-- Component : Comparator of n-bits                                                                                                                 
---------------------------------------------------------------------------------

LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.numeric_std.all;


ENTITY Comparator IS
   GENERIC ( DataWidth       : natural;
             twosComplement : bit ); -- if set to 1 a and b are signed otherwise unsigned
   PORT ( dataA         : IN  std_logic_vector( (DataWidth - 1) DOWNTO 0 );
          dataB         : IN  std_logic_vector( (DataWidth - 1) DOWNTO 0 );
          aEqualsB      : OUT std_logic;
          aGreaterThanB : OUT std_logic;
          aLessThanB    : OUT std_logic );
END ENTITY Comparator;

ARCHITECTURE behaviour OF Comparator IS 

--------------------------------------------------------------------------------
-- All used signals are defined here                                          --
--------------------------------------------------------------------------------
   SIGNAL s_signedGreater   : std_logic;
   SIGNAL s_signedLess      : std_logic;
   SIGNAL s_unsignedGreater : std_logic;
   SIGNAL s_unsignedLess    : std_logic;

BEGIN

   s_signedLess      <= '1' WHEN signed(dataA) < signed(dataB) ELSE '0';
   s_unsignedLess    <= '1' WHEN unsigned(dataA) < unsigned(dataB) ELSE '0';
   s_signedGreater   <= '1' WHEN signed(dataA) > signed(dataB) ELSE '0';
   s_unsignedGreater <= '1' WHEN unsigned(dataA) > unsigned(dataB) ELSE '0';

   aEqualsB      <= '1' WHEN dataA = dataB ELSE '0';
   aGreaterThanB <= s_signedGreater WHEN twosComplement = '1' ELSE s_unsignedGreater;
   aLessThanB    <= s_signedLess WHEN twosComplement = '1' ELSE s_unsignedLess;

END ARCHITECTURE behaviour;