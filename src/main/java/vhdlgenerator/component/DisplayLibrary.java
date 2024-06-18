/**
 * @file DisplayLibrary.java
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
package vhdlgenerator.component;

// java awt package
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//java swing package
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vhdlgenerator.application.AppVG;
import vhdlgenerator.component.adder.Adder;
import vhdlgenerator.component.comparator.Comparator;
import vhdlgenerator.component.decoder.Decoder;
import vhdlgenerator.component.demultiplexer.Demux;
import vhdlgenerator.component.encoder.Encoder;
import vhdlgenerator.component.flipflop.DFlipFlop;
import vhdlgenerator.component.flipflop.JKFlipFlop;
import vhdlgenerator.component.multiplexer.Mux;
import vhdlgenerator.component.priorityencoder.PriorityEncoder;
import vhdlgenerator.component.segment7.Segment7;
import vhdlgenerator.component.subtractor.Subtractor;
import vhdlgenerator.generator.WindowCode;

/**
 * this class display the component Library
 *
 * @author DOUDOU DIAWARA
 */
@SuppressWarnings("serial")
public class DisplayLibrary extends JFrame implements ActionListener
{
	//
	// PRIVATE
	//

	/**
	 * Frame title component
	 */
	private static final String TITLE_FRAME = "GENERATE COMPONENT";
	/**
	 * Button width
	 */
	private static final int BUTTON_WIDTH = 180;
	/**
	 * Button height
	 */
	private static final int BUTTON_HEIGHT = 75;
	/**
	 * back button to go home
	 */
	private final JButton back_button = new JButton("Go Back Home", WindowCode.icon_back_button);

	//
	// PUBLIC
	//
	/**
	 * System separator file path On Windows OS "backslashes"(//) are used to
	 * separate directories on Linux/MAC OS "anti-slashes"(\\)
	 */
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	/**
	 * title select a directory
	 */
	public static final String SELECT_FOLDER = "<html><b>SELECT A DIRECTORY</b></html>";
	/**
	 * code generated successfully
	 */
	public static final String GENERATED_COMPONENT = "<html><span style=color:'green';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>COMPONENT GENERATED SUCCESSFULLY!</span></html>";

	/**
	 * library of VHDL component available in VHDL
	 */
	private static final JButton[] library_button = { new JButton("Multiplexer"), new JButton("Demultiplexer"),
			new JButton("Encoder"), new JButton("Priority Encoder"), new JButton("Decoder"),
			new JButton("7-Segment-Display"), new JButton("Adder"), new JButton("Subtractor"),
			new JButton("Comparator"), new JButton("D-Flip-Flop"), new JButton("JK-Flip-Flop") };

	/**
	 * Button Font
	 */
	private final Font BUTTON_FONT = new Font("ArialBlack", Font.ITALIC | Font.BOLD, 16);

	/**
	 * Constructor to show a frame of the library of Component available
	 */
	public DisplayLibrary()
	{
		// title
		super(TITLE_FRAME);
		// size frame
		setSize(AppVG.FRAME_WIDTH, AppVG.FRAME_HEIGHT);
		// no resizable frame
		setResizable(false);
		// icon
		setIconImage(AppVG.icon.getImage());

		// position center
		setLocationRelativeTo(null);
		// close operation
		setDefaultCloseOperation(DisplayLibrary.EXIT_ON_CLOSE);
		// Layout Mode
		setLayout(new BorderLayout());
		Container container = getContentPane();

		// BACK BUTTON
		back_button.setOpaque(true);
		back_button.setBackground(Color.WHITE);
		back_button.setFont(WindowCode.button_font);
		back_button.addActionListener(this);

		// BACK BUTTON PANEL
		JPanel back_panel_button = new JPanel();
		back_panel_button.setPreferredSize(new Dimension(100, 50));
		back_panel_button.setLayout(new BorderLayout());
		back_panel_button.add(back_button, BorderLayout.WEST);

		container.add(back_panel_button, BorderLayout.NORTH);

		// LIBRARY COMPONENT PANEL
		JPanel library_button_panel = new JPanel();
		library_button_panel.setLayout(new FlowLayout());

		// Add button library
		for (JButton button : library_button)
		{
			button.setFont(BUTTON_FONT);
			button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
			button.setBackground(Color.cyan);
			library_button_panel.add(button);
			// actionListener
			button.addActionListener(this);
		}

		container.add(library_button_panel, BorderLayout.CENTER);

	}

	/**
	 * this method open a Frame to select a Directory to create the VHDL component
	 * see {@link vhdlgenerator.component}
	 *
	 * @return path of the directory selected if valid otherwise <code>null</code>.
	 * @throws NullPointerException if the path selected is null.
	 */
	public static String chooseFolder() throws NullPointerException
	{
		File file = WindowCode.chooseFile(null, DisplayLibrary.SELECT_FOLDER, true);

		if (file != null && file.isDirectory())
		{
			return file.getPath();
		}

		throw new NullPointerException();
	}

