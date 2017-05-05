
package net.imagej.ui.swing.tools;

import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;


@Plugin(type = Tool.class, name = "Oval", description = "Oval selections",
	iconPath = "/icons/tools/oval.png", priority = SwingEllipseTool.PRIORITY)
public class SwingEllipseTool extends AbstractTool {

	public static final double PRIORITY = SwingRectangleTool.PRIORITY - 1;

}
