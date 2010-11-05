package org.smxide.camel.editor.editor;

import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansModelElementTypes;
import org.springframework.ide.eclipse.beans.ui.namespaces.DefaultNamespaceLabelProvider;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.ISourceModelElement;

public class CamelNamespaceLabelProvider extends DefaultNamespaceLabelProvider {

	@Override
	public Image getImage(ISourceModelElement element, IModelElement context,
		boolean isDecorating) {

		if (element.getElementType() == IBeansModelElementTypes.BEAN_TYPE) {
			IBean bean = (IBean) element;
			if (bean.getClassName().contains("org.apache.camel")) {
				return Images.CAMEL.createImage();
//			} else {
//				return BeansUIImages.getImage(BeansUIImages.IMG_OBJS_BEAN);
			}
		}

		return super.getImage(element, context, isDecorating);
	}

	@Override
	public String getText(ISourceModelElement element, IModelElement context,
		boolean isDecorating) {

		if (element.getElementType() == IBeansModelElementTypes.BEAN_TYPE) {
			IBean bean = (IBean) element;
			if (bean.getClassName().contains("org.apache.camel")) {
				return "Camel Context " + bean.getClassName();
			}
		}

		return super.getText(element, context, isDecorating);
	}
}
