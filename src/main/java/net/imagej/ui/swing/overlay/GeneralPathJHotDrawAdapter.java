

package net.imagej.ui.swing.overlay;

import java.awt.Shape;

import net.imagej.display.ImageDisplay;
import net.imagej.display.OverlayView;
import net.imagej.overlay.GeneralPathOverlay;
import net.imagej.overlay.Overlay;
import net.imagej.ui.swing.tools.SwingPolygonTool;
import net.imglib2.roi.GeneralPathRegionOfInterest;

import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.Figure;
import org.scijava.plugin.Plugin;
import org.scijava.tool.Tool;


@Plugin(type = JHotDrawAdapter.class, priority = GeneralPathJHotDrawAdapter.PRIORITY)
public class GeneralPathJHotDrawAdapter extends
	AbstractJHotDrawAdapter<GeneralPathOverlay, GeneralPathFigure>
{

	public static final double PRIORITY = SwingPolygonTool.PRIORITY + 0.5;

	private static GeneralPathOverlay downcastOverlay(final Overlay overlay) {
		assert overlay instanceof GeneralPathOverlay;
		return (GeneralPathOverlay) overlay;
	}

	// -- JHotDrawAdapter methods --

	@Override
	public boolean supports(final Tool tool) {
		return false;
	}

	@Override
	public boolean supports(final Overlay overlay, final Figure figure) {
		if (!(overlay instanceof GeneralPathOverlay)) return false;
		return figure == null || figure instanceof GeneralPathFigure;
	}

	@Override
	public Overlay createNewOverlay() {
		final GeneralPathOverlay o = new GeneralPathOverlay(getContext());
		return o;
	}

	@Override
	public Figure createDefaultFigure() {
		final GeneralPathFigure figure =
			new GeneralPathFigure(new PolygonFigure());
		initDefaultSettings(figure);
		figure.set(AttributeKeys.WINDING_RULE, AttributeKeys.WindingRule.EVEN_ODD);
		return figure;
	}

	@Override
	public void updateOverlay(final GeneralPathFigure figure,
		final OverlayView view)
	{
		super.updateOverlay(figure, view);
		final GeneralPathOverlay overlay = downcastOverlay(view.getData());
		final GeneralPathRegionOfInterest roi = overlay.getRegionOfInterest();
		roi.reset();
		BezierPathFunctions.addToRegionOfInterest(figure.getGeneralPath()
			.getPathIterator(null), roi);
		overlay.update();
	}

	@Override
	public void updateFigure(final OverlayView view,
		final GeneralPathFigure figure)
	{
		super.updateFigure(view, figure);
		final GeneralPathOverlay overlay = downcastOverlay(view.getData());
		final GeneralPathRegionOfInterest roi = overlay.getRegionOfInterest();
		figure.setGeneralPath(roi.getGeneralPath());
	}

	@Override
	public JHotDrawTool getCreationTool(final ImageDisplay display) {
		throw new UnsupportedOperationException(); // new IJBezierTool(display,
																								// this);
	}

	@Override
	public Shape toShape(final GeneralPathFigure figure) {
		return figure.getGeneralPath();
	}

}
