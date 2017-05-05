
package net.imagej.ui.swing.tools;

import org.scijava.input.MouseCursor;
import org.scijava.plugin.Plugin;
import org.scijava.tool.AbstractTool;
import org.scijava.tool.Tool;

@Plugin(type = Tool.class, name = "Text", description = "Text tool",
	iconPath = "/icons/tools/text.png", priority = SwingTextTool.PRIORITY,
	enabled = false)
public class SwingTextTool extends AbstractTool {

	public static final double PRIORITY = -115;

	@Override
	public MouseCursor getCursor() {
		return MouseCursor.TEXT;
	}

	// TODO: Implement TextJHotDrawAdapter.

}
