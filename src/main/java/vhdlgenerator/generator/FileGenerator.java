/**
 * @file FileGenerator.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 * @version 0.0
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 *
 */
package vhdlgenerator.generator;

import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * this class generate the code for the VHDL file. this class take information
 * from the class {@link Port} and write those information to the file.
 *
 * @author DOUDOU DIAWARA
 *
 *         If a null object is passed in argument to any method, then a
 *         <code>NullPointerException</code> will be thrown.
 */
public class FileGenerator
{

	// CONSTANT
	/** if the file already exists */
	private static final String FILE_ALREADY_EXISTS = "THE FILE ALREADY EXISTS DO YOU WANNA OVERIDDE IT?";
	/** error message cannot write to the file */
	private static final String NO_WRITE = "ERROR CANNOT WRITE TO THE FILE!";
	/** newline character */
	public static final char NEWLINE = '\n';
	/** tab character */
	public static final String TAB = "\t";
	/** colon character */
	public static final String COLON = ":";
	/** comma character */
	public static final String COMMA = ",";
	/** semicolon character */
	public static final String SEMICOLON = ";";
	/** string that inform that a new value has been passed in argument */
	public static final String NULL_VALUE = "passing null value";
	/** string that indicate that the file name given is null */
	public static final String NULL_FILE = "File name cannot be null.";

	//
	// VHDL KEYWORDS
	//

	/**
	 * VHDL keywords <a href=
	 * "http://www.pldworld.com/_hdl/2/_ref/acc-eda/vhdl_keywords/vhdl_keywords.htm">VHDL
	 * Keywords </a>
	 */
	public static final HashMap<String, String> vhdl_keywords = new HashMap<String, String>();

	// INITIALIZE VHDL KEYWORDS
	static
	{
		// initialize vhdl keywords
		vhdl_keywords.put("IEEE", "library ieee;\nuse ieee.std_logic_1164.all;\nuse ieee.numeric_std.all;\n");
		vhdl_keywords.put("ENTITY", "entity ");
		vhdl_keywords.put("END_ENTITY", "end entity;");
		vhdl_keywords.put("IS", " is");
		vhdl_keywords.put("IN", ": in ");
		vhdl_keywords.put("OUT", ": out ");
		vhdl_keywords.put("END", "end ");
		vhdl_keywords.put("BEGIN", "begin\n");
		vhdl_keywords.put("GENERIC", "generic(");
		vhdl_keywords.put("END_GENERIC", ");");
		vhdl_keywords.put("PORT", "\tport(");
		vhdl_keywords.put("END_PORT", ");");
		vhdl_keywords.put("ARCHITECTURE", "\narchitecture behaviour of ");
		vhdl_keywords.put("ARCHITECTURE_IS", " is \nbegin\n");
		vhdl_keywords.put("END_ARCHITECTURE", "end behaviour ;");
		vhdl_keywords.put("INTEGER", " integer");
		vhdl_keywords.put("STD_LOGIC", "std_logic");
		vhdl_keywords.put("STD_LOGIC_VECTOR", "std_logic_vector");
		vhdl_keywords.put("VARIABLE_ASSIGNMENT", " := ");
		vhdl_keywords.put("COMPONENT", "component ");
		vhdl_keywords.put("END_COMPONENT", "end component ;");
		vhdl_keywords.put("CONSTANT", "constant ");
		vhdl_keywords.put("SIGNAL", "signal ");
		vhdl_keywords.put("PORT_MAP", " port map(\n");
		vhdl_keywords.put("GENERIC_MAP", " generic map(");
		vhdl_keywords.put("PROCESS", "\tprocess begin\n\t");
		vhdl_keywords.put("END_PROCESS", "end process;\n");

	}

	//
	// ATTRIBUTES
	//

	/**
	 * filename given by the user
	 */
	private String file_name;
	/**
	 * information of the file VHDL interface
	 */
	private Port info_interface;

	/**
	 * Constructor to generate the file given the path to the file and the
	 * information of the VHDL code to write
	 *
	 * @param file_name      path to the file to create
	 * @param info_interface information to write to the file
	 * @exception NullPointerException if any null object is passed
	 */
	public FileGenerator(String file_name, Port info_interface)
	{
		if (file_name == null || info_interface == null)
		{
			throw new NullPointerException(NULL_VALUE);
		} else
		{
			this.file_name = file_name;
			this.info_interface = info_interface;
		}
	}

