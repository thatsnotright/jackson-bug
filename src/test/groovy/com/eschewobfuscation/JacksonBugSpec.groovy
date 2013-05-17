package com.eschewobfuscation

import com.eschewobfuscation.OtherWidgetConfig
import spock.lang.*
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.eschewobfuscation.Widget

/**
 *
 * @author Rob Elsner
 */
class JacksonBugSpec extends Specification {
    ObjectMapper objectMapper = new ObjectMapper() {{

        configure(MapperFeature.AUTO_DETECT_SETTERS, true);
        configure(MapperFeature.USE_GETTERS_AS_SETTERS, false);
        configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, false);
        configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        configure(MapperFeature.AUTO_DETECT_GETTERS, true);
        configure(MapperFeature.AUTO_DETECT_IS_GETTERS, true);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }};


    def "test WidgetConfig"() {
        given:
        String json="""{
  "type": "media",
  "label": "wf",
  "settings": {
    "title": "wf",
    "allow_fullscreen": false,
    "infinite_scroll": false,
    "disable_auto_load": false
  }
}"""
        when:
        println json
        Widget w = objectMapper.readValue(json, Widget)

        then:
        assert w.label == "wf"
        assert w.settings.title == "wf"

        String des = objectMapper.writeValueAsString(w)

        assert des.count("type") == 1
        assert des.contains(""""title":"wf""")
    }

    def "test OtherWidgetConfig"() {
        given:
        String json="""{
  "type": "other",
  "label": "wf",
  "settings": {
    "title": "wf",
    "allow_fullscreen": false,
    "infinite_scroll": false,
    "disable_auto_load": false,
    "someField": "a value",
    "sprockets": [{"name":"Sprocket A"},{"name":"Sprocket B"}]
  }
}"""
        when:
        println json
        Widget w = objectMapper.readValue(json, Widget)

        then:
        assert w.label == "wf"
        assert w.settings.title == "wf"

        assert w.settings instanceof OtherWidgetConfig
        assert w.settings.someField == "a value"
        assert w.settings.sprockets.size() == 2
        assert w.settings.sprockets.first().name == "Sprocket A"

        String des = objectMapper.writeValueAsString(w)

        assert des.count("type") == 1
        assert des.contains(""""title":"wf""")
    }
}
