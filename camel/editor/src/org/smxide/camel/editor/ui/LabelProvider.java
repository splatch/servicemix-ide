package org.smxide.camel.editor.ui;


import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansModelElementTypes;
import org.springframework.ide.eclipse.beans.ui.BeansUIImages;
import org.springframework.ide.eclipse.beans.ui.namespaces.DefaultNamespaceLabelProvider;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.ISourceModelElement;


public class LabelProvider extends DefaultNamespaceLabelProvider {

	@Override
	public Image getImage(ISourceModelElement element, IModelElement context,
		boolean isDecorating) {

		if (element.getElementType() == IBeansModelElementTypes.BEAN_TYPE) {
			IBean bean = (IBean) element;
			if (bean.getClassName().contains("org.apache.servicemix.file")) {
				return Images.FILE_ENDPOINT.createImage();
			} else {
				return BeansUIImages.getImage(BeansUIImages.IMG_OBJS_BEAN);
			}
		}

		return super.getImage(element, context, isDecorating);
	}

	@Override
	public String getText(ISourceModelElement element, IModelElement context,
		boolean isDecorating) {

		if (element.getElementType() == IBeansModelElementTypes.BEAN_TYPE) {
			IBean bean = (IBean) element;
			if (bean.getClassName().contains("org.apache.servicemix.file")) {
				return "File endpoint (" /*+ Naming.getTypedStringFromModel(bean, "service") + ")"*/;
			}
		}

		return super.getText(element, context, isDecorating);
	}
}
