/**
 *
 */
package org.levin.ikexpression.parser;

/**
 * 表达式词元
 * @@author 林良益，卓诗垚
 * @version 2.0
 * Sep 23, 2008
 *
 */
public class Element {
    private String text;
    private ElementType type;//类型
    private int index;//元素在表达式中的起始索引号，从0算起

    public static Element create(String text, int index, ElementType type) {
        return new Element(text, index, type);
    }

    private Element(String text, int index, ElementType type) {
        this.text = text;
        this.index = index;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
