/**
 * @file WindowCode.java
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
package vhdlgenerator.generator;

//SWING
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
// I/O
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Objects;
//UTIL
import java.util.regex.Pattern;

import vhdlgenerator.application.AppVG;
import vhdlgenerator.testbench.Testbench;
import vhdlgenerator.texteditor.Editor;

/**
 *
 * This class generate a GUI for the application and takes input from the user
 * using a form then pass those information to the class @see FileGenerator to
 * generate the file
 *
 * @author DOUDOU DIAWARA
 *
 */
@SuppressWarnings("serial")
public class WindowCode extends JFrame implements ActionListener
{

	//
	// Constant
	//

	// public re-use in others class.
	/**
	 * select title
	 */
	public static final String SELECT = "Select";
	/**
	 * to select a file
	 */
	public static final String SELECT_FILE = "<html><b>SELECT A FILE OR TYPE THE FILE NAME AND IT WILL BE CREATED</b></html>";
	/**
	 * error message
	 */
	public static final String ERROR_MSG_OPEN = "<html><span style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>AN ERROR OCCURRED WHILE TRYING TO CREATE OR OPEN THE FILE!</span><br/><b>check if your  path is valid</b> </html>";

	//
	// private
	//
	/**
	 * title code generator
	 */
	private static final String TITLE = "CODE GENERATOR";
	/** title error */
	private static final String TITLE_ERROR = "Error!";
	/**
	 * code generator successfully
	 */
	private static final String TITLE_GENERATED = "CODE GENERATED!";
	/**
	 * asking to open file
	 */
	private static final String OPEN_FILE = "<html><b>DO YOU WANT TO OPEN THE VHDL FILE?</b></html>";
	/**
	 * asking to open the testbench
	 */
	private static final String OPEN_TESTBENCH = "<html><b>DO YOU WANT TO OPEN THE TESTBENCH FILE?</b></html>";
	/**
	 * title to ask to generated testbench
	 */
	private static final String GENERATE_TB = "<html><b>DO YOU WANT TO GENERATE A <mark>TESTBENCH</mark> FILE?</b></html>";
	/**
	 * error opening file
	 */
	private static final String NO_OPEN_FILE = "<html><span style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>file found! but a Error occured trying to open the file! </span> </html>";
	/**
	 * code has been generated successfully
	 */
	private static final String GENERATED = "<html><span style=color:'green';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>CODE GENERATED SUCCESSFULLY!</span></html>";
	/**
	 * testbench generated successfully
	 */
	private static final String GENERATED_TB = "<html><span style=color:'green';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>TESTBENCH GENERATED SUCCESSFULLY!</span></html>";
	/** empty file path */
	private static final String NULL_FILE_PATH = "<html><span style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>FILE PATH CANNOT BE EMPTY </span> </html>";
	/**
	 * invalid file path
	 */
	private static final String INVALID_FILE_PATH = "<html><b>INVALID FILE PATH! PLEASE CHECK YOUR FILE PATH:</b><br/><span style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>file path connot be null<br/>file path cannot be a directory<br/></span> </html>";
	/**
	 * invalid identifier
	 */
	private static final String INVALID_IDENTIFIER = "<html><b>Basic VHDL identifiers/name consist of:</b><br> <span style=color:'red'>letters, underscores and digits and <mark>must start with a letter!</mark></span></html>";
	/**
	 * invalid format
	 */
	private static final String INVALID_GENERIC_FORMAT = "<html>INVALID FORMAT IN FIELDS GENERIC CONSTANT NAME AND VALUE!<br>PLEASE RESPECT THE FORMAT :<br> <b>DataWidth;8</b> <mark>Only one generic constant is allow</mark></html>";
	/**
	 * null value
	 */
	private static final String NULL_ENTITY = "<html><span style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>ENTITY NAME CANNOT BE EMPTY</span></html>";
	/**
	 * empty signal input value
	 */
	private static final String NULL_VALUES = "<html>THIS FIELDS CANNOT BE EMPTY:"
			+ "<br><span  style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic';>"
			+ "FILE_PATH<br>ENTITY_NAME<br>INPUT_SIGNALS<br>IN_DATA_SIGNALS<br>OUTPUT_SIGNALS<br>OUT_DATA_SIGNALS</span></html>";
	/**
	 * invalid signal format
	 */
	private static final String NOT_MATCHING_SIGNAL_FORMAT_IN = "<html>PLEASE CHECK YOUR INPUT SIGNALS MATCHES THIS FORMAT : <br> <b>input</b> or <b>input1;input2</b> or <b>a;b</b> \n <b> name of the input signal seperated by SEMICOLON <b>;</b></html>";
	/**
	 * error number of input signal and data type
	 */
	private static final String NOT_MATCHING_SIGNAL_FORMAT_OUT = "<html>PLEASE CHECK YOUR OUT SIGNALS MATCHES THIS FORMAT : <br> <b>output</b> or <b>output1;output2</b> or <b>a;b</b> \n <b> name of the output signal seperated by SEMICOLON <b>;</b></html>";
	/**
	 * error number of output signal and data type
	 */
	private static final String NOT_MATCHING_DATA_TYPE = "<html>PLEASE CHECK YOUR DATA TYPE SIGNALS MATCHES THIS FORMAT : <br> <b>std_logic</b> or <b>std_logic_vector;std_logic</b> or <b>bit;bit_vector</b> \n <b> name of the data type signal seperated by SEMICOLON <b>;</b></html>";
	/**
	 * not matching number of signals and data type
	 */
	private static final String NOT_MATCHING_NUMBER_SIGNAL_DATA_TYPE = "<html><span style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>PLEASE CHECK YOUR NUMBERS OF SIGNALS IS EQUAL TO THE NUMBERS OF DATA TYPE</span> </html>";

