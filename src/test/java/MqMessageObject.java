import dk.jdsj.mq.MqItem;
import dk.jdsj.mq.MqSerializable;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MqSerializable
public class MqMessageObject {
    @MqItem(length = 12)
    private String string;
    @MqItem(length = 6, padding = '0', rightPad = false)
    private int primitiveInt;
    @MqItem(length = 6, padding = '0', rightPad = false)
    private Integer integer;
    @MqItem(length = 1, trueChar = 'J', falseChar = 'N')
    private Boolean bool;
    @MqItem(length = 1, trueChar = 'J', falseChar = 'N')
    private boolean primitiveBool;
    @MqItem(length = 6, padding = '0', rightPad = false)
    private long primitiveLong;
    @MqItem(length = 6, padding = '0', rightPad = false)
    private Long objLong;
    @MqItem(length = 8, datePattern = "ddMMyyyy")
    private Date date;

}
