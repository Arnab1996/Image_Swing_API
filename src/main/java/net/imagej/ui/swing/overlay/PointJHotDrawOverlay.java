

package net.imagej.ui.swing.overlay;

import java.awt.Shape;

import net.imagej.display.ImageDisplay;
import net.imagej.display.OverlayView;
import net.imagej.overlay.Overlay;
import net.imagej.overlay.PointOverlay;
import net.imagej.ui.swing.tools.SwingPointTool;

import org.jhotdraw.draw.Figure;
import org.scijava.plugin.Plugin;
import org.scijava.tool.Tool;
import org.scijava.util.ColorRGB;


@Plugin(type = JHotDrawAdapter.class, priority = SwingPointTool.PRIORITY)
public class PointJHotDrawOverlay extends
	AbstractJHotDrawAdapter<PointOverlay, PointFigure>
{

	// -- JHotDrawAdapter methods --

	@Override
	public boolean supports(final Tool tool) {
		return tool instanceof SwingPointTool;
	}

	@Override
	public boolean supports(final Overlay overlay, final Figure figure) {
		if (!(overlay instanceof PointOverlay)) return false;
		return figure == null || figure instanceof PointFigure;
	}

	@Override
	public PointOverlay createNewOverlay() {
		return new PointOverlay(getContext());
	}

	@Override
	public Figure createDefaultFigure() {
		final PointFigure figure = new PointFigure();
		initDefaultSettings(figure);
		return figure;
	}

	@Override
	public void updateFigure(final OverlayView view, final PointFigure figure) {
		super.updateFigure(view, figure);
		final PointFigure pointFigure = figure;
		final Overlay overlay = view.getData();
		assert overlay instanceof PointOverlay;
		final PointOverlay pointOverlay = (PointOverlay) overlay;
		pointFigure.setFillColor(pointOverlay.getFillColor());
		pointFigure.setLineColor(pointOverlay.getLineColor());
		pointFigure.setPoints(pointOverlay.getPoints());
	}

	@Override
	public void updateOverlay(final PointFigure figure, final OverlayView view) {
		final Overlay overlay = view.getData();
		assert overlay instanceof PointOverlay;
		final PointOverlay pointOverlay = (PointOverlay) overlay;
		// do not let call to super.updateOverlay() mess with drawing attributes
		// so save colors
		final ColorRGB fillColor = overlay.getFillColor();
		final ColorRGB lineColor = overlay.getLineColor();
		// call super in case it initializes anything of importance
		super.updateOverlay(figure, view);
		// and restore colors to what we really want
		pointOverlay.setFillColor(fillColor);
		pointOverlay.setLineColor(lineColor);
		// set points
		pointOverlay.setPoints(figure.getPoints());
		pointOverlay.update();
	}

	@Override
	public JHotDrawTool getCreationTool(final ImageDisplay display) {
		return new IJCreationTool<>(display, this);
	}

	@Override
	public Shape toShape(final PointFigure figure) {
		throw new UnsupportedOperationException();
	}

}
