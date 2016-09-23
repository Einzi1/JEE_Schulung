/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * // Copyright (c) 1998, 2007, Oracle. All rights reserved.
 * 
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package oracle.toplink.essentials.platform.database;

import java.io.*;
import java.sql.*;
import java.util.*;
import oracle.toplink.essentials.exceptions.*;
import oracle.toplink.essentials.expressions.*;
import oracle.toplink.essentials.internal.databaseaccess.*;
import oracle.toplink.essentials.internal.expressions.*;
import oracle.toplink.essentials.internal.helper.*;
import oracle.toplink.essentials.queryframework.*;
import oracle.toplink.essentials.sessions.SessionProfiler;
import oracle.toplink.essentials.internal.sessions.AbstractRecord;
import oracle.toplink.essentials.internal.sessions.AbstractSession;

/**
 * <p><b>Purpose</b>: Provides Sybase specific behaviour.
 * <p><b>Responsibilities</b>:<ul>
 * <li> Native SQL for byte[], Date, Time, & Timestamp.
 * <li> Native sequencing using @@IDENTITY.
 * </ul>
 *
 * @since TOPLink/Java 1.0
 */
public class SybasePlatform extends oracle.toplink.essentials.platform.database.DatabasePlatform {
    // An array could be used here with the type being the index, but upon looking at the source some types are
    // assigned negative values, making them unusable as indexes without guessing at modifying them.
    // this attribute is used for registering output params in stored procedure calls.  JConnect 5.5 requires
    // that the API that accepts a string is called so we have a collection of strings to use.
    protected Hashtable typeStrings;

    protected Hashtable getTypeStrings() {
        if (typeStrings == null) {
            initializeTypeStrings();
        }
        return typeStrings;
    }

    protected synchronized void initializeTypeStrings() {
        if (typeStrings == null) {
            typeStrings = new Hashtable(30);
            typeStrings.put(new Integer(Types.ARRAY), "ARRAY");
            typeStrings.put(new Integer(Types.BIGINT), "BIGINT");
            typeStrings.put(new Integer(Types.BINARY), "BINARY");
            typeStrings.put(new Integer(Types.BIT), "BIT");
            typeStrings.put(new Integer(Types.BLOB), "BLOB");
            typeStrings.put(new Integer(Types.CHAR), "CHAR");
            typeStrings.put(new Integer(Types.CLOB), "CLOB");
            typeStrings.put(new Integer(Types.DATE), "DATE");
            typeStrings.put(new Integer(Types.DECIMAL), "DECIMAL");
            typeStrings.put(new Integer(Types.DOUBLE), "DOUBLE");
            typeStrings.put(new Integer(Types.FLOAT), "FLOAT");
            typeStrings.put(new Integer(Types.INTEGER), "INTEGER");
            typeStrings.put(new Integer(Types.JAVA_OBJECT), "JAVA_OBJECT");
            typeStrings.put(new Integer(Types.LONGVARBINARY), "LONGVARBINARY");
            typeStrings.put(new Integer(Types.LONGVARCHAR), "LONGVARCHAR");
            typeStrings.put(new Integer(Types.NULL), "NULL");
            typeStrings.put(new Integer(Types.NUMERIC), "NUMERIC");
            typeStrings.put(new Integer(Types.OTHER), "OTHER");
            typeStrings.put(new Integer(Types.REAL), "REAL");
            typeStrings.put(new Integer(Types.REF), "REF");
            typeStrings.put(new Integer(Types.SMALLINT), "SMALLINT");
            typeStrings.put(new Integer(Types.STRUCT), "STRUCT");
            typeStrings.put(new Integer(Types.TIME), "TIME");
            typeStrings.put(new Integer(Types.TIMESTAMP), "TIMESTAMP");
            typeStrings.put(new Integer(Types.TINYINT), "TINYINT");
            typeStrings.put(new Integer(Types.VARBINARY), "VARBINARY");
            typeStrings.put(new Integer(Types.VARCHAR), "VARCHAR");
        }
    }

    /**
     * INTERNAL:
     * If using native SQL then print a byte[] as '0xFF...'
     */
    protected void appendByteArray(byte[] bytes, Writer writer) throws IOException {
        if (usesNativeSQL() && (!usesByteArrayBinding())) {
            writer.write("0x");
            Helper.writeHexString(bytes, writer);
        } else {
            super.appendByteArray(bytes, writer);
        }
    }