	/**
	 * the valid form name for a signal in VHDL
	 */
	private static final String SIGNAL_NAME_FORMAT = "(([^0-9][A-Za-z]*_*[A-Za-z0-9]*;*)*)";
	/**
	 * the valid form name for a data type in VHDL
	 */
	private static final String SIGNAL_DATA_FORMAT = "(([^0-9][A-Za-z_0-9]*;*)*)";

	//
	// Attributes
	//

	/**
	 * data to write to the file
	 */
	private Port info_interface;

	//
	// SIZE OF FRAME ELEMENT
	//

	/**
	 * x position to align component horizontally
	 */
	private final int ALIGN_X_AXIS = 9;
	/**
	 * label width
	 */
	private final int LABEL_WIDTH = 400;
	/**
	 * label height
	 */
	private final int LABEL_HEIGTH = 30;
	/**
	 * input width
	 */
	private final int INPUT_WIDTH = 500;
	/**
	 * input height
	 */
	private final int INPUT_HEIGTH = 25;

	//
	// SIZE OF EDITOR
	//
	/**
	 * width editor code
	 */
	private final int EDITOR_WIDTH = 600;
	/**
	 * height editor code
	 */
	private final int EDITOR_HEIGHT = 150;

	//
	// JLABEL
	//

	/**
	 * JLabel for created file
	 */
	private final JLabel label_file_path = new JLabel(
			"<html><b>PATH TO THE FILE TO CREATE:</b><span style=color:red> *</span>");
	/**
	 * JLabel foe entity name
	 */
	private final JLabel label_entity_name = new JLabel("<html><b>ENTITY NAME</b><span style=color:red> *</span>");
	/**
	 * JLabel of generic constant
	 */
	private final JLabel label_generic = new JLabel(
			"<html> <b>GENERIC CONSTANT NAME AND VALUE (example :  DataWidth;8) </b><html>");
	/**
	 * JLabel for data type input signal
	 */
	private final JLabel label_input_signal = new JLabel(
			"<html><b>INPUT SIGNALS (example : input1;input2;input3;...)</b><span style=color:red> *</span>");
	/**
	 * JLabel input signal
	 */
	private final JLabel label_data_type_input = new JLabel(
			"<html><b>IN DATA TYPES: ordered like the input signals</b> <span style=color:red> *</span>");
	/**
	 * JLabel data type output signal
	 */
	private final JLabel label_data_type_output = new JLabel(
			"<html><b>OUT DATA TYPES: ordered like the output signals</b> <span style=color:red> *</span>");
	/**
	 * JLabel output signal
	 */
	private final JLabel label_ouput_signal = new JLabel(
			"<html><b>OUTPUT SIGNALS (example : output1;output2;output3;...)</b><span style=color:red> *</span>");

	//
	// ICON
	//

	/**
	 * arrow icon
	 */
	private final ImageIcon icon_arrow = new ImageIcon(this.getClass().getClassLoader().getResource("arrow.png"));
	/**
	 * label for VHDL code
	 */
	private final JLabel label_vhdl_code = new JLabel("VHDL CODE", icon_arrow, 0);

	/**
	 * icon for the back button
	 */
	public static final ImageIcon icon_back_button = new ImageIcon(
			WindowCode.class.getClassLoader().getResource("back.gif"));
	/**
	 * back button
	 */
	private final JButton back_button = new JButton("Go Back Home", icon_back_button);

	/**
	 * background color
	 */
	private final String bg_color = "#f8f9f9";
	/**
	 * label color
	 */
	private final String field_color = "#000000";
	/**
	 * data type color
	 */
	private final String data_type_color = "#39c1ea";

	/**
	 * font Monospaced
	 */
	public static final Font monospaced = new Font("Monospaced", Font.ITALIC, 14);
	/**
	 * button font ArialBlack
	 */
	public static final Font button_font = new Font("ArialBlack", Font.ITALIC | Font.BOLD, 14);

	//
	// JTEXTFIELDS TEXT INPUT
	//