	//
	// GETTERS AND SETTERS
	//
	/**
	 * Set the value of file_name
	 *
	 * @param newVar the new value of file_name
	 */
	public void setFileName(String newVar)
	{
		file_name = newVar;
	}

	/**
	 * Get the value of file_name
	 *
	 * @return the value of file_name
	 */
	public String getFileName()
	{
		return file_name;
	}

	/**
	 * get all the information about the VHDL code
	 *
	 * @return the Port information
	 */
	public Port getPort()
	{
		return this.info_interface;
	}

	//
	// Method
	//
	/**
	 * this method separate a given String by a delimiter.
	 *
	 * @param sep   the delimiter to use
	 * @param input String to separate
	 * @return array String after split
	 */
	public String[] separator(final String sep, final String input)
	{
		return input.split(sep);
	}

	/**
	 * Create a File given the path if the file already exists it will ask to
	 * overwrite it.
	 *
	 * @param file_name path to the file to create or open
	 *
	 * @throws NullPointerException if file_name is <code>null</code>
	 * @exception IOException if the file cannot be created or open
	 *
	 * @return <code>true</code> if the file has been created successfully otherwise
	 *         <code>false</code>.
	 */
	public static boolean openFile(final String file_name) throws NullPointerException, IOException
	{
		boolean exit = false;

		if (file_name == null)
		{
			throw new NullPointerException(NULL_FILE);
		} else
		{

			// path to the file
			File file = new File(file_name);

			if (!(file.exists())) // test file if already exist
			{
				if (file.createNewFile()) // not exist yet create
				{
					exit = true;

				}
			} else
			{
				/* JFrame information file already exists */

				int choice = JOptionPane.showConfirmDialog(null, FILE_ALREADY_EXISTS);

				if (choice == JOptionPane.YES_OPTION) // override file
				{
					if (removeFile(file)) // delete existing file
					{
						exit = file.createNewFile();
						return exit;

					}
				}
			}
		}

		return exit;
	}

	/**
	 * to remove a file.
	 *
	 * @param file to remove
	 * @exception NullPointerException if file_name is <code>null</code>
	 * @exception IOException          when the file cannot be deleted
	 *
	 * @return <code>true</code> if the file has been deleted successfully otherwise
	 *         <code>false</code>.
	 */
	public static boolean removeFile(File file) throws NullPointerException, IOException
	{
		if (file == null)
		{
			throw new NullPointerException(NULL_VALUE);
		} else
		{
			return file.delete();
		}
	}

	/**
	 * this method write a data to a file
	 *
	 * @param file_name the path of the file.
	 * @param data      to write to the file
	 * @exception NullPointerException if passing any <code>null</code> Object
	 */
	public static void writeData(final String file_name, final String data)
	{
		if (data == null || file_name == null)
		{
			throw new NullPointerException(NULL_VALUE);

		} else
		{
			// file to write data
			try (FileWriter file = new FileWriter(file_name, true))
			{

				// writing data
				file.write(data);
				file.append(NEWLINE);

			} catch (IOException e)
			{

				WindowCode.errorFrame(NO_WRITE);
			}

		}
	}

	/**
	 * write the entity name into the file_name
	 */
	private void writeEntity()
	{
		/*
		 * entity name_entity is
		 */
		StringBuffer data = new StringBuffer();

		data.append(vhdl_keywords.get("IEEE")).append(NEWLINE) // importing library
				.append(vhdl_keywords.get("ENTITY")).append(this.info_interface.getNameEntity())
				.append(vhdl_keywords.get("IS")); // entity

		// write to the file
		writeData(this.getFileName(), data.toString());

	}

	/**
	 * write the constant generic map to the file
	 *
	 */
	private void writeGeneric()
	{
		/*
		 * generic(DataWitdh : integer := 8 ),
		 */
		if (this.info_interface.getGenericMap() != null)
		{ // return the generic name and the generic value
			String[] name_value = separator(SEMICOLON, this.info_interface.getGenericMap());
			// GENERIC MAP
			String data = TAB + vhdl_keywords.get("GENERIC") + name_value[0] + COLON + vhdl_keywords.get("INTEGER")
					+ vhdl_keywords.get("VARIABLE_ASSIGNMENT") + name_value[1] + vhdl_keywords.get("END_GENERIC");

			// write generic to file
			writeData(this.getFileName(), data);
		}
	}

