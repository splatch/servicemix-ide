package org.smxide.camel.editor.editor;

import org.apache.camel.Processor;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.spi.TransactedPolicy;
import org.smxide.camel.editor.editor.calculator.PublicMethodCalculator;
import org.smxide.camel.editor.editor.calculator.TypedReferenceCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.NamespaceContentAssistProcessorSupport;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.PropertyBeanReferenceContentAssistCalculator;

/**
 * Content assistant for camel-spring namespace.
 *
 * @author luke@code-house.org
 */
public class CamelNamespaceContentAssistant extends NamespaceContentAssistProcessorSupport {

	@Override
	public void init() {
		registerContentAssistCalculator("process", "ref",
			new TypedReferenceCalculator(Processor.class));

		registerContentAssistCalculator("transacted", "ref", 
			new TypedReferenceCalculator(TransactedPolicy.class));

		TypedReferenceCalculator aggregateStrategyCalculator = 
			new TypedReferenceCalculator(AggregationStrategy.class);

		registerContentAssistCalculator("aggregate", "strategyRef", aggregateStrategyCalculator);
		registerContentAssistCalculator("split", "strategyRef", aggregateStrategyCalculator);
		registerContentAssistCalculator("enrich", "strategyRef", aggregateStrategyCalculator);

		registerContentAssistCalculator("method", "bean", new PropertyBeanReferenceContentAssistCalculator());
		registerContentAssistCalculator("method", "method", new PublicMethodCalculator());

		// uri suggestions?
		// registerContentAssistCalculator("uri", new UriContentAssistantCalculator());
	}

}
