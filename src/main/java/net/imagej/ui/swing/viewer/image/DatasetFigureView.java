

package net.imagej.ui.swing.viewer.image;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import net.imagej.Dataset;
import net.imagej.display.DatasetView;
import net.imagej.display.event.DataViewUpdatedEvent;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.ImageFigure;
import org.scijava.AbstractContextual;
import org.scijava.event.EventHandler;
import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;
import org.scijava.ui.awt.AWTImageTools;


public class DatasetFigureView extends AbstractContextual implements FigureView
{

	private final DatasetView datasetView;
	private final ImageFigure figure;

	@Parameter
	private LogService log;

	public DatasetFigureView(final SwingImageDisplayViewer displayViewer,
		final DatasetView datasetView)
	{
		setContext(datasetView.getContext());
		this.datasetView = datasetView;
		final JHotDrawImageCanvas canvas = displayViewer.getCanvas();
		final Drawing drawing = canvas.getDrawing();
		figure = new ImageFigure();
		figure.setSelectable(false);
		figure.setTransformable(false);
		final Dataset dataset = datasetView.getData();
		final double minX = dataset.getImgPlus().realMin(0);
		final double minY = dataset.getImgPlus().realMin(1);
		final double maxX = dataset.getImgPlus().realMax(0);
		final double maxY = dataset.getImgPlus().realMax(1);
		figure.setBounds(new Point2D.Double(minX, minY), new Point2D.Double(maxX,
			maxY));
		drawing.add(figure);
	}

	@EventHandler
	protected void onDataViewUpdatedEvent(final DataViewUpdatedEvent event) {
		if (event.getView() == datasetView) update();
	}

	@Override
	public void update() {
		log.debug("Updating image figure: " + this);
		final Image image = datasetView.getScreenImage().image();
		final BufferedImage bufImage = AWTImageTools.makeBuffered(image);
		figure.setBounds(new Rectangle2D.Double(0, 0, bufImage.getWidth(),
			bufImage.getHeight()));
		figure.setBufferedImage(bufImage);
	}

	// -- FigureView methods --

	@Override
	public ImageFigure getFigure() {
		return figure;
	}

	@Override
	public DatasetView getDataView() {
		return datasetView;
	}

	@Override
	public void dispose() {
		getFigure().requestRemove();
	}

}
