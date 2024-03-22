/**
 * @file Mux.java
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
 *
 */
package vhdlgenerator.component.multiplexer;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the Code for the Multiplexer
 *
 * @author DOUDOU DIAWARA
 */
public class Mux
{

	/**
	 * represent the current size of the Multiplexer to generate.
	 */
	private int mux_size;
	/**
	 * possible size of Multiplexer to generate.
	 */
	public static Object[] possibleValues = { "2", "4", "8", "16" };

	/** name of the mux2:1 file. */
	private final String FILE_MUX2 = "Mux_2x1.vhdl";
	/** name of the mux4:1 file. */
	private final String FILE_MUX4 = "Mux_4x1.vhdl";
	/** name of the mux8:1 file. */
	private final String FILE_MUX8 = "Mux_8x1.vhdl";
	/** name of the mux16:1 file. */
	private final String FILE_MUX16 = "Mux_16x1.vhdl";

	//
	// VHDL Code For Multiplexer
	//

	/** VHDL code of Mux 2 to 1 */
	private final String MUX2_VHDL = """
			library ieee;
			use ieee.std_logic_1164.all;
			use ieee.numeric_std.all;

			entity Mux_2x1 is
			generic (DataWidth : natural := 8);
			        port(
			        input0,input1 : in std_logic_vector(DataWidth-1 downto 0);
			        output : out std_logic_vector(DataWidth-1 downto 0);
			        sel : in std_logic
			    );
			end Mux_2x1;

			architecture behaviour of Mux_2x1 is
			begin
			    with sel select
			    output <= input0 when '0',
			    input1 when '1',
			    (others => '0') when others;
			end behaviour;
			""";
	/** VHDL code of Mux 4 to 1 */
	private final String MUX4_VHDL = """
			library  IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity Mux_4x1 is
			    generic (DataWidth : natural := 8);
			    port(
			        input0,input1,input2,input3 : in std_logic_vector(DataWidth-1 downto 0);
			        sel : in std_logic_vector( 1 downto 0);
			        output : out std_logic_vector(DataWidth-1 downto 0)
			    );
			end entity Mux_4x1;

			architecture behaviour of Mux_4x1 is

			begin
			    with sel select
			    output <=   input0 when "00",
			                input1 when "01",
			                input2 when "10",
			                input3 when "11",
			    (others => '0') when others;

			end architecture behaviour;
			    		""";
	/** VHDL code of Mux 8 to 1 */
	private final String MUX8_VHDL = """
			library IEEE;
			use IEEE.STD_LOGIC_1164.ALL;

			entity Mux8x1 is
			    generic (DataWidth : natural := 8);
			    Port ( input0,input1,input2,input3,input4,input5,input6,input7 : in std_logic_vector(DataWidth-1 downto 0);
			           sel : in STD_LOGIC_VECTOR (2 downto 0);
			           output : out std_logic_vector(DataWidth-1 downto 0)
			    );
			end Mux8x1;

			architecture behaviour of Mux8x1 is
			begin
			    process(sel,input0,input1,input2,input3,input4,input5,input6,input7) is begin

			    case sel is
			        when "000" =>
			            output <= input0;
			        when "001" =>
			            output <= input1;
			        when "010" =>
			            output <= input2;
			        when "011" =>
			            output <= input3;
			        when "100" =>
			            output <= input4;
			        when "101" =>
			            output <= input5;
			        when "110" =>
			            output <= input6;
			        when "111" =>
			            output <= input7;
			        when others =>
			            output <= (others => '0');
			    end case;
			   end process;
			end behaviour;
			    		""";
	/** Mux 16 to 1 */
	private final String MUX16_VHDL = """
			library ieee;
			use ieee.std_logic_1164.ALL;
			use ieee.numeric_std.all;

			entity Mux_16x1 is
			    generic (DataWidth : natural := 8);
			    Port ( input0 : in std_logic_vector (DataWidth-1 downto 0);
			           input1 : in std_logic_vector (DataWidth-1 downto 0);
			           input2 : in std_logic_vector (DataWidth-1 downto 0);
			           input3 : in std_logic_vector (DataWidth-1 downto 0);
			           input4 : in std_logic_vector (DataWidth-1 downto 0);
			           input5 : in std_logic_vector (DataWidth-1 downto 0);
			           input6 : in std_logic_vector (DataWidth-1 downto 0);
			           input7 : in std_logic_vector (DataWidth-1 downto 0);
			           input8 : in std_logic_vector (DataWidth-1 downto 0);
			           input9 : in std_logic_vector (DataWidth-1 downto 0);
			           input10 : in std_logic_vector (DataWidth-1 downto 0);
			           input11 : in std_logic_vector (DataWidth-1 downto 0);
			           input12 : in std_logic_vector (DataWidth-1 downto 0);
			           input13 : in std_logic_vector (DataWidth-1 downto 0);
			           input14 : in std_logic_vector (DataWidth-1 downto 0);
			           input15 : in std_logic_vector (DataWidth-1 downto 0);
			           sel : in std_logic_vector (3 downto 0);
			           output : out std_logic_vector(DataWidth-1 downto 0)
			           );
			end Mux_16x1;

			architecture behaviour of Mux_16x1 is
			begin
			    process(sel,input0,input1,input2,input3,input4,input5,
			    input6,input7,input8,input9,input10,input11,input12,input13,input14,input15) is
			    begin
			        case sel is
			            when "0000" =>
			                output <= input0;
			            when "0001" =>
			                output <= input1;
			            when "0010" =>
			                output <= input2;
			            when "0011" =>
			                output <= input3;
			            when "0100" =>
			                output <= input4;
			            when "0101" =>
			                output <= input5;
			            when "0110" =>
			                output <= input6;
			            when "0111" =>
			                output <= input7;
			            when "1000" =>
			                output <= input8;
			            when "1001" =>
			                output <= input9;
			            when "1010" =>
			                output <= input10;
			            when "1011" =>
			                output <= input11;
			            when "1100" =>
			                output <= input12;
			            when "1101" =>
			                output <= input13;
			            when "1110" =>
			                output <= input14;
			            when "1111" =>
			                output <= input15;
			            when others =>
			                output <= (others => '0');
			        end case;
			    end process;

			end behaviour;
			    		""";