    /**
     * INTERNAL:
     * Answer a platform correct string representation of a Date, suitable for SQL generation.
     * Native format: 'yyyy-mm-dd
     */
    protected void appendDate(java.sql.Date date, Writer writer) throws IOException {
        if (usesNativeSQL()) {
            writer.write("'");
            writer.write(Helper.printDate(date));
            writer.write("'");
        } else {
            super.appendDate(date, writer);
        }
    }

    /**
     * INTERNAL:
     * Write a timestamp in Sybase specific format (yyyy-mm-dd-hh.mm.ss.fff).
     */
    protected void appendSybaseTimestamp(java.sql.Timestamp timestamp, Writer writer) throws IOException {
        writer.write("'");
        writer.write(Helper.printTimestampWithoutNanos(timestamp));
        writer.write(':');

        // Must truncate the nanos to three decimal places,
        // it is actually a complex algorithm...
        String nanoString = Integer.toString(timestamp.getNanos());
        int numberOfZeros = 0;
        for (int num = Math.min(9 - nanoString.length(), 3); num > 0; num--) {
            writer.write('0');
            numberOfZeros++;
        }
        if ((nanoString.length() + numberOfZeros) > 3) {
            nanoString = nanoString.substring(0, (3 - numberOfZeros));
        }
        writer.write(nanoString);
        writer.write("'");
    }

    /**
     * INTERNAL:
     * Answer a platform correct string representation of a Time, suitable for SQL generation.
     * The time is printed in the ODBC platform independent format {t'hh:mm:ss'}.
     */
    protected void appendTime(java.sql.Time time, Writer writer) throws IOException {
        if (usesNativeSQL()) {
            writer.write("'");
            writer.write(Helper.printTime(time));
            writer.write("'");
        } else {
            super.appendTime(time, writer);
        }
    }

    /**
     * INTERNAL:
     * Answer a platform correct string representation of a Timestamp, suitable for SQL generation.
     * The date is printed in the ODBC platform independent format {d'YYYY-MM-DD'}.
     */
    protected void appendTimestamp(java.sql.Timestamp timestamp, Writer writer) throws IOException {
        if (usesNativeSQL()) {
            appendSybaseTimestamp(timestamp, writer);
        } else {
            super.appendTimestamp(timestamp, writer);
        }
    }

    /**
     * INTERNAL:
     * Answer a platform correct string representation of a Calendar, suitable for SQL generation.
     * The date is printed in the ODBC platform independent format {d'YYYY-MM-DD'}.
     */
    protected void appendCalendar(Calendar calendar, Writer writer) throws IOException {
        if (usesNativeSQL()) {
            appendSybaseCalendar(calendar, writer);
        } else {
            super.appendCalendar(calendar, writer);
        }
    }

    /**
     * INTERNAL:
     * Write a timestamp in Sybase specific format ( yyyy-mm-dd-hh.mm.ss.fff)
     */
    protected void appendSybaseCalendar(Calendar calendar, Writer writer) throws IOException {
        writer.write("'");
        writer.write(Helper.printCalendar(calendar));
        writer.write("'");
    }

    /**
     * INTERNAL:
     * Build operator.
     */
    public ExpressionOperator atan2Operator() {
        return ExpressionOperator.simpleTwoArgumentFunction(ExpressionOperator.Atan2, "ATN2");
    }

