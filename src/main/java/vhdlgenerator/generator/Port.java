/**
* @file Port.java

* @author DOUDOU DIAWARA  @see <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
* @version 0.0
*
* @section LICENSE
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of “Commons Clause” License Condition v1.0
* but not for Commercial use
*
*/
package vhdlgenerator.generator;

/**
 * this class hold the Port information about the VHDL code to write to the
 * file.
 *
 * @author DOUDOU DIAWARA
 *
 */
public class Port
{

	//
	// ATTRIBUTES
	//

	/** VHDL entity name */
	private String entity_name;
	/** VHDL architecture name */
	private String architecture_name;
	/** generic constant VHDL */
	private String generic_map;
	/** inputs signals */
	private String input_port;
	/** inputs data types signals */
	private String in_data_type;
	/** outputs signals */
	private String output_port;
	/** outputs data types */
	private String out_data_type;
	/** VHDL code implementation */
	private String code_implementation;

	//
	// CONSTRUCTORS
	//
	/**
	 * Constructor for the interface of the VHDL code.
	 *
	 * @param entity_name         the name of the entity interface
	 * @param architecture_name   the name architecture of the implementation
	 * @param generic_map         the DataWidth of the data type
	 * @param input_port          the input signals
	 * @param in_data_type        Data type of the input signals
	 * @param output_port         the output signals
	 * @param out_data_type       Data type of the output signals
	 * @param code_implementation VHDL code to write to the file
	 */
	public Port(String entity_name, String architecture_name, String generic_map, String input_port,
			String in_data_type, String output_port, String out_data_type, String code_implementation)
	{
		this.entity_name = entity_name;
		this.architecture_name = architecture_name;
		this.generic_map = generic_map;
		this.input_port = input_port;
		this.in_data_type = in_data_type;
		this.output_port = output_port;
		this.out_data_type = out_data_type;
		this.code_implementation = code_implementation;
	}

	//
	// METHODS GETTERS AND SETTERS
	//

	/**
	 * Set the value of generic_map
	 *
	 * @param newVar the new value of generic_map
	 */
	public void setGenericMap(String newVar)
	{
		generic_map = newVar;
	}

	/**
	 * Get the value of generic_map
	 *
	 * @return the value of generic_map
	 */
	public String getGenericMap()
	{
		return generic_map;
	}

	/**
	 * return the input data type
	 *
	 * @return the different data type of the input signals
	 */
	public String getInDataType()
	{
		return in_data_type;
	}

	/**
	 * Set the new value of in_data_type
	 *
	 * @param new_data_type value
	 */
	public void setInDataType(String new_data_type)
	{
		in_data_type = new_data_type;

	}

	/**
	 * return output data signal
	 *
	 * @return the different data type of the output signals
	 */
	public String getOutDataType()
	{
		return out_data_type;
	}

	/**
	 * set the output data type
	 *
	 * @param out_data_type new value
	 */
	public void setOutDataType(String out_data_type)
	{
		this.out_data_type = out_data_type;

	}

	/**
	 * Set the value of input_port
	 *
	 * @param newVar the new value of input_port
	 */
	public void setInputPort(String newVar)
	{
		input_port = newVar;
	}

	/**
	 * Get the value of input_port
	 *
	 * @return the value of input_port
	 */
	public String getInputPort()
	{
		return input_port;
	}

	/**
	 * Set the value of output_port
	 *
	 * @param newVar the new value of output_port
	 */
	public void setOutputPort(String newVar)
	{
		output_port = newVar;
	}

	/**
	 * Get the value of output_port
	 *
	 * @return the value of output_port
	 */
	public String getOutputPort()
	{
		return output_port;
	}

	/**
	 * Set the value of name_entity
	 *
	 * @param newVar the new value of name_entity
	 */
	public void setNameEntity(String newVar)
	{
		entity_name = newVar;
	}

	/**
	 * Get the value of name_entity
	 *
	 * @return the value of name_entity
	 */
	public String getNameEntity()
	{
		return entity_name;
	}

	/**
	 * Get the value of the architecture name
	 *
	 * @return the architecture name of the implementation
	 */
	public String getNameArchitecture()
	{
		return architecture_name;
	}

	/**
	 * Set the value of the architecture name
	 *
	 * @param new_architecture value
	 */
	public void setNameArchitecture(String new_architecture)
	{
		this.architecture_name = new_architecture;
	}

	/**
	 * the VHDL code implementation of Port
	 *
	 * @return code_implementation to write to the file
	 */
	public String getCodeImplementation()
	{
		return code_implementation;
	}

	/**
	 * Set the value of code_implementation
	 *
	 * @param code_implementation the new value of code_implementation to write to
	 *                                the file
	 */
	public void setCodeImplementation(String code_implementation)
	{
		this.code_implementation = code_implementation;
	}

}
