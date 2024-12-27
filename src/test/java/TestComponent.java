
/**
 * @file TestComponent.java
 *
 * @version 0.1
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import vhdlgenerator.component.adder.Adder;
import vhdlgenerator.component.comparator.Comparator;
import vhdlgenerator.component.decoder.Decoder;
import vhdlgenerator.component.demultiplexer.Demux;
import vhdlgenerator.component.encoder.Encoder;
import vhdlgenerator.component.flipflop.DFlipFlop;
import vhdlgenerator.component.flipflop.JKFlipFlop;
import vhdlgenerator.component.flipflop.TFlipFlop;
import vhdlgenerator.component.multiplexer.Mux;
import vhdlgenerator.component.priorityencoder.PriorityEncoder;
import vhdlgenerator.component.segment7.Segment7;
import vhdlgenerator.component.subtractor.Subtractor;

/**
 * this class test the different constructor and methods in the package {link
 * #vhdlgenerator.component}
 *
 * @author DOUDOU DIAWARA
 */
class TestComponent
{
	Path path; // path to temporary Directory
	String path_string; // toString path

	@BeforeEach
	public void setup()
	{
		// create temporary directory
		try
		{
			path = Files.createTempDirectory("Compoenent");
			path_string = path.toString();
		} catch (IOException e)
		{
			System.out.println("Error can't no create temporary directory for test!\n "
					+ "check if you have permission to create temporary directory");
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Test Component Mux")
	void testMux()
	{
		String MUX2 = """
				LIBRARY ieee;
				USE ieee.std_logic_1164.ALL;
				USE ieee.numeric_std.ALL;

				ENTITY Mux_2x1 IS
					GENERIC (DataWidth : NATURAL := 8);
					PORT (
						input0, input1 : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
						output : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
						sel : IN STD_LOGIC
					);
				END Mux_2x1;

				ARCHITECTURE behaviour OF Mux_2x1 IS
				BEGIN
					WITH sel SELECT
						output <= input0 WHEN '0',
						input1 WHEN '1',
						(OTHERS => '0') WHEN OTHERS;
				END behaviour;
				""";
		String MUX8 = """
				LIBRARY IEEE;
				USE IEEE.STD_LOGIC_1164.ALL;

				ENTITY Mux8x1 IS
					GENERIC (DataWidth : NATURAL := 8);
					PORT (
						input0, input1, input2, input3, input4, input5, input6, input7 : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
						sel : IN STD_LOGIC_VECTOR (2 DOWNTO 0);
						output : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0)
					);
				END Mux8x1;

				ARCHITECTURE behaviour OF Mux8x1 IS
				BEGIN
					PROCESS (sel, input0, input1, input2, input3, input4, input5, input6, input7) IS BEGIN

						CASE sel IS
							WHEN "000" =>
								output <= input0;
							WHEN "001" =>
								output <= input1;
							WHEN "010" =>
								output <= input2;
							WHEN "011" =>
								output <= input3;
							WHEN "100" =>
								output <= input4;
							WHEN "101" =>
								output <= input5;
							WHEN "110" =>
								output <= input6;
							WHEN "111" =>
								output <= input7;
							WHEN OTHERS =>
								output <= (OTHERS => '0');
						END CASE;
					END PROCESS;
				END behaviour;
				""";
		// to read the data written to the file
		String content_mux2 = null;
		String content_mux8 = null;
		// write Mux 2x1 code
		String path_mux2 = Mux.writeMux("2", path_string);
		String path_mux8 = Mux.writeMux("8", path_string);
		try
		{
			content_mux2 = Files.readString(Paths.get(path_mux2));
			content_mux8 = Files.readString(Paths.get(path_mux8));
			// check if the correct data was written to the file
			assertEquals(MUX2, content_mux2);
			assertEquals(MUX8, content_mux8);

		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}

		// test invalid size Mux set mux to invalid size
		Assertions.assertThrows(IllegalArgumentException.class, () -> Mux.writeMux("3", path_string));
	}

	@Test
	@DisplayName("test Component Demux")
	void testDemux()
	{
		String DEMUX4 = """
				LIBRARY ieee;
				USE ieee.std_logic_1164.ALL;
				USE ieee.numeric_std.ALL;

				ENTITY Demux1_4 IS
					GENERIC (DataWidth : NATURAL := 8);
					PORT (
						input : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
						sel : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
						output0, output1, output2, output3 : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0)
					);
				END ENTITY Demux1_4;

				ARCHITECTURE behaviour OF Demux1_4 IS
				BEGIN
					PROCESS (input, sel) IS BEGIN
						CASE sel IS
							WHEN "00" =>
								output0 <= input;
							WHEN "01" =>
								output1 <= input;
							WHEN "10" =>
								output2 <= input;
							WHEN "11" =>
								output3 <= input;
							WHEN OTHERS =>
								output0 <= (OTHERS => '0');
								output1 <= (OTHERS => '0');
								output2 <= (OTHERS => '0');
								output3 <= (OTHERS => '0');
						END CASE;
					END PROCESS;
				END ARCHITECTURE behaviour;
				""";
		String DEMUX8 = """
				LIBRARY ieee;
				USE ieee.std_logic_1164. ALL;
				USE ieee.numeric_std.ALL;

				ENTITY Demux1_8 IS
					GENERIC (DataWidth : NATURAL := 8);
					PORT (
						input : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
						sel : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
						output0, output1, output2, output3, output4, output5, output6, output7 : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0)
					);
				END Demux1_8;

				ARCHITECTURE behaviour OF Demux1_8 IS
				BEGIN
					PROCESS (input, sel) IS BEGIN
						CASE sel IS
							WHEN "000" =>
								output0 <= input;
							WHEN "001" =>
								output1 <= input;
							WHEN "010" =>
								output2 <= input;
							WHEN "011" =>
								output3 <= input;
							WHEN "100" =>
								output4 <= input;
							WHEN "101" =>
								output5 <= input;
							WHEN "110" =>
								output6 <= input;
							WHEN "111" =>
								output7 <= input;
							WHEN OTHERS =>
								output0 <= (OTHERS => '0');
								output1 <= (OTHERS => '0');
								output2 <= (OTHERS => '0');
								output3 <= (OTHERS => '0');
								output4 <= (OTHERS => '0');
								output5 <= (OTHERS => '0');
								output6 <= (OTHERS => '0');
								output7 <= (OTHERS => '0');
						END CASE;
					END PROCESS;
				END behaviour;
				""";
		// to read the data written to the file
		String content_demux4 = null;
		String content_demux8 = null;
		// write Mux 2x1 code
		String path_demux4 = Demux.writeDemux("4", path_string);
		String path_demux8 = Demux.writeDemux("8", path_string);
		try
		{
			content_demux4 = Files.readString(Paths.get(path_demux4));
			content_demux8 = Files.readString(Paths.get(path_demux8));
			// check if the correct data was written to the file
			assertEquals(DEMUX4, content_demux4);
			assertEquals(DEMUX8, content_demux8);
		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}

		// test invalid size Demux set demux to invalid size
		Assertions.assertThrows(IllegalArgumentException.class, () -> Demux.writeDemux("3", path_string));
	}

	@Test
	@DisplayName("test Component Adder")
	void adder()
	{
		String ARITHMETIC_ADDER_VHDL = """
				--------------------------------------------------------
				-- VHDL code for n-bit adder
				--
				-- function of adder:
				-- A plus B to get n-bit sum and 1 bit carry
				-- we may use generic statement to set the parameter
				-- n(DataWidth) of the adder.
				--------------------------------------------------------

				LIBRARY ieee;
				USE ieee.std_logic_1164.ALL;
				USE ieee.std_logic_arith.ALL;
				USE ieee.std_logic_unsigned.al
				ENTITY ADDER IS
					GENERIC (n : NATURAL := 8);
					PORT (
						a : IN STD_LOGIC_VECTOR(n - 1 DOWNTO 0);
						b : IN STD_LOGIC_VECTOR(n - 1 DOWNTO 0);
						carry : OUT STD_LOGIC;
						sum : OUT STD_LOGIC_VECTOR(n - 1 DOWNTO 0)
					);

				END ENTITY ADDER;

				ARCHITECTURE behaviour OF ADDER IS

					-- define a temparary signal to store the result

					SIGNAL result : STD_LOGIC_VECTOR(n DOWNTO 0);

				BEGIN

					-- the 3rd bit should be carry
					result <= ('0' & a) + ('0' & b);
					sum <= result(n - 1 DOWNTO 0);
					carry <= result(n);

				END behaviour;
				""";
		String HALF_ADDER = """
				--------------------------------------------------------
				-- VHDL code for 2-bit adder
				--
				-- function of adder:
				-- A plus B to get bit sum and 1 bit carry
				--------------------------------------------------------
				library IEEE;
				use IEEE.std_logic_1164.all;
				use IEEE.numeric_std.all;

				entity HalfAdder is
					port(
					a : in std_logic ;
					b : in std_logic ;
					sum : out std_logic ;
					carry : out std_log
					);
				end entity HalfAdder;

				architecture behaviour of HalfAdder is
				begin
					sum <= a xor b ;
					carry <= a and b
				end architecture behaviour;
				""";
		// to read the data written to the file
		String content_full_adder = null;
		String content_half_adder = null;
		//
		// write full_adder and half_adder code
		String path_arithmetic_adder = Adder.writeAdder("Arithmetic-Adder", path_string);
		String path_half_adder = Adder.writeAdder("Bit-HalfAdder", path_string);
		try
		{
			content_full_adder = Files.readString(Paths.get(path_arithmetic_adder));
			content_half_adder = Files.readString(Paths.get(path_half_adder));

			// check if the correct data was written to the file
			assertEquals(ARITHMETIC_ADDER_VHDL, content_full_adder);
			assertEquals(HALF_ADDER, content_half_adder);

		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}

		// test invalid Adder
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> Adder.writeAdder("arithmetic_adder", path_string));
	}

	@Test
	@DisplayName("test Component FlipFLop")
	void testFlipFlop()
	{
		String DFILPFLOP_VHDL = """
				LIBRARY IEEE;
				USE IEEE.std_logic_1164.ALL;
				USE IEEE.numeric_std.ALL;

				ENTITY d_flip_flop IS
					PORT (
						d, clk : IN STD_LOGIC;
						q : OUT STD_LOGIC
					);
				END ENTITY d_flip_flop;

				ARCHITECTURE behaviour OF d_flip_flop IS

				BEGIN
					dff : PROCESS (d, clk)
					BEGIN
						IF rising_edge(clk) THEN
							q <= d;
						END IF;
					END PROCESS dff;

				END ARCHITECTURE behaviour;
				""";
		// jk-flip-flop
		String JK_VHDL = """
				LIBRARY IEEE;
				USE IEEE.std_logic_1164.ALL;
				USE IEEE.numeric_std.ALL;

				ENTITY jk_flip_flop IS
					PORT (
						j, k, clk, rst : IN STD_LOGIC;
						q, not_q : OUT STD_LOGIC
					);
				END jk_flip_flop;

				ARCHITECTURE behaviour OF jk_flip_flop IS
				BEGIN
					PROCESS (clk, rst)
						VARIABLE qn : STD_LOGIC;
					BEGIN
						IF (rst = '1') THEN
							qn := '0';
						ELSIF (clk'EVENT AND clk = '1') THEN
							IF (j = '0' AND k = '0') THEN
								qn := qn;
							ELSIF (j = '0' AND k = '1') THEN
								qn := '0';
							ELSIF (j = '1' AND k = '0') THEN
								qn := '1';
							ELSIF (j = '1' AND k = '1') THEN
								qn := NOT qn;
							ELSE
								NULL;
							END IF;
						END IF;
						q <= qn;
						not_q <= NOT qn;
					END PROCESS;

				END ARCHITECTURE behaviour;
				""";
		String TFLIPFLOP_SYNC_VHDL = """
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
				""";

		String content_dflipflop = null;
		String content_jkflipflop = null;
		String content_tflipflop_sync = null;
		//
		// write flipflop code
		String path_dflipflop = DFlipFlop.writeDFlipFlop("D-Flip-Flop", path_string);
		String path_jkflipflop = JKFlipFlop.writeJK(path_string);
		String path_tflipflop_sync = TFlipFlop.writeTFlipFlop("T-Flip-Flop-Sync", path_string);

		try
		{
			content_dflipflop = Files.readString(Paths.get(path_dflipflop));
			content_jkflipflop = Files.readString(Paths.get(path_jkflipflop));
			content_tflipflop_sync = Files.readString(Paths.get(path_tflipflop_sync));

			// check if the correct data was written to the file
			assertEquals(DFILPFLOP_VHDL, content_dflipflop);
			assertEquals(JK_VHDL, content_jkflipflop);
			assertEquals(TFLIPFLOP_SYNC_VHDL, content_tflipflop_sync);

		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}

		// test invalid
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> DFlipFlop.writeDFlipFlop("DFlipFlop", path_string));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> TFlipFlop.writeTFlipFlop("T-Flip-Flop", path_string));
		Assertions.assertThrows(NullPointerException.class, () -> JKFlipFlop.writeJK(null));

	}

	@Test
	@DisplayName("test Component Encoder Decoder and PriorityEncoder")
	void testCoder()
	{
		String ENCODER4_VHDL = """
				LIBRARY IEEE;
				USE IEEE.std_logic_1164.ALL;
				USE IEEE.numeric_std.ALL;

				ENTITY Encoder4x2 IS
					PORT (
						input : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
						enable : IN STD_LOGIC;
						output : OUT STD_LOGIC_VECTOR(1 DOWNTO 0)
					);
				END ENTITY Encoder4x2;

				ARCHITECTURE behaviour OF Encoder4x2 IS

				BEGIN

					encoder : PROCESS (enable, input) IS BEGIN
						IF enable = '1' THEN
							CASE input IS
								WHEN "0001" =>
									output <= "00";
								WHEN "0010" =>
									output <= "01";
								WHEN "0100" =>
									output <= "10";
								WHEN "1000" =>
									output <= "11";
								WHEN OTHERS =>
									output <= (OTHERS => 'X');
							END CASE;
						END IF;

					END PROCESS encoder;
				END ARCHITECTURE behaviour;
				""";
		String DECODER3_VHDL = """
							LIBRARY IEEE;
							USE IEEE.STD_LOGIC_1164.ALL;
							USE IEEE.numeric_std.ALL;

							ENTITY Decoder3_8 IS
								PORT (
									input : IN STD_LOGIC_VECTOR(2 DOWNTO 0);
									enable : IN STD_LOGIC;
									output : OUT STD_LOGIC_VECTOR(7 DOWNTO 0));
							END Decoder3_8;

							ARCHITECTURE Behavioral OF Decoder3_8 IS
							BEGIN
								PROCESS (input, enable)
								BEGIN
									IF enable = '1' THEN
										CASE input IS
											WHEN "000" =>
												output <= "00000001";
											WHEN "001" =>
												output <= "00000010";
											WHEN "010" =>
												output <= "00000100";
											WHEN "011" =>
												output <= "00001000";
											WHEN "100" =>
												output <= "00010000";
											WHEN "101" =>
												output <= "00100000";
											WHEN "110" =>
												output <= "01000000";
											WHEN "111" =>
												output <= "10000000";
											WHEN OTHERS =>
												output <= "00000000"; -- default case
										END CASE;
									ELSE
										output <= "00000000"; -- output is all '0' when disabled
									END IF;
								END PROCESS;
							END Behavioral;
				""";
		String PR16_VHDL = """
				-------------------------------------------------------
				-- Design Name : PriorityEncoder16_4
				-- File Name   : PriorityEncoder16_4.vhdl
				-- Function    : Pri Encoder using when-else
				-------------------------------------------------------
				LIBRARY ieee;
				USE ieee.std_logic_1164.ALL;

				ENTITY PriorityEncoder16_4 IS
					PORT (
						enable : IN STD_LOGIC; --  Enable for the encoder
						encoder_in : IN STD_LOGIC_VECTOR (15 DOWNTO 0); --  16-bit Input
						binary_out : OUT STD_LOGIC_VECTOR (3 DOWNTO 0) --  4 bit binary Output

					);
				END PriorityEncoder16_4;

				ARCHITECTURE behaviour OF PriorityEncoder16_4 IS

				BEGIN

					binary_out <= "0000" WHEN enable = '0' ELSE
						"0001" WHEN encoder_in(1) = '1' ELSE
						"0010" WHEN encoder_in(2) = '1' ELSE
						"0011" WHEN encoder_in(3) = '1' ELSE
						"0100" WHEN encoder_in(4) = '1' ELSE
						"0101" WHEN encoder_in(5) = '1' ELSE
						"0110" WHEN encoder_in(6) = '1' ELSE
						"0111" WHEN encoder_in(7) = '1' ELSE
						"1000" WHEN encoder_in(8) = '1' ELSE
						"1001" WHEN encoder_in(9) = '1' ELSE
						"1010" WHEN encoder_in(10) = '1' ELSE
						"1011" WHEN encoder_in(11) = '1' ELSE
						"1100" WHEN encoder_in(12) = '1' ELSE
						"1101" WHEN encoder_in(13) = '1' ELSE
						"1110" WHEN encoder_in(14) = '1' ELSE
						"1111" WHEN encoder_in(15) = '1' ELSE
						"0000";
				END behaviour;
				""";
		String content_encoder = null;
		String content_decoder = null;
		String content_priority_encoder = null;
		// write encoder decoder an₫ priority-encoder
		String path_encoder = Encoder.writeEncoder("4", path_string);
		String path_decoder = Decoder.writeDecoder("3", path_string);
		String path_priority_encoder = PriorityEncoder.writePriorityEncoder("16", path_string);

		try
		{
			content_encoder = Files.readString(Paths.get(path_encoder));
			content_decoder = Files.readString(Paths.get(path_decoder));
			content_priority_encoder = Files.readString(Paths.get(path_priority_encoder));
			// check if the correct data was written to the file
			assertEquals(ENCODER4_VHDL, content_encoder);
			assertEquals(DECODER3_VHDL, content_decoder);
			assertEquals(PR16_VHDL, content_priority_encoder);
		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}

		// test invalid
		Assertions.assertThrows(IllegalArgumentException.class, () -> Encoder.writeEncoder("1", path_string));
		Assertions.assertThrows(IllegalArgumentException.class, () -> Decoder.writeDecoder("5", path_string));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> PriorityEncoder.writePriorityEncoder("7", path_string));
	}

	@Test
	@DisplayName("test Component Comparator")
	void testComparator()
	{
		String BIT_COMPARATOR_VHDL = """
				--==============================================================================
				--== Component : BitComparator                                                ==
				--== compare two bits a and b if a == b then a_equals_b=1 ,                    ==
				--== if a > b then a_greater_than_b=1 , if a < b then a_less_than_b           ==
				--==============================================================================

				LIBRARY ieee;
				USE ieee.std_logic_1164.ALL;
				USE ieee.numeric_std.ALL;
				ENTITY BitComparator IS

					PORT (
						a : IN STD_LOGIC;
						b : IN STD_LOGIC;
						a_equals_b : OUT STD_LOGIC;
						a_greater_than_b : OUT STD_LOGIC;
						a_less_than_b : OUT STD_LOGIC);

				END ENTITY BitComparator;

				ARCHITECTURE behaviour OF BitComparator IS

				BEGIN

					a_equals_b <= a XNOR b;
					a_less_than_b <= NOT(a) AND b;
					a_greater_than_b <= a AND NOT(b);

				END behaviour;
				""";
		String COMPARATOR_VHDL = """
				--------------------------------------------------------------------------------
				-- Component : Comparator of n-bits
				---------------------------------------------------------------------------------

				LIBRARY ieee;
				USE ieee.std_logic_1164.ALL;
				USE ieee.numeric_std.ALL;
				ENTITY Comparator IS
					GENERIC (
						DataWidth : NATURAL;
						twosComplement : BIT); -- if set to 1 a and b are signed otherwise unsigned
					PORT (
						dataA : IN STD_LOGIC_VECTOR((DataWidth - 1) DOWNTO 0);
						dataB : IN STD_LOGIC_VECTOR((DataWidth - 1) DOWNTO 0);
						aEqualsB : OUT STD_LOGIC;
						aGreaterThanB : OUT STD_LOGIC;
						aLessThanB : OUT STD_LOGIC);
				END ENTITY Comparator;

				ARCHITECTURE behaviour OF Comparator IS

					--------------------------------------------------------------------------------
					-- All used signals are defined here                                          --
					--------------------------------------------------------------------------------
					SIGNAL s_signedGreater : STD_LOGIC;
					SIGNAL s_signedLess : STD_LOGIC;
					SIGNAL s_unsignedGreater : STD_LOGIC;
					SIGNAL s_unsignedLess : STD_LOGIC;

				BEGIN

					s_signedLess <= '1' WHEN signed(dataA) < signed(dataB) ELSE '0';
					s_unsignedLess <= '1' WHEN unsigned(dataA) < unsigned(dataB) ELSE '0';
					s_signedGreater <= '1' WHEN signed(dataA) > signed(dataB) ELSE '0';
					s_unsignedGreater <= '1' WHEN unsigned(dataA) > unsigned(dataB) ELSE '0';

					aEqualsB <= '1' WHEN dataA = dataB ELSE '0';
					aGreaterThanB <= s_signedGreater WHEN twosComplement = '1' ELSE s_unsignedGreater;
					aLessThanB <= s_signedLess WHEN twosComplement = '1' ELSE s_unsignedLess;

				END ARCHITECTURE behaviour;
				""";

		// data files
		String content_bit_comparator = null;
		String content_comparator = null;

		//
		// write Comparator
		String path_bit_comparator = Comparator.writeComparator("BitComparator", path_string);
		String path_comparator = Comparator.writeComparator("Comparator", path_string);

		try
		{
			content_bit_comparator = Files.readString(Paths.get(path_bit_comparator));
			content_comparator = Files.readString(Paths.get(path_comparator));

			// check if the correct data was written to the file
			assertEquals(BIT_COMPARATOR_VHDL, content_bit_comparator);
			assertEquals(COMPARATOR_VHDL, content_comparator);

		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}

		// test invalid
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> Comparator.writeComparator("2BitComparator", path_string));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> Comparator.writeComparator("full-Comparator", path_string));
	}

	@Test
	@DisplayName("test Component Subtractor")
	void testSubtractor()
	{
		String HALF_SUBTRACTOR_VHDL = """
				------------------------------------------
				-- VHDL Code for the 2-bit Comparator
				-- function HalfSubtractor
				-- A - B
				-----------------------------------------

				LIBRARY IEEE;
				USE IEEE.std_logic_1164.ALL;
				USE IEEE.numeric_std.ALL;
				ENTITY HalfSubtractor IS
					PORT (
						a, b : IN STD_LOGIC;
						difference, borrow : OUT STD_LOGIC
					);
				END ENTITY HalfSubtractor;

				ARCHITECTURE behaviour OF HalfSubtractor IS

				BEGIN
					difference <= (NOT(a) AND b) OR (a AND NOT(b));
					borrow <= NOT(a) AND b;
				END ARCHITECTURE behaviour;
				""";
		String FULL_SUBTRACTOR_VHDL = """
							------------------------------------------
							-- VHDL Code for the 3-bit Comparator
							-- function FullSubtractor
							-- A - B
							-----------------------------------------

							LIBRARY IEEE;
							USE IEEE.std_logic_1164.ALL;
							USE IEEE.numeric_std.ALL;
							ENTITY FullSubtractor IS
								PORT (
									a, b, c : IN STD_LOGIC;
									difference, borrow : OUT STD_LOGIC
								);
							END ENTITY FullSubtractor;

							ARCHITECTURE behaviour OF FullSubtractor IS

							BEGIN

								difference <= (NOT(a) AND NOT(b) AND c) OR (NOT(a) AND b AND NOT(c)) OR (a AND NOT(b) AND NOT(c)) OR (a AND b AND c);
								borrow <= (NOT(a) AND c) OR (NOT(a) AND b) OR b;

							END ARCHITECTURE behaviour;
				""";
		// data files
		String content_half_sub = null;
		String content_full_sub = null;

		//
		// write Comparator
		String path_half_sub = Subtractor.writeSubtractor("HalfSubtractor", path_string);
		String path_full_sub = Subtractor.writeSubtractor("FullSubtractor", path_string);

		try
		{
			content_half_sub = Files.readString(Paths.get(path_half_sub));
			content_full_sub = Files.readString(Paths.get(path_full_sub));

			// check if the correct data was written to the file
			assertEquals(HALF_SUBTRACTOR_VHDL, content_half_sub);
			assertEquals(FULL_SUBTRACTOR_VHDL, content_full_sub);

		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}

		// test invalid
		Assertions.assertThrows(IllegalArgumentException.class, () -> Subtractor.writeSubtractor("Sub", path_string));
		Assertions.assertThrows(IllegalArgumentException.class, () -> Subtractor.writeSubtractor("minus", path_string));
	}

	@Test
	@DisplayName("test component seven segment display")
	void testSegment7()
	{
		String SEGMENT7_VHDL = """
				LIBRARY ieee;
				USE ieee.std_logic_1164.ALL;
				USE ieee.numeric_std.ALL;

				ENTITY segment7 IS
					PORT (
						hex : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
						decimal_point : IN STD_LOGIC;
						segment : OUT STD_LOGIC_VECTOR(7 DOWNTO 0)
					);
				END ENTITY;

				ARCHITECTURE behaviour OF segment7 IS
				BEGIN
					PROCESS (hex, decimal_point) IS BEGIN
						CASE hex IS
							WHEN "0000" =>
								segment(6 DOWNTO 0) <= "1111110"; -- 0
							WHEN "0001" =>
								segment(6 DOWNTO 0) <= "0110000"; -- 1
							WHEN "0010" =>
								segment(6 DOWNTO 0) <= "1101101"; -- 2
							WHEN "0011" =>
								segment(6 DOWNTO 0) <= "1111001"; -- 3
							WHEN "0100" =>
								segment(6 DOWNTO 0) <= "0110011"; -- 4
							WHEN "0101" =>
								segment(6 DOWNTO 0) <= "1011011"; -- 5
							WHEN "0110" =>
								segment(6 DOWNTO 0) <= "1011111"; -- 6
							WHEN "0111" =>
								segment(6 DOWNTO 0) <= "1110000"; -- 7
							WHEN "1000" =>
								segment(6 DOWNTO 0) <= "1111111"; -- 8
							WHEN "1001" =>
								segment(6 DOWNTO 0) <= "1111011"; -- 9
							WHEN "1010" =>
								segment(6 DOWNTO 0) <= "1110111"; -- a
							WHEN "1011" =>
								segment(6 DOWNTO 0) <= "0011111"; -- b
							WHEN "1100" =>
								segment(6 DOWNTO 0) <= "1001110"; -- c
							WHEN "1101" =>
								segment(6 DOWNTO 0) <= "0111101"; -- d
							WHEN "1110" =>
								segment(6 DOWNTO 0) <= "1001111"; -- e
							WHEN "1111" =>
								segment(6 DOWNTO 0) <= "1000111"; -- f
							WHEN OTHERS =>
								segment(6 DOWNTO 0) <= "0000000";
						END CASE;
						segment(7) <= decimal_point;
					END PROCESS;

				END behaviour;
				""";
		String path_file = Segment7.writeSegment7(path_string);

		try
		{
			String content = Files.readString(Paths.get(path_file));

			// check if the correct data was written to the file
			assertEquals(SEGMENT7_VHDL, content);

		} catch (IOException e)
		{
			System.out.println("Error reading file: " + e.getMessage());
		}
		Assertions.assertThrows(NullPointerException.class, () -> Segment7.writeSegment7(null));

	}

}