	/**
	 * input for the file path
	 */
	private final JTextField file_path = new JTextField(80);
	/**
	 * input for the VHDL entity name
	 */
	private final JTextField entity_name = new JTextField(80);
	/**
	 * input for the generic constant
	 */
	private final JTextField generic = new JTextField(80);
	/**
	 * input for the input port signals
	 */
	private final JTextField input_signals = new JTextField(80);
	/**
	 * input for the output port signals
	 */
	private final JTextField output_signals = new JTextField(80);
	/**
	 * input for the data type input signal
	 */
	private final JTextField data_type_in = new JTextField(80);
	/**
	 * input for the data type output signal
	 */
	private final JTextField data_type_out = new JTextField(80);

	/**
	 * editor area VHDL
	 */
	private Editor editor;

	//
	// BUTTON
	//

	/**
	 * button to browser file explorer
	 */
	private final JButton button_browse = new JButton("Browse File");
	/**
	 * button to generate code
	 */
	private final JButton button_generate = new JButton("GENERATE FILE !");

	//
	// JLIST
	//

	/**
	 * list of available data type
	 */
	private final String[] LIST_DATA_TYPE = { "std_logic", "std_logic_vector", "bit", "bit_vector" };
	/**
	 * list for choosing the data type input signal
	 */
	private final JComboBox<String> data_type_list_in = new JComboBox<String>(LIST_DATA_TYPE);
	/**
	 * list for choosing the data type output signal
	 */
	private final JComboBox<String> data_type_list_out = new JComboBox<String>(LIST_DATA_TYPE);

