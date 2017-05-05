

package net.imagej.ui.swing.viewer.table;

import javax.swing.JTable;

import net.imagej.table.Table;
import net.imagej.ui.viewer.table.AbstractTableDisplayViewer;

import org.scijava.display.Display;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UserInterface;
import org.scijava.ui.swing.SwingUI;
import org.scijava.ui.viewer.DisplayViewer;
import org.scijava.ui.viewer.DisplayWindow;

@Plugin(type = DisplayViewer.class)
public class SwingTableDisplayViewer extends AbstractTableDisplayViewer {

	@Override
	public boolean isCompatible(final UserInterface ui) {
		// TODO: Consider whether to use an interface for Swing UIs instead?
		return ui instanceof SwingUI;
	}

	@Override
	public void view(final DisplayWindow w, final Display<?> d) {
		super.view(w, d);
		setPanel(new SwingTableDisplayPanel(getDisplay(), w));
	}

}
