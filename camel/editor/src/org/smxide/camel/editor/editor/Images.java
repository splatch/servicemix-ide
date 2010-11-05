package org.smxide.camel.editor.editor;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.smxide.camel.editor.Activator;
import org.springframework.ide.eclipse.beans.ui.BeansUIPlugin;

public class Images {

	public final static ImageDescriptor CAMEL = getImageDescriptor("/icons/full/obj16/entity.gif");

	private static ImageDescriptor getImageDescriptor(String loc) {
		try {
			ImageDescriptor result = ImageDescriptor.createFromURL(
				new URL(Activator.getDefault().getBundle()
					.getEntry("/"), loc));
			return result;
		} catch (MalformedURLException e) {
			BeansUIPlugin.log(e);
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}
}
