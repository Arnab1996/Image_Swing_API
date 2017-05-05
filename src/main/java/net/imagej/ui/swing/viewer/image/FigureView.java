

package net.imagej.ui.swing.viewer.image;

import net.imagej.display.DataView;

import org.jhotdraw.draw.Figure;


public interface FigureView {

	/** Gets the linked JHotDraw figure. */
	public Figure getFigure();

	/** Gets the linked ImageJ data view. */
	public DataView getDataView();

	/** Updates the figure to match the linked data view. */
	public void update();

	/** Removes the figure from the scene. */
	public void dispose();

}
