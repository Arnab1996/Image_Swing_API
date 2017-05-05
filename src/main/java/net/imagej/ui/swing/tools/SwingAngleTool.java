

package net.imagej.ui.swing.tools;

import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;


@Plugin(type = Tool.class, name = "Angle", description = "Angle overlays",
	iconPath = "/icons/tools/angle.png", priority = SwingAngleTool.PRIORITY)
public class SwingAngleTool extends AbstractTool {

	public static final double PRIORITY = SwingPolylineTool.PRIORITY - 1;

}
