package org.smxide.camel.editor.editor.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistProposalRecorder;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.PropertyBeanReferenceContentAssistCalculator;

/**
 * Base class for predefined type reference calculators.
 * 
 * @author luke@code-house.org
 */
@SuppressWarnings("rawtypes") // lack of generics? Doesn't matter in runtime
public class TypedReferenceCalculator extends PropertyBeanReferenceContentAssistCalculator {

	private List<String> types;

	public TypedReferenceCalculator(List<String> types) {
		this.types = types;
	}

	public TypedReferenceCalculator(String ... type) {
		this(Arrays.asList(type));
	}

	public TypedReferenceCalculator(Class ... types) {
		this(classToString(types));
	}

	private static List<String> classToString(Class[] types) {
		List<String> list = new ArrayList<String>();
		for (Class clazz : types) {
			list.add(clazz.getName());
		}
		return list;
	}

	@Override
	protected List<String> calculateType(IContentAssistContext context,
		IContentAssistProposalRecorder recorder) {
		return types;
	}
}
