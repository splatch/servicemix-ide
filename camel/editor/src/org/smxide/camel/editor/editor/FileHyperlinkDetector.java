package org.smxide.camel.editor.editor;

import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.ClassHyperlinkCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.NamespaceHyperlinkDetectorSupport;

public class FileHyperlinkDetector extends NamespaceHyperlinkDetectorSupport {

	@Override
	public void init() {
		ClassHyperlinkCalculator javaElement = new ClassHyperlinkCalculator();
		registerHyperlinkCalculator("sender", "marshaler", javaElement);
	}

}
