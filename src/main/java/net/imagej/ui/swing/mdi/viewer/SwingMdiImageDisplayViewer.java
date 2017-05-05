

package net.imagej.ui.swing.mdi.viewer;

import javax.swing.JInternalFrame;

import net.imagej.ui.swing.viewer.image.AbstractSwingImageDisplayViewer;
import net.imagej.ui.swing.viewer.image.SwingImageDisplayViewer;

import org.scijava.display.Display;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UserInterface;
import org.scijava.ui.swing.mdi.SwingMdiUI;
import org.scijava.ui.viewer.DisplayViewer;
import org.scijava.ui.viewer.DisplayWindow;

@Plugin(type = DisplayViewer.class)
public class SwingMdiImageDisplayViewer extends AbstractSwingImageDisplayViewer
{

	// -- DisplayViewer methods --

	@Override
	public boolean isCompatible(final UserInterface ui) {
		return ui instanceof SwingMdiUI;
	}

	@Override
	public void view(final DisplayWindow w, final Display<?> d) {
		super.view(w, d);
		getPanel().addEventDispatcher(dispatcher);
	}

}
