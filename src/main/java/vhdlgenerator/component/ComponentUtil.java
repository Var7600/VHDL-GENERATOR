/**
 * @file ComponentUtil.java
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

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import vhdlgenerator.generator.FileGenerator;
import vhdlgenerator.generator.WindowCode;

/**
 * some utility method for frame/pane creation
 *
 * @author DOUDOU DIAWARA
 */
public final class ComponentUtil
{

	private ComponentUtil()
	{
	} // prevent instantiation

	/**
	 * this method open a frame with a error_msg.
	 *
	 * @param error_msg the error message to display
	 */
	public static void errorFrame(final String error_msg)
	{
		if (error_msg == null)
		{
			JOptionPane.showMessageDialog(null, "passing error_msg with value null ", "Error!",
					JOptionPane.ERROR_MESSAGE);
		} else
		{

			JEditorPane message = new JEditorPane();
			message.setContentType("text/html");
			message.setText(error_msg);
			JOptionPane pane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
			JDialog dialog = pane.createDialog(null, "Error!");
			dialog.setVisible(true);
		}

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
					errorFrame(
							"<html><span style=color:'red';font-family:'ArialBlack';font-size:'14px';font-style:'italic'>file found! but a Error occured trying to open the file! </span> </html>");
				}
			}
		}

	}

	/**
	 * open the File manager Explorer to choose a file or directory.
	 *
	 * @param parent the component that the File Manager depend on for
	 *                   LookAndFeel,positioning
	 * @param title  of the FileChooser.
	 * @return the file or the directory selected otherwise return null.
	 */
	public static File chooseFile(Component parent, String title)
	{
		// file selected
		File file = null;
		// choose file
		JFileChooser files = new JFileChooser(new File("."));

		files.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		files.setToolTipText("select folder");

		// title of the FileChooser
		if (title == null)
		{
			files.setDialogTitle("<html><b>SELECT A FILE OR TYPE THE FILE NAME AND IT WILL BE CREATED</b></html>");
		} else
		{
			files.setDialogTitle(title);
		}

		files.setApproveButtonText("Select");
		int choice = files.showOpenDialog(parent);

		if (choice == JFileChooser.APPROVE_OPTION)
		{
			file = files.getSelectedFile();
		}

		return file;
	}

	/**
	 * open the File manager Explorer to choose a file.
	 *
	 * @param parent the component that the File Manager depend on for
	 *                   LookAndFeel,positioning
	 * @return the file selected.
	 * @throws NullPointerException if the path selected is null.
	 */
	public static File chooseFile(Component parent) throws NullPointerException
	{
		// file selected
		File file = null;
		// choose file
		JFileChooser files = new JFileChooser(new File("."));
		files.setDialogTitle("<html><b>SELECT A FILE OR TYPE THE FILE NAME IT WILL BE CREATED </b></html>");
		files.setApproveButtonText("SELECT");
		files.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int choice = files.showOpenDialog(parent);

		if (choice == JFileChooser.APPROVE_OPTION)
		{
			file = files.getSelectedFile();
		}

		return file;
	}

	/**
	 * this method open a Frame to select a Directory to create the VHDL component
	 * see {@link vhdlgenerator.component.ComponentUtil}
	 *
	 * @param parent the component that the File Manager depend on for
	 *                   LookAndFeel,positioning
	 *
	 * @return path of the directory selected if valid otherwise <code>null</code>.
	 */
	public static String chooseFolder(Component parent) throws NullPointerException
	{

		// open file chooser in current working directory
		JFileChooser files = new JFileChooser(new File("."));

		files.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		files.setDialogTitle("<html><b>SELECT A DIRECTORY</b></html>");
		files.setToolTipText("select folder");
		files.setApproveButtonText("SELECT");

		int choice = files.showOpenDialog(parent);

		if (choice == JFileChooser.APPROVE_OPTION)
		{
			// return file selected
			return files.getSelectedFile().getPath();
		}

		return null;
	}

	/**
	 * this method open a successful frame when a file is generated in the component
	 * library and then call
	 * {@link vhdlgenerator.component.ComponentUtil#showFile(String)} to open the
	 * file
	 *
	 * @param path to the file that was generated
	 */
	public static void openFileGenerated(String path)
	{
		// code generated successfully
		int option = (Integer) WindowCode.successFrame(DisplayLibrary.GENERATED_COMPONENT).getValue();

		if (option == JOptionPane.YES_OPTION || option == JOptionPane.CANCEL_OPTION
				|| option == JOptionPane.CLOSED_OPTION)
		{
			// show the file created
			showFile(path);
		}
	}

}
