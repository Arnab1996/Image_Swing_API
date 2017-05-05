

package net.imagej.ui.swing.sdi.viewer;

import javax.swing.JFrame;

import net.imagej.ui.swing.viewer.image.AbstractSwingImageDisplayViewer;
import net.imagej.ui.swing.viewer.image.SwingImageDisplayViewer;

import org.scijava.plugin.Plugin;
import org.scijava.ui.UserInterface;
import org.scijava.ui.swing.SwingUI;
import org.scijava.ui.viewer.DisplayViewer;

@Plugin(type = DisplayViewer.class)
public class SwingSdiImageDisplayViewer extends AbstractSwingImageDisplayViewer
{

	// -- DisplayViewer methods --

	@Override
	public boolean isCompatible(final UserInterface ui) {
		return ui instanceof SwingUI;
	}

}
