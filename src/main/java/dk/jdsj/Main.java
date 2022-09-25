package dk.jdsj;

import dk.jdsj.mq.MqSerializer;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();

        test.setTest("blabl");
        test.setTest1(12);
        test.setTest2("s");

        MqSerializer serializer = new MqSerializer();
        try {
            System.out.println(serializer.getMqMessageString(test));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}