	/**
	 * construct to generate the Window Object to take information from the user.
	 *
	 */
	public WindowCode()
	{
		// TITLE
		super(TITLE);

		// PORT INTERFACE INFO
		this.info_interface = new Port(null, null, null, null, null, null, null, null); // zero information yet

		// SIZE
		setSize(AppVG.FRAME_WIDTH, AppVG.FRAME_HEIGHT);

		// RESIZABLE
		setResizable(false);

		// LAYOUT MANAGER
		setLayout(null);

		// LOCATION CENTER OF THE SCREEN
		setLocationRelativeTo(null);

		// ON CLOSE
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GET PANEL CURRENT WindowCode
		Container container = getContentPane();

		// BACK BUTTON
		back_button.setOpaque(true);
		back_button.setBackground(Color.WHITE);
		back_button.setFont(button_font);
		back_button.setBounds(755, 5, 165, 45);
		back_button.addActionListener(this);
		container.add(back_button);

		// FONT OF THE WINDOW
		container.setFont(monospaced);
		label_vhdl_code.setFont(button_font);

		// BACKGROUND COLOR
		container.setBackground(Color.decode(bg_color));

		// ICON OF THE FRAME
		setIconImage(AppVG.icon.getImage());

		// TOOL TIPS
		file_path.setToolTipText(
				"<html><span style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';> <b>Current Working directory if not specific path</b> Or <b>Full path to the file</b> <br>  On <b>Windows</b>, use: <b>\\</b> to specify the path of the file if the file already exists, it would ask you to replace it <br><b>FIELDS CANNOT BE EMPTY</b> </span>");

		entity_name.setToolTipText("<html><span> <b>entity name<b><br><b>FIELDS CANNOT BE EMPTY</b></span>");

		generic.setToolTipText(
				"<html><span style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';>the size of your array data type (example :  <b>DataWidth;8</b>) means <br> <font color='blue'><b>generic</b></font>( DataWidth : <font color='#39c1ea'><b>integer</b> </font>:= <font color='#6400c8'>8</font>)</span><br><b>FIELDS CAN BE EMPTY</b>");

		input_signals.setToolTipText(
				"<html><span style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';>the input signals separated with (semicolon) ; <b>input1;input2</b> means <br> input1 : <font color='blue'><b>IN</b></font> ; input2 : <font color='blue'><b>IN</b></font><b></span><br><b>FIELDS CANNOT BE EMPTY</b>");

		data_type_in.setToolTipText(
				"<html><span style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';>the data type of the input signals separated with <b>(semicolon)</b> ; in order  of the input signals <b>std_logic;std_logic_vector<b> means <br> input1 :<font color='blue'><b>IN</b></font> <font color='#39c1ea'><b>std_logic</b></font>; input2 : <font color='blue'><b>IN </b></font><font color='#39c1ea'><b>std_logic_vector</b></font>( (DataWidth - <font color='#6400c8'>1</font>) <font color='blue'><b>downto</b></font> <font color='#6400c8't>0</font> )</span><br><b>FIELDS CANNOT BE EMPTY</b>");

		output_signals.setToolTipText(
				"<html><span style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';>the out signals separated with (semicolon) ; <b>output1;output2</b> means <br> <b>output1 :  <font color='blue'><b>OUT</b></font> ; ouput2 : <font color='blue'><b>OUT</b></font> <b> </span><br><b>FIELDS CANNOT BE EMPTY</b>");

		data_type_out.setToolTipText(
				"<html><span style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';>the data type of the output signals separated with (semicolon) ; in order  of the output signals <b>std_logic;std_logic_vector<b> means <br> <b>output1 :  <font color='blue'><b>OUT</b></font> <font color='#39c1ea'><b>std_logic</b></font>; output2 : <font color='blue'><b>OUT</b></font> <font color='#39c1ea'><b>std_logic_vector</b></font> ( (DataWidth - <font color='#6400c8'>1</font>) downto <font color='#6400c8'>0</font>)<b> </span><br><b>FIELDS CANNOT BE EMPTY</b>");

		label_vhdl_code.setToolTipText(
				"<html><p style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';>write your VHDL code implementation(this field can be empty) example <br/>  <b> <font color='blue'>if rising_edge</font>(clk) <font color='blue'>then</font></b> <br/><b>output &lt;=</b> <b><font color='red'>not</font></b> <b>(input)<br/><font color='blue'>end if </font>;</b></p>");

		// JLABEL CHANGE TEXT COLOR
		changeColorLabel(field_color);

		// JTEXTFIELD CHANGE FONT
		changeFontFields(monospaced);

		/*
		 * setBounds(X_AXIS,Y_AXIS, WIDTH , HEIGHT)
		 */

		// POSITION OF INPUT PATH FILE
		label_file_path.setBounds(ALIGN_X_AXIS, 1, LABEL_WIDTH, LABEL_HEIGTH);
		file_path.setBounds(ALIGN_X_AXIS, 25, INPUT_WIDTH, INPUT_HEIGTH);
		container.add(label_file_path);
		container.add(file_path);

		// ADD BUTTON BROWSER
		button_browse.setBounds(INPUT_WIDTH + 20, 25, 115, 25);
		button_browse.setFont(button_font);
		button_browse.setBackground(Color.decode("#87CEFA")); // LIGHT SKY BLUE
		container.add(button_browse);

		// POSITION OF ENTITY
		label_entity_name.setBounds(ALIGN_X_AXIS, 45, LABEL_WIDTH, LABEL_HEIGTH);
		entity_name.setBounds(ALIGN_X_AXIS, 68, INPUT_WIDTH, INPUT_HEIGTH);
		container.add(label_entity_name);
		container.add(entity_name);

		// POSITION OF GENERIC
		label_generic.setBounds(ALIGN_X_AXIS, 88, LABEL_WIDTH, LABEL_HEIGTH);
		generic.setBounds(ALIGN_X_AXIS, 116, INPUT_WIDTH, INPUT_HEIGTH);
		container.add(label_generic);
		container.add(generic);

		// POSITON OF INPUT SIGNALS
		label_input_signal.setBounds(ALIGN_X_AXIS, 140, LABEL_WIDTH, LABEL_HEIGTH);
		input_signals.setBounds(ALIGN_X_AXIS, 165, INPUT_WIDTH, INPUT_HEIGTH);
		container.add(label_input_signal);
		container.add(input_signals);

		// POSITION OF INPUT DATA TYPES
		label_data_type_input.setBounds(ALIGN_X_AXIS, 185, LABEL_WIDTH, LABEL_HEIGTH);
		data_type_in.setForeground(Color.decode(data_type_color)); // highlight data type
		data_type_in.setBounds(ALIGN_X_AXIS, 210, INPUT_WIDTH, INPUT_HEIGTH);

		// JCOMBOX LIST IN
		data_type_list_in.setForeground(Color.decode(data_type_color));
		data_type_list_in.setFont(monospaced);
		data_type_list_in.setBounds(520, 210, 175, 25);
		container.add(label_data_type_input);
		container.add(data_type_in);
		container.add(data_type_list_in);

		// POSITON OF OUTPUT SIGNALS
		label_ouput_signal.setBounds(ALIGN_X_AXIS, 235, LABEL_WIDTH, LABEL_HEIGTH);
		output_signals.setBounds(ALIGN_X_AXIS, 260, INPUT_WIDTH, INPUT_HEIGTH);
		container.add(label_ouput_signal);
		container.add(output_signals);

		// POSITION OF OUPUT DATA TYPES
		label_data_type_output.setBounds(ALIGN_X_AXIS, 280, LABEL_WIDTH, LABEL_HEIGTH);
		data_type_out.setForeground(Color.decode(data_type_color));// highlight data type
		data_type_out.setBounds(ALIGN_X_AXIS, 308, INPUT_WIDTH, INPUT_HEIGTH);

		// JCOMBOX LIST OUT
		data_type_list_out.setForeground(Color.decode(data_type_color));
		data_type_list_out.setFont(monospaced);
		data_type_list_out.setBounds(520, 308, 175, 25);
		container.add(label_data_type_output);
		container.add(data_type_out);
		data_type_list_out.setSelectedIndex(1); // show first option std_logic_vector rather than std_logic
		container.add(data_type_list_out);

		// TEXT EDITOR
		editor = new Editor(EDITOR_WIDTH, EDITOR_HEIGHT);

		editor.setLocation(ALIGN_X_AXIS, 340);
		container.add(editor);

		// LABEL VHDL CODE
		label_vhdl_code.setBounds(470, 400, LABEL_WIDTH, LABEL_HEIGTH);
		container.add(label_vhdl_code);

		// GENERATE BUTTON
		button_generate.setBounds(AppVG.FRAME_WIDTH / 3, 500, 180, 50); // position middle of the frame
		button_generate.setBackground(Color.decode("#7CFC00")); // LAWN GREEN
		button_generate.setFont(button_font);
		container.add(button_generate);

		// EVENT MANAGER
		button_browse.addActionListener(this);
		button_generate.addActionListener(this);
		data_type_list_in.addActionListener(this);
		data_type_list_out.addActionListener(this);

	}
	/**
	 * <b>for testing purpose</b>
	 * @param value the new text value for the field
	 */
	public void setGeneric(String value)
	{
		this.generic.setText(value);
	}
	// GETTERS
	/**
	 * return the file_path
	 *
	 * @return the value of file_path
	 */
	public String getFilePath()
	{
		return this.file_path.getText();
	}

