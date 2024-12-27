library ieee;
use ieee.std_logic_1164.all;

entity T_FlipFlop_tb is
end entity T_FlipFlop_tb;

architecture Behavioral of T_FlipFlop_tb is
    component tff_sync_reset is
        Port ( data : in  std_logic;
            reset: in std_logic;
            Clk : in  std_logic;
            Q : out std_logic);
    end component;

    signal data, reset, Clk_sig, Q_sig : std_logic;

begin
    uut: tff_sync_reset port map (
        data => data,
        reset => reset,
        Clk => Clk_sig,
        Q => Q_sig
    );

    -- Clock signal generation
    process
    begin
        Clk_sig <= '0';
        wait for 10 ns;
        Clk_sig <= '1';
        wait for 10 ns;
    end process;

    -- Testbench stimulus
    process
    begin
        -- Initialize signals
        data <= '0';
        wait for 20 ns;

        -- Test case 1: Toggle output on positive edge with T=1
        data <= '1';
        wait for 20 ns;
        data <= '0';
        wait for 20 ns;

        -- Test case 2: No change on positive edge with T=0
        wait for 20 ns;
        reset <= '1';
        wait for 5 ns;
        reset <='0';
        wait for 10 ns;

        wait;
    end process;
end architecture Behavioral;