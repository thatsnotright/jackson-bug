package eschewobfuscation;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Store widget specific configuration in a custom subclass for each widget
 *
 * @author Rob Elsner
 */
public class WidgetConfig {
	private Map<String, Object> settings = Maps.newHashMap();

	public WidgetConfig() {
		//  nothing to do!
	}

	@JsonAnySetter
	public void set(String key, Object value) {
		settings.put(key, value);
	}

	/**
	 * Sets the client-controlled settings for this widget.
	 */
	@JsonAnyGetter
	public Map<String, Object> get() {
		return settings;
	}

	@JsonIgnore
	public Object get(String key) {
		return settings.get(key);
	}
}
