

package net.imagej.ui.swing.overlay;

import net.imagej.display.ImageDisplay;

import org.jhotdraw.draw.tool.DelegationSelectionTool;


public class IJDelegationSelectionTool extends DelegationSelectionTool
	implements JHotDrawTool
{

	@Override
	public ImageDisplay getDisplay() {
		return null;
	}

	@Override
	public JHotDrawAdapter<?> getAdapter() {
		return null;
	}

	@Override
	public boolean isConstructing() {
		return false;
	}

}
