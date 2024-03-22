/**
 * @file Comparator.java
 *
 * @author DOUDOU DIAWARA
 * @see <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 * @version 0.0
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use.
 */

package vhdlgenerator.component.comparator;

import java.io.IOException;

import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL Code for the Comparator
 *
 * @author DOUDOU DIAWARA
 */
public class Comparator
{
	/** possible choice of Subtractor */
	public static Object[] possibleValues = { "BitComparator", "Comparator" };
	/** filename for the 1-BitComparator */
	private final String FILE_BIT_COMPARATOR1 = "BitComparator.vhdl";
	/** filename for the 2-BitComparator */
	private final String FILE_COMPARATOR = "Comparator.vhdl";

	/** VHDL CODE for the 1-BitComparator */
	private final String BIT_COMPARATOR1_VHDL = """
			--==============================================================================
			--== Component : BitComparator                                                ==
			--== compare two bits a and b if a == b then a_equals_b=1 ,                    ==
			--== if a > b then a_greater_than_b=1 , if a < b then a_less_than_b           ==
			--==============================================================================

			LIBRARY ieee;
			USE ieee.std_logic_1164.all;
			USE ieee.numeric_std.all;


			ENTITY BitComparator IS

			   PORT ( a         : IN  std_logic;
			          b         : IN  std_logic;
			          a_equals_b      : OUT std_logic;
			          a_greater_than_b : OUT std_logic;
			          a_less_than_b    : OUT std_logic );

			END ENTITY BitComparator;

			ARCHITECTURE behaviour OF BitComparator IS

			BEGIN

			   a_equals_b <= a XNOR b;
			   a_less_than_b <= NOT(a) AND b ;
			   a_greater_than_b <= a AND not(b) ;

			END behaviour;

						""";

	/** VHDL CODE for the Comparator */
	private final String COMPARATOR_VHDL = """
			--------------------------------------------------------------------------------
			-- Component : Comparator of n-bits
			---------------------------------------------------------------------------------

			LIBRARY ieee;
			USE ieee.std_logic_1164.all;
			USE ieee.numeric_std.all;


			ENTITY Comparator IS
			   GENERIC ( DataWidth       : natural;
			             twosComplement : bit ); -- if set to 1 a and b are signed otherwise unsigned
			   PORT ( dataA         : IN  std_logic_vector( (DataWidth - 1) DOWNTO 0 );
			          dataB         : IN  std_logic_vector( (DataWidth - 1) DOWNTO 0 );
			          aEqualsB      : OUT std_logic;
			          aGreaterThanB : OUT std_logic;
			          aLessThanB    : OUT std_logic );
			END ENTITY Comparator;

			ARCHITECTURE behaviour OF Comparator IS

			--------------------------------------------------------------------------------
			-- All used signals are defined here                                          --
			--------------------------------------------------------------------------------
			   SIGNAL s_signedGreater   : std_logic;
			   SIGNAL s_signedLess      : std_logic;
			   SIGNAL s_unsignedGreater : std_logic;
			   SIGNAL s_unsignedLess    : std_logic;

			BEGIN

			   s_signedLess      <= '1' WHEN signed(dataA) < signed(dataB) ELSE '0';
			   s_unsignedLess    <= '1' WHEN unsigned(dataA) < unsigned(dataB) ELSE '0';
			   s_signedGreater   <= '1' WHEN signed(dataA) > signed(dataB) ELSE '0';
			   s_unsignedGreater <= '1' WHEN unsigned(dataA) > unsigned(dataB) ELSE '0';

			   aEqualsB      <= '1' WHEN dataA = dataB ELSE '0';
			   aGreaterThanB <= s_signedGreater WHEN twosComplement = '1' ELSE s_unsignedGreater;
			   aLessThanB    <= s_signedLess WHEN twosComplement = '1' ELSE s_unsignedLess;

			END ARCHITECTURE behaviour;

						""";

	/**
	 * default Constructor
	 */
	public Comparator()
	{

	}

	/**
	 * write the VHDL Code of the Comparator
	 *
	 * @param type of the Comparator to write to the file
	 */
	public void writeComparator(String type)
	{
		try
		{
			// if the file has been opened
			boolean created = false;

			String file_path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(file_path))
			{
				if ("BitComparator".equalsIgnoreCase(type))
				{

					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_BIT_COMPARATOR1;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, BIT_COMPARATOR1_VHDL);
				}

				if ("Comparator".equalsIgnoreCase(type))
				{
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_COMPARATOR;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, COMPARATOR_VHDL);

				}

			}
			// FRAME for successful generation of the component
			if (created)
			{
				// code generated successfully
				int option = (Integer) WindowCode.successFrame(DisplayLibrary.GENERATED_COMPONENT).getValue();

				if (option == JOptionPane.YES_OPTION || option == JOptionPane.CANCEL_OPTION
						|| option == JOptionPane.CLOSED_OPTION)
				{
					// show the file created
					WindowCode.showFile(file_path);
				}
			}

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
			// WindowCode.errorFrame(WindowCode.ERROR_MSG_OPEN);
		}
	}
}
