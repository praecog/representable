package hu.vr.representable;

import hu.vr.representable.renderer.RendererService;

/**
 * Convenience class for extension.
 * @author Viktor Remeli
 *
 */
public abstract class AbstractXmlLeaf implements XmlRepresentable<Tag, Attribute> {
	@Override
	public String acceptRenderer(RendererService renderer) {
		return renderer.render(this);
	}
}
