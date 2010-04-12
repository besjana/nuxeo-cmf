/*
 * (C) Copyright 2006-2009 Nuxeo SAS (http://nuxeo.com/) and contributors.
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
 *
 * $Id$
 */

package org.nuxeo.cm.web.envelope;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.nuxeo.cm.cases.CaseConstants;
import org.nuxeo.cm.cases.MailEnvelope;
import org.nuxeo.cm.cases.MailEnvelopeItem;
import org.nuxeo.cm.service.CorrespondenceDocumentTypeService;
import org.nuxeo.cm.web.distribution.CorrespondenceDistributionActionsBean;
import org.nuxeo.cm.web.invalidations.CorrespondenceContextBound;
import org.nuxeo.cm.web.mailbox.CorrespondenceAbstractActionsBean;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.webapp.helpers.ResourcesAccessor;
import org.nuxeo.runtime.api.Framework;


/**
 * @author Nicolas Ulrich
 *
 */
@Name("correspEnvelopeActions")
@Scope(ScopeType.CONVERSATION)
@CorrespondenceContextBound
public class CorrespondenceEnvelopeActionsBean extends
        CorrespondenceAbstractActionsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(CorrespondenceDistributionActionsBean.class);

    @In(create = true, required = false)
    protected transient FacesMessages facesMessages;

    @In(create = true)
    protected transient ResourcesAccessor resourcesAccessor;

    /**
     * @return true if this envelope is still in draft
     * @throws ClientException
     */
    public boolean isInitialEnvelope() throws ClientException {
        MailEnvelope env = getCurrentEnvelope();

        if (env != null) {
            return getCurrentEnvelope().isDraft();
        } else {
            return false;
        }
    }

    /**
     * Remove a mail from the current envelope
     *
     * @param doc the mail to remove
     * @throws ClientException
     */
    public void removeEmail(DocumentModel doc) throws ClientException {

        MailEnvelope currentEnvelope = getCurrentEnvelope();
        MailEnvelopeItem item = doc.getAdapter(MailEnvelopeItem.class);
        currentEnvelope.removeMailEnvelopeItem(item, documentManager);

    }

}
