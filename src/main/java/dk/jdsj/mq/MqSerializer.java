package dk.jdsj.mq;

import dk.jdsj.exceptions.MqSerializationException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

public class MqSerializer {
    private void checkIfSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new MqSerializationException("The object to serialize is null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(MqSerializable.class)) {
            throw new MqSerializationException("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with MqSerializable");
        }
    }

    public String getMqMessageString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        StringBuilder builder = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(MqItem.class)) {
                builder.append(getPaddedString(field, object));
            }
        }
        return builder.toString();
    }

    private String getPaddedString(Field field, Object object) throws Exception {
        boolean isRight = field.getAnnotation(MqItem.class).rightPad();
        char padding = field.getAnnotation(MqItem.class).padding();
        int length = field.getAnnotation(MqItem.class).length();
        if (isRight) {
            return StringUtils.rightPad(castToString(field, object), length, padding);
        }
        return StringUtils.leftPad(castToString(field, object), length, padding);
    }

    private String castToString(Field field, Object object) throws IllegalAccessException {
        switch (field.getType().getSimpleName()) {
            case "Boolean":
            case "boolean": return getFlag(field, object);
            default: return String.valueOf(field.get(object));
        }
    }

    private String getFlag(Field field, Object object) throws IllegalAccessException {
        char trueChar = field.getAnnotation(MqItem.class).trueChar();
        char falseChar = field.getAnnotation(MqItem.class).falseChar();
        if((Boolean) field.get(object)) {
            return trueChar + "";
        }
        return falseChar + "";
    }
}
