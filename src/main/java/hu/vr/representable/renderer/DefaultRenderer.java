package hu.vr.representable.renderer;

import java.util.Map.Entry;

import hu.vr.representable.XmlRepresentable;
import hu.vr.representable.XmlRepresentableContainer;
import hu.vr.representable.context.RepresentableContext;
import hu.vr.representable.taxonomy.AttributeValue;
import hu.vr.representable.taxonomy.Tag;

/**
 * XML renderer with support for standard &lt;...&gt;...&lt;/...&gt; tag syntax and compact &lt;.../&gt; tags.
 * Whitespaces are minified, but support for pretty-printing with newlines and indentations
 * is provided in the form of hooks (overrideable protected methods) for subclasses.
 */
public class DefaultRenderer extends AbstractRenderer {

	public DefaultRenderer() {
		super();
	}

	/**
	 * {@inheritDoc AbstractRenderer#AbstractRenderer(RepresentableContext)}
	 */
	public DefaultRenderer(RepresentableContext factory) {
		super(factory);
	}

	@Override
	public String render(Object object) {
		String result = super.render(object);
		if(result!=null && result.length()>0) {
			return result;
		}
		else if(object!=null) {
			return object.toString();
		}
		return "";
	}
	
	@Override
	public String render(XmlRepresentable<?,?> xmlRepr) {
		if(xmlRepr==null) {
			return ""; 
		}
		else if(xmlRepr.getTag()==null
				|| xmlRepr.getTag().toString()==null
				|| xmlRepr.getTag().toString().isEmpty()) {
			return renderContentOnly(xmlRepr);
		}
		else {
			return renderTag(xmlRepr);
		}
	}
	
	@Override
	public String render(XmlRepresentableContainer<?,?,?,?> xmlReprCont) {
		if(xmlReprCont==null) {
			return ""; 
		}
		else if(xmlReprCont.getTag()==null 
				|| xmlReprCont.getTag().toString()==null
				|| xmlReprCont.getTag().toString().isEmpty()) {
			return renderContentOnly(xmlReprCont);
		}
		else {
			return renderTag(xmlReprCont);
		}
	}
	
	protected String renderContentOnly(XmlRepresentable<?,?> xmlRepr) {
		return xmlRepr.getContent()==null ? "" : xmlRepr.getContent().toString();
	}
	
	/**
	 * Render leaf tag.
	 */
	protected String renderTag(XmlRepresentable<?,?> xmlRepr) {		
		StringBuilder sb = new StringBuilder();
		appendOpeningTag(sb, xmlRepr);
		if(!isCompactTag(xmlRepr)) {
			sb.append(renderContentOnly(xmlRepr));
			appendClosingTag(sb, xmlRepr);
		}
		return sb.toString();
	}
	
	/**
	 * Render container tag.
	 */
	protected String renderTag(XmlRepresentableContainer<?,?,?,?> xmlReprCont) {		
		StringBuilder sb = new StringBuilder();
		appendOpeningTag(sb, xmlReprCont);
		if(!isCompactTag(xmlReprCont)) {
			increaseDepth();
			sb.append(renderContentOnly(xmlReprCont));
			for(XmlRepresentable<?,?> child : xmlReprCont.getChildren()) {
				sb.append(child.acceptRenderer(this));
			}
			decreaseDepth();
			appendClosingTag(sb, xmlReprCont);
		}
		return sb.toString();
	}

	protected void appendOpeningTag(StringBuilder sb, XmlRepresentable<?,?> xmlRepr) {
		appendBeginningOfOpeningTag(sb, xmlRepr);
		if(isCompactTag(xmlRepr)) {
			sb.append("/>");
		}
		else {
			sb.append('>');
		}
	}
	
	protected void appendOpeningTag(StringBuilder sb, XmlRepresentableContainer<?,?,?,?> xmlRepr) {
		appendBeginningOfOpeningTag(sb, xmlRepr);
		if(isCompactTag(xmlRepr)) {
			sb.append("/>");
		}
		else {
			sb.append('>');
		}
	}
	
	protected void appendClosingTag(StringBuilder sb, XmlRepresentable<?,?> xmlRepr) {
		sb.append("</");
		sb.append(xmlRepr.getTag());
		sb.append('>');
	}
	
	private void appendBeginningOfOpeningTag(StringBuilder sb, XmlRepresentable<?,?> xmlRepr) {
		sb.append('<');
		sb.append(xmlRepr.getTag());
		if(xmlRepr.getAttributes()!=null) {
			for(Entry<?, AttributeValue> attributeEntry : xmlRepr.getAttributes().entrySet()) {
				sb.append(' ');
				sb.append(attributeEntry.getKey());
				sb.append("=\"");
				sb.append(attributeEntry.getValue());
				sb.append("\"");
			}
		}
	}

	/**
	 * @param xml element representation
	 * @return Tag can be written in [.../] compact form.
	 */
	protected boolean isCompactTag(XmlRepresentable<?,?> xmlRepr) {
		return xmlRepr.getContent()==null;
	}
	
	/**
	 * @param xml container element representation
	 * @return Tag can be written in [.../] compact form.
	 */
	protected boolean isCompactTag(XmlRepresentableContainer<?,?,?,?> xmlReprCont) {
		return xmlReprCont.getContent()==null 
				&& (xmlReprCont.getChildren()==null || xmlReprCont.getChildren().isEmpty());
	}
	
	/**
	 * Tag equality check.
	 */
	protected boolean elementHasTag(XmlRepresentable<?,?> element, Tag tag) {
		return element!=null && tag!=null && element.getTag()!=null
				&& tag.toString().equals( element.getTag().toString() );
	}
	
	/**
	 * Hook for subclasses
	 */
	protected void increaseDepth() {
		/* noop */
	}
	
	/**
	 * Hook for subclasses
	 */
	protected void decreaseDepth() {
		/* noop */
	}

}
