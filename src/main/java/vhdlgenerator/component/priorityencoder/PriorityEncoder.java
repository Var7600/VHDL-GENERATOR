/**
 * @file PriorityEncoder.java
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
package vhdlgenerator.component.priorityencoder;

import java.io.IOException;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code for the PriorityEncoder.
 *
 * @author DOUDOU DIAWARA
 */
public final class PriorityEncoder
{
	//
	// private
	//

	/** file name for the PriorityEncoder4x2 */
	private static final String FILE_PR4 = "PriorityEncoder4_2.vhdl";
	/** file name for the PriorityEncoder8x3 */
	private static final String FILE_PR8 = "PriorityEncoder8_2.vhdl";
	/** file name for the PriorityEncoder16x4 */
	private static final String FILE_PR16 = "PriorityEncoder16_4.vhdl";

	/** VHDL code for the PriorityEncoder4_2 */
	private static final String PR4_VHDL = """
			-----------------------------------------------------------------------------------------------------------------------------------
			-- Desing : Priority Encoder 4x2
			-- Function : if more than one input is active/high the priority is given to the most significant bit to the least significant bit.
			-----------------------------------------------------------------------------------------------------------------------------------

			LIBRARY IEEE;
			USE IEEE.std_logic_1164.ALL;
			USE IEEE.numeric_std.ALL;

			ENTITY PriorityEncoder4_2 IS
				PORT (
					enable : IN STD_LOGIC; --  Enable for the encoder
					encoder_in : IN STD_LOGIC_VECTOR (3 DOWNTO 0); --  4-bit Input
					encoder_out : OUT STD_LOGIC_VECTOR (2 DOWNTO 0) --  3 bit binary Output (bit at position 0(least significant) if set to 1 that indicate more than one input is active/high
				);
			END ENTITY PriorityEncoder4_2;

			ARCHITECTURE behaviour OF PriorityEncoder4_2 IS
			BEGIN
				PROCESS (enable, encoder_in) BEGIN
					IF (enable = '1') THEN
						IF (encoder_in(3) = '1') THEN
							encoder_out <= "111";
						ELSIF (encoder_in(2) = '1') THEN
							encoder_out <= "101";
						ELSIF (encoder_in(1) = '1') THEN
							encoder_out <= "001";
						ELSIF (encoder_in(0) = '1') THEN
							encoder_out <= "001";
						ELSE
							encoder_out <= "000";
						END IF;
					END IF;
				END PROCESS;

			END ARCHITECTURE behaviour;
			""";
	/** VHDL code for the PriorityEncoder8_3 */
	private static final String PR8_VHDL = """
			-----------------------------------------------------------------------------------------------------------------------------------
			-- Desing : Priority Encoder 8x3
			-- Function : if more than one input is active/high the priority is given to the most significant bit to the least significant bit.
			-----------------------------------------------------------------------------------------------------------------------------------

			LIBRARY ieee;
			USE ieee.std_logic_1164.ALL;
			USE ieee.numeric_std.ALL;

			ENTITY PriorityEncoder8_3 IS
				PORT (
					enable : IN STD_LOGIC; --  Enable for the encoder
					input : IN STD_LOGIC_VECTOR (7 DOWNTO 0); --  8-bit Input
					output : OUT STD_LOGIC_VECTOR (2 DOWNTO 0) -- 3 bit binary Output
				);
			END PriorityEncoder8_3;

			ARCHITECTURE behaviour OF PriorityEncoder8_3 IS
			BEGIN
				prio_enco8_3 : PROCESS (input, input) IS
				BEGIN
					IF enable = '1' THEN
						IF input(7) = '1'THEN
							output <= "111";
						ELSIF input(6) = '1' THEN
							output <= "110";
						ELSIF input(5) = '1' THEN
							output <= "101";
						ELSIF input(4) = '1' THEN
							output <= "100";
						ELSIF input(3) = '1' THEN
							output <= "011";
						ELSIF input(2) = '1' THEN
							output <= "010";
						ELSIF input(1) = '1' THEN
							output <= "001";
						ELSIF input(0) = '1' THEN
							output <= "000";
						END IF;
					END IF;
				END PROCESS prio_enco8_3;

			END behaviour;
			""";
	/** VHDL code for the PriorityEncoder16_4 */
	private static final String PR16_VHDL = """
			-------------------------------------------------------
			-- Design Name : PriorityEncoder16_4
			-- File Name   : PriorityEncoder16_4.vhdl
			-- Function    : Pri Encoder using when-else
			-------------------------------------------------------
			LIBRARY ieee;
			USE ieee.std_logic_1164.ALL;

			ENTITY PriorityEncoder16_4 IS
				PORT (
					enable : IN STD_LOGIC; --  Enable for the encoder
					encoder_in : IN STD_LOGIC_VECTOR (15 DOWNTO 0); --  16-bit Input
					binary_out : OUT STD_LOGIC_VECTOR (3 DOWNTO 0) --  4 bit binary Output

				);
			END PriorityEncoder16_4;

			ARCHITECTURE behaviour OF PriorityEncoder16_4 IS

			BEGIN

				binary_out <= "0000" WHEN enable = '0' ELSE
					"0001" WHEN encoder_in(1) = '1' ELSE
					"0010" WHEN encoder_in(2) = '1' ELSE
					"0011" WHEN encoder_in(3) = '1' ELSE
					"0100" WHEN encoder_in(4) = '1' ELSE
					"0101" WHEN encoder_in(5) = '1' ELSE
					"0110" WHEN encoder_in(6) = '1' ELSE
					"0111" WHEN encoder_in(7) = '1' ELSE
					"1000" WHEN encoder_in(8) = '1' ELSE
					"1001" WHEN encoder_in(9) = '1' ELSE
					"1010" WHEN encoder_in(10) = '1' ELSE
					"1011" WHEN encoder_in(11) = '1' ELSE
					"1100" WHEN encoder_in(12) = '1' ELSE
					"1101" WHEN encoder_in(13) = '1' ELSE
					"1110" WHEN encoder_in(14) = '1' ELSE
					"1111" WHEN encoder_in(15) = '1' ELSE
					"0000";
			END behaviour;
			""";