	/**
	 * Get all the information about the VHDL code
	 *
	 * @return the Port information
	 */
	public Port getPort()
	{
		return this.info_interface;
	}

	/**
	 * check if a Component Swing value is <code>null</code> <b>a Swing Component is
	 * <code>null</code> when the text of the component is of length zero</b>
	 *
	 * @param str to test return <code>true</code> if <code>null</code> else
	 *                <code>false</code>
	 * @return true if the Component is null otherwise false
	 */
	private boolean isNull(final String str)
	{
		return str.length() == 0;
	}

	/**
	 * this method changes the font of the JTextField the default Font is
	 * Monospaced,ITALIC,14 see {@link WindowCode#monospaced} you can change the
	 * Font example:
	 *
	 * <pre>
	 * Font font = new Font("SansSerif", FONT.ITALIC, 14); // (Font,Style,size)
	 * changeFontFields(font);
	 * </pre>
	 *
	 * @see java.awt.Font
	 * @param font the font to apply
	 *
	 */
	private void changeFontFields(final Font font)
	{
		if (font == null)
		{
			return;
		}
		file_path.setFont(font);
		entity_name.setFont(font);
		generic.setFont(font);
		input_signals.setFont(font);
		output_signals.setFont(font);
		data_type_in.setFont(font);
		data_type_out.setFont(font);
		data_type_list_in.setFont(font);
		data_type_list_out.setFont(font);
	}

	/**
	 * this method changes the color of the JTextField Label the color code is a
	 * String Hexadecimal starting with # example :
	 *
	 * <pre>
	 * 	String black = #000000 ;
	 * changeColorLabel(black);
	 * </pre>
	 *
	 * @param color the new color to apply
	 */
	private void changeColorLabel(final String color)
	{
		if (color == null)
		{
			return;
		}
		// STEEL BLUE
		label_file_path.setForeground(Color.decode(color));
		label_entity_name.setForeground(Color.decode(color));
		label_generic.setForeground(Color.decode(color));
		label_input_signal.setForeground(Color.decode(color));
		label_ouput_signal.setForeground(Color.decode(color));
		label_data_type_input.setForeground(Color.decode(color));
		label_data_type_output.setForeground(Color.decode(color));
	}

	/**
	 * this method open a frame with a error_msg.
	 *
	 * @param error_msg the error message to display
	 */
	public static void errorFrame(final String error_msg)
	{
		if (error_msg == null)
		{
			JOptionPane.showMessageDialog(null, "passing error_msg with value null ", TITLE_ERROR,
					JOptionPane.ERROR_MESSAGE);
		} else
		{

			JEditorPane message = new JEditorPane();
			message.setContentType("text/html");
			message.setText(error_msg);
			JOptionPane pane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
			JDialog dialog = pane.createDialog(null, TITLE_ERROR);
			dialog.setVisible(true);
		}

	}

	/**
	 * show a Frame with a message.
	 *
	 * @param msg_success the successful message to display into the created Frame
	 * @return return the Frame created
	 */
	public static JOptionPane successFrame(String msg_success)
	{
		// Confirmation CODE GENERATED
		JEditorPane message = new JEditorPane();
		message.setContentType("text/html");
		message.setText(msg_success);
		JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(null, TITLE_GENERATED);
		dialog.setVisible(true);
		return pane;
	}

	/**
	 * this method check if the File path given is valid
	 *
	 * @param path to the file
	 * @return <code>true</code> if the path is valid else <code>false</code>
	 * @exception java.nio.file.InvalidPathException when the path can't be
	 *                                                   check(the path string
	 *                                                   contains invalid
	 *                                                   characters, or path string
	 *                                                   is invalid for other file
	 *                                                   system specific reasons)
	 * @exception NullPointerException               if the path is null.
	 */
	public static boolean validateFilePath(final String path) throws NullPointerException
	{
		/*
		 * in a Swing Component null is when the text of the component is of length zero
		 */
		if (path != null && path.length() != 0) // is not null
		{
			try
			{
				Paths.get(path);
				return true;
			} catch (InvalidPathException e)
			{

				return false;
			}
		} else
		{
			throw new NullPointerException("the value of path is null");
		}

	}

