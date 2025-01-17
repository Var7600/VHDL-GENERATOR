/**
 * @file Comparator.java
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

package vhdlgenerator.component.comparator;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL Code for the Comparator
 *
 * @author DOUDOU DIAWARA
 */
public final class Comparator
{

	/** filename for the 1-BitComparator */
	private static final String FILE_BIT_COMPARATOR = "BitComparator.vhdl";
	/** filename for the 2-BitComparator */
	private static final String FILE_COMPARATOR = "Comparator.vhdl";

	/** VHDL CODE for the 1-BitComparator */
	private static final String BIT_COMPARATOR_VHDL = """
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

	/** VHDL CODE for the Comparator */
	private static final String COMPARATOR_VHDL = """
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

	/** possible choice of Comparator */
	public static Object[] possibleValues = { "BitComparator", "Comparator" };

	/**
	 * default Constructor
	 */
	private Comparator()
	{
	} // prevent instantiation

	/**
	 * write the VHDL Code of the Comparator
	 *
	 * @param type      of the Comparator to write to the file
	 * @param file_path path where to write it
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeComparator(final String type, final String file_path)
	{
		StringBuilder full_path = null;
		try
		{
			if (WindowCode.validateFilePath(file_path))
			{
				full_path = new StringBuilder(file_path + DisplayLibrary.FILE_SEPARATOR);

				if ("BitComparator".equalsIgnoreCase(type))
				{

					// create the full path name of the file
					full_path.append(FILE_BIT_COMPARATOR);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), BIT_COMPARATOR_VHDL);

				} else if ("Comparator".equalsIgnoreCase(type))
				{
					// create the full path name of the file
					full_path.append(FILE_COMPARATOR);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), COMPARATOR_VHDL);

				} else
				{
					throw new IllegalArgumentException(
							"Invalid Comparator type option " + type + " valid type are BitComparator and Comparator");
				}

			}

		} catch (IOException | NullPointerException e)
		{
			// e.printStackTrace();
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
