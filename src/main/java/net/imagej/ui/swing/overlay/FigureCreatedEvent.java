

package net.imagej.ui.swing.overlay;

import net.imagej.display.ImageDisplay;
import net.imagej.display.OverlayView;
import net.imagej.display.event.DataViewEvent;

import org.jhotdraw.draw.Figure;


public class FigureCreatedEvent extends DataViewEvent {

	private final OverlayView view;
	private final Figure figure;
	private final ImageDisplay display;

	public FigureCreatedEvent(final OverlayView view, final Figure figure,
		final ImageDisplay display)
	{
		super(view);
		this.view = view;
		this.figure = figure;
		this.display = display;
	}

	/** Gets the newly created {@link Figure}. */
	public Figure getFigure() {
		return figure;
	}

	/** Gets the associated {@link ImageDisplay}. */
	public ImageDisplay getDisplay() {
		return display;
	}

	// -- DataViewEvent methods --

	@Override
	public OverlayView getView() {
		return view;
	}

}
