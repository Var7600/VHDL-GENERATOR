/**
 * @file JKFlipFlop.java
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
 */
package vhdlgenerator.component.flipflop;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of a JK-flip-flop
 *
 * @author DOUDOU DIAWARA
 */
public final class JKFlipFlop
{
	/** jk flip flop filename */
	private static final String FILE_JK = "jk_flip_flop.vhdl";

	/** jk flip flop VHDL */
	private static final String JK_VHDL = """
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

	/**
	 * default constructor
	 */
	private JKFlipFlop()
	{
	} // prevent instantiation

	/**
	 * writing the VHDL jk flip flop code
	 *
	 * @param path to write the jkfliflop
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception NullPointerException if the full path returned is
	 *                                     <code>null</code>
	 */
	public static String writeJK(final String path)
	{
		String full_path = null;
		try
		{
			if (WindowCode.validateFilePath(path))
			{
				// path to the file
				full_path = path + DisplayLibrary.FILE_SEPARATOR + FILE_JK;
				// create file path
				FileGenerator.openFile(full_path);
				// write VHDL code simple d-flip-flop
				FileGenerator.writeData(full_path, JK_VHDL);
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
		return full_path;
	}
}
