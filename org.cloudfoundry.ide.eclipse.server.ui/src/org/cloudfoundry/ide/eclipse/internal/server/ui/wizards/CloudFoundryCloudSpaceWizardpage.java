/*******************************************************************************
 * Copyright (c) 2012, 2013 GoPivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     GoPivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.cloudfoundry.ide.eclipse.internal.server.ui.wizards;

import org.cloudfoundry.ide.eclipse.internal.server.core.CloudFoundryServer;
import org.cloudfoundry.ide.eclipse.internal.server.ui.CloudSpacesSelectionPart;
import org.cloudfoundry.ide.eclipse.internal.server.ui.editor.CloudSpaceChangeHandler;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class CloudFoundryCloudSpaceWizardpage extends WizardPage {

	private CloudSpaceChangeHandler spaceChangeHandler;

	private final CloudFoundryServer cloudServer;

	private CloudSpacesSelectionPart spacesPart;

	public CloudFoundryCloudSpaceWizardpage(CloudFoundryServer cloudServer, CloudSpaceChangeHandler spaceChangeHandler) {
		super(cloudServer.getServer().getName() + " Organization and Spaces");
		this.cloudServer = cloudServer;
		this.spaceChangeHandler = spaceChangeHandler;
	}

	public void createControl(Composite parent) {

		spacesPart = new CloudSpacesSelectionPart(spaceChangeHandler, cloudServer, this);
		Composite composite = spacesPart.createComposite(parent);
		setControl(composite);
	}

	public boolean isPageComplete() {
		return spaceChangeHandler != null && spaceChangeHandler.hasSetSpace();
	}

	public void refreshListOfSpaces() {
		if (spacesPart != null) {
			spacesPart.setInput();
		}
	}

}
