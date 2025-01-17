/**
 * source
 * <a href="https://tips4java.wordpress.com/2008/10/29/line-painter/">Original
 * Code</a>
 *
 * @file LinePainter.java
 *
 * @author DOUDOU DIAWARA @see
 * <a href="https://github.com/Var7600/VHDL-GENERATOR">Github Page</a>
 *
 * @version 0.0  (my version from theèoriginal source )
 *
 * @section. LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of “Commons Clause” License Condition v1.0 but not for Commercial
 * use
 *
 */
package vhdlgenerator.texteditor;

//SWING
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 * this class Track the movement of the Caret by painting the background line at
 * the current caret/cursor position.
 *
 * @author DOUDOU DIAWARA
 */
public class LinePainter extends MouseAdapter implements Highlighter.HighlightPainter, CaretListener, MouseListener
{

	/** JTextComponent to highlight */
	private JTextComponent component;
	/** highlight color */
	private final Color caretHighlight = Color.decode("#ffffaa");
	/** model for highlighting */
	private Rectangle2D lastView;

	/**
	 * The text to make the current selection lighter.
	 *
	 * @param component text component that requires background line painting
	 */
	public LinePainter(JTextComponent component)
	{
		this.component = component;
		// Add listeners so we know when to change highlighting
		component.addCaretListener(this);
		component.addMouseListener(this);

		try
		{ // Turn highlighting on by adding a dummy highlight
			component.getHighlighter().addHighlight(0, 0, this);
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c)
	{
		try
		{
			Rectangle2D area = component.modelToView2D(component.getCaretPosition());
			g.setColor(caretHighlight);
			g.fillRect(0, area.getBounds().y, component.getWidth(), 20);

			if (lastView == null)
			{
				lastView = area;
			}
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * Caret position has changed, remove the highlight
	 */
	private void resetHighlight()
	{
		// Use invokeLater to make sure updates to the Document are completed,
		// otherwise Undo processing causes the modelToView method to loop.

		SwingUtilities.invokeLater(() -> {
			try
			{
				int offset = component.getCaretPosition();
				Rectangle2D currentView = component.modelToView2D(offset);

				// Remove the highlighting from the previously highlighted line
				if (lastView.getBounds().y != currentView.getBounds().y)
				{
					component.repaint(0, lastView.getBounds().y, component.getWidth(), 20);
					lastView = currentView;
				}
			} catch (BadLocationException e)
			{
				e.printStackTrace();
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void caretUpdate(CaretEvent e)
	{
		resetHighlight();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		resetHighlight();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		resetHighlight();
	}
}
