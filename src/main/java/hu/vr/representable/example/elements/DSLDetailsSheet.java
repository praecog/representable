package hu.vr.representable.example.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * A key-value sheet representation giving an overview
 * of the most important (header) details of a Directly Secured Loan. 
 */
public class DSLDetailsSheet extends AbstractHtmlContainer {
	
	private final DirectlySecuredLoan dsl;
	
	public DSLDetailsSheet(DirectlySecuredLoan dsl) {
		super();
		this.dsl = dsl;
	}

	@Override
	public List<XmlRepresentable<? extends HtmlTag, ? extends HtmlAttribute>> getChildren() {
		List<XmlRepresentable<? extends HtmlTag, ? extends HtmlAttribute>> children = new ArrayList<>();
		if(dsl!=null) {
			children.add(new KeyValuePair("Credit contract nr", dsl.getCreditContractNr()));
			children.add(new KeyValuePair("Account ID", dsl.getAccountId()));
			children.add(new KeyValuePair("Loan name", dsl.getLoanName()));
			children.add(new KeyValuePair("Limit", dsl.getLimit()));
			children.add(new KeyValuePair("Risk value", dsl.getRiskValue()));
			children.add(new KeyValuePair("Covered amount", dsl.getCoveredAmt()));
		}
		else {
			children.add(new KeyValuePair("Directly Secured Loan", "none"));
		}
		
		return children;
	}

	@Override
	public HtmlContainerTag getTag() {
		return HtmlContainerTag.DomainElement.table;
	}

	@Override
	public Map<? extends HtmlContainerAttribute, AttributeValue> getAttributes() {
		return null;
	}

	@Override
	public TextContent getContent() {
		return null;
	}

}
