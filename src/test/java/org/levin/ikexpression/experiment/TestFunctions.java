/**
 *
 */
package org.levin.ikexpression.experiment;

import org.junit.Ignore;

/**
 * @author linly
 */
@Ignore
public class TestFunctions {
    /**
     * 自己定义的以Object为参数的Java方法
     * @param o
     * @return
     */
    public String sayHello(Object o) {
        if (o instanceof Integer) {
            return "Integer";
        } else if (o instanceof Double) {
            return "Double";
        } else {
            return "Float";
        }
    }

    public void sayHello(double o) {
        System.out.print(o);
    }
}
