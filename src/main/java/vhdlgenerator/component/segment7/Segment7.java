/**
 * @file Segment7.java
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
 */

package vhdlgenerator.component.segment7;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of the segment7 display in VHDL.
 *
 * @author DOUDOU DIAWARA
 */
public final class Segment7
{
	/** file name of the segment7Dispaly */
	private static final String FILE_SEGMENT7 = "segment7.vhdl";
	/** VHDL code of the seven segment display */
	private static final String SEGMENT7_VHDL = """
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

	/**
	 * default constructor
	 *
	 */
	private Segment7()
	{
	} // prevent instantiation

	/**
	 * write the VHDL code of the seven segment display to the folder choose by the
	 * user.
	 *
	 * @param path to write the file
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeSegment7(final String path)
	{
		String full_path = null;
		try
		{

			if (WindowCode.validateFilePath(path))
			{
				// create the full path name of the file
				full_path = path + DisplayLibrary.FILE_SEPARATOR + FILE_SEGMENT7;
				// create a file
				FileGenerator.openFile(full_path);
				// write VHDL code
				FileGenerator.writeData(full_path, SEGMENT7_VHDL);
			}
		} catch (NullPointerException | IOException e)
		{
			// e.printStackTrace();
		}

		if (full_path == null)
		{
			// a error occured when trying to write to path
			throw new NullPointerException("could not write to file!");
		}
		// return path
		return full_path;

	}

}
