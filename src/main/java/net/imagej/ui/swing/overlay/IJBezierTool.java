

package net.imagej.ui.swing.overlay;

import net.imagej.display.ImageDisplay;

import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.tool.BezierTool;


public class IJBezierTool extends BezierTool implements JHotDrawTool {

	private final ImageDisplay display;
	private final JHotDrawAdapter<BezierFigure> adapter;

	public IJBezierTool(final ImageDisplay display,
		final JHotDrawAdapter<BezierFigure> adapter)
	{
		super((BezierFigure) adapter.createDefaultFigure());
		this.display = display;
		this.adapter = adapter;
	}

	// -- BezierTool methods --

	@Override
	protected BezierFigure createFigure() {
		return (BezierFigure) getAdapter().createDefaultFigure();
	}

	@Override
	protected void finishCreation(final BezierFigure figure,
		final DrawingView drawingView)
	{
		super.finishCreation(figure, drawingView);
		final JHotDrawService jHotDrawService =
			getDisplay().getContext().getService(JHotDrawService.class);
		jHotDrawService.linkOverlay(figure, getAdapter(), getDisplay());
	}

	// -- JHotDrawTool methods --

	@Override
	public ImageDisplay getDisplay() {
		return display;
	}

	@Override
	public JHotDrawAdapter<BezierFigure> getAdapter() {
		return adapter;
	}

	@Override
	public boolean isConstructing() {
		return createdFigure != null;
	}

}