	/**
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
			var app = new AppVG();
			app.setLocationRelativeTo(null);
			app.setVisible(true);
		}

		String action = event.getActionCommand();
		//
		// Multiplexer
		//
		if (action.equals("Multiplexer"))
		{
			// Mux size choice
			String mux_size = (String) JOptionPane.showInputDialog(this,
					"choose the number of inputs of the Multiplexer:", "Multiplexer", JOptionPane.INFORMATION_MESSAGE,
					null, Mux.possibleValues, Mux.possibleValues[0]);

			if (mux_size != null)
			{
				// generate Mux VHDL code.
				new Mux(Integer.parseInt(mux_size)).writeMux();

			}
		}
		//
		// Demultiplexer
		//
		if (action.equals("Demultiplexer"))
		{
			// Demux size choice
			String demux_size = (String) JOptionPane.showInputDialog(this,
					"choose the number of outputs of the Demultiplexer:", "Demultiplexer",
					JOptionPane.INFORMATION_MESSAGE, null, Demux.possibleValues, Demux.possibleValues[0]);
			if (demux_size != null)
			{
				// generate Demux VHDL code.
				new Demux(Integer.parseInt(demux_size)).writeDemux();

			}

		}
		//
		// Decoder
		//
		if (action.equals("Decoder"))
		{
			// Decoder size
			String decoder_size = (String) JOptionPane.showInputDialog(this, "choose input size of the Decoder:",
					"Decoder", JOptionPane.INFORMATION_MESSAGE, null, Decoder.possibleValues,
					Decoder.possibleValues[0]);

			if (decoder_size != null)
			{
				// decoder VHDL code.
				new Decoder(Integer.parseInt(decoder_size)).writeDecoder();

			}

		}
		//
		// segment 7 Display
		//
		if (action.equals("7-Segment-Display"))
		{
			new Segment7().writeSegment7();
		}
		//
		// flip flop
		//
		if (action.equals("D-Flip-Flop"))
		{
			String dff = (String) JOptionPane.showInputDialog(this, "choose your D-Flip-Flop type:", "D Flip-Flop",
					JOptionPane.INFORMATION_MESSAGE, null, DFlipFlop.possibleValues, DFlipFlop.possibleValues[0]);

			if (dff != null)
			{
				switch (dff)

				{
				case "D-Flip-Flop":
					new DFlipFlop(0).writeD();
					break;
				case "D-Flip-Flop-Reset":
					new DFlipFlop(1).writeD();
					break;
				case "D-Flip-Flop-Set":
					new DFlipFlop(2).writeD();
					break;
				default:
					System.err.println("Invalid Option!: " + dff + "valid option are:0,1,2");
					break;

				}
			}

		}
		//
		// JK Flip Flop
		if (action.equals("JK-Flip-Flop"))
		{
			new JKFlipFlop().writeJK();
		}
		//
		// Encoder
		//
		if (action.equals("Encoder"))
		{
			String input_size = (String) JOptionPane.showInputDialog(this, "choose your input size:", "Encoder",
					JOptionPane.INFORMATION_MESSAGE, null, Encoder.possibleValues, Encoder.possibleValues[0]);

			if (input_size != null)
			{
				switch (input_size)
				{
				case "4":
					new Encoder(4).writeEncoder();
					break;
				case "8":
					new Encoder(8).writeEncoder();
					break;
				default:
					System.err.println("Invalid Option!: " + input_size + "valid option are 4 or 8");
					break;

				}
			}
		}
		//
		// Priority Encoder
		//
		if (action.equals("Priority Encoder"))
		{
			String input_size = (String) JOptionPane.showInputDialog(this, "choose your input size:", "PriorityEncoder",
					JOptionPane.INFORMATION_MESSAGE, null, PriorityEncoder.possibleValues,
					PriorityEncoder.possibleValues[0]);

			if (input_size != null)
			{
				switch (input_size)
				{
				case "4":
					new PriorityEncoder(4).writePriorityEncoder();
					break;
				case "8":
					new PriorityEncoder(8).writePriorityEncoder();
					break;
				case "16":
					new PriorityEncoder(16).writePriorityEncoder();
					break;
				default:
					System.err.println("Invalid Option!: " + input_size + " valid options are 4 or 8 or 16");
					break;

				}

			}

		}
		//
		// Adder
		//
		if (action.equals("Adder"))
		{
			String adder_type = (String) JOptionPane.showInputDialog(this, "choose your Adder type:", "Adder",
					JOptionPane.INFORMATION_MESSAGE, null, Adder.possibleValues, Adder.possibleValues[0]);

			if (adder_type != null)
			{
				new Adder().writeAdder(adder_type);

			}

		}
		//
		// Subtractor
		//
		if (action.equals("Subtractor"))
		{
			String type = (String) JOptionPane.showInputDialog(this, "choose your Subtractor type:", "Subtractor",
					JOptionPane.INFORMATION_MESSAGE, null, Subtractor.possibleValues, Subtractor.possibleValues[0]);

			if (type != null)
			{
				new Subtractor().writeSubtractor(type);
			}
		}
		//
		// Comparator
		//
		if (action.equals("Comparator"))
		{
			String type = (String) JOptionPane.showInputDialog(this, "choose your Comparator type:", "Comparator",
					JOptionPane.INFORMATION_MESSAGE, null, Comparator.possibleValues, Comparator.possibleValues[0]);

			if (type != null)
			{
				new Comparator().writeComparator(type);
			}
		}

	}

}
