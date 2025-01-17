/**
 * @file Subtractor.java
 *
 * @author DOUDOU DIAWARA
 * @see <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 * @version 0.1
 *
 * @section. LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use.
 */
package vhdlgenerator.component.subtractor;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code for a Subtractor
 *
 * @author DOUDOU DIAWARA
 */
public final class Subtractor
{
	/**
	 * filename for the HalfSubtractor
	 */
	private static final String FILE_HALF_SUBTRACTOR = "HalfSubtractor.vhdl";
	/**
	 * filename for the FullSubtractor
	 */
	private static final String FILE_FULL_SUBTRACTOR = "FullSubtractor.vhdl";

	/**
	 * filename for the Subtractor
	 */
	private static final String FILE_SUBTRACTOR = "Subtractor.vhdl";

	/**
	 * VHDL code for the Subtractor
	 */
	private static final String SUBTRACTOCR_VHDL = """
			LIBRARY IEEE;
			USE IEEE.std_logic_1164.ALL;
			USE IEEE.numeric_std.ALL;

			ENTITY Subtractor IS
				GENERIC (DataWidth : NATURAL := 8);
				PORT (
					a, b : IN STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0); -- Input ports for the signed numbers to be subtracted
					result : OUT STD_LOGIC_VECTOR(DataWidth - 1 DOWNTO 0); -- Output port for the result
					overflow : OUT STD_LOGIC
				);
			END ENTITY Subtractor;

			ARCHITECTURE behaviour OF Subtractor IS

				-- define a temparary signal to store the result
				SIGNAL temp_result : STD_LOGIC_VECTOR(DataWidth DOWNTO 0);
			BEGIN
				-- result
				temp_result <= STD_LOGIC_VECTOR(signed('0' & a) - signed('0' & b));
				-- sum
				result <= temp_result(DataWidth - 1 DOWNTO 0);
				-- overflow
				overflow <= temp_result(DataWidth);

			END ARCHITECTURE behaviour;
						""";
	/**
	 * VHDL code from the HalfSubtractor
	 */
	private static final String HALF_SUBTRACTOR_VHDL = """
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

	/**
	 * VHDL code from the FullSubtractor
	 */
	private static final String FULL_SUBTRACTOR_VHDL = """
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

	/** possible choice of Subtractor */
	public static final Object[] possibleValues = { "HalfSubtractor", "FullSubtractor", "Subtractor" };

	/**
	 * Constructor for the Subtractor
	 */
	private Subtractor()
	{
	} // prevent instantiation

	/**
	 * write the VHDL CODE for the Subtractor to the file.
	 *
	 * @param type of the Subtractor see {@link Subtractor#possibleValues}
	 * @param path where to write the Subtractor
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeSubtractor(final String type, final String path)
	{
		StringBuilder full_path = null;
		try
		{

			if (WindowCode.validateFilePath(path))
			{
				full_path = new StringBuilder(path + DisplayLibrary.FILE_SEPARATOR);
				switch (type)
				{
				case "Subtractor" -> {

					// create the full path name of the file
					full_path.append(FILE_SUBTRACTOR);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), SUBTRACTOCR_VHDL);
				}

				case "HalfSubtractor" -> {
					// create the full path name of the file
					full_path.append(FILE_HALF_SUBTRACTOR);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), HALF_SUBTRACTOR_VHDL);
				}

				case "FullSubtractor" -> {
					// create the full path name of the file
					full_path.append(FILE_FULL_SUBTRACTOR);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), FULL_SUBTRACTOR_VHDL);
				}

				default -> throw new IllegalArgumentException(
						"Error invalid option for Subtractor! valid option are HalfSubtractor, FullSubtractor, Subtractor");

				}
			}

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
			// e.printStackStrace()

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