	/**
	 * this method check if the identifier of a VHDL object is valid, <b>Basic
	 * identifiers consist of letters, underscores and digits and must start with a
	 * letter!</b>
	 *
	 * @param identifier to validate
	 * @return <code>true</code> if the identifier is valid else <code>false</code>
	 */
	public boolean validateIdentifier(final String identifier)
	{
		// regex to match Valid name of identifier in VHDL
		String pattern = "[^_0-9][A-Za-z]*(_?[0-9A-Za-z])*";
		return Pattern.matches(pattern, identifier); // not matching pattern
	}

	/**
	 * this method check if a input matches the current pattern (regular expression)
	 *
	 * @param pattern (regular expression) to matches
	 * @param input   String to check if input matches pattern
	 * @return <code>true</code> if the pattern and the input matches otherwise
	 *         <code>false</code>
	 */
	public boolean checkFormatInput(final String pattern, final String input)
	{
		return Pattern.matches(pattern, input); // not matching pattern
	}

	/**
	 * this method validate the input entity name, check if the entity name given by
	 * the user is a valid VHDL identifier
	 * {@link WindowCode#validateIdentifier(String)} if the input is Valid then we
	 * <b>set</b> our info_interface @see Port#entity_name otherwise a error message
	 * will be Display.
	 *
	 * @return <code>true</code> if the input is Valid otherwise <code>false</code>
	 */
	public boolean validateEntityName()
	{

		if (isNull(entity_name.getText())) // get entity name
		{
			// entity name is null
			errorFrame(NULL_ENTITY);

		} else
		{
			// valid identifier
			if (validateIdentifier(entity_name.getText()))
			{
				// get information ENTITY NAME
				info_interface.setNameEntity(entity_name.getText());
				return true;
			} else
			{
				errorFrame(INVALID_IDENTIFIER);
			}
		}

		return false;
	}

	/**
	 * this method validate the generic map constant input if the generic constant
	 * matches the require format, then we <b>set</b> our info_interface
	 * {@link Port#generic_map} variable otherwise a error message is Displayed on
	 * the screen.
	 *
	 * <u>Note</u>: <br>
	 * <b>check {@link WindowCode#generic} value is not <code>null</code> before
	 * calling the method otherwise a <code>NullPointerException</code> is
	 * throw</b>.
	 *
	 * @return <code>true</code> if the generic constant is valid otherwise
	 *         <code>false</code>
	 */
	public boolean validateGeneric()
	{

		String pattern = "[A-Za-z]+[0-9]*[;]\\d+";

		if (checkFormatInput(pattern, generic.getText()))
		{
			info_interface.setGenericMap(generic.getText());
			return true;
		} else
		{
			errorFrame(INVALID_GENERIC_FORMAT);
		}

		return false;
	}

	/**
	 * this method validate if the input signal given is valid if the input format
	 * is valid then we <b>set</b> the info_interface see @link{Port#input_port}
	 * member otherwise a error message is Displayed
	 *
	 * see {@link WindowCode#SIGNAL_NAME_FORMAT} for a valid pattern
	 *
	 * @return <code>true</code> if the input signal matches the correct format
	 *         otherwise <code>false</code>
	 */
	private boolean validateInputSignal()
	{

		if (!(isNull(input_signals.getText())))
		{
			String signals = input_signals.getText();

			if (checkFormatInput(SIGNAL_NAME_FORMAT, signals))
			{
				info_interface.setInputPort(signals);
				return true;
			} else
			{
				errorFrame(NOT_MATCHING_SIGNAL_FORMAT_IN);
			}

		}
		return false;
	}

	/**
	 * this method validate if the output signal is valid if the output format is
	 * valid then we <b>set</b> the info_interface
	 *
	 * @see Port#output_port otherwise a error message is Displayed
	 *
	 *      see {@link WindowCode#SIGNAL_NAME_FORMAT} for a valid pattern
	 *
	 * @return <code>true</code> if the output signal matches the correct format
	 *         otherwise <code>false</code>
	 */
	private boolean validateOutputSignal()
	{

		if (!(isNull(output_signals.getText())))
		{
			String signals = output_signals.getText();

			if (checkFormatInput(SIGNAL_NAME_FORMAT, signals))
			{
				info_interface.setOutputPort(signals);
				return true;
			} else
			{
				errorFrame(NOT_MATCHING_SIGNAL_FORMAT_OUT);
			}

		}

		return false;

	}

