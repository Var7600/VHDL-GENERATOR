/**
 * @file Adder.java
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
package vhdlgenerator.component.adder;

import java.io.IOException;
import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code for the HalfAdder and FullAdder
 *
 * @author DOUDOU DIAWARA
 */
public final class Adder
{

	/**
	 * filename for the HalfAdder
	 */
	private static final String FILE_HALF_ADDER = "HalfAdder.vhdl";
	/**
	 * filename for the FullAdder
	 */
	private static final String FILE_FULL_ADDER = "FullAdder.vhdl";
	/**
	 * filename for the arithmetic adder
	 */
	private static final String FILE_ARITH_ADDER = "Adder.vhdl";

	/**
	 * VHDL CODE for the HalfAdder
	 */
	private static final String HALF_ADDER_VHDL = """
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

	/**
	 * VHDL CODE for the FullAdder
	 */
	private static final String FULL_ADDER_VHDL = """
			--------------------------------------------------------
			-- VHDL code for 3-bit adder
			--
			-- function of adder:
			-- a plus b plus carry_in to get bit sum and bit carry (carry_out)
			--------------------------------------------------------
			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			entity FullAdder is
			port (
			a  : in std_logic;
			b  : in std_logic;
			carry_in : in std_logic;
			sum   : out std_logic;
			carry_out : out std_logic
			);
			end FullAdder;

			architecture behaviour of FullAdder is
			begin
			sum   <= a xor b xor carry_in; -- sum of a + b
			carry_out <= ((a xor b) and carry_in) or (a and b); -- carry of a
			end behaviour;
			""";
	/**
	 * VHDL CODE for the arithmetic Adder
	 */
	private static final String ARITHMETIC_ADDER_VHDL = """
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

	/**
	 * choice between bitwise-HalfAdder or bitwise-FullAdder or ArithmeticAdder
	 */
	public static final Object[] possibleValues = { "Bit-HalfAdder", "Bit-FullAdder", "Arithmetic-Adder" };

	/**
	 * default Constructor for Adder
	 */
	private Adder()
	{
	} // prevent instantiation

	/**
	 * write the VHDL code for the Adder chosen to the directory.
	 *
	 * @param type the type of Adder to write see {@link Adder#possibleValues}
	 * @param path to write the Adder
	 *
	 * @return the full path of the file generated if file_path is valid otherwise
	 *         throw Exception
	 *
	 * @exception IllegalArgumentException if the mux_size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writeAdder(String type, String path)
	{
		StringBuilder full_path = null;
		try
		{
			if (WindowCode.validateFilePath(path))
			{
				full_path = new StringBuilder(path + DisplayLibrary.FILE_SEPARATOR);

				switch (type)
				{
				case "Bit-HalfAdder" -> {
					// create the full path name of the file
					full_path.append(FILE_HALF_ADDER);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), HALF_ADDER_VHDL);
				}

				case "Bit-FullAdder" -> {

					// create the full path name of the file
					full_path.append(FILE_FULL_ADDER);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), FULL_ADDER_VHDL);
				}

				case "Arithmetic-Adder" -> {
					// create the full path name of the file
					full_path.append(FILE_ARITH_ADDER);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write code to the file
					FileGenerator.writeData(full_path.toString(), ARITHMETIC_ADDER_VHDL);
				}
				default -> throw new IllegalArgumentException("Error invalid option for Adder! valid option are "
						+ "Bit-HalfAdder, Bit-FullAdder  and Adder");

				}
			}

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
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
