package com.sromku.bugsnag.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.sromku.bugsnag.model.Error;
import com.sromku.bugsnag.utils.Utils;

public class BugDetailsDialog extends TitleAreaDialog {

	private final Error error;

	public BugDetailsDialog(Shell parentShell, Error error) {
		super(parentShell);
		this.error = error;
	}

	@Override
	public void create() {
		super.create();
		setTitle("Report: " + error.id);
		setMessage(error.exception, IMessageProvider.ERROR);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		// set the internal layout of the container
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);

		createStackTrace(container);

		return area;
	}

	private void createStackTrace(Composite container) {
		Label lbtStacktrace = new Label(container, SWT.NONE);
		lbtStacktrace.setText("Stacktrace");

		GridData dataStacktrace = new GridData();
		dataStacktrace.grabExcessHorizontalSpace = true;
		dataStacktrace.grabExcessVerticalSpace = true;
		dataStacktrace.minimumWidth = 590;
		dataStacktrace.minimumHeight = 200;
		dataStacktrace.horizontalAlignment = GridData.FILL_BOTH;
		dataStacktrace.verticalAlignment = GridData.FILL_BOTH;

		Text textStacktrace = new Text(container, SWT.MULTI | SWT.READ_ONLY | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		// TODO
		textStacktrace.setText("" /*Utils.getWrappedString(error.bugDetails.stacktrace) */);
		textStacktrace.setLayoutData(dataStacktrace);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Close", true);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(600, 400);
	}

	@Override
	protected void okPressed() {
		super.okPressed();
	}

}
