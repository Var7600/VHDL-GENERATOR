
/**
 * @file Decoder.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.1
 *
 * @section. LICENSE This program is free software; you can redistribute it
 * and/or modify it under the terms of “Commons Clause” License Condition v1.0
 * but not for Commercial use
 *
 */
package vhdlgenerator.component.decoder;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the code for the Decoder
 *
 * @author DOUDOU DIAWARA
 *
 */
public final class Decoder
{
	/** name of the decoder2:4 file. */
	private static final String FILE_DECODER2 = "Decoder2_4.vhdl";
	/** name of the decoder3:8 file. */
	private static final String FILE_DECODER3 = "Decoder3_8.vhdl";
	/** name of the decoder4:16 file */
	private static final String FILE_DECODER4 = "Decoder4_16.vhdl";
	/** possible size of the Decoder */
	public static Object[] possibleValues = { "2", "3", "4" };

	//
	// VHDL code for the Decoder
	//

	/** VHDL code of the decoder 2x4 */
	private static final String DECODER2_VHDL = """
			LIBRARY IEEE;
			USE IEEE.std_logic_1164.ALL;
			USE IEEE.numeric_std.ALL;

			ENTITY Decoder2_4 IS
				PORT (
					input : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
					enable : IN STD_LOGIC;
					output : OUT STD_LOGIC_VECTOR(3 DOWNTO 0)
				);
			END Decoder2_4;

			ARCHITECTURE behaviour OF Decoder2_4 IS

			BEGIN
				PROCESS (enable, input) IS BEGIN
					IF (enable = '1') THEN
						CASE input IS
							WHEN "00" =>
								output <= "0001";
							WHEN "01" =>
								output <= "0010";
							WHEN "10" =>
								output <= "0100";
							WHEN "11" =>
								output <= "1000";
							WHEN OTHERS =>
								output <= "0000";
						END CASE;
					END IF;
				END PROCESS;

			END behaviour;
			""";

	/** VHDL code of the decoder 3x8 */
	private static final String DECODER3_VHDL = """
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
	/** VHDL code of the decoder 4x16 */
	private static final String DECODER4_VHDL = """
			LIBRARY IEEE;
			USE IEEE.STD_logic_1164.ALL;

			ENTITY decoder4_16 IS
				PORT (
					enable : IN STD_LOGIC;
					input : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
					output : OUT STD_LOGIC_VECTOR(15 DOWNTO 0));
			END decoder4_16;

			ARCHITECTURE behaviour OF decoder4_16 IS
			BEGIN
				PROCESS (enable, input) IS BEGIN
					IF (enable = '1') THEN
						output(0) <= (NOT input(3)) AND (NOT input(2)) AND (NOT input(1)) AND (NOT input(0));
						output(1) <= (NOT input(3)) AND (NOT input(2)) AND (NOT input(1)) AND input(0);
						output(2) <= (NOT input(3)) AND (NOT input(2)) AND input(1) AND (NOT input(0));
						output(3) <= (NOT input(3)) AND (NOT input(2)) AND input(1) AND input(0);
						output(4) <= (NOT input(3)) AND input(2) AND (NOT input(1)) AND (NOT input(0));
						output(5) <= (NOT input(3)) AND input(2) AND (NOT input(1)) AND input(0);
						output(6) <= (NOT input(3)) AND input(2) AND input(1) AND (NOT input(0));
						output(7) <= (NOT input(3)) AND input(2) AND input(1) AND input(0);
						output(8) <= input(3) AND (NOT input(2)) AND (NOT input(1)) AND (NOT input(0));
						output(9) <= input(3) AND (NOT input(2)) AND (NOT input(1)) AND input(0);
						output(10) <= input(3) AND (NOT input(2)) AND input(1) AND (NOT input(0));
						output(11) <= input(3) AND (NOT input(2)) AND input(1) AND input(0);
						output(12) <= input(3) AND input(2) AND (NOT input(1)) AND (NOT input(0));
						output(13) <= input(3) AND input(2) AND (NOT input(1)) AND input(0);
						output(14) <= input(3) AND input(2) AND input(1) AND (NOT input(0));
						output(15) <= input(3) AND input(2) AND input(1) AND input(0);
					ELSE
						output <= (OTHERS => '0');
					END IF;
				END PROCESS;
			END Behaviour;
			""";

	/**
	 * default constructor
	 */
	private Decoder()
	{
	} // prevent instantiation

	/**
	 * write the code of the Decoder to the file
	 *
	 * @param decoder_size the size of the decoder to write see
	 *                         {@link Decoder#possibleValues}
	 * @param file_path    the path to write the decoder
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeDecoder(final String decoder_size, final String file_path)
	{
		StringBuilder full_path = null;

		try
		{
			if (WindowCode.validateFilePath(file_path))
			{
				full_path = new StringBuilder(file_path + DisplayLibrary.FILE_SEPARATOR);

				// deocder 2,3,4
				switch (decoder_size)
				{
				case "2" -> {
					// create the full path name of the file
					full_path.append(FILE_DECODER2);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), DECODER2_VHDL);
				}
				case "3" -> {
					// create the full path name of the file
					full_path.append(FILE_DECODER3);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), DECODER3_VHDL);
				}

				case "4" -> {
					// create the full path name of the file
					full_path.append(FILE_DECODER4);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), DECODER4_VHDL);

				}

				default -> throw new IllegalArgumentException(
						"Error invalid option for Decoder! valid option are 2 or 3 or 4");

				}

			}

		} catch (IOException | NullPointerException e)
		{
			// e.printStackStrace();
		}
		if (full_path == null)
		{
			// a error occured when trying to write to path
			throw new NullPointerException("could not write to file!");
		}
		// return path
		return full_path.toString();
	}
}