    protected Hashtable buildFieldTypes() {
        Hashtable fieldTypeMapping;

        fieldTypeMapping = new Hashtable();
        fieldTypeMapping.put(Boolean.class, new FieldTypeDefinition("BIT default 0", false, false));

        fieldTypeMapping.put(Integer.class, new FieldTypeDefinition("INTEGER", false));
        fieldTypeMapping.put(Long.class, new FieldTypeDefinition("NUMERIC", 19));
        fieldTypeMapping.put(Float.class, new FieldTypeDefinition("FLOAT(16)", false));
        fieldTypeMapping.put(Double.class, new FieldTypeDefinition("FLOAT(32)", false));
        fieldTypeMapping.put(Short.class, new FieldTypeDefinition("SMALLINT", false));
        fieldTypeMapping.put(Byte.class, new FieldTypeDefinition("SMALLINT", false));
        fieldTypeMapping.put(java.math.BigInteger.class, new FieldTypeDefinition("NUMERIC", 38));
        fieldTypeMapping.put(java.math.BigDecimal.class, new FieldTypeDefinition("NUMERIC", 38).setLimits(38, -19, 19));
        fieldTypeMapping.put(Number.class, new FieldTypeDefinition("NUMERIC", 38).setLimits(38, -19, 19));

        fieldTypeMapping.put(String.class, new FieldTypeDefinition("VARCHAR", 255));
        fieldTypeMapping.put(Character.class, new FieldTypeDefinition("CHAR", 1));
        
        fieldTypeMapping.put(Byte[].class, new FieldTypeDefinition("IMAGE", false));
        fieldTypeMapping.put(Character[].class, new FieldTypeDefinition("TEXT", false));
        fieldTypeMapping.put(byte[].class, new FieldTypeDefinition("IMAGE", false));
        fieldTypeMapping.put(char[].class, new FieldTypeDefinition("TEXT", false));
        fieldTypeMapping.put(java.sql.Blob.class, new FieldTypeDefinition("IMAGE", false));
        fieldTypeMapping.put(java.sql.Clob.class, new FieldTypeDefinition("TEXT", false));        
        
        fieldTypeMapping.put(java.sql.Date.class, new FieldTypeDefinition("DATETIME", false));
        fieldTypeMapping.put(java.sql.Time.class, new FieldTypeDefinition("DATETIME", false));
        fieldTypeMapping.put(java.sql.Timestamp.class, new FieldTypeDefinition("DATETIME", false));

        return fieldTypeMapping;
    }

    /**
     * INTERNAL:
     * Build the identity query for native sequencing.
     */
    public ValueReadQuery buildSelectQueryForNativeSequence() {
        ValueReadQuery selectQuery = new ValueReadQuery();
        StringWriter writer = new StringWriter();
        writer.write("SELECT @@IDENTITY");
        selectQuery.setSQLString(writer.toString());
        return selectQuery;
    }

    /**
     * INTERNAL:
     * Because each platform has different requirements for accessing stored procedures and
     * the way that we can combine resultsets and output params the stored procedure call
     * is being executed on the platform.  This entire process needs some serious refactoring to eliminate
     * the chance of bugs.
     */
    public Object executeStoredProcedure(DatabaseCall dbCall, PreparedStatement statement, DatabaseAccessor accessor, AbstractSession session) throws SQLException {
        Object result = null;
        ResultSet resultSet = null;
        if (!dbCall.getReturnsResultSet()) {
            accessor.executeDirectNoSelect(statement, dbCall, session);
            result = accessor.buildOutputRow((CallableStatement)statement, dbCall, session);

            //ReadAllQuery may be returning just output params, or they may be executing a DataReadQuery, which also
            //assumes a vector
            if (dbCall.areManyRowsReturned()) {
                Vector tempResult = new Vector();
                ((Vector)tempResult).add(result);
                result = tempResult;
            }
        } else {
            // start the process of procesing the result set and the output params.  this is specific to Sybase JConnect 5.5
            // as we must process the result set before the output params.
            session.startOperationProfile(SessionProfiler.STATEMENT_EXECUTE);
            try {
                resultSet = statement.executeQuery();
            } finally {
                session.endOperationProfile(SessionProfiler.STATEMENT_EXECUTE);
            }
            dbCall.matchFieldOrder(resultSet, accessor, session);

            // cursored result set and output params not supported because of database limitation
            if (dbCall.isCursorReturned()) {
                dbCall.setStatement(statement);
                dbCall.setResult(resultSet);
                return dbCall;
            }
            result = processResultSet(resultSet, dbCall, statement, accessor, session);

            if (dbCall.shouldBuildOutputRow()) {
                AbstractRecord outputRow = accessor.buildOutputRow((CallableStatement)statement, dbCall, session);
                dbCall.getQuery().setProperty("output", outputRow);
                session.getEventManager().outputParametersDetected(outputRow, dbCall);
            }
            return result;
            // end special sybase behaviour.
        }
        return result;
    }

    /**
     * INTERNAL:
     * Used for batch writing and sp defs.
     */
    public String getBatchDelimiterString() {
        return "";
    }

