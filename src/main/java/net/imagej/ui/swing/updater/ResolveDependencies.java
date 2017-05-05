

package net.imagej.ui.swing.updater;

import java.util.ArrayList;

import net.imagej.updater.Conflicts;
import net.imagej.updater.Conflicts.Conflict;
import net.imagej.updater.FilesCollection;

@SuppressWarnings("serial")
public class ResolveDependencies extends ConflictDialog {

	protected Conflicts conflicts;
	protected boolean forUpload;

	public ResolveDependencies(final UpdaterFrame owner,
		final FilesCollection files)
	{
		this(owner, files, false);
	}

	public ResolveDependencies(final UpdaterFrame owner,
		final FilesCollection files, final boolean forUpload)
	{
		super(owner, "Resolve dependencies");

		this.forUpload = forUpload;
		conflicts = new Conflicts(files);
		conflictList = new ArrayList<>();
	}

	@Override
	protected void updateConflictList() {
		conflictList.clear();
		for (final Conflict conflict : conflicts.getConflicts(forUpload))
			conflictList.add(conflict);
	}
}