	// Mux 32 to 1 (see if needed to add it later)
	/*
	 * private final String MUX32=""" """;
	 */

	/**
	 * Constructor that takes the size of the Multiplexer to write to the file.
	 *
	 * @param mux_size the size of the Multiplexer to create(number of input of
	 *                     Multiplexer).
	 * @exception IllegalArgumentException if the mux_size is not valid for
	 *                                         available size see
	 *                                         {@link Mux#possibleValues}.
	 */
	public Mux(int mux_size)
	{
		if (mux_size != 2 && mux_size != 4 && mux_size != 8 && mux_size != 16)
		{
			throw new IllegalArgumentException(
					"Invalid size for the constructor Mux!(valid size are 2 or 4 or 8 or 16 ");
		}

		this.mux_size = mux_size;
	}

	/**
	 * getter return the size of the Multiplexer
	 *
	 * @return the size of the Multiplexer.
	 */
	public int getSizeMux()
	{
		return this.mux_size;

	}

	/**
	 * setter the new size of the Multiplexer
	 *
	 * @param new_size of the Multiplexer
	 * @exception IllegalArgumentException if the input size is invalid see
	 *                                         {@link Mux#possibleValues}
	 */
	public void setSizeMux(int new_size)
	{
		if (new_size != 2 && new_size != 4 && new_size != 8 && new_size != 16)
		{
			throw new IllegalArgumentException(
					"Invalid size for the constructor Mux!(valid size are 2 or 4 or 8 or 16 ");
		}

		this.mux_size = new_size;

	}

	/**
	 * write the code of the Multiplexer to the file.
	 */
	public void writeMux()
	{

		try
		{
			// if the file has been opened.
			boolean open = false;
			// folder chosen
			String file_path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(file_path))
			{

				// Mux size 2,4,8,16,32
				switch (this.mux_size)
				{
				case 2:
					// new file path
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_MUX2;
					// create the new file.
					open = FileGenerator.openFile(file_path);
					// write the code
					FileGenerator.writeData(file_path, MUX2_VHDL);
					break;

				case 4:
					// new file path
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_MUX4;
					// create the new file.
					open = FileGenerator.openFile(file_path);
					// write the code
					FileGenerator.writeData(file_path, MUX4_VHDL);
					break;

				case 8:
					// new file path
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_MUX8;
					// create the new file
					open = FileGenerator.openFile(file_path);
					// write the code
					FileGenerator.writeData(file_path, MUX8_VHDL);
					break;

				case 16:

					// new file path
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_MUX16;
					// create the new file
					open = FileGenerator.openFile(file_path);
					// write the code
					FileGenerator.writeData(file_path, MUX16_VHDL);
					break;

				/*
				 * case 32: maybe add it in the future //write the code
				 * FileGenerator.writeData(file.getPath(),MUX32); //generated break;
				 */
				default:
					System.err.println("Error multiplexer size! " + this.mux_size);
					break;
				}

				// file was generated successfully
				if (open)
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
			}

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
			// WindowCode.errorFrame(WindowCode.ERROR_MSG_OPEN);
		}

	}

}