    /**
     * INTERNAL:
     * This method is used to print the required output parameter token for the
     * specific platform.  Used when stored procedures are created.
     */
    public String getCreationInOutputProcedureToken() {
        return getInOutputProcedureToken();
    }

    /**
     * INTERNAL:
     * This method was added because SQLServer requires the output paramater token
     * to be set on creation but not on execution.
     */
    public String getCreationOutputProcedureToken() {
        return getOutputProcedureToken();
    }

    /**
     * INTERNAL:
     * This method is used to print the output parameter token when stored
     * procedures are called
     */
    public String getInOutputProcedureToken() {
        return "OUT";
    }

    /**
     * INTERNAL:
     * Returns the type name corresponding to the jdbc type
     */
    public String getJdbcTypeName(int jdbcType) {
        return (String)getTypeStrings().get(new Integer(jdbcType));
    }

    /**
     * INTERNAL:
     * returns the maximum number of characters that can be used in a field
     * name on this platform.
     */
    public int getMaxFieldNameSize() {
        return 22;
    }

    /**
     * INTERNAL:
     * Return the catalog information through using the native SQL catalog selects.
     * This is required because many JDBC driver do not support meta-data.
     * Willcards can be passed as arguments.
     */
    public Vector getNativeTableInfo(String table, String creator, AbstractSession session) {
        // need to filter only tables / views
        String query = "SELECT * FROM sysobjects WHERE table_type <> 'SYSTEM_TABLE'";
        if (table != null) {
            if (table.indexOf('%') != -1) {
                query = query + " AND table_name LIKE " + table;
            } else {
                query = query + " AND table_name = " + table;
            }
        }
        if (creator != null) {
            if (creator.indexOf('%') != -1) {
                query = query + " AND table_owner LIKE " + creator;
            } else {
                query = query + " AND table_owner = " + creator;
            }
        }
        return session.executeSelectingCall(new oracle.toplink.essentials.queryframework.SQLCall(query));
    }

    /**
     * INTERNAL:
     * This method is used to print the output parameter token when stored
     * procedures are called
     */
    public String getOutputProcedureToken() {
        return "OUTPUT";
    }

    /**
     * INTERNAL:
     * Used for sp defs.
     */
    public String getProcedureArgumentString() {
        return "@";
    }

    /**
     * INTERNAL:
     * Used for sp calls.
     */
    public String getProcedureCallHeader() {
        return "EXECUTE ";
    }

    /**
     * INTERNAL:
     */
    public String getStoredProcedureParameterPrefix() {
        return "@";
    }

    /**
     * INTERNAL:
     * This method returns the delimiter between stored procedures in multiple stored procedure
     * calls.
     */
    public String getStoredProcedureTerminationToken() {
        return " go";
    }

    /**
     * INTERNAL:
     * This method returns the query to select the timestamp
     * from the server for Sybase.
     */
    public ValueReadQuery getTimestampQuery() {
        if (timestampQuery == null) {
            timestampQuery = new ValueReadQuery();
            timestampQuery.setSQLString("SELECT GETDATE()");
        }
        return timestampQuery;
    }

