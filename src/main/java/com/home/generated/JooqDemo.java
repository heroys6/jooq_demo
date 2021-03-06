/**
 * This class is generated by jOOQ
 */
package com.home.generated;


import com.home.generated.tables.Authors;
import com.home.generated.tables.BookAuthor;
import com.home.generated.tables.Books;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JooqDemo extends SchemaImpl {

	private static final long serialVersionUID = 1785461696;

	/**
	 * The reference instance of <code>jooq_demo</code>
	 */
	public static final JooqDemo JOOQ_DEMO = new JooqDemo();

	/**
	 * No further instances allowed
	 */
	private JooqDemo() {
		super("jooq_demo");
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			Authors.AUTHORS,
			Books.BOOKS,
			BookAuthor.BOOK_AUTHOR);
	}
}
