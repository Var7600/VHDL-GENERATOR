-------------------------------------------------------
-- Design Name : tff_sync_reset
-- File Name   : tff_sync_reset.vhdl
-- Function    : T flip-flop sync reset
-------------------------------------------------------

LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY tff_sync_reset IS
    PORT (
        data : IN STD_LOGIC; -- Data input
        clk : IN STD_LOGIC; -- Clock input
        reset : IN STD_LOGIC; -- Reset input
        q : OUT STD_LOGIC -- Q output

    );
END ENTITY;

ARCHITECTURE rtl OF tff_sync_reset IS
    SIGNAL t : STD_LOGIC;
BEGIN
    PROCESS (clk) BEGIN
        IF (rising_edge(clk)) THEN
            IF (reset = '0') THEN
                t <= '0';
            ELSE
                IF data='0' THEN
                    t <= '0';
                ELSE
                    t <= NOT t;
                END IF;
            END IF;
        END IF;
    END PROCESS;
    q <= t;
END ARCHITECTURE;