/**
 * @file Demux.java
 *
 * @author DOUDOU DIAWARA
 * @see <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 * @version 0.1
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use.
 */
package vhdlgenerator.component.demultiplexer;

import java.io.IOException;
import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the code of the Demultiplexer chosen by the user.
 *
 * @author DOUDOU DIAWARA
 */
public final class Demux
{

	/**
	 * name of the demux1:2 file.
	 */
	private static final String FILE_DEMUX2 = "Demux1_2.vhdl";
	/**
	 * name of the demux1:4 file.
	 */
	private static final String FILE_DEMUX4 = "Demux1_4.vhdl";
	/**
	 * name of the demux1:8 file.
	 */
	private static final String FILE_DEMUX8 = "Demux1_8.vhdl";

	/*
	 * name of the demux1:16 file. (maybe? to use on next version)
	 */
	// private final String FILE_DEMUX16 = "Demux_1x16.vhdl";

	/**
	 * possible size for demultiplexer
	 */
	public static final Object[] possibleValues = { "2", "4", "8" };
	//
	// VHDL Code For demultiplexer
	//
	/**
	 * VHDL code for the demultiplexer 1x2
	 */
	private static final String DEMUX2_VHDL = """
			LIBRARY IEEE;
			USE IEEE.std_logic_1164.ALL;
			USE IEEE.numeric_std.ALL;

			ENTITY Demux1_2 IS
				GENERIC (DataWidth : NATURAL := 8);
				PORT (
					input : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0);
					sel : IN STD_LOGIC;
					output0, output1 : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0)
				);
			END ENTITY Demux1_2;

			ARCHITECTURE behaviour OF Demux1_2 IS

			BEGIN

				demux : PROCESS (input, sel) IS
				BEGIN
					CASE sel IS
						WHEN '0' =>
							output0 <= input;
						WHEN '1' =>
							output1 <= input;
						WHEN OTHERS =>
							output0 <= (OTHERS => '0');
							output1 <= (OTHERS => '0');
					END CASE;

				END PROCESS demux;

			END ARCHITECTURE behaviour;
			""";
	/**
	 * VHDL code for the demultiplexer 1x4
	 */
	private static final String DEMUX4_VHDL = """
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
	/**
	 * VHDL code for the demultiplexer 1x8
	 */
	private static final String DEMUX8_VHDL = """
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

	/**
	 * default constructor
	 */
	private Demux()
	{
	} // prevent instantiation

	/**
	 * write the code of the Demultiplexer to the file.
	 *
	 * @param demux_size the size see {@link Demux#possibleValues} of the
	 *                       demultiplexer
	 * @param file_path  to write the demultiplexer
	 *
	 * @return the full path of the file generated if file_path is valid otherwise
	 *         throw Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeDemux(String demux_size, String file_path)
	{
		StringBuilder full_path = null;
		try
		{

			if (WindowCode.validateFilePath(file_path))
			{
				full_path = new StringBuilder(file_path + DisplayLibrary.FILE_SEPARATOR);

				// Demux size 2,4,8
				switch (demux_size)
				{
				case "2" -> {
					// new file path
					full_path.append(FILE_DEMUX2);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), DEMUX2_VHDL);
				}

				case "4" -> {
					// new file path
					full_path.append(FILE_DEMUX4);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), DEMUX4_VHDL);
				}

				case "8" -> {
					// new file path
					full_path.append(FILE_DEMUX8);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), DEMUX8_VHDL);
				}

				default -> throw new IllegalArgumentException(
						"Error demultiplexer! " + demux_size + " is not valid! option are 2,4,8");

				}
			}
		} catch (NullPointerException | IOException e)
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
