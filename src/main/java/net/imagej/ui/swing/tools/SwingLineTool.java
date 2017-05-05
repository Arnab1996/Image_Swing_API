
package net.imagej.ui.swing.tools;

import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;


@Plugin(type = Tool.class, name = "Line",
	description = "Straight line overlays", iconPath = "/icons/tools/line.png",
	priority = SwingLineTool.PRIORITY)
public class SwingLineTool extends AbstractTool {

	public static final double PRIORITY = SwingPolygonTool.PRIORITY - 1;

}
