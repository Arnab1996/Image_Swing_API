

package net.imagej.ui.swing.viewer.image;

import net.imagej.ui.viewer.image.ImageDisplayViewer;

public interface SwingImageDisplayViewer extends ImageDisplayViewer {

	@Override
	SwingImageDisplayPanel getPanel();

	/** Gets the JHotDraw canvas used by the viewer. */
	JHotDrawImageCanvas getCanvas();

}
