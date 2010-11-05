package org.smxide.camel.editor.editor;

import org.smxide.camel.editor.editor.hyperlink.MethodElementHyperlinkCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.BeanHyperlinkCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.NamespaceHyperlinkDetectorSupport;

/**
 * Class responsible for linking XML content with Java code.
 * 
 * @author luke@code-house.org
 */
public class CamelNamespaceHyperlinkDetector extends NamespaceHyperlinkDetectorSupport {

	@Override
	public void init() {
		BeanHyperlinkCalculator beanHyperlink = new BeanHyperlinkCalculator();
		registerHyperlinkCalculator("process", "ref", beanHyperlink);
		registerHyperlinkCalculator("transacted", "ref", beanHyperlink);
		registerHyperlinkCalculator("aggregate", "strategyRef", beanHyperlink);
		registerHyperlinkCalculator("split", "strategyRef", beanHyperlink);
		registerHyperlinkCalculator("enrich", "strategyRef", beanHyperlink);

		registerHyperlinkCalculator("method", "bean", beanHyperlink);
		registerHyperlinkCalculator("method", "method", new MethodElementHyperlinkCalculator());
	}

}
