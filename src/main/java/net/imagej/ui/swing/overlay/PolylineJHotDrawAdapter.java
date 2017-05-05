
package net.imagej.ui.swing.overlay;

import java.awt.Shape;
import java.awt.geom.PathIterator;

import net.imagej.display.ImageDisplay;
import net.imagej.display.OverlayView;
import net.imagej.overlay.GeneralPathOverlay;
import net.imagej.overlay.Overlay;
import net.imagej.ui.swing.tools.SwingPolylineTool;
import net.imglib2.roi.GeneralPathRegionOfInterest;

import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.geom.BezierPath.Node;
import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.tool.Tool;

@Plugin(type = JHotDrawAdapter.class, priority = SwingPolylineTool.PRIORITY)
public class PolylineJHotDrawAdapter extends
	AbstractJHotDrawAdapter<GeneralPathOverlay, BezierFigure>
{

	@Parameter(required = false)
	private LogService log;

	// -- JHotDrawAdapter methods --

	@Override
	public boolean supports(final Tool tool) {
		return tool instanceof SwingPolylineTool;
	}

	@Override
	public boolean supports(final Overlay overlay, final Figure figure) {
		if (!(overlay instanceof GeneralPathOverlay)) return false;
		return figure == null || figure instanceof PolylineFigure;
	}

	@Override
	public Overlay createNewOverlay() {
		return new GeneralPathOverlay(getContext());
	}

	@Override
	public Figure createDefaultFigure() {
		final BezierFigure figure = new PolylineFigure();
		initDefaultSettings(figure);
		return figure;
	}

	@Override
	public void updateOverlay(final BezierFigure figure, final OverlayView view) {
		super.updateOverlay(figure, view);
		assert view.getData() instanceof GeneralPathOverlay;
		final GeneralPathOverlay gpo = (GeneralPathOverlay) view.getData();
		final GeneralPathRegionOfInterest gpr = gpo.getRegionOfInterest();
		gpr.reset();
		for (int i = 0; i < figure.getNodeCount(); i++) {
			final Node n = figure.getNode(i);
			if (i == 0) gpr.moveTo(n.x[0], n.y[0]);
			else gpr.lineTo(n.x[0], n.y[0]);
		}
		gpo.update();
	}

	@Override
	public void updateFigure(final OverlayView view, final BezierFigure figure) {
		super.updateFigure(view, figure);
		assert view.getData() instanceof GeneralPathOverlay;
		final GeneralPathOverlay gpo = (GeneralPathOverlay) view.getData();
		final GeneralPathRegionOfInterest gpr = gpo.getRegionOfInterest();
		final PathIterator pi = gpr.getGeneralPath().getPathIterator(null);
		final int nCount = figure.getNodeCount();
		int i = 0;
		final double[] pos = new double[6];
		while (!pi.isDone()) {
			pi.currentSegment(pos);
			final Node n = new Node(pos[0], pos[1]);
			if (i < nCount) figure.getNode(i).setTo(n);
			else figure.addNode(n);
			pi.next();
			i++;
		}
		while (i < figure.getNodeCount())
			figure.removeNode(i);
	}

	@Override
	public JHotDrawTool getCreationTool(final ImageDisplay display) {
		return new IJBezierTool(display, this);
	}

	@Override
	public Shape toShape(final BezierFigure figure) {
		return figure.getBezierPath().toGeneralPath();
	}

}
