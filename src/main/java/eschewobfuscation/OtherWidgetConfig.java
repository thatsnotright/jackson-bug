package eschewobfuscation;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Store widget specific configuration in a custom subclass for each widget
 *
 * @author Rob Elsner
 */
public class OtherWidgetConfig extends WidgetConfig {
	private Map<String, Object> settings = Maps.newHashMap();

	public OtherWidgetConfig() {
		//  nothing to do!
	}

	private String someField;

	public String getSomeField() {
		return someField;
	}

	public void setSomeField(String someField) {
		this.someField = someField;
	}

}
