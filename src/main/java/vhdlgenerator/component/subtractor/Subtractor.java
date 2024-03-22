/**
 * @file Subtractor.java
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
package vhdlgenerator.component.subtractor;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code for a Subtractor
 *
 * @author DOUDOU DIAWARA
 */
public class Subtractor
{
	/**
	 * filename for the HalfSubtractor
	 */
	private final String FILE_HALF_SUBTRACTOR = "HalfSubtractor.vhdl";
	/**
	 * filename for the FullSubtractor
	 */
	private final String FILE_FULL_SUBTRACTOR = "FullSubtractor.vhdl";

	/**
	 * filename for the Subtractor
	 */
	private final String FILE_SUBTRACTOR = "Subtractor.vhdl";

	/**
	 * VHDL code for the Subtractor
	 */
	private final String SUBTRACTOCR_VHDL = """
			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity Subtractor is
			    generic ( DataWidth : natural := 8);
			    Port (
			            a, b: in  std_logic_vector(DataWidth-1 downto 0); -- Input ports for the signed numbers to be subtracted
			            result: out std_logic_vector(DataWidth-1 downto 0); -- Output port for the result
			            overflow : out std_logic
			    );
			end entity Subtractor;

			architecture behaviour of Subtractor is

			    -- define a temparary signal to store the result
			    signal temp_result: std_logic_vector(DataWidth  downto 0);
			begin


			        -- result
			        temp_result <= std_logic_vector(signed('0'& a) - signed('0' & b));
			        -- sum
			        result <= temp_result(DataWidth-1 downto 0);
			        -- overflow
			        overflow <= temp_result(DataWidth) ;

			end architecture behaviour;
			""";
	/**
	 * VHDL code from the HalfSubtractor
	 */
	private final String HALF_SUBTRACTOR_VHDL = """
			------------------------------------------
			-- VHDL Code for the 2-bit Comparator
			-- function HalfSubtractor
			-- A - B
			-----------------------------------------

			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;


			entity HalfSubtractor is
			    port(
			        a,b: in std_logic ;
			        difference,borrow: out STD_LOGIC
			    );
			end entity HalfSubtractor;

			architecture behaviour of HalfSubtractor is

			begin
			    difference <= (not(a) and b) or (a and not(b)) ;
			    borrow <= not(a) and b ;
			end architecture behaviour;
			    		""";

	/**
	 * VHDL code from the FullSubtractor
	 */
	private final String FULL_SUBTRACTOR_VHDL = """
			------------------------------------------
			-- VHDL Code for the 3-bit Comparator
			-- function FullSubtractor
			-- A - B
			-----------------------------------------

			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;


			entity FullSubtractor is
			    port(
			        a,b,c: in std_logic ;
			        difference,borrow: out STD_LOGIC
			    );
			end entity FullSubtractor;

			architecture behaviour of FullSubtractor is

			begin

			    difference <= (not(a) and not(b) and c) or (not(a) and b and not(c)) or (a and not(b) and not(c)) or (a and b and c);
			    borrow <= (not(a) and c) or (not(a) and b) or b;

			end architecture behaviour;
			    		""";

	/** possible choice of Subtractor */
	public static Object[] possibleValues = { "HalfSubtractor", "FullSubtractor", "Subtractor" };

	/**
	 * Constructor for the Subtractor
	 */
	public Subtractor()
	{
	}

	/**
	 * the VHDL Code of the Subtractor
	 *
	 * @return the VHDL Code for the Subtractor
	 */
	public String getSubtractor()
	{
		return SUBTRACTOCR_VHDL;
	}

	/**
	 * write the VHDL CODE for the Subtractor to the file.
	 *
	 * @param type of the Subtractor
	 */
	public void writeSubtractor(String type)
	{

		try
		{
			// if the file has been opened
			boolean created = false;

			String file_path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(file_path))
			{
				switch (type)
				{
				case "Subtractor":

					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_SUBTRACTOR;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, SUBTRACTOCR_VHDL);

					break;
				case "HalfSubtractor":
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_HALF_SUBTRACTOR;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, HALF_SUBTRACTOR_VHDL);

					break;

				case "FullSubtractor":
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_FULL_SUBTRACTOR;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, FULL_SUBTRACTOR_VHDL);

					break;
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
