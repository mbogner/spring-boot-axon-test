package pm.mbo.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonSerializer {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String serialize(final Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }

    private JsonSerializer() {
        throw new IllegalAccessError();
    }
}