	/**
	 * this method check if the input data type matches the format if the data type
	 * matches the right format then info_interface value is <b>set</b>
	 *
	 * see (@link WindowCode#SIGNAL_DATA_FORMAT}
	 *
	 * @see Port#in_data_type otherwise it will display a error message.
	 *
	 * @return <code>true</code> if the data type matches the right format otherwise
	 *         <code>false</code>
	 */
	public boolean validateDataInSignal()
	{

		if (!(isNull(data_type_in.getText())))
		{
			String data_type = data_type_in.getText();

			if (checkFormatInput(SIGNAL_DATA_FORMAT, data_type))
			{
				info_interface.setInDataType(data_type);
				return true;
			} else
			{
				errorFrame(NOT_MATCHING_DATA_TYPE);
			}

		}
		return false;
	}

	/**
	 * this method check if the output data type matches the good format if the data
	 * type matches the format then info_interface value is <b>set</b> @see see
	 * (@link WindowCode#SIGNAL_DATA_FORMAT} Port#out_data_type otherwise will
	 * display a error message
	 *
	 * @return <code>true</code> if the data type output matches the right format
	 *         otherwise <code>false</code>
	 */
	public boolean validateDataOutSignal()
	{

		if (!(isNull(data_type_out.getText())))
		{
			String data_type = data_type_out.getText();

			if (checkFormatInput(SIGNAL_DATA_FORMAT, data_type))
			{
				info_interface.setOutDataType(data_type);
				return true;
			} else
			{
				errorFrame(NOT_MATCHING_DATA_TYPE);
			}

		}
		return false;
	}

	/**
	 * this method check if the number of input signal and the number of output
	 * signal is equal to see {@link Port}
	 *
	 * @return <code>true</code> if it is the same number otherwise
	 *         <code>false</code>
	 */
	public boolean equalNumberSignalData()
	{
		int number_of_input_signal = info_interface.getInputPort().split(FileGenerator.SEMICOLON).length;
		int number_in_data_signal = info_interface.getInDataType().split(FileGenerator.SEMICOLON).length;
		int number_of_output_signal = info_interface.getOutputPort().split(FileGenerator.SEMICOLON).length;
		int number_out_data_signal = info_interface.getOutDataType().split(FileGenerator.SEMICOLON).length;

		return number_in_data_signal == number_of_input_signal && number_out_data_signal == number_of_output_signal;
	}

	/**
	 * this method verify if all the information given by the user is correct then
	 * generate the file.
	 */
	private void generate()
	{
		//
		// CHECK THE VALIDITY OF ALL REQUIRED FIELDS TO THEN GENERATE THE FILE
		//

		if (validateEntityName() && validateInputSignal() && validateOutputSignal() && validateDataInSignal()
				&& validateDataOutSignal())
		{
			// check generic constant is a valid pattern
			if (!(isNull(generic.getText())) && !validateGeneric())
			{
				return;
			}
			// check if the number of variable match the number of data type.
			if (!equalNumberSignalData())
			{
				errorFrame(NOT_MATCHING_NUMBER_SIGNAL_DATA_TYPE);
				return;
			}
			//
			// CODE IMPLEMENTATION
			//
			if (!isNull(editor.getCode()))
			{

				info_interface.setCodeImplementation(editor.getCode());
			}
			//
			// GENERATE FILE
			//

			try
			{
				// open the file to generated
				boolean open = FileGenerator.openFile(file_path.getText());
				// the file that has been generated
				FileGenerator genFile;

				if (open)
				{
					genFile = new FileGenerator(this.getFilePath(), this.getPort());

					// Write Data to the file.
					genFile.writeHDL();

					// GENERATED.
					JOptionPane pane = successFrame(GENERATED);

					Object selectedValue = pane.getValue();

					if (selectedValue instanceof Integer)
					{

						int value = ((Integer) selectedValue);

						if (value == JOptionPane.YES_OPTION || value == JOptionPane.CANCEL_OPTION
								|| value == JOptionPane.CLOSED_OPTION)
						{

							// ASK TO GENERATE TESTBENCH FILE
							int choice = JOptionPane.showConfirmDialog(null, GENERATE_TB);

							// GENERATED TESTBENCH
							if (choice == JOptionPane.YES_OPTION)
							{

								Testbench testbench = new Testbench(genFile);
								// WRITE TESTBENCH CODE
								testbench.writeTB();

								// GENERATED!!!
								pane = successFrame(GENERATED_TB);

								value = ((Integer) pane.getValue());

								if (value == JOptionPane.YES_OPTION || value == JOptionPane.CANCEL_OPTION
										|| value == JOptionPane.CLOSED_OPTION)
								{

									// OPEN GENERATED FILES
									// ASK TO OPEN THE FILE(VHDL file) GENERATED
									choice = JOptionPane.showConfirmDialog(null, OPEN_FILE);

									if (choice == JOptionPane.YES_OPTION)
									{
										// ASK TO OPEN THE TESTBENCH FILE
										choice = JOptionPane.showConfirmDialog(null, OPEN_TESTBENCH);

										if (choice == JOptionPane.YES_OPTION)
										{
											showFile(this.getFilePath());
											showFile(testbench.getFilePath());

										}

									}

								}

							} else
							{
								// OPEN ONLY THE VHDL FILE GENERATED
								showFile(this.getFilePath());
							}

						}
					}

				}

			} catch (NullPointerException | IOException e)
			{

				WindowCode.errorFrame(ERROR_MSG_OPEN);
			}

		}
	}