    /**
     * INTERNAL:
     * Initialize any platform-specific operators
     */
    protected void initializePlatformOperators() {
        super.initializePlatformOperators();
        addOperator(operatorOuterJoin());
        addOperator(ExpressionOperator.simpleFunction(ExpressionOperator.Today, "GETDATE"));
        // GETDATE returns both date and time. It is not the perfect match for 
        // ExpressionOperator.currentDate and ExpressionOperator.currentTime
        // However, there is no known function on sql server that returns just 
        // the date or just the time.
        addOperator(ExpressionOperator.simpleFunction(ExpressionOperator.CurrentDate, "GETDATE"));
        addOperator(ExpressionOperator.simpleFunction(ExpressionOperator.CurrentTime, "GETDATE"));
        addOperator(ExpressionOperator.simpleFunction(ExpressionOperator.Length, "CHAR_LENGTH"));
        addOperator(ExpressionOperator.sybaseLocateOperator());
        addOperator(ExpressionOperator.simpleThreeArgumentFunction(ExpressionOperator.Substring, "SUBSTRING"));
        addOperator(ExpressionOperator.addDate());
        addOperator(ExpressionOperator.dateName());
        addOperator(ExpressionOperator.datePart());
        addOperator(ExpressionOperator.dateDifference());
        addOperator(ExpressionOperator.difference());
        addOperator(ExpressionOperator.charIndex());
        addOperator(ExpressionOperator.charLength());
        addOperator(ExpressionOperator.reverse());
        addOperator(ExpressionOperator.replicate());
        addOperator(ExpressionOperator.right());
        addOperator(ExpressionOperator.cot());
        addOperator(ExpressionOperator.sybaseAtan2Operator());
        addOperator(ExpressionOperator.sybaseAddMonthsOperator());
        addOperator(ExpressionOperator.sybaseInStringOperator());
        // bug 3061144
        addOperator(ExpressionOperator.simpleTwoArgumentFunction(ExpressionOperator.Nvl, "ISNULL"));
        // CR### TO_NUMBER, TO_CHAR, TO_DATE is CONVERT(type, ?)
        addOperator(ExpressionOperator.sybaseToNumberOperator());
        addOperator(ExpressionOperator.sybaseToDateToStringOperator());
        addOperator(ExpressionOperator.sybaseToDateOperator());
        addOperator(ExpressionOperator.sybaseToCharOperator());
        addOperator(ExpressionOperator.simpleFunction(ExpressionOperator.Ceil, "CEILING"));
        addOperator(modOperator());
    }

    public boolean isSybase() {
        return true;
    }

    /**
     * INTERNAL:
     * Builds a table of maximum numeric values keyed on java class. This is used for type testing but
     * might also be useful to end users attempting to sanitize values.
     * <p><b>NOTE</b>: BigInteger & BigDecimal maximums are dependent upon their precision & Scale
     */
    public Hashtable maximumNumericValues() {
        Hashtable values = new Hashtable();

        values.put(Integer.class, new Integer(Integer.MAX_VALUE));
        values.put(Long.class, new Long(Long.MAX_VALUE));
        values.put(Double.class, new Double((double)Float.MAX_VALUE));
        values.put(Short.class, new Short(Short.MAX_VALUE));
        values.put(Byte.class, new Byte(Byte.MAX_VALUE));
        values.put(Float.class, new Float(Float.MAX_VALUE));
        values.put(java.math.BigInteger.class, new java.math.BigInteger("99999999999999999999999999999999999999"));
        values.put(java.math.BigDecimal.class, new java.math.BigDecimal("9999999999999999999.9999999999999999999"));
        return values;
    }

    /**
     * INTERNAL:
     * Builds a table of minimum numeric values keyed on java class. This is used for type testing but
     * might also be useful to end users attempting to sanitize values.
     * <p><b>NOTE</b>: BigInteger & BigDecimal minimums are dependent upon their precision & Scale
     */
    public Hashtable minimumNumericValues() {
        Hashtable values = new Hashtable();

        values.put(Integer.class, new Integer(Integer.MIN_VALUE));
        values.put(Long.class, new Long(Long.MIN_VALUE));
        values.put(Double.class, new Double((double)1.4012984643247149E-44));// The double values are weird. They lose precision at E-45
        values.put(Short.class, new Short(Short.MIN_VALUE));
        values.put(Byte.class, new Byte(Byte.MIN_VALUE));
        values.put(Float.class, new Float(Float.MIN_VALUE));
        values.put(java.math.BigInteger.class, new java.math.BigInteger("-99999999999999999999999999999999999999"));
        values.put(java.math.BigDecimal.class, new java.math.BigDecimal("-9999999999999999999.9999999999999999999"));
        return values;
    }

    /**
     * INTERNAL:
     * Override the default MOD operator.
     */
    public ExpressionOperator modOperator() {
        ExpressionOperator result = new ExpressionOperator();
        result.setSelector(ExpressionOperator.Mod);
        Vector v = new Vector();
        v.addElement(" % ");
        result.printsAs(v);
        result.bePostfix();
        result.setNodeClass(oracle.toplink.essentials.internal.expressions.FunctionExpression.class);
        return result;
    }

