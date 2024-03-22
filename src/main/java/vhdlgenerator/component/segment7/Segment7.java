/**
 * @file Segment7.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.0
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 */

package vhdlgenerator.component.segment7;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of the segment7 display in VHDL.
 *
 * @author DOUDOU DIAWARA
 */
public class Segment7
{
	/** file name of the segment7Dispaly */
	private final String FILE_SEGMENT7 = "segment7.vhdl";
	/** VHDL code of the seven segment display */
	private final String SEGMENT7_VHDL = """
			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			entity segment7 is
			    port(
			        hex : in std_logic_vector(3 downto 0);
			        decimal_point : in std_logic  ;
			        segment : out std_logic_vector(7 downto 0)
			    );
			end entity;

			architecture behaviour of segment7 is
			    begin
			    process(hex,decimal_point) is begin
			        case hex is
			        when "0000" =>
			                segment(6 downto 0) <= "1111110"; -- 0
			        when "0001" =>
			                segment(6 downto 0) <= "0110000"; -- 1
			        when "0010" =>
			                segment(6 downto 0) <= "1101101" ; -- 2
			        when "0011" =>
			                segment(6 downto 0) <= "1111001"; -- 3
			        when "0100" =>
			                segment(6 downto 0) <= "0110011" ; -- 4
			        when "0101" =>
			                segment(6 downto 0) <= "1011011" ; -- 5
			        when "0110" =>
			                segment(6 downto 0) <= "1011111" ; -- 6
			        when "0111" =>
			                segment(6 downto 0) <= "1110000" ; -- 7
			        when "1000" =>
			                segment(6 downto 0) <= "1111111";  -- 8
			        when "1001" =>
			                segment(6 downto 0) <= "1111011" ; -- 9
			        when "1010" =>
			                segment(6 downto 0) <= "1110111" ; -- a
			        when "1011" =>
			                segment(6 downto 0) <= "0011111";  -- b
			        when "1100" =>
			                segment(6 downto 0) <= "1001110" ; -- c
			        when "1101" =>
			                segment(6 downto 0) <= "0111101" ; -- d
			        when "1110" =>
			                segment(6 downto 0) <= "1001111" ;  -- e
			        when "1111" =>
			                segment(6 downto 0) <= "1000111" ;  -- f
			        when others =>
			                segment(6 downto 0) <= "0000000" ;
			        end case ;
			        segment(7) <= decimal_point ;
			    end process ;

			end behaviour;
						""";

	/**
	 * default constructor for invocation of the methods
	 * {@link Segment7#writeSegment7()}
	 */
	public Segment7()
	{

	}

	/**
	 * write the VHDL code of the seven segment display to the folder choose by the
	 * user.
	 */
	public void writeSegment7()
	{
		try
		{
			// check if the file has been created
			boolean created = false;
			// folder path
			String path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(path))
			{
				// create the full path name of the file
				path = path + DisplayLibrary.FILE_SEPARATOR + FILE_SEGMENT7;
				// create a file
				created = FileGenerator.openFile(path);
				// write VHDL code
				FileGenerator.writeData(path, SEGMENT7_VHDL);
				// show the file created
				WindowCode.showFile(path);

				if (created)
				{
					// code generated successfully
					int option = (Integer) WindowCode.successFrame(DisplayLibrary.GENERATED_COMPONENT).getValue();

					if (option == JOptionPane.YES_OPTION || option == JOptionPane.CANCEL_OPTION
							|| option == JOptionPane.CLOSED_OPTION)
					{
						// show the file created
						WindowCode.showFile(path);
					}
				}
			}
		} catch (NullPointerException | IOException e)
		{
			// handle cannot open file or create file.
			// WindowCode.errorFrame(WindowCode.ERROR_MSG_OPEN);
			// e.printStackTrace();
		}

	}

}
