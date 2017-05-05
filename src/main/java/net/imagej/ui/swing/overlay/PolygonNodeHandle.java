

package net.imagej.ui.swing.overlay;

import java.awt.Point;
import java.awt.event.InputEvent;

import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.handle.BezierNodeHandle;


public class PolygonNodeHandle extends BezierNodeHandle {

	public PolygonNodeHandle(final BezierFigure owner, final int index,
		final Figure transformOwner)
	{
		super(owner, index, transformOwner);
	}

	public PolygonNodeHandle(final BezierFigure owner, final int index) {
		super(owner, index);
	}

	@Override
	public void trackEnd(final Point anchor, final Point lead,
		final int modifiersEx)
	{
		// Remove the behavior associated with the shift keys
		super.trackEnd(anchor, lead, modifiersEx &
			~(InputEvent.META_DOWN_MASK | InputEvent.CTRL_DOWN_MASK |
				InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
	}

}