    /**
     * INTERNAL:
     * Create the outer join operator for this platform
     */
    protected ExpressionOperator operatorOuterJoin() {
        ExpressionOperator result = new ExpressionOperator();
        result.setSelector(ExpressionOperator.EqualOuterJoin);
        Vector v = new Vector();
        v.addElement(" =* ");
        result.printsAs(v);
        result.bePostfix();
        result.setNodeClass(RelationExpression.class);
        return result;
    }

    /**
     * INTERNAL:
     * Append the receiver's field 'identity' constraint clause to a writer.
     */
    public void printFieldIdentityClause(Writer writer) throws ValidationException {
        try {
            writer.write(" IDENTITY");
        } catch (IOException ioException) {
            throw ValidationException.fileError(ioException);
        }
    }

    /**
     * INTERNAL:
     * Append the receiver's field 'NULL' constraint clause to a writer.
     */
    public void printFieldNullClause(Writer writer) throws ValidationException {
        try {
            writer.write(" NULL");
        } catch (IOException ioException) {
            throw ValidationException.fileError(ioException);
        }
    }

    /**
     * INTERNAL:
     * This method is used to register output parameter on Callable Statements for Stored Procedures
     * as each database seems to have a different method.
     */
    public void registerOutputParameter(CallableStatement statement, int index, int jdbcType) throws SQLException {
        statement.registerOutParameter(index, jdbcType, (String)getTypeStrings().get(new Integer(jdbcType)));
    }

    /**
     * INTERNAL:
     * USed for sp calls.
     */
    public boolean requiresProcedureCallBrackets() {
        return false;
    }

    /**
     * INTERNAL:
     * Used for sp calls.  Sybase must print output after output params.
     */
    public boolean requiresProcedureCallOuputToken() {
        return true;
    }

    /**
     * INTERNAL:
     * Indicates whether the version of CallableStatement.registerOutputParameter method
     * that takes type name should be used.
     */
    public boolean requiresTypeNameToRegisterOutputParameter() {
        return true;
    }

    /**
     * INTERNAL:
     * This is required in the construction of the stored procedures with
     * output parameters
     */
    public boolean shouldPrintInOutputTokenBeforeType() {
        return false;
    }

    /**
     * INTERNAL:
     * Some database require outer joins to be given in the where clause, others require it in the from clause.
     */
    public boolean shouldPrintOuterJoinInWhereClause() {
        return false;
    }

    /**
     * INTERNAL:
     * This is required in the construction of the stored procedures with
     * output parameters
     */
    public boolean shouldPrintOutputTokenBeforeType() {
        return false;
    }

    /**
     * INTERNAL:
     * If native sequencing is being used on Sybase then the values must be
     * retrieved after the insert.
     * This method is to be used *ONLY* by sequencing classes
     */
    public boolean shouldNativeSequenceAcquireValueAfterInsert() {
        return true;
    }

    /**
     * INTERNAL:
     * JDBC defines and outer join syntax, many drivers do not support this. So we normally avoid it.
     */
    public boolean shouldUseJDBCOuterJoinSyntax() {
        return false;
    }

    /**
     * INTERNAL:
     * Return true if the receiver uses host sequence numbers, generated on the database.
     * Sybase does through IDENTITY field types.
     */
    public boolean supportsNativeSequenceNumbers() {
        return true;
    }

    /**
     * INTERNAL:
     */
    public boolean supportsLocalTempTables() {
        return true;
    }
     
    /**
     * INTERNAL:
     */
    protected String getCreateTempTableSqlPrefix() {
        return "CREATE TABLE ";
    }          

    /**
     * INTERNAL:
     */
    public DatabaseTable getTempTableForTable(DatabaseTable table) {
        return new DatabaseTable("#" + table.getName(), table.getTableQualifier());
    }          

    /**
     * INTERNAL:
     */
    public void writeUpdateOriginalFromTempTableSql(Writer writer, DatabaseTable table,
                                                    Collection pkFields,
                                                    Collection assignedFields) throws IOException 
    {
        writer.write("UPDATE ");
        String tableName = table.getQualifiedName();
        writer.write(tableName);
        String tempTableName = getTempTableForTable(table).getQualifiedName();
        writeAutoAssignmentSetClause(writer, null, tempTableName, assignedFields);
        writer.write(" FROM ");
        writer.write(tableName);
        writer.write(", ");
        writer.write(tempTableName);
        writeAutoJoinWhereClause(writer, tableName, tempTableName, pkFields);
    }          
}