	//
	// public
	//

	/** possible size of the PriorityEncoder */
	public static final Object[] possibleValues = { "4", "8", "16" };

	/**
	 * default constructor
	 */
	private PriorityEncoder()
	{
	} // prevent instantiation

	/**
	 * write the PriorityEncoder VHDL code to the file.
	 *
	 * @param size the size of the PriorityEncoder see
	 *                 {@link PriorityEncoder#possibleValues}
	 * @param path where to write the PriorityEncoder
	 *
	 * @return the full path of the file generated if file_path otherwise throw
	 *         Exception
	 *
	 * @exception IllegalArgumentException if the size is invalid
	 * @exception NullPointerException     if the full path returned is
	 *                                         <code>null</code>
	 */
	public static String writePriorityEncoder(final String size, final String path)
	{
		StringBuilder full_path = null;
		try
		{

			if (WindowCode.validateFilePath(path))
			{
				full_path = new StringBuilder(path + DisplayLibrary.FILE_SEPARATOR);
				// size 4,8,16
				switch (size)
				{
				case "4" -> {
					// new file path
					full_path.append(FILE_PR4);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), PR4_VHDL);
				}

				case "8" -> {
					// new file path
					full_path.append(FILE_PR8);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), PR8_VHDL);
				}

				case "16" -> {
					// new file path
					full_path.append(FILE_PR16);
					// create the new file
					FileGenerator.openFile(full_path.toString());
					// write the code
					FileGenerator.writeData(full_path.toString(), PR16_VHDL);
				}

				default -> throw new IllegalArgumentException("Error PriorityEncoder! " + size + " option are 4,8,16");

				}

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
		return full_path.toString();

	}

}
