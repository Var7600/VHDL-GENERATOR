/**
 * @file AppVG.java
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
 */

package vhdlgenerator.application;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import vhdlgenerator.component.DisplayLibrary;
import vhdlgenerator.generator.WindowCode;

/**
 * this class start the welcome screen for the Application
 *
 * @author DOUDOU DIAWARA
 *
 *         the Look and Feel of the Window Frame is provided by
 *         <a href="https://www.formdev.com/flatlaf/">Themes</a> current theme
 *         is FlatLightLaf if a error occur for whatever reason than the default
 *         theme will be launch
 *
 *         LICENSE This program is free software; you can redistribute it and/or
 *         modify it under the terms of “Commons Clause” License Condition v1.0
 *         but not for Commercial use.
 *
 */
@SuppressWarnings("serial")
public class AppVG extends JFrame implements ActionListener
{

	//
	// PUBLIC CONSTANTS
	//

	/** width of the frame */
	public static final int FRAME_WIDTH = 944;
	/** height of the frame */
	public static final int FRAME_HEIGHT = 604;
	/** color button lawn green */
	public static final String COLOR_BUTTON = "#7CFC00";
	/** name of the icon image */
	public static final String ICON_IMAGE = "favicon.png";
	/** favicon/icon image */
	public static final ImageIcon icon = new ImageIcon(AppVG.class.getClassLoader().getResource(ICON_IMAGE));

	//
	// PRIVATE
	//

	/** title frame */
	private static final String TITLE = "VHDL_GENERATOR";
	/** path of the logo */
	private final String WELCOME_LOGO_PATH = "welcome-logo.png";
	/** background image */
	private final String BACKGROUND_IMAGE = "welcome-screen-background.png";
	/** font button */
	private final Font button_font = new Font("ArialBlack", Font.ITALIC | Font.BOLD, 14);
	/** button component */
	private final JButton button_component = new JButton("GENERATE COMPONENT");
	/** button to generate code */
	private final JButton button_generate_code = new JButton("GENERATE CODE");

	/**
	 * this constructor start the application
	 */

	public AppVG()
	{
		// title
		super(TITLE);
		// Layout Manager
		setLayout(null);
		// no resizable
		setResizable(false);
		// close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// size
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// icon
		setIconImage(icon.getImage());

		// BACKGROUND
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(getClass().getClassLoader().getResource(BACKGROUND_IMAGE)));
		background.setSize(FRAME_WIDTH, FRAME_HEIGHT);

		// IMAGE
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getClassLoader().getResource(WELCOME_LOGO_PATH)));
		logo.setBounds(FRAME_WIDTH / 3, 35, logo.getPreferredSize().width, logo.getPreferredSize().height);
		background.add(logo);

		// BUTTON
		button_component.setFont(button_font);
		button_component.setBackground(Color.decode(COLOR_BUTTON));
		button_component.setBounds((FRAME_WIDTH / 3) + 40, 336, 208, 50);
		button_component.addActionListener(this);
		background.add(button_component);

		button_generate_code.setFont(button_font);
		button_generate_code.setBackground(Color.decode(COLOR_BUTTON));
		button_generate_code.setBounds((FRAME_WIDTH / 3) + 40, 420, 208, 50);
		button_generate_code.addActionListener(this);
		background.add(button_generate_code);

		this.add(background);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == button_generate_code)
		{
			this.dispose();
			var window_code = new WindowCode();
			window_code.setLocationRelativeTo(null);
			window_code.setVisible(true);

		} else if (event.getSource() == button_component)
		{

			this.dispose();
			var library = new DisplayLibrary();
			library.setLocationRelativeTo(null);
			library.setVisible(true);

		}
	}

	/**
	 * this method starts the Application Main Screen And Load the default theme
	 */
	public void startApp()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);

		try
		{
			// FlatLaf Theme
			UIManager.setLookAndFeel(new FlatLightLaf());
			SwingUtilities.invokeLater(() -> {

				AppVG app = new AppVG();
				app.setLocationRelativeTo(null);
				app.setVisible(true);
			});

		} catch (Exception e)
		{

			System.err.println("Failed to initialize flatlaf theme!\n Lauching Default System theme.");
			// Default theme
			SwingUtilities.invokeLater(() -> {

				AppVG app = new AppVG();
				app.setLocationRelativeTo(null);
				app.setVisible(true);
			});

		}

	}

	/**
	 * main method
	 *
	 * @param args for the main method.
	 */
	public static void main(String[] args)
	{
		new AppVG().startApp();
	}

}
