

package net.imagej.ui.swing.updater;

import javax.swing.JComboBox;

import net.imagej.updater.FileObject;
import net.imagej.updater.FilesCollection;

@SuppressWarnings("serial")
public class ViewOptions extends JComboBox<Object> {

	public static enum Option {
		ALL("all files"), INSTALLED("installed files only"), UNINSTALLED(
			"uninstalled files only"), UPTODATE("only up-to-date files"), UPDATEABLE(
			"updateable files only"),
			LOCALLY_MODIFIED("locally modified files only"), MANAGED(
				"downloaded files only"), OTHERS("local-only files"),
			CHANGES("changes"), SELECTED("selected");

		String label;

		Option(final String label) {
			this.label = "View " + label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	protected final int customOptionStart;

	public ViewOptions() {
		super(Option.values());

		customOptionStart = getItemCount();

		setMaximumRowCount(15);
	}

	public void clearCustomOptions() {
		while (getItemCount() > customOptionStart)
			removeItemAt(customOptionStart);
	}

	protected interface CustomOption {

		Iterable<FileObject> getIterable();
	}

	public void addCustomOption(final String title,
		final Iterable<FileObject> iterable)
	{
		addItem(new CustomOption() {

			@Override
			public String toString() {
				return title;
			}

			@Override
			public Iterable<FileObject> getIterable() {
				return iterable;
			}
		});
	}

	public Iterable<FileObject> getView(final FileTable table) {
		if (getSelectedIndex() >= customOptionStart) return ((CustomOption) getSelectedItem())
			.getIterable();

		final FilesCollection files =
			table.files.clone(table.getAllFiles().notHidden());
		files.sort();
		switch ((Option) getSelectedItem()) {
			case INSTALLED:
				return files.installed();
			case UNINSTALLED:
				return files.uninstalled();
			case UPTODATE:
				return files.upToDate();
			case UPDATEABLE:
				return files.shownByDefault();
			case LOCALLY_MODIFIED:
				return files.locallyModified();
			case MANAGED:
				return files.managedFiles();
			case OTHERS:
				return files.localOnly();
			case CHANGES:
				return files.changes();
			case SELECTED:
				return table.getSelectedFiles();
			default:
				return files;
		}
	}
}
