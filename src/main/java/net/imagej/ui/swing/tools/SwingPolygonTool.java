

package net.imagej.ui.swing.tools;

import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;


@Plugin(type = Tool.class, name = "Polygon", description = "Polygon overlays",
	iconPath = "/icons/tools/polygon.png", priority = SwingPolygonTool.PRIORITY)
public class SwingPolygonTool extends AbstractTool {

	public static final double PRIORITY = SwingEllipseTool.PRIORITY - 1;

}
