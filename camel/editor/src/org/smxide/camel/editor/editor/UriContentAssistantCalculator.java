package org.smxide.camel.editor.editor;

import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistProposalRecorder;

public class UriContentAssistantCalculator implements IContentAssistCalculator {

	public void computeProposals(IContentAssistContext arg0,
			IContentAssistProposalRecorder arg1) {
		/**
		 * Record a calculated content assist proposal
		 * @param image the image to show in the content assist
		 * @param relevance the sorting relevance
		 * @param displayText the text to display in the content assist UI widget
		 * @param replaceText the replace text added to the document
		 */
		arg1.recordProposal(null, 0, "File endpoint", "file:");
		arg1.recordProposal(null, 0, "Zone endpoint", "zone:");
		arg1.recordProposal(null, 0, "Ftp endpoint", "ftp:");
	}

}
