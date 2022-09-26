import dk.jdsj.mq.MqSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MqSerializerTest {

    private MqMessageObject message;
    private MqSerializer serializer;

    @BeforeAll
    public void setup() throws ParseException {
        serializer = new MqSerializer();
        message = MqMessageObject.builder()
                .integer(123)
                .primitiveInt(22)
                .string("string")
                .bool(true)
                .primitiveBool(false)
                .objLong(321L)
                .primitiveLong(123)
                .date(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"))
                .primitiveDouble(123.0)
                .objDouble(321.0)
                .build();
    }

    @Test
    public void test_serializer() throws Exception {
        var messageString = serializer.getMqMessageString(message);
        var message = "string      000022000123JN000123000321101020210123.00321.0";
        assertEquals(message, messageString);
    }
}
