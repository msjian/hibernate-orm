// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.hibernate.dialect;

import java.sql.Types;

/**
 * created by msjian on 2019年12月16日
 * Detailled comment
 *
 */
public class SnowflakeDialect extends Dialect {

    /**
     * snowflakeDialect constructor.
     */
    public SnowflakeDialect() {
		super();

        // https://docs.snowflake.net/manuals/sql-reference/intro-summary-data-types.html
        // registerColumnType(Types.NUMERIC, "NUMBER"); //$NON-NLS-1$
        // registerColumnType(Types.NUMERIC, "NUMERIC"); //$NON-NLS-1$
        // registerColumnType(Types.NUMERIC, "DECIMAL"); //$NON-NLS-1$
        //
        // registerColumnType(Types.INTEGER, "INTEGER"); //$NON-NLS-1$
        // registerColumnType(Types.SMALLINT, "SMALLINT"); //$NON-NLS-1$
        // registerColumnType(Types.INTEGER, "INT"); //$NON-NLS-1$
        // registerColumnType(Types.BIGINT, "BIGINT"); //$NON-NLS-1$
        //
        // registerColumnType(Types.VARCHAR, "VARCHAR"); //$NON-NLS-1$
        // registerColumnType(Types.CHAR, "varchar(1)"); //$NON-NLS-1$
        // registerColumnType(Types.VARCHAR, "varchar($l)"); //$NON-NLS-1$
        // registerColumnType(Types.VARCHAR, "STRING"); //$NON-NLS-1$
        // registerColumnType(Types.VARCHAR, "TEXT"); //$NON-NLS-1$
        // registerColumnType(Types.BINARY, "BINARY"); //$NON-NLS-1$
        // registerColumnType(Types.VARBINARY, "VARBINARY"); //$NON-NLS-1$
        //
        // registerColumnType(Types.BOOLEAN, "BOOLEAN"); //$NON-NLS-1$
        //
        // registerColumnType(Types.DOUBLE, "DOUBLE"); //$NON-NLS-1$
        // registerColumnType(Types.DOUBLE, "REAL"); //$NON-NLS-1$
        // registerColumnType(Types.DOUBLE, "FLOAT"); //$NON-NLS-1$
        // registerColumnType(Types.DOUBLE, "FLOAT4"); //$NON-NLS-1$
        // registerColumnType(Types.DOUBLE, "FLOAT8"); //$NON-NLS-1$
        // registerColumnType(Types.DOUBLE, "DOUBLE PRECISION"); //$NON-NLS-1$
        //
        // registerColumnType(Types.DATE, "DATE"); //$NON-NLS-1$
        // registerColumnType(Types.TIME, "TIME"); //$NON-NLS-1$
        // registerColumnType(Types.TIMESTAMP, "TIMESTAMP"); //$NON-NLS-1$
        // registerColumnType(Types.TIMESTAMP, "DATETIME"); //$NON-NLS-1$
        // registerColumnType(Types.TIMESTAMP, "TIMESTAMP_LTZ"); //$NON-NLS-1$
        // registerColumnType(Types.TIMESTAMP, "TIMESTAMP_NTZ"); //$NON-NLS-1$
        // registerColumnType(Types.TIMESTAMP, "TIMESTAMP_TZ"); //$NON-NLS-1$
        // registerColumnType(Types.ARRAY, "ARRAY"); //$NON-NLS-1$

        registerColumnType(Types.BIGINT, "numeric(19,0)");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.INTEGER, "int");
        registerColumnType(Types.CHAR, "char(1)");
        registerColumnType(Types.VARCHAR, "varchar($l)");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.DOUBLE, "double precision");
        registerColumnType(Types.DATE, "datetime");
        registerColumnType(Types.TIME, "datetime");
        registerColumnType(Types.TIMESTAMP, "datetime");
        registerColumnType(Types.VARBINARY, "varbinary($l)");
        registerColumnType(Types.NUMERIC, "numeric($p,$s)");
        registerColumnType(Types.CLOB, "text");
    }
    
    public boolean supportsSequences() {
        return true;
    }

    public String getQuerySequencesString() {
        return "SHOW SEQUENCES"; //$NON-NLS-1$
    }

	public String getCreateSequenceString(String sequenceName) {
        return "create sequence " + sequenceName; //$NON-NLS-1$
	}
	public String getDropSequenceString(String sequenceName) {
        return "drop sequence " + sequenceName + " restrict"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public String getSequenceNextValString(String sequenceName) {
        return "select " + getSelectSequenceNextValString(sequenceName); //$NON-NLS-1$
	}

	public String getSelectSequenceNextValString(String sequenceName) {
        return sequenceName + ".nextval"; //$NON-NLS-1$
	}

    public boolean supportsIdentityColumns() {
        // when true, create datamart sn2 is ok,
        // but cannot support insert data, get NPE on IdentityGenerator.java:118
        // can fix above error by set supportsInsertSelectIdentity false, but will
        // need getIdentitySelectString()
        // so set to false
        return false;
    }

    public boolean supportsInsertSelectIdentity() {
        // if it is true, then get NPE at IdentityGenerator.java:118
        return false;
	}

    // public String getIdentitySelectString() {
        // when supportsInsertSelectIdentity is false, then need this
        // not found support way
    // return ""; //$NON-NLS-1$
    // }

    public String getIdentityColumnString() {
        return "identity not null"; //$NON-NLS-1$
    }

    public boolean supportsTemporaryTables() {
        return true;
    }

	public boolean supportsLimit() {
		return true;
	}
	public boolean supportsLimitOffset() {
		return true;
	}
	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}
	public String getCurrentTimestampSelectString() {
        return "select current_timestamp"; //$NON-NLS-1$
	}

    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

}
