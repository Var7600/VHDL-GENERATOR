/**
 * @file Mux.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.1
 *
 * @section. LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 *
 */
package vhdlgenerator.component.multiplexer;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the Code for the Multiplexer
 *
 * @author DOUDOU DIAWARA
 */
public final class Mux
{

	/** name of the mux2:1 file. */
	private static final String FILE_MUX2 = "Mux_2x1.vhdl";
	/** name of the mux4:1 file. */
	private static final String FILE_MUX4 = "Mux_4x1.vhdl";
	/** name of the mux8:1 file. */
	private static final String FILE_MUX8 = "Mux_8x1.vhdl";
	/** name of the mux16:1 file. */
	private static final String FILE_MUX16 = "Mux_16x1.vhdl";

	//
	// VHDL Code For Multiplexer
	//

	/** VHDL code of Mux 2 to 1 */
	private static final String MUX2_VHDL = """
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
	/** VHDL code of Mux 4 to 1 */
	private static final String MUX4_VHDL = """
						LIBRARY IEEE;
						USE IEEE.std_logic_1164.ALL;
						USE IEEE.numeric_std.ALL;

						ENTITY Mux_4x1 IS
							GENERIC (DataWidth : NATURAL := 8);
							PORT (
								input0, input1, input2, input3 : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
								sel : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
								output : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0)
							);
						END ENTITY Mux_4x1;

						ARCHITECTURE behaviour OF Mux_4x1 IS

						BEGIN
							WITH sel SELECT
								output <= input0 WHEN "00",
								input1 WHEN "01",
								input2 WHEN "10",
								input3 WHEN "11",
								(OTHERS => '0') WHEN OTHERS;

						END ARCHITECTURE behaviour;
			""";
	/** VHDL code of Mux 8 to 1 */
	private static final String MUX8_VHDL = """
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
	/** Mux 16 to 1 */
	private static final String MUX16_VHDL = """
			LIBRARY ieee;
			USE ieee.std_logic_1164.ALL;
			USE ieee.numeric_std.ALL;

			ENTITY Mux_16x1 IS
				GENERIC (DataWidth : NATURAL := 8);
				PORT (
					input0 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input1 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input2 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input3 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input4 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input5 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input6 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input7 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input8 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input9 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input10 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input11 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input12 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input13 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input14 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					input15 : IN STD_LOGIC_VECTOR (DataWidth - 1 DOWNTO 0);
					sel : IN STD_LOGIC_VECTOR (3 DOWNTO 0);
					output : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0)
				);
			END Mux_16x1;

			ARCHITECTURE behaviour OF Mux_16x1 IS
			BEGIN
				PROCESS (sel, input0, input1, input2, input3, input4, input5,
					input6, input7, input8, input9, input10, input11, input12, input13, input14, input15) IS
				BEGIN
					CASE sel IS
						WHEN "0000" =>
							output <= input0;
						WHEN "0001" =>
							output <= input1;
						WHEN "0010" =>
							output <= input2;
						WHEN "0011" =>
							output <= input3;
						WHEN "0100" =>
							output <= input4;
						WHEN "0101" =>
							output <= input5;
						WHEN "0110" =>
							output <= input6;
						WHEN "0111" =>
							output <= input7;
						WHEN "1000" =>
							output <= input8;
						WHEN "1001" =>
							output <= input9;
						WHEN "1010" =>
							output <= input10;
						WHEN "1011" =>
							output <= input11;
						WHEN "1100" =>
							output <= input12;
						WHEN "1101" =>
							output <= input13;
						WHEN "1110" =>
							output <= input14;
						WHEN "1111" =>
							output <= input15;
						WHEN OTHERS =>
							output <= (OTHERS => '0');
					END CASE;
				END PROCESS;

			END behaviour;
			""";
	/**
	 * possible size of Multiplexer to generate.
	 */
	public static final Object[] possibleValues = { "2", "4", "8", "16" };

	// Mux 32 to 1 (see if needed to add it later)

	// private static final String MUX32=""" """;

	/**
	 * default constructor
	 */
	private Mux()
	{
	} // prevent instantiation

	/**
	 * write the code of the Multiplexer to the file.
	 *
	 * @param mux_size  of the Mux see {@link Mux#possibleValues}
	 * @param file_path the path to write the file
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeMux(final String mux_size, final String file_path)
	{
		StringBuilder full_path = null;
		try
		{

			if (WindowCode.validateFilePath(file_path))
			{

				full_path = new StringBuilder(file_path + DisplayLibrary.FILE_SEPARATOR);

				// Mux size 2,4,8,16,32
				switch (mux_size)
				{
				case "2" -> {
					// new file path
					full_path.append(FILE_MUX2);
					// create the new file.
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), MUX2_VHDL);

				}
				case "4" -> {
					// new file path
					full_path.append(FILE_MUX4);
					// create the new file.
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), MUX4_VHDL);
				}
				case "8" -> {
					// new file path
					full_path.append(FILE_MUX8);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), MUX8_VHDL);
				}

				case "16" -> {
					// new file path
					full_path.append(FILE_MUX16);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), MUX16_VHDL);
				}

				// case 32: maybe add it in the future //write the code
				default ->
					throw new IllegalArgumentException("Error multiplexer size!" + mux_size + "option are 2,4,8,16");
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
