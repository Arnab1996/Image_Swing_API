

package net.imagej.ui.swing.tools;

import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;

@Plugin(type = Tool.class, name = "Rectangle",
	description = "Rectangular overlays",
	iconPath = "/icons/tools/rectangle.png",
	priority = SwingRectangleTool.PRIORITY)
public class SwingRectangleTool extends AbstractTool {

	public static final double PRIORITY = 100;

}
