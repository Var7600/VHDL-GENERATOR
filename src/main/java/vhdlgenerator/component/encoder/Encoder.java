/**
 * @file Encoder.java
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
package vhdlgenerator.component.encoder;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the VHDL code of the Encoder
 *
 * @author DOUDOU DIAWARA
 */
public class Encoder
{
	/** the size of the encoder(number of inputs of for the encoder */
	private int encoder_size;

	/**
	 * possible size for the encoder
	 */
	public final static Object[] possibleValues = { "4", "8" };

	/** file name for the encoder4x2 */
	private final String FILE_ENCODER4 = "Encoder4x2.vhdl";
	/** file name for the encoder8x3 */
	private final String FILE_ENCODER8 = "Encoder8x3.vhdl";
	/** VHDL code for the encoder4x2 */
	private final String ENCODER4_VHDL = """
			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity Encoder4x2 is
			    port(
			        input : in std_logic_vector(3 downto 0);
			        enable: in std_logic;
			        output : out STD_LOGIC_VECTOR(1 downto 0)
			    );
			end entity Encoder4x2;

			architecture behaviour of Encoder4x2 is

			begin

			    encoder:process(enable,input) is begin
			        if enable = '1' then
			            case input is
			            when "0001" =>
			                output <= "00";
			            when "0010" =>
			                output <= "01" ;
			            when "0100" =>
			                output <= "10";
			            when "1000" =>
			                output <= "11";
			            when others =>
			                output <= (others => 'X');
			            end case;
			        end if;

			    end process encoder;


			end architecture behaviour;

						""";
	/** VHDL code for the encoder8x3 */
	private final String ENCODER8_VHDL = """

			library IEEE;

			use IEEE.STD_LOGIC_1164.all;
			use IEEE.NUMERIC_STD. all;

			entity Encoder8x3 is

			    port (
			        input: in std_logic_vector (7 downto 0);
			        enable : in STD_LOGIC;
			        output: out std_logic_vector (2 downto 0)
			    );

			end Encoder8x3;

			architecture behaviour of Encoder8x3 is

			begin

			    process (enable,input) is
			    begin
			        if enable = '1' then
			            case input is

			                when "00000001" => output <= "000";

			                when "00000010" => output <= "001";

			                when "00000100" => output <= "010";

			                when "00001000" => output <= "011";

			                when "00010000" => output <= "100";

			                when "00100000" => output <= "101";

			                when "01000000" => output <= "110";

			                when "10000000" => output <= "111";

			                when others => output <= "XXX";

			            end case ;
			        end if;


			    end process ;

			end behaviour;

						""";

	/**
	 * Constructor for the size of the encoder to create.
	 *
	 * @param encoder_size the size of the encoder for the available size see
	 *                         {@link Encoder#possibleValues}
	 * @exception IllegalArgumentException if the encoder_size is not available for
	 *                                         available size for the encoder see
	 *                                         {@link Encoder#possibleValues}.
	 */
	public Encoder(int encoder_size)
	{
		if (encoder_size != 4 && encoder_size != 8)
		{
			throw new IllegalArgumentException("Invalid size for the constructor Encoder!(available size are 4 and 8)");
		}

		this.encoder_size = encoder_size;
	}

	/**
	 * getter for the Encoder size.
	 *
	 * @return encoder_size the number of output of the encoder.
	 */
	public int getEncoderSize()
	{
		return encoder_size;
	}

	/**
	 * setter for the size of the encoder
	 *
	 * @param new_size of the encoder
	 * @exception IllegalArgumentException if the new size is invalid for valid size
	 *                                         see {@link Encoder#possibleValues}
	 */
	public void setEncoderSize(int new_size)
	{
		if (new_size != 4 && new_size != 8)
		{
			throw new IllegalArgumentException("Invalid size for the constructor Encoder!(available size are 4 and 8)");
		}

		this.encoder_size = new_size;
	}

	/**
	 * write the VHDL code of the Encoder to the directory chosen
	 */

	public void writeEncoder()
	{
		try
		{
			// if the file has been opened
			boolean created = false;
			// chose a directory path
			String file_path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(file_path))
			{

				// encoder 2,3
				switch (this.encoder_size)
				{
				case 4:
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_ENCODER4;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, ENCODER4_VHDL);
					break;

				case 8:
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_ENCODER8;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, ENCODER8_VHDL);
					break;

				default:
					System.err.println("Error invalid option for Encoder! valid option are 4 and 8");
					break;

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
			}

		} catch (IOException | NullPointerException e)
		{
			// handle cannot open file or create file.
			// WindowCode.errorFrame(WindowCode.ERROR_MSG_OPEN);
		}
	}

}
