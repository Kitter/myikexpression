/**
 *
 */
package org.levin.ikexpression.parser.reader;

import org.levin.ikexpression.parser.Element;

/**
 * @author 林良益，卓诗垚
 * @version 2.0
 * Oct 9, 2008
 */
public interface ElementReader {
    Element read(ExpressionReader reader);
}
