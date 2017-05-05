

package net.imagej.ui.swing.widget;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.imagej.widget.ColorTableWidget;
import net.imglib2.display.ColorTable;

import org.scijava.plugin.Plugin;
import org.scijava.ui.swing.widget.SwingInputWidget;
import org.scijava.widget.InputWidget;
import org.scijava.widget.WidgetModel;

@Plugin(type = InputWidget.class)
public class SwingColorTableWidget extends SwingInputWidget<ColorTable>
	implements ColorTableWidget<JPanel>
{

	// -- fields --

	private BufferedImage image;
	private JLabel picLabel;

	// -- constructors --

	public SwingColorTableWidget() {
		image = new BufferedImage(256, 20, BufferedImage.TYPE_INT_RGB);
	}

	// -- InputWidget methods --

	@Override
	public ColorTable getValue() {
		return (ColorTable) get().getValue();
	}

	@Override
	public void set(final WidgetModel model) {
		super.set(model);
		picLabel = new JLabel(); // new ImageIcon(image));
		getComponent().add(picLabel);
		refreshWidget();
	}

	@Override
	public boolean supports(final WidgetModel model) {
		return model.isType(ColorTable.class);
	}

	// -- AbstractUIInputWidget methods ---

	@Override
	public void doRefresh() {
		ColorTable colorTable = getValue();
		fillImage(colorTable);
		picLabel.setIcon(new ImageIcon(image));
		picLabel.repaint();
	}

	// -- helpers --

	private void fillImage(ColorTable cTable) {
		for (int x = 0; x < 256; x++) {
			int r = cTable.get(0, x) & 0xff;
			int g = cTable.get(1, x) & 0xff;
			int b = cTable.get(2, x) & 0xff;
			int rgb = (r << 16) | (g << 8) | b;
			for (int y = 0; y < 20; y++) {
				image.setRGB(x, y, rgb);
			}
		}
	}
}
