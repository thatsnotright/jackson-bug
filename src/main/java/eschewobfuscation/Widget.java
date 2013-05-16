package eschewobfuscation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Widget {
	private List<String> feeds = Lists.newArrayList();
	private Map<String, Object> widgetData = Maps.newHashMap();
	private String label;

	WidgetConfig config;

	private String type;

	public Widget() {
	}

	/**
	 * Returns the label for this widget.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label for this widget.  No two widgets within a given site may use the same label.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	/**
	 * Sets the type for this widget.
	 */
	public void setType(String type) {
		this.type = type;
	}
	@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type", visible=true)
	@JsonSubTypes({
		@JsonSubTypes.Type(name="media", value=WidgetConfig.class),
		@JsonSubTypes.Type(name="other", value=OtherWidgetConfig.class)
	})
	public WidgetConfig getSettings() {
		return this.config;
	}
	public void setSettings(WidgetConfig settings) {
		this.config = settings;
	}
}
