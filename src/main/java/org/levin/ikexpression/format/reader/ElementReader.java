/**
 * 
 */
package org.levin.ikexpression.format.reader;

import java.io.IOException;

import org.levin.ikexpression.format.Element;
import org.levin.ikexpression.format.ExpressionReader;
import org.levin.ikexpression.format.FormatException;

/**
 * @author 林良益，卓诗垚
 * @version 2.0 
 * Oct 9, 2008
 */
public interface ElementReader {
	Element read(ExpressionReader sr) throws FormatException, IOException;
}
