

package net.imagej.ui.swing.tools;

import org.scijava.input.MouseCursor;
import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;

@Plugin(type = Tool.class, name = "Point", description = "Point overlays",
	iconPath = "/icons/tools/point.png", priority = SwingPointTool.PRIORITY)
public class SwingPointTool extends AbstractTool {

	public static final double PRIORITY = SwingAngleTool.PRIORITY - 1;

	// -- Tool methods --

	@Override
	public MouseCursor getCursor() {
		return MouseCursor.CROSSHAIR;
	}

}
