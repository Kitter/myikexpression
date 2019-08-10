/**
 *
 */
package org.levin.ikexpression.datameta;

import org.levin.ikexpression.utils.Lists;

/**
 * 常量数据描述
 * @author 林良益，卓诗垚
 * @version 2.0
 * 2008-09-23
 */
public class Constant extends BaseDataMeta {

    public Constant(DataType dataType, Object value) {
        super(dataType, value);

        if (dataType == null) {
            throw new IllegalArgumentException("非法参数：数据类型为空");
        }
        //如果为集合类型，生成集合容器
        if (DataType.DATATYPE_LIST == dataType) {
            if (dataValue == null) {
                dataValue = Lists.newArrayList();
            }
        }
    }

    public Constant(Reference ref) {
        super(null, ref);
        this.setReference(true);
    }
}
