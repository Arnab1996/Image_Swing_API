

package net.imagej.ui.swing.tools;

import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;

@Plugin(type = Tool.class, name = "Polyline",
	description = "Polyline overlays", iconPath = "/icons/tools/polyline.png",
	priority = SwingPolylineTool.PRIORITY)
public class SwingPolylineTool extends AbstractTool {

	public static final double PRIORITY = SwingLineTool.PRIORITY - 1;

}
