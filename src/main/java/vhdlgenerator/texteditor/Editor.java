/**
 * @file Editor.java
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
package vhdlgenerator.texteditor;

//SWING
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.undo.UndoManager;

/**
 *
 * this class create a TextEditor to write VHDL CODE.
 *
 * @author DOUDOU DIAWARA
 */
@SuppressWarnings("serial")
public class Editor extends JScrollPane
{
	/** system line separator */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/** line number display width */
	private int LINE_NUMBERS_WIDTH = 50;
	/** font editor */
	private final Font FONT = new Font("Monospaced", Font.ITALIC, 14);
	/** bold style */
	private SimpleAttributeSet bold = new SimpleAttributeSet();
	/** text area */
	private JTextPane code;
	/** manager for undo and redo */
	private UndoManager undo_manager;
	/** displaying line numbers */
	private JTextPane line_numbers_area;
	/** number of lines */
	private int line_length = -1;
	/** indentation replace Tabs with 5 space */
	private static final String INDENTATION = "     ";

	/**
	 * this constructor create the text editor to write your code.
	 *
	 * @param width  of the text Editor
	 * @param height of the text Editor
	 */
	public Editor(final int width, final int height)
	{
		setSize(width, height); // editor size
		setFont(FONT); // editor font

		// Manager
		undo_manager = new UndoManager();

		// LINE NUMBERS PROPRETIES
		line_length = 0;
		line_numbers_area = new JTextPane();
		line_numbers_area.setBackground(Color.LIGHT_GRAY); // background color
		line_numbers_area.setForeground(Color.black); // foreground color
		line_numbers_area.setFont(FONT);// line numbers font
		line_numbers_area.setEditable(false);

		// LINE-NUMBERS SHOULD BE RIGHT-ALIGNED
		SimpleAttributeSet rightAlign = new SimpleAttributeSet();
		StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
		line_numbers_area.setParagraphAttributes(rightAlign, true);
		StyleConstants.setBold(bold, true);
		try
		{
			// mark line one 1 before pressing new line
			line_numbers_area.getDocument().insertString(0, 1 + LINE_SEPARATOR, bold);
		} catch (BadLocationException e)
		{
			// do nothing when trying modify a invalid position
		}

		// CODE EDITOR
		code = new JTextPane();
		// TOOLTIP/HELP
		code.setFont(FONT);
		code.setToolTipText(
				"<html><span style=font-family:'ArialBlack';font-size:'12px';font-style:'italic';>write your VHDL code implementation here exemple <br/> <b><font color='blue'>if rising_edge</font>(clk) <font color='blue'>then</font></b> <br/><b>output1 &lt;= <font color='red'>not</font>(input1)</b><br/><b><font color='blue'>end if </font> ;</span><br/><b>- Crtl+Z to Undo<br/>- Crtl+Y to Redo</b>");

		// GET DOCUMENT OBJECT
		Document document_code = code.getDocument();
		// MANAGING UNDO AND REDO
		document_code.addUndoableEditListener(undo_manager);
		// FOCUS TEXT AREA
		code.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e)
			{
				code.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(final KeyEvent e)
					{
						// Ctrl + Z == undo
						if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown() && undo_manager.canUndo())
						{
							undo_manager.undo();
						}
						// Ctrl + Y == Redo
						if (e.getKeyCode() == KeyEvent.VK_Y && e.isControlDown() && undo_manager.canRedo())
						{
							undo_manager.redo();
						}
					}

				});

			}

		});

		// Replacing tabs with two spaces
		((AbstractDocument) document_code).setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException
			{
				super.insertString(fb, offset, text.replace("\t", INDENTATION), attrs);
			}
		});

		// DOCUMENT STATE
		document_code.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				lineNumbers();
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				lineNumbers();
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				lineNumbers();
			}
		});

		// Adds lineNumbers as row header on the left side of the main JTextPane
		this.setRowHeaderView(line_numbers_area);

		this.getRowHeader()
				.setPreferredSize(new Dimension(LINE_NUMBERS_WIDTH, line_numbers_area.getPreferredSize().height));

		// Sets the main-component in the JScrollPane.
		this.getViewport().add(code);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		// paint the current line
		new LinePainter(code);

		// Syntax Highlighting for the editor
		SyntaxHighlighting syntax = new SyntaxHighlighting();
		syntax.addSyntaxHighlightingToTextPane(code);

	}

	/**
	 * the line display width.
	 *
	 * @return the line number width
	 */
	public int getLineNumberWidth()
	{
		return this.LINE_NUMBERS_WIDTH;
	}

	/**
	 * change the width of the line number
	 *
	 * @param width the new width for the line number
	 */
	public void setLineNumberWidth(int width)
	{
		if (width > 0)
		{
			this.LINE_NUMBERS_WIDTH = width;
		}

	}

	/**
	 * the VHDL code written in the Code Editor.
	 *
	 * @return the code given by the user in the Editor
	 */
	public String getCode()
	{
		return this.code.getText();
	}

	/**
	 * to change the code just for testing purpose
	 *
	 * @param text the new code for the editor
	 */
	public void setCode(String text)
	{
		if (text != null)
			this.code.setText(text);
		else
			this.code.setText("");
	}

	/**
	 * Get the lines written in the editor
	 *
	 * @return the line numbers of the editor
	 */
	public String getLines()
	{
		return this.line_numbers_area.getText();
	}

	/**
	 * the number of lines in the Editor
	 *
	 * @return the number of lines of the editor
	 */
	public int getLineNumber()
	{
		return this.line_length;
	}

	/**
	 * text editor code
	 *
	 * @return the object component for the Text fields
	 */
	public JTextPane getTextComponent()
	{
		return this.code;
	}

	/**
	 * Display the line number in the Editor
	 */
	private void lineNumbers()
	{
		String text = code.getText();
		// Calculating the number of lines
		// length of the text minus length of the text without newline
		final int length = (text.length() - text.replace("\n", "").length()) + 1;
		// change the editor number of lines
		this.line_length = length;

		// LINE NUMBERS FONT STYLE
		SimpleAttributeSet font_lines = new SimpleAttributeSet();
		StyleConstants.setFontFamily(font_lines, "Monospaced");
		StyleConstants.setFontSize(font_lines, 14);
		StyleConstants.setItalic(font_lines, true);

		try
		{
			// Remove all in JTextPane line_numbers_area
			Document document = line_numbers_area.getDocument();
			document.remove(0, document.getLength());

			for (int lines = 1; lines <= line_length; lines++)
			{

				// Non-bold line-numbers
				if (lines < length)
				{
					document.insertString(document.getLength(), lines + " " + LINE_SEPARATOR, font_lines);

					// Last line-number bold
				} else
				{
					document.insertString(document.getLength(), lines + " " + LINE_SEPARATOR, bold);
				}
			}

		} catch (BadLocationException e)
		{
			// do nothing when trying modify a invalid position
		}

	}

}
