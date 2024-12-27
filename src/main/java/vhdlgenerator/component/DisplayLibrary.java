/**
 * @file DisplayLibrary.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.1
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 *
 */
package vhdlgenerator.component;

import static vhdlgenerator.component.Library.ADDER;
import static vhdlgenerator.component.Library.COMPARATOR;
import static vhdlgenerator.component.Library.DECODER;
import static vhdlgenerator.component.Library.DEMULTIPLEXER;
import static vhdlgenerator.component.Library.DFILPFLOP;
import static vhdlgenerator.component.Library.ENCODER;
import static vhdlgenerator.component.Library.JKFLIPFLOP;
import static vhdlgenerator.component.Library.MULTIPLEXER;
import static vhdlgenerator.component.Library.PRIORITY_ENCODER;
import static vhdlgenerator.component.Library.SEVEN_SEGMENT;
import static vhdlgenerator.component.Library.SUBTRACTOR;
import static vhdlgenerator.component.Library.TFLIPFLOP;

// java awt package
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//java swing package
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

// vhdlgenerator package
import vhdlgenerator.application.AppVG;
import vhdlgenerator.component.adder.Adder;
import vhdlgenerator.component.comparator.Comparator;
import vhdlgenerator.component.decoder.Decoder;
import vhdlgenerator.component.demultiplexer.Demux;
import vhdlgenerator.component.encoder.Encoder;
import vhdlgenerator.component.flipflop.DFlipFlop;
import vhdlgenerator.component.flipflop.JKFlipFlop;
import vhdlgenerator.component.flipflop.TFlipFlop;
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
public final class DisplayLibrary extends JFrame implements ActionListener
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
	 * code generated successfully
	 */
	public static final String GENERATED_COMPONENT = "<html><span style=color:'green';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>COMPONENT GENERATED SUCCESSFULLY!</span></html>";

	/**
	 * library of VHDL component available in VHDL
	 */
	private static final JButton[] library_button = { new JButton(MULTIPLEXER), new JButton(DEMULTIPLEXER),
			new JButton(ENCODER), new JButton(PRIORITY_ENCODER), new JButton(DECODER), new JButton(SEVEN_SEGMENT),
			new JButton(ADDER), new JButton(SUBTRACTOR), new JButton(COMPARATOR), new JButton(DFILPFLOP),
			new JButton(JKFLIPFLOP), new JButton(TFLIPFLOP) };

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
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		if (action.equals(MULTIPLEXER))
		{
			// Mux size choice
			String mux_size = (String) JOptionPane.showInputDialog(this,
					"choose the number of inputs of the Multiplexer:", MULTIPLEXER, JOptionPane.INFORMATION_MESSAGE,
					null, Mux.possibleValues, Mux.possibleValues[0]);

			if (mux_size != null)
			{
				// choosing directory
				String path = ComponentUtil.chooseFolder(this);
				if (path != null)
				{
					// generate Mux VHDL code.
					Mux.writeMux(mux_size, path);
					// open file
					ComponentUtil.openFileGenerated(path);
				}

			}
		}
		//
		// Demultiplexer
		//
		if (action.equals(DEMULTIPLEXER))
		{
			// Demux size choice
			String demux_size = (String) JOptionPane.showInputDialog(this,
					"choose the number of outputs of the Demultiplexer:", DEMULTIPLEXER,
					JOptionPane.INFORMATION_MESSAGE, null, Demux.possibleValues, Demux.possibleValues[0]);

			if (demux_size != null)
			{ // choosing folder
				String path = ComponentUtil.chooseFolder(this);

				if (path != null)
				{
					// generate Demux VHDL code.
					Demux.writeDemux(demux_size, path);
					// open file
					ComponentUtil.openFileGenerated(path);
				}

			}

		}
		//
		// Decoder
		//
		if (action.equals(DECODER))
		{
			// Decoder size
			String decoder_size = (String) JOptionPane.showInputDialog(this, "choose input size of the Decoder:",
					"Decoder", JOptionPane.INFORMATION_MESSAGE, null, Decoder.possibleValues,
					Decoder.possibleValues[0]);

			if (decoder_size != null)
			{
				// choosing directory
				String path = ComponentUtil.chooseFolder(this);
				if (path != null)
				{
					// generate file
					Decoder.writeDecoder(decoder_size, path);
					// open file
					ComponentUtil.openFileGenerated(path);
				}

			}

		}
		//
		// segment 7 Display
		//
		if (action.equals(SEVEN_SEGMENT))
		{
			String path = ComponentUtil.chooseFolder(this);

			if (path != null)
			{
				Segment7.writeSegment7(path);
				ComponentUtil.openFileGenerated(path);
			}
		}
		//
		// D flip flop
		//
		if (action.equals(DFILPFLOP))
		{
			// choosing option
			String dff = (String) JOptionPane.showInputDialog(this, "choose D-Flip-Flop type:", DFILPFLOP,
					JOptionPane.INFORMATION_MESSAGE, null, DFlipFlop.possibleValues, DFlipFlop.possibleValues[0]);

			if (dff != null)
			{
				// choosing directory
				String path = ComponentUtil.chooseFolder(this);
				if (path != null)
				{
					// generate file
					DFlipFlop.writeDFlipFlop(dff, path);
					// open file
					ComponentUtil.openFileGenerated(path);
				}
			}

		}
		//
		// JK Flip Flop
		if (action.equals(JKFLIPFLOP))
		{

			String path = ComponentUtil.chooseFolder(this);
			if (path != null)
			{
				JKFlipFlop.writeJK(path);
				ComponentUtil.openFileGenerated(path);
			}

		}
		//
		// T FLIP FLOP
		//
		//
		if (action.equals(TFLIPFLOP))
		{
			// choosing option
			String tff = (String) JOptionPane.showInputDialog(this, "choose T-Flip-Flop type:", TFLIPFLOP,
					JOptionPane.INFORMATION_MESSAGE, null, TFlipFlop.possibleValues, TFlipFlop.possibleValues[0]);

			if (tff != null)
			{
				// choosing directory
				String path = ComponentUtil.chooseFolder(this);

				if (path != null)
				{
					// generate file
					TFlipFlop.writeTFlipFlop(tff, path);
					// open file
					ComponentUtil.openFileGenerated(path);
				}
			}

		}
		// Encoder
		//
		if (action.equals(ENCODER))
		{
			String input_size = (String) JOptionPane.showInputDialog(this, "choose your input size:", "Encoder",
					JOptionPane.INFORMATION_MESSAGE, null, Encoder.possibleValues, Encoder.possibleValues[0]);

			String path = ComponentUtil.chooseFolder(this);

			if (input_size != null)
			{
				// generated file
				Encoder.writeEncoder(input_size, path);
				ComponentUtil.openFileGenerated(path);

			}
		}
		//
		// Priority Encoder
		//
		if (action.equals(PRIORITY_ENCODER))
		{
			String input_size = (String) JOptionPane.showInputDialog(this, "choose your input size:", "PriorityEncoder",
					JOptionPane.INFORMATION_MESSAGE, null, PriorityEncoder.possibleValues,
					PriorityEncoder.possibleValues[0]);
			String path = ComponentUtil.chooseFolder(this);

			if (input_size != null && path != null)
			{
				PriorityEncoder.writePriorityEncoder(input_size, path);
				ComponentUtil.openFileGenerated(path);
			}
		}
		//
		// Adder
		//
		if (action.equals(ADDER))
		{
			String adder_type = (String) JOptionPane.showInputDialog(this, "choose your " + ADDER + " type:", ADDER,
					JOptionPane.INFORMATION_MESSAGE, null, Adder.possibleValues, Adder.possibleValues[0]);

			String path = ComponentUtil.chooseFolder(null);

			if (adder_type != null && path != null)
			{
				Adder.writeAdder(adder_type, path);
				ComponentUtil.openFileGenerated(path);
			}

		}
		//
		// Subtractor
		//
		if (action.equals(SUBTRACTOR))
		{
			String type = (String) JOptionPane.showInputDialog(this, "choose your " + SUBTRACTOR + " type:", SUBTRACTOR,
					JOptionPane.INFORMATION_MESSAGE, null, Subtractor.possibleValues, Subtractor.possibleValues[0]);

			String path = ComponentUtil.chooseFolder(this);

			if (type != null && path != null)
			{
				Subtractor.writeSubtractor(type, path);
				ComponentUtil.openFileGenerated(path);
			}
		}
		//
		// Comparator
		//
		if (action.equals(COMPARATOR))
		{
			// choose option
			String type = (String) JOptionPane.showInputDialog(this, "choose your " + COMPARATOR + " type:", COMPARATOR,
					JOptionPane.INFORMATION_MESSAGE, null, Comparator.possibleValues, Comparator.possibleValues[0]);

			if (type != null)
			{
				// choosing folder
				String path = ComponentUtil.chooseFolder(this);
				if (path != null)
				{
					// generated code
					Comparator.writeComparator(type, path);
					// open file
					ComponentUtil.openFileGenerated(path);
				}

			}
		}

	}

}
