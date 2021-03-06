/**
 * This class is generated by jOOQ
 */
package com.home.generated.tables.records;


import com.home.generated.tables.Authors;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;
import java.sql.Timestamp;


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
public class AuthorsRecord extends UpdatableRecordImpl<AuthorsRecord> implements Record3<Long, String, Timestamp> {

	private static final long serialVersionUID = 1472216299;

	/**
	 * Setter for <code>jooq_demo.authors.id</code>.
	 */
	public void setId(Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>jooq_demo.authors.id</code>.
	 */
	public Long getId() {
		return (Long) getValue(0);
	}

	/**
	 * Setter for <code>jooq_demo.authors.full_name</code>.
	 */
	public void setFullName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>jooq_demo.authors.full_name</code>.
	 */
	public String getFullName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>jooq_demo.authors.birthday</code>.
	 */
	public void setBirthday(Timestamp value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>jooq_demo.authors.birthday</code>.
	 */
	public Timestamp getBirthday() {
		return (Timestamp) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Long> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Long, String, Timestamp> fieldsRow() {
		return (Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Long, String, Timestamp> valuesRow() {
		return (Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field1() {
		return Authors.AUTHORS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Authors.AUTHORS.FULL_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field3() {
		return Authors.AUTHORS.BIRTHDAY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getFullName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value3() {
		return getBirthday();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthorsRecord value1(Long value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthorsRecord value2(String value) {
		setFullName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthorsRecord value3(Timestamp value) {
		setBirthday(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthorsRecord values(Long value1, String value2, Timestamp value3) {
		value1(value1);
		value2(value2);
		value3(value3);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AuthorsRecord
	 */
	public AuthorsRecord() {
		super(Authors.AUTHORS);
	}

	/**
	 * Create a detached, initialised AuthorsRecord
	 */
	public AuthorsRecord(Long id, String fullName, Timestamp birthday) {
		super(Authors.AUTHORS);

		setValue(0, id);
		setValue(1, fullName);
		setValue(2, birthday);
	}
}
