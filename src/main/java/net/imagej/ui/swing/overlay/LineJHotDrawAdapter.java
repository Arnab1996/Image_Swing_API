

package net.imagej.ui.swing.overlay;

import java.awt.Shape;
import java.awt.geom.Point2D;

import net.imagej.display.ImageDisplay;
import net.imagej.display.OverlayView;
import net.imagej.overlay.LineOverlay;
import net.imagej.overlay.Overlay;
import net.imagej.ui.swing.tools.SwingLineTool;

import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.LineFigure;
import org.jhotdraw.geom.BezierPath.Node;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.tool.Tool;
import org.scijava.tool.ToolService;
import org.scijava.util.RealCoords;


@Plugin(type = JHotDrawAdapter.class, priority = SwingLineTool.PRIORITY)
public class LineJHotDrawAdapter extends
	AbstractJHotDrawAdapter<LineOverlay, LineFigure>
{

	@Parameter
	private ToolService toolService;

	// -- JHotDrawAdapter methods --

	@Override
	public boolean supports(final Tool tool) {
		return tool instanceof SwingLineTool;
	}

	@Override
	public boolean supports(final Overlay overlay, final Figure figure) {
		if (!(overlay instanceof LineOverlay)) return false;
		return figure == null || figure instanceof LineFigure;
	}

	@Override
	public LineOverlay createNewOverlay() {
		return new LineOverlay(getContext());
	}

	@Override
	public Figure createDefaultFigure() {
		final LineFigure figure = new LineFigure();
		initDefaultSettings(figure);
		return figure;
	}

	@Override
	public void updateFigure(final OverlayView view, final LineFigure figure) {
		super.updateFigure(view, figure);
		final LineFigure lineFigure = figure;
		final Overlay overlay = view.getData();
		assert overlay instanceof LineOverlay;
		final LineOverlay lineOverlay = (LineOverlay) overlay;
		final double pt1X = lineOverlay.getLineStart(0);
		final double pt1Y = lineOverlay.getLineStart(1);
		final double pt2X = lineOverlay.getLineEnd(0);
		final double pt2Y = lineOverlay.getLineEnd(1);
		lineFigure.setStartPoint(new Point2D.Double(pt1X, pt1Y));
		lineFigure.setEndPoint(new Point2D.Double(pt2X, pt2Y));
	}

	@Override
	public void updateOverlay(final LineFigure figure, final OverlayView view) {
		super.updateOverlay(figure, view);
		final LineFigure line = figure;
		final Overlay overlay = view.getData();
		assert overlay instanceof LineOverlay;
		final LineOverlay lineOverlay = (LineOverlay) overlay;
		final Node startNode = line.getNode(0);
		final double x1 = startNode.getControlPoint(0).x;
		final double y1 = startNode.getControlPoint(0).y;
		lineOverlay.setLineStart(x1, 0);
		lineOverlay.setLineStart(y1, 1);
		final Node endNode = line.getNode(1);
		final double x2 = endNode.getControlPoint(0).x;
		final double y2 = endNode.getControlPoint(0).y;
		lineOverlay.setLineEnd(x2, 0);
		lineOverlay.setLineEnd(y2, 1);
		lineOverlay.update();
		toolService.reportLine(x1, y1, x2, y2);
	}

	@Override
	public JHotDrawTool getCreationTool(final ImageDisplay display) {
		return new IJCreationTool<>(display, this);
	}

	@Override
	public void report(final RealCoords p1, final RealCoords p2) {
		toolService.reportLine(p1, p2);
	}

	@Override
	public Shape toShape(final LineFigure figure) {
		return figure.getBezierPath().toGeneralPath();
	}

}
