

package net.imagej.ui.swing.overlay;

import net.imagej.ImageJPlugin;
import net.imagej.display.ImageDisplay;
import net.imagej.display.OverlayView;
import net.imagej.overlay.Overlay;

import org.jhotdraw.draw.Figure;
import org.scijava.display.Display;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.RichPlugin;
import org.scijava.tool.Tool;
import org.scijava.util.RealCoords;


public interface JHotDrawAdapter<F extends Figure> extends ImageJPlugin,
	RichPlugin
{

	/**
	 * Determines whether the adapter is designed to work with the given tool.
	 * 
	 * @param tool The tool in question.
	 * @return True iff the adapter is compatible with the given tool.
	 */
	boolean supports(Tool tool);

	/**
	 * Determines whether the adapter can handle a particular overlay, or overlay
	 * / figure combination.
	 * 
	 * @param overlay - an overlay that might be editable
	 * @param figure - a figure that will be either updated by the overlay or will
	 *          update the overlay. The figure can be null: this indicates that
	 *          the adapter is capable of creating the figure associated with the
	 *          overlay/
	 */
	boolean supports(Overlay overlay, Figure figure);

	/**
	 * Creates a new overlay.
	 * 
	 * @return an Overlay of the associated type in the default initial state
	 */
	Overlay createNewOverlay();

	/** Creates a default figure of the type handled by this adapter. */
	Figure createDefaultFigure();

	/**
	 * Update the overlay to match the appearance of the figure
	 * 
	 * @param figure the figure that holds the current correct appearance
	 * @param view view of the overlay that needs to be changed to bring it
	 *          in-sync with the figure.
	 */
	void updateOverlay(F figure, OverlayView view);

	/**
	 * Update the appearance of the figure to match the overlay
	 * 
	 * @param view view of the overlay to be represented by the figure
	 * @param figure the figure that is to be made to look like the overlay
	 */
	void updateFigure(OverlayView view, F figure);

	JHotDrawTool getCreationTool(ImageDisplay display);

	void mouseDown(Display<?> d, int x, int y);

	void mouseDrag(Display<?> d, int x, int y);

	void report(RealCoords p1, RealCoords p2);

}
