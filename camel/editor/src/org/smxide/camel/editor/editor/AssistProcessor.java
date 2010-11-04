package org.smxide.camel.editor.editor;

import org.apache.camel.Processor;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.spi.TransactedPolicy;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.ui.internal.contentassist.ContentAssistRequest;
import org.smxide.camel.editor.editor.calculator.TypedReferenceCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.ClassContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.DefaultContentAssistContext;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.DefaultContentAssistProposalRecorder;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistProposalRecorder;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.NamespaceContentAssistProcessorSupport;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.PackageContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.namespaces.NamespaceUtils;

public class AssistProcessor extends NamespaceContentAssistProcessorSupport {

	@Override
	public void init() {
		registerContentAssistCalculator("http://camel.apache.org/schema/spring", "*",
			"process", "ref", new TypedReferenceCalculator(Processor.class));

		registerContentAssistCalculator("transacted", "ref", new TypedReferenceCalculator(TransactedPolicy.class));

		TypedReferenceCalculator aggregatuibStrategyCalculator = new TypedReferenceCalculator(
			AggregationStrategy.class);

		registerContentAssistCalculator("aggregate", "strategyRef", aggregatuibStrategyCalculator);
		registerContentAssistCalculator("split", "strategyRef", aggregatuibStrategyCalculator);
		registerContentAssistCalculator("enrich", "strategyRef", aggregatuibStrategyCalculator);
		registerContentAssistCalculator("uri", new UriContentAssistantCalculator());

		registerContentAssistCalculator("package", null, new PackageContentAssistCalculator());
	}

	@Override
	protected void computeTagInsertionProposals(ContentAssistRequest request, IDOMNode node) {
		String parentNodeName = null;
		String parentNamespaceUri = null;

		IDOMNode parentNode = (IDOMNode) node.getParentNode();
		if (parentNode != null) {
			parentNodeName = parentNode.getLocalName();
			parentNamespaceUri = parentNode.getNamespaceURI();
		}
		
		// make sure the for old-style DTDs we assume the default namespace
		if (parentNamespaceUri == null) {
			parentNamespaceUri = NamespaceUtils.DEFAULT_NAMESPACE_URI;
		}

		IContentAssistCalculator calculator = new ClassContentAssistCalculator(true);

		IContentAssistContext context = new DefaultContentAssistContext(request, "*", "");
		IContentAssistProposalRecorder recorder = new DefaultContentAssistProposalRecorder(request);

		if (calculator != null) {
			calculator.computeProposals(context, recorder);
		}
	}
}
