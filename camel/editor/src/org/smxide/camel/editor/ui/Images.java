package org.smxide.camel.editor.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.smxide.camel.editor.Activator;
import org.springframework.ide.eclipse.beans.ui.BeansUIPlugin;

public class Images {

	public final static ImageDescriptor FILE_ENDPOINT = getImageDescriptor("/icons/obj16/file.png");

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
