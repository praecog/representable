package hu.vr.representable.html.tags;

import hu.vr.representable.Tag;

public interface HtmlContainerTag extends HtmlTag {
	public enum Domain implements HtmlContainerTag{
		div,
		p,
		a;
		
		public static final Domain tagName(String tagName) {
			if(tagName==null) {
				return null;
			}
			return Domain.valueOf(tagName.replaceAll("[^a-zA-Z0-9_:.]","").replaceFirst("^[^a-zA-Z_:]*", "").toLowerCase());
		}
		
		public static final Domain tag(Tag tag) {
			return tagName(tag.toString());
		}
	}
}
