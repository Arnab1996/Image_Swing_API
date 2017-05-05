

package net.imagej.ui.swing.overlay;

import java.awt.Shape;

import net.imagej.Dataset;
import net.imagej.display.ImageDisplay;
import net.imagej.display.ImageDisplayService;
import net.imagej.overlay.Overlay;
import net.imagej.overlay.ThresholdOverlay;
import net.imagej.threshold.ThresholdService;
import net.imagej.ui.swing.tools.SwingPolygonTool;

import org.jhotdraw.draw.Figure;
import org.scijava.Priority;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.tool.Tool;

@Plugin(type = JHotDrawAdapter.class, priority = Priority.HIGH_PRIORITY)
public class ThresholdJHotDrawAdapter extends
	AbstractJHotDrawAdapter<ThresholdOverlay, ThresholdFigure>
{

	@Parameter
	private ImageDisplayService imageDisplayService;

	@Parameter
	private ThresholdService thresholdService;

	// -- JHotDrawAdapter methods --

	@Override
	public boolean supports(Tool tool) {
		return false; // there is no threshold tool
	}

	@Override
	public boolean supports(Overlay overlay, Figure figure) {
		if (!(overlay instanceof ThresholdOverlay)) return false;
		return figure == null || figure instanceof ThresholdFigure;
	}

	@Override
	public Overlay createNewOverlay() {
		ImageDisplay display = imageDisplayService.getActiveImageDisplay();
		if (display == null) return null;
		return thresholdService.getThreshold(display);
	}

	@Override
	public Figure createDefaultFigure() {
		ImageDisplay display = imageDisplayService.getActiveImageDisplay();
		if (display == null) return null;
		Dataset dataset = imageDisplayService.getActiveDataset();
		if (dataset == null) return null;
		ThresholdOverlay overlay = thresholdService.getThreshold(display);
		return new ThresholdFigure(display, dataset, overlay);
	}

	@Override
	public JHotDrawTool getCreationTool(ImageDisplay display) {
		return new IJCreationTool<>(display, this);
	}

	@Override
	public Shape toShape(ThresholdFigure figure) {
		throw new UnsupportedOperationException("to be implemented"); // TODO
	}

}