	/**
	 * write the name and value of the signals
	 */
	private void writeSignal()
	{
		/*
		 * port ( input : IN datatype ...; output : OUT datatype ... );
		 */

		/*
		 * **** INTPUT SIGNALS ***
		 */
		// buffer to write to the file
		StringBuffer buffer = new StringBuffer();
		String downTo = "-1 downto 0)";

		// get the input signals
		String[] signal_split = separator(SEMICOLON, this.info_interface.getInputPort());
		// get input signals datatype
		String[] data_type_split = separator(SEMICOLON, this.info_interface.getInDataType());

		buffer.append(vhdl_keywords.get("PORT")).append(NEWLINE).append(TAB + TAB + TAB);

		// i to iterate in_data_type
		int i = 0;
		for (String input_signal : signal_split)
		{

			buffer.append(input_signal); // input signal name
			// add directional signal
			buffer.append(vhdl_keywords.get("IN"));

			// input data type signal
			buffer.append(data_type_split[i]);

			// add length to array std_logic_vector value generic constant
			if (this.info_interface.getGenericMap() != null
					&& data_type_split[i].equals(vhdl_keywords.get("STD_LOGIC_VECTOR")))
			{
				String[] name_value_generic = separator(SEMICOLON, this.info_interface.getGenericMap());
				// add only the name not the value
				buffer.append("(").append(name_value_generic[0]).append(downTo);
			}

			// separation between signals
			buffer.append(SEMICOLON);
			buffer.append(NEWLINE);
			buffer.append(TAB);
			buffer.append(TAB);
			buffer.append(TAB);

			// next input data type
			i++;

		}

		/*
		 * **** OUPUT SIGNALS ***
		 */
		// get the output signals string_split
		signal_split = separator(SEMICOLON, this.info_interface.getOutputPort());

		// get the output data type signal
		data_type_split = separator(SEMICOLON, this.info_interface.getOutDataType());

		// loop to out_data_type
		i = 0;

		for (String output : signal_split)
		{

			buffer.append(output);
			// add directional signal
			buffer.append(vhdl_keywords.get("OUT"));

			// Output data type signal
			buffer.append(data_type_split[i]);

			// add length to array std_logic_vector value generic constant
			if (this.info_interface.getGenericMap() != null
					&& data_type_split[i].equals(vhdl_keywords.get("STD_LOGIC_VECTOR")))
			{
				String[] name_value_generic = separator(SEMICOLON, this.info_interface.getGenericMap());
				// add only the name not the value
				buffer.append("(").append(name_value_generic[0]).append(downTo);
			}

			// last out signal don't add comma
			if (!output.equals(signal_split[signal_split.length - 1]))
			{
				buffer.append(TAB);
				buffer.append(TAB);
				buffer.append(SEMICOLON);
				buffer.append(NEWLINE);
			}

			i++;
		}

		// END PORT AND END ENTITY
		buffer.append(NEWLINE);
		buffer.append(TAB);
		buffer.append(vhdl_keywords.get("END_PORT"));
		buffer.append(NEWLINE);
		buffer.append(vhdl_keywords.get("END")).append(info_interface.getNameEntity()).append(SEMICOLON);

		// write all data to the file
		writeData(this.getFileName(), buffer.toString());

	}

	/**
	 * write the architecture name to the file
	 */
	private void writeArchitecture()
	{
		/*
		 * architecture name of entity is begin implementation code end name ;
		 */

		StringBuffer data = new StringBuffer();
		data.append(vhdl_keywords.get("ARCHITECTURE"));
		data.append(this.info_interface.getNameEntity());
		data.append(vhdl_keywords.get("ARCHITECTURE_IS"));

		// VHDL CODE IMPLEMENTATION TO WRITE TO THE FILE
		if (info_interface.getCodeImplementation() != null)
		{
			data.append(info_interface.getCodeImplementation());
			data.append(NEWLINE);
		}

		// END ARCHITECTURE
		data.append(vhdl_keywords.get("END_ARCHITECTURE"));

		// write to the file
		writeData(this.getFileName(), data.toString());

	}

	/**
	 * this method write all the VHDL information @see Port given to the file
	 * {@link FileGenerator#file_name}
	 *
	 */
	public void writeHDL()
	{
		writeEntity();
		writeGeneric();
		writeSignal();
		writeArchitecture();

	}

}