	/**
	 * open the File manager Explorer to choose a file or directory.
	 *
	 * @param parent the component that the File Manager depend on for
	 *                   LookAndFeel,positioning
	 * @param title  of the FileChooser.
	 * @param folder if <code>true</code> need to select a folder otherwise a file.
	 * @return the file or the directory selected otherwise return null.
	 */
	public static File chooseFile(Component parent, String title, boolean folder)
	{
		// file selected
		File file = null;
		// choose file
		JFileChooser files = new JFileChooser(new File("."));
		// selection between folder or file
		if (folder)
		{
			files.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			files.setToolTipText("select folder");

		}
		// title of the FileChooser
		if (title == null)
		{
			files.setDialogTitle(SELECT_FILE);
		} else
		{
			files.setDialogTitle(title);
		}

		files.setApproveButtonText(SELECT);
		int choice = files.showOpenDialog(parent);

		if (choice == JFileChooser.APPROVE_OPTION)
		{
			file = files.getSelectedFile();
		}

		return file;
	}

	/**
	 * this method open a file with the default Application on the system. if
	 * there's no default application associated with the file then the program will
	 * try to open the file Directory if a error occur a error message is generated
	 * on the screen.
	 *
	 * @param file_path the path of the file to open
	 *
	 * @exception NullPointerException if file_path is null.
	 */
	public static void showFile(String file_path)
	{
		Objects.requireNonNull(file_path, FileGenerator.NULL_VALUE);

		// check if the Desktop class is supported on the current platform
		if (Desktop.isDesktopSupported())
		{
			// return a instance of the current desktop context
			Desktop desk = Desktop.getDesktop();

			// check if opening a file is supported
			if (desk.isSupported(Desktop.Action.OPEN))
			{
				try
				{
					// trying to open the file
					desk.open(new File(file_path));

				} catch (IOException e)
				{

					errorFrame(NO_OPEN_FILE);

				}
			}
		}

	}

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		//
		// BUTTON GO BACK HOME
		//
		if (event.getSource() == back_button)
		{
			this.dispose();
			new AppVG().startApp();
		}
		// BROWSING FILE BUTTON
		//
		if (event.getSource() == button_browse)
		{

			File file = chooseFile(this, null, false);
			if (file != null)
			{
				String path = file.getPath();
				if (!(path.endsWith(".vhdl") && path.endsWith(".vhd")))
				{
					// add filename extension
					path = path + ".vhdl";
				}
				try
				{
					// validate File path
					if (validateFilePath(path))
					{
						file_path.setText(path);
					}

				} catch (NullPointerException e)
				{
					errorFrame(NULL_FILE_PATH);
				}
			}

		}
		//
		// SELECTING DATA TYPE
		//
		if (event.getSource() == data_type_list_in)
		{
			StringBuilder in = new StringBuilder();
			
			if (!isNull(data_type_in.getText()))
			{
				in.append(data_type_in.getText());
				in.append((String) data_type_list_in.getSelectedItem());
				in.append(FileGenerator.SEMICOLON);

			} else
			{
				in.append((String) data_type_list_in.getSelectedItem());
				in.append(FileGenerator.SEMICOLON);
			}

			data_type_in.setText(in.toString());
		}
		if (event.getSource() == data_type_list_out)
		{
			StringBuilder out = new StringBuilder();
			if (!isNull(data_type_out.getText()))
			{
				out.append(data_type_out.getText());
				out.append((String) data_type_list_out.getSelectedItem());
				out.append(FileGenerator.SEMICOLON);
			} else
			{

				out.append((String) data_type_list_out.getSelectedItem());
				out.append(FileGenerator.SEMICOLON);
			}

			data_type_out.setText(out.toString());
		}

		//
		// GENERATE FILE BUTTON
		//
		if (event.getSource() == button_generate)
		{

			if (isNull(file_path.getText()) || isNull(entity_name.getText()) || isNull(input_signals.getText())
					|| isNull(data_type_in.getText()) || isNull(data_type_out.getText()))
			{
				// all fields are NULL/EMPTY
				errorFrame(NULL_VALUES);
			} else
			{
				//
				// Path file
				if (isNull(file_path.getText()))
				{
					// only file_path is empty
					errorFrame(INVALID_FILE_PATH);
				}

				try
				{

					if (validateFilePath(file_path.getText()))
					{
						// Generate file
						generate();
					}
				} catch (NullPointerException e)
				{
					errorFrame(INVALID_FILE_PATH);
				}

			}
		}
	}

}
