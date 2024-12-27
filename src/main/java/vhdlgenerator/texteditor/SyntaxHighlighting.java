/**
 *
 * @file SytaxHighlighting.java
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
package vhdlgenerator.texteditor;

import java.awt.Color;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * this class do the syntax highlighting for the VHDL LANG KEYWORDS
 *
 * @author DOUDOU DIAWARA
 */
public class SyntaxHighlighting
{

	/**
	 * Default Constructor
	 */
	public SyntaxHighlighting()
	{
	}

	/**
	 * Add a DocumentListener to the JTextPane to trigger syntax highlighting on
	 * text changes.
	 *
	 * @param text_pane the JTextPane to highlight
	 */
	public void addSyntaxHighlightingToTextPane(JTextPane text_pane)
	{

		text_pane.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				updateHighlighting(text_pane);
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				updateHighlighting(text_pane);
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				// don't need change here(cause an infinite loop recursion and freeze editor)
			}
		});
	}

	/**
	 * Update the syntax highlighting in the JTextPane.
	 *
	 * @param text_pane the JTextPane to highlight
	 */
	public void updateHighlighting(JTextPane text_pane)
	{
		// Perform the syntax highlighting
		highlight(text_pane.getStyledDocument());
	}

	/**
	 * catch Token needed to be Highlighted and Perform the syntax highlighting
	 *
	 * @param document to be highlighted
	 */
	public void highlight(StyledDocument document)
	{
		try
		{
			VhdlSyntax lexer = new VhdlSyntax(new StringReader(document.getText(0, document.getLength())));

			// start parsing
			lexer.yylex();

			// list of Token to highlight
			List<Token> list_token = lexer.getListToken();
			Iterator<Token> it = list_token.iterator();

			while (it.hasNext())
			{
				Token token = it.next();
				// for Debug purpose
				// System.out.println("token type: " + token.getType() + "token offset : " +
				// token.getOffSet() + "token length: " + token.getTokenLength() + "value: " +
				// token.getValue());
				switch (token.getType())
				{
				case SymbolType.IDENTIFIER:
					doHighlight(document, SymbolType.IDENTIFIER_COLOR, token);
					break;

				case SymbolType.COMMENT:
					doHighlight(document, SymbolType.COMMENT_COLOR, token);
					break;

				case SymbolType.KEYWORDS:
					doHighlight(document, SymbolType.KEYWORDS_COLOR, token);
					break;

				case SymbolType.DATA_TYPE:
					doHighlight(document, SymbolType.DATA_COLOR, token);
					break;

				case SymbolType.FUNCTIONS:
					doHighlight(document, SymbolType.FUNCTIONS_COLOR, token);
					break;
				case SymbolType.OPERATORS:
					doHighlight(document, SymbolType.OPERATORS_COLOR, token);
					break;
				default:
					break;
				}

			}

			// reset list of Token
			list_token.clear();

		} catch (BadLocationException | IOException e)
		{
			// DO nothing
		}

	}

	/**
	 * highlight the Token with a color
	 *
	 * @param document to change character attributes
	 * @param color    the color to paint the Token
	 * @param token    to decorate {@link Token}
	 */
	private void doHighlight(StyledDocument document, Color color, Token token)
	{
		SimpleAttributeSet decoration = new SimpleAttributeSet();
		Runnable painter = () -> {
			StyleConstants.setForeground(decoration, color);
			document.setCharacterAttributes(token.getOffSet(), token.getTokenLength(), decoration, true);
		};

		SwingUtilities.invokeLater(painter);

	}

}
