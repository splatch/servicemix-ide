package org.smxide.camel.editor.editor.calculator;

import org.eclipse.jdt.core.IType;
import org.smxide.camel.editor.editor.util.JdtTypeLocator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.MethodContentAssistCalculator;
import org.springframework.ide.eclipse.core.java.FlagsMethodFilter;
import org.springframework.ide.eclipse.core.java.IMethodFilter;

/**
 * Method filter which allow references to any public, non void method defined
 * by bean.
 *
 * @author luke@code-house.org
 */
public class PublicMethodCalculator extends MethodContentAssistCalculator {

	private static IMethodFilter FILTER;

	static {
		FILTER = new FlagsMethodFilter(FlagsMethodFilter.PUBLIC
			| FlagsMethodFilter.NOT_CONSTRUCTOR | FlagsMethodFilter.NOT_VOID
			| FlagsMethodFilter.NOT_STATIC, -1 /*Number of parameters in method*/);
	}

	public PublicMethodCalculator() {
		super(FILTER);
	}

	/**
	 * This method has to return type of the bean. It will be used by Spring IDE
	 * to filter methods.
	 */
	@Override
	protected IType calculateType(IContentAssistContext context) {
		return JdtTypeLocator.getMethodElementBeanType(context.getNode(), context
			.getFile());
	}

	

}
