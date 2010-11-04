package org.smxide.camel.editor.editor.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistProposalRecorder;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.PropertyBeanReferenceContentAssistCalculator;

public class AbstractTypeReferenceCalculator extends PropertyBeanReferenceContentAssistCalculator {

	private List<String> types;

	public AbstractTypeReferenceCalculator(List<String> types) {
		this.types = types;
	}

	public AbstractTypeReferenceCalculator(String ... type) {
		this(Arrays.asList(type));
	}

	public AbstractTypeReferenceCalculator(Class ... types) {
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
