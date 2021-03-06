package hu.vr.representable.example.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.vr.representable.RendererService;
import hu.vr.representable.XmlRepresentable;
import hu.vr.representable.example.model.DirectlySecuredLoan;
import hu.vr.representable.html.AbstractHtmlContainer;
import hu.vr.representable.taxonomy.AttributeValue;
import hu.vr.representable.taxonomy.TextContent;
import hu.vr.representable.taxonomy.html.attributes.HtmlAttribute;
import hu.vr.representable.taxonomy.html.attributes.HtmlContainerAttribute;
import hu.vr.representable.taxonomy.html.tags.HtmlContainerTag;
import hu.vr.representable.taxonomy.html.tags.HtmlTag;

/**
 * A sketchy representation of DirectlySecuredLoan, done by composition.
 * <br>
 * But it could equally well be done through inheritance
 * (extends DirectlySecuredLoan, bypassing AbstractHtmlContainer convenience class and implementing
 * {@link hu.vr.representable.XmlRepresentableContainer}&lt;HtmlContainerTag, HtmlContainerAttribute, HtmlTag, HtmlAttribute&gt;).
 */
public class DSLShallow extends AbstractHtmlContainer {
	
	private final DirectlySecuredLoan dsl;
	private final List<XmlRepresentable<? extends HtmlTag, ? extends HtmlAttribute>> children = new ArrayList<>();
	Map<HtmlContainerAttribute, AttributeValue> attributes = new HashMap<>();
	
	public DSLShallow(DirectlySecuredLoan dsl) {
		super();
		this.dsl = dsl;
		children.add(new DSLDetailsSheet(this.dsl));
		this.setStyle("background-color:lightblue;");
	}
	
	public void setStyle(String styleCss) {
		attributes.put(HtmlContainerAttribute.DomainElement.style, AttributeValue.attrValue(styleCss));
	}

	@Override
	public final HtmlContainerTag getTag() {
		return HtmlContainerTag.DomainElement.div;
	}

	@Override
	public final TextContent getContent() {
		return TextContent.text("Directly Secured Loan");
	}

	@Override
	public String acceptRenderer(RendererService renderer) {
		return renderer.render(this);
	}

	@Override
	public Map<? extends HtmlContainerAttribute, AttributeValue> getAttributes() {
		return attributes;
	}

	@Override
	public List<XmlRepresentable<? extends HtmlTag, ? extends HtmlAttribute>> getChildren() {
		return children;
	}

}
