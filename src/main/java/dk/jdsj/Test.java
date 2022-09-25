package dk.jdsj;

import dk.jdsj.mq.MqItem;
import dk.jdsj.mq.MqSerializable;

@MqSerializable
public class Test {

    @MqItem(length = 10)
    private String test;
    @MqItem(length = 6, rightPad = false, padding = '0')
    private int test1;
    @MqItem(length = 5)
    private String test2;


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }
}
