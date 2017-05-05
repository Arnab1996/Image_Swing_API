

package net.imagej.ui.swing.commands;

import net.imagej.ui.swing.overlay.SwingOverlayManager;

import org.scijava.Context;
import org.scijava.command.Command;
import org.scijava.command.ContextCommand;
import org.scijava.plugin.Menu;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type = Command.class, menu = { @Menu(label = "Image"), @Menu(label = "Overlay"),
	@Menu(label = "Overlay Manager") })
public class OverlayManager extends ContextCommand {

	@Parameter
	private Context context;

	@Override
	public void run() {
		final SwingOverlayManager overlaymgr = new SwingOverlayManager(context);
		overlaymgr.setVisible(true);
	}

}
