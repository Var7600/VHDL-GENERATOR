/**
 * @file Encoder.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.1
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 *
 */
package vhdlgenerator.component.encoder;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of the Encoder
 *
 * @author DOUDOU DIAWARA
 */
public final class Encoder
{

	/** file name for the encoder4x2 */
	private static final String FILE_ENCODER4 = "Encoder4x2.vhdl";
	/** file name for the encoder8x3 */
	private static final String FILE_ENCODER8 = "Encoder8x3.vhdl";
	/** VHDL code for the encoder4x2 */
	private static final String ENCODER4_VHDL = """
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
	/** VHDL code for the encoder8x3 */
	private static final String ENCODER8_VHDL = """
			LIBRARY IEEE;
			USE IEEE.STD_LOGIC_1164.ALL;
			USE IEEE.NUMERIC_STD. ALL;

			ENTITY Encoder8x3 IS

				PORT (
					input : IN STD_LOGIC_VECTOR (7 DOWNTO 0);
					enable : IN STD_LOGIC;
					output : OUT STD_LOGIC_VECTOR (2 DOWNTO 0)
				);

			END Encoder8x3;

			ARCHITECTURE behaviour OF Encoder8x3 IS

			BEGIN

				PROCESS (enable, input) IS
				BEGIN
					IF enable = '1' THEN
						CASE input IS

							WHEN "00000001" => output <= "000";

							WHEN "00000010" => output <= "001";

							WHEN "00000100" => output <= "010";

							WHEN "00001000" => output <= "011";

							WHEN "00010000" => output <= "100";

							WHEN "00100000" => output <= "101";

							WHEN "01000000" => output <= "110";

							WHEN "10000000" => output <= "111";

							WHEN OTHERS => output <= "XXX";

						END CASE;
					END IF;
				END PROCESS;

			END behaviour;
			""";

	/**
	 * possible size for the encoder
	 */
	public static final Object[] possibleValues = { "4", "8" };

	/**
	 * default constructor
	 */
	private Encoder()
	{
	} // prevent instantiation

	/**
	 * write the VHDL code of the Encoder to the directory chosen
	 *
	 * @param encoder_size of the encoder see {@link Encoder#possibleValues}
	 * @param file_path    the path where to write the final
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */

	public static String writeEncoder(final String encoder_size, final String file_path)
	{
		StringBuilder full_path = null;
		try
		{

			if (WindowCode.validateFilePath(file_path))
			{
				full_path = new StringBuilder(file_path + DisplayLibrary.FILE_SEPARATOR);

				// encoder 4,8
				switch (encoder_size)
				{
				case "4" -> {
					// create the full path name of the file
					full_path.append(FILE_ENCODER4);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), ENCODER4_VHDL);
				}
				case "8" -> {
					// create the full path name of the file
					full_path.append(FILE_ENCODER8);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), ENCODER8_VHDL);
				}

				default ->
					throw new IllegalArgumentException("Error invalid option for Encoder! valid option are 4 and 8");

				}
			}

		} catch (IOException | NullPointerException e)
		{
			// e.printStackTrace();
		}
		if (full_path == null)
		{
			// a error occurred when trying to write to path
			throw new NullPointerException("could not write to file!");
		}
		// return path
		return full_path.toString();
	}

}
