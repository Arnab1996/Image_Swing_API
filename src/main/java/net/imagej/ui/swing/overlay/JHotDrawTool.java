

package net.imagej.ui.swing.overlay;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.event.UndoableEditListener;

import net.imagej.display.ImageDisplay;

import org.jhotdraw.draw.tool.Tool;


public interface JHotDrawTool extends Tool {

	void addUndoableEditListener(UndoableEditListener l);

	void removeUndoableEditListener(UndoableEditListener listener);

	void setInputMap(InputMap map);

	void setActionMap(ActionMap map);

	/** Gets the {@link ImageDisplay} associated with this JHotDraw tool. */
	ImageDisplay getDisplay();

	/** Gets the {@link JHotDrawAdapter} associated with this JHotDraw tool. */
	JHotDrawAdapter<?> getAdapter();

	boolean isConstructing();

}
