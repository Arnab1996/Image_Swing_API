

package net.imagej.ui.swing.overlay;

import java.awt.event.MouseEvent;

import net.imagej.display.ImageDisplay;

import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.tool.CreationTool;

public class IJCreationTool<F extends Figure> extends CreationTool implements JHotDrawTool {

	private final ImageDisplay display;
	private final JHotDrawAdapter<F> adapter;

	public IJCreationTool(final ImageDisplay display,
		final JHotDrawAdapter<F> adapter)
	{
		super(adapter.createDefaultFigure());
		this.display = display;
		this.adapter = adapter;
	}

	// -- CreationTool methods --

	@Override
	protected Figure createFigure() {
		return getAdapter().createDefaultFigure();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void creationFinished(final Figure figure) {
		super.creationFinished(figure);
		final JHotDrawService jHotDrawService =
			getDisplay().getContext().getService(JHotDrawService.class);
		jHotDrawService.linkOverlay((F)figure, getAdapter(), getDisplay());
	}

	// -- JHotDrawTool methods --

	@Override
	public ImageDisplay getDisplay() {
		return display;
	}

	@Override
	public JHotDrawAdapter<F> getAdapter() {
		return adapter;
	}

	@Override
	public boolean isConstructing() {
		return createdFigure != null;
	}

	// -- MouseListener methods --

	@Override
	public void mouseClicked(MouseEvent evt) {
		if (!isLeftClick(evt)) return;
		super.mouseClicked(evt);
	}
	
	@Override
	public void mousePressed(MouseEvent evt) {
		if (!isLeftClick(evt)) return;
		super.mousePressed(evt);
		adapter.mouseDown(getDisplay(), evt.getX(), evt.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent evt) {
		if (!isLeftClick(evt)) return;
		super.mouseReleased(evt);
	}

	// -- MouseMotionListener methods --

	@Override
	public void mouseDragged(MouseEvent evt) {
		super.mouseDragged(evt);
		adapter.mouseDrag(getDisplay(), evt.getX(), evt.getY());
	}

	// -- Helper methods --

	private boolean isLeftClick(final MouseEvent evt) {
		return evt.getButton() == MouseEvent.BUTTON1;
	}

}
