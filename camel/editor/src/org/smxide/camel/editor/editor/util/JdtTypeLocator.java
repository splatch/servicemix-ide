package org.smxide.camel.editor.editor.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IType;
import org.springframework.ide.eclipse.beans.ui.editor.util.BeansEditorUtils;
import org.springframework.ide.eclipse.core.java.JdtUtils;
import org.w3c.dom.Node;

public class JdtTypeLocator {

	public static IType getMethodElementBeanType(Node node, IFile file) {
		if (BeansEditorUtils.hasAttribute(node, "bean")) {
			String ref = BeansEditorUtils.getAttribute(node, "bean");

			if (ref != null) {
				String className = BeansEditorUtils.getClassNameForBean(file, node
					.getOwnerDocument(), ref);
				return JdtUtils.getJavaType(file.getProject(), className);
			}
		}
		return null;
	}

}
