/*
 * (C) Copyright 2010 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Nicolas Ulrich
 */
package org.nuxeo.cm.core.service;

import org.nuxeo.cm.service.CaseManagementDocumentTypeService;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.ComponentInstance;
import org.nuxeo.runtime.model.DefaultComponent;


/**
 * @author Nicolas Ulrich
 *
 */
public class CorrespondenceDocumentTypeServiceImpl extends DefaultComponent
        implements CaseManagementDocumentTypeService {

    private static final long serialVersionUID = 1L;

    private String postDocType;

    private String envelopeDocType;

    private String responseOutgoingDocType;

    @Override
    public void activate(ComponentContext context) throws Exception {
        super.activate(context);
    }

    @Override
    public void registerContribution(Object contribution,
            String extensionPoint, ComponentInstance contributor)
            throws Exception {

        CorrespondenceDocumentTypeDescriptor distributionType = ((CorrespondenceDocumentTypeDescriptor) contribution);

        if (distributionType.postDocType != null) {
            postDocType = distributionType.postDocType;
        }

        if (distributionType.envelopeDocType != null) {
            envelopeDocType = distributionType.envelopeDocType;
        }

        if (distributionType.outgoingDocType != null) {
            responseOutgoingDocType = distributionType.outgoingDocType;
        }

    }

    @Override
    public void unregisterContribution(Object contribution,
            String extensionPoint, ComponentInstance contributor) {
        envelopeDocType = null;
        postDocType = null;
    }

    public String getCaseType() {
        return this.envelopeDocType;
    }

    public String getCaseLinkType() {
        return this.postDocType;
    }

    public String getResponseOutgoingDocType() {
        return this.responseOutgoingDocType;
    }

}