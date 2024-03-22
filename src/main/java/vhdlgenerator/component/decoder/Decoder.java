
/**
 * @file Decoder.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.0
 *
 * @section LICENSE This program is free software; you can redistribute it
 * and/or modify it under the terms of “Commons Clause” License Condition v1.0
 * but not for Commercial use
 *
 */
package vhdlgenerator.component.decoder;

import java.io.IOException;
import javax.swing.JOptionPane;

import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * this class generate the code for the Decoder
 *
 * @author DOUDOU DIAWARA
 *
 */
public class Decoder
{
	/** name of the decoder2:4 file. */
	private static final String FILE_DECODER2 = "Decoder2_4.vhdl";
	/** name of the decoder3:8 file. */
	private static final String FILE_DECODER3 = "Decoder3_8.vhdl";
	/** name of the decoder4:16 file */
	private static final String FILE_DECODER4 = "Decoder4_16.vhdl";
	/** represent the number of input of the Decoder */
	private int decoder_size;
	/** possible size of the Decoder */
	public static Object[] possibleValues = { "2", "3", "4" };

	//
	// VHDL code for the Decoder
	//

	/** VHDL code of the decoder 2x4 */
	private final String DECODER2_VHDL = """
			library IEEE;
			use IEEE.std_logic_1164.all;
			use IEEE.numeric_std.all;

			entity Decoder2_4 is
			    port(
			        input : in std_logic_vector(1 downto 0);
			        enable : in std_logic;
			        output : out std_logic_vector(3 downto 0)
			    );
			end  Decoder2_4;

			architecture behaviour of Decoder2_4 is

			begin
			    process(enable,input) is begin
			        if(enable = '1') then
			            case input is
			                when "00" =>
			                    output <= "0001" ;
			                when "01" =>
			                    output <= "0010" ;
			                when "10" =>
			                    output <= "0100" ;
			                when "11" =>
			                    output <= "1000" ;
			                when others =>
			                    output <= "0000";
			                end case;
			        end if;
			    end process;

			end  behaviour;
									""";

	/** VHDL code of the decoder 3x8 */
	private final String DECODER3_VHDL = """
			library IEEE;
			use IEEE.STD_LOGIC_1164.ALL;
			use IEEE.numeric_std.all;

			entity Decoder3_8 is
			    Port ( input : in STD_LOGIC_VECTOR(2 downto 0);
			           enable : in STD_LOGIC;
			           output : out STD_LOGIC_VECTOR(7 downto 0));
			end Decoder3_8;

			architecture Behavioral of Decoder3_8 is
			begin
			    process(input, enable)
			    begin
			        if enable = '1' then
			            case input is
			                when "000" =>
			                    output <= "00000001";
			                when "001" =>
			                    output <= "00000010";
			                when "010" =>
			                    output <= "00000100";
			                when "011" =>
			                    output <= "00001000";
			                when "100" =>
			                    output <= "00010000";
			                when "101" =>
			                    output <= "00100000";
			                when "110" =>
			                    output <= "01000000";
			                when "111" =>
			                    output <= "10000000";
			                when others =>
			                    output <= "00000000"; -- default case
			            end case;
			        else
			            output <= "00000000"; -- output is all '0' when disabled
			        end if;
			    end process;
			end Behavioral;
						""";
	/** VHDL code of the decoder 4x16 */
	private final String DECODER4_VHDL = """
			library IEEE;
			use IEEE.STD_logic_1164.all;

			entity decoder4_16 is
			    port(
			          enable : in std_logic ;
			         input: in std_logic_vector(3 downto 0);
			         output: out std_logic_vector(15 downto 0));
			end decoder4_16;

			architecture behaviour of decoder4_16 is
			begin
			    process(enable,input) is begin
			         if(enable = '1') then
			            output(0) <= (not input(3)) and (not input(2)) and (not input(1)) and (not input(0));
			            output(1) <= (not input(3)) and (not input(2)) and (not input(1)) and input(0);
			            output(2) <= (not input(3)) and (not input(2)) and input(1) and (not input(0));
			            output(3) <= (not input(3)) and (not input(2)) and input(1) and input(0);
			            output(4) <= (not input(3)) and input(2) and (not input(1)) and (not input(0));
			            output(5) <= (not input(3)) and input(2) and (not input(1)) and input(0);
			            output(6) <= (not input(3)) and input(2) and input(1) and (not input(0));
			            output(7) <= (not input(3)) and input(2) and input(1) and input(0);
			            output(8) <= input(3) and (not input(2)) and (not input(1)) and (not input(0));
			            output(9) <= input(3) and (not input(2)) and (not input(1)) and input(0);
			            output(10)<= input(3) and (not input(2)) and input(1) and (not input(0));
			            output(11)<= input(3) and (not input(2)) and input(1) and input(0);
			            output(12) <= input(3) and input(2) and (not input(1)) and (not input(0));
			            output(13) <= input(3) and input(2) and (not input(1)) and input(0);
			            output(14) <= input(3) and input(2) and input(1) and (not input(0));
			            output(15) <=  input(3) and  input(2) and  input(1) and  input(0);
			        else
			            output <= (others => '0') ;
			        end if;
			    end process ;
			end Behaviour;
						""";

	/**
	 * Constructor with the size(number of input) of the Decoder to create.
	 *
	 * @param decoder_size the size of the decoder to create number of input.
	 * @exception IllegalArgumentException if the decoder_size is not valid for
	 *                                         available size see
	 *                                         {@link Decoder#possibleValues}.
	 */
	public Decoder(int decoder_size)
	{
		if (decoder_size != 2 && decoder_size != 3 && decoder_size != 4)
		{
			throw new IllegalArgumentException(
					"Invalid size for the constructor Decoder!(valid size are 2 or 3 or 4) ");
		}

		this.decoder_size = decoder_size;

	}

	/**
	 * getter that return the size of the decoder
	 *
	 * @return the size of the decoder
	 */
	public int getDecoderSize()
	{
		return this.decoder_size;

	}

	/**
	 * setter change the number of input of the decoder
	 *
	 * @param new_size the new decoder size (number of input)
	 * @exception IllegalArgumentException if the input size is Invalid see
	 *                                         {@link Decoder#possibleValues}
	 */
	public void setDecoderSize(int new_size)
	{
		if (new_size != 2 && new_size != 3 && new_size != 4)
		{
			throw new IllegalArgumentException(
					"Invalid size for the constructor Decoder!(valid size are 2 or 3 or 4) ");
		}
		this.decoder_size = new_size;

	}

	/**
	 * write the code of the Decoder to the file
	 */
	public void writeDecoder()
	{

		try
		{
			// if the file has been opened
			boolean created = false;

			String file_path = DisplayLibrary.chooseFolder();

			if (WindowCode.validateFilePath(file_path))
			{

				// deocder 2,3,4
				switch (this.decoder_size)
				{
				case 2:
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_DECODER2;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, DECODER2_VHDL);
					break;

				case 3:
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_DECODER3;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, DECODER3_VHDL);
					break;

				case 4:
					// create the full path name of the file
					file_path = file_path + DisplayLibrary.FILE_SEPARATOR + FILE_DECODER4;
					// create the new file
					created = FileGenerator.openFile(file_path);
					// write code to the file
					FileGenerator.writeData(file_path, DECODER4_VHDL);

					break;

				default:
					System.err.println("Error invalid option for Decoder! valid option are 2 or 3 or 4");
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
