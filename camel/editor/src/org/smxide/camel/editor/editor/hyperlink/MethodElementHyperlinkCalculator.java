package org.smxide.camel.editor.editor.hyperlink;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.text.IDocument;
import org.smxide.camel.editor.editor.util.JdtTypeLocator;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.MethodHyperlinkCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.util.BeansEditorUtils;
import org.w3c.dom.Node;

/**
 * Hyperlink detector for &lt;method bean=.. method=... /&gt; element.
 * 
 * @author luke@code-house.org
 */
public class MethodElementHyperlinkCalculator extends MethodHyperlinkCalculator {

	@Override
	protected IType calculateType(String name, String target, Node node,
		Node parentNode, IDocument document) {
		return JdtTypeLocator.getMethodElementBeanType(node,
			BeansEditorUtils.getFile(document));
	}

}