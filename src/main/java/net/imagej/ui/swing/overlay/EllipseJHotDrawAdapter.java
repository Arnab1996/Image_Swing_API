
package net.imagej.ui.swing.overlay;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import net.imagej.display.ImageDisplay;
import net.imagej.display.OverlayView;
import net.imagej.overlay.EllipseOverlay;
import net.imagej.overlay.Overlay;
import net.imagej.ui.swing.tools.SwingEllipseTool;

import org.jhotdraw.draw.EllipseFigure;
import org.jhotdraw.draw.Figure;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.tool.Tool;
import org.scijava.tool.ToolService;
import org.scijava.util.RealCoords;


@Plugin(type = JHotDrawAdapter.class, priority = SwingEllipseTool.PRIORITY)
public class EllipseJHotDrawAdapter extends
	AbstractJHotDrawAdapter<EllipseOverlay, EllipseFigure>
{

	protected static EllipseOverlay downcastOverlay(final Overlay roi) {
		assert roi instanceof EllipseOverlay;
		return (EllipseOverlay) roi;
	}

	protected static EllipseFigure downcastFigure(final Figure figure) {
		assert figure instanceof EllipseFigure;
		return (EllipseFigure) figure;
	}

	@Parameter
	private ToolService toolService;

	// -- JHotDrawAdapter methods --

	@Override
	public boolean supports(final Tool tool) {
		return tool instanceof SwingEllipseTool;
	}

	@Override
	public boolean supports(final Overlay overlay, final Figure figure) {
		if (!(overlay instanceof EllipseOverlay)) return false;
		return figure == null || figure instanceof EllipseFigure;
	}

	@Override
	public Overlay createNewOverlay() {
		return new EllipseOverlay(getContext());
	}

	@Override
	public Figure createDefaultFigure() {
		final EllipseFigure figure = new EllipseFigure();
		initDefaultSettings(figure);
		return figure;
	}

	@Override
	public void updateFigure(final OverlayView view, final EllipseFigure figure)
	{
		super.updateFigure(view, figure);
		final EllipseOverlay overlay = downcastOverlay(view.getData());
		final double centerX = overlay.getOrigin(0);
		final double centerY = overlay.getOrigin(1);
		final double radiusX = overlay.getRadius(0);
		final double radiusY = overlay.getRadius(1);

		figure.setBounds(new Point2D.Double(centerX - radiusX, centerY - radiusY),
			new Point2D.Double(centerX + radiusX, centerY + radiusY));
	}

	@Override
	public void
		updateOverlay(final EllipseFigure figure, final OverlayView view)
	{
		super.updateOverlay(figure, view);
		final EllipseOverlay overlay = downcastOverlay(view.getData());
		final Rectangle2D.Double bounds = figure.getBounds();
		final double x = bounds.getMinX();
		final double y = bounds.getMinY();
		final double w = bounds.getWidth();
		final double h = bounds.getHeight();
		overlay.setOrigin(x + w / 2, 0);
		overlay.setOrigin(y + h / 2, 1);
		overlay.setRadius(w / 2, 0);
		overlay.setRadius(h / 2, 1);
		overlay.update();
		toolService.reportRectangle(x, y, w, h);
	}

	@Override
	public JHotDrawTool getCreationTool(final ImageDisplay display) {
		return new IJCreationTool<>(display, this);
	}

	@Override
	public void report(final RealCoords p1, final RealCoords p2) {
		toolService.reportRectangle(p1, p2);
	}

	@Override
	public Shape toShape(final EllipseFigure figure) {
		final Rectangle2D.Double bounds = figure.getBounds();
		return new Ellipse2D.Double(bounds.x, bounds.y, bounds.width,
			bounds.height);
	}

}
