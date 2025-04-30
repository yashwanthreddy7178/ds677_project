/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PFR_WhereClause
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_PFR_WhereClause 
{

    /** TableName=PFR_WhereClause */
    public static final String Table_Name = "PFR_WhereClause";

    /** AD_Table_ID=1000309 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException;

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AndOr */
    public static final String COLUMNNAME_AndOr = "AndOr";

	/** Set And/Or.
	  * Logical operation: AND or OR
	  */
	public void setAndOr (String AndOr);

	/** Get And/Or.
	  * Logical operation: AND or OR
	  */
	public String getAndOr();

    /** Column name CloseBracket */
    public static final String COLUMNNAME_CloseBracket = "CloseBracket";

	/** Set CloseBracket.
	  * Закрывающая скобка
	  */
	public void setCloseBracket (String CloseBracket);

	/** Get CloseBracket.
	  * Закрывающая скобка
	  */
	public String getCloseBracket();

    /** Column name ColumnName */
    public static final String COLUMNNAME_ColumnName = "ColumnName";

	/** Set DB Column Name.
	  * Name of the column in the database
	  */
	public void setColumnName (String ColumnName);

	/** Get DB Column Name.
	  * Name of the column in the database
	  */
	public String getColumnName();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name isStatic */
    public static final String COLUMNNAME_isStatic = "isStatic";

	/** Set isStatic	  */
	public void setisStatic (boolean isStatic);

	/** Get isStatic	  */
	public boolean isStatic();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name OpenBracket */
    public static final String COLUMNNAME_OpenBracket = "OpenBracket";

	/** Set OpenBracket.
	  * Открывающая скобка
	  */
	public void setOpenBracket (String OpenBracket);

	/** Get OpenBracket.
	  * Открывающая скобка
	  */
	public String getOpenBracket();

    /** Column name Operation */
    public static final String COLUMNNAME_Operation = "Operation";

	/** Set Operation.
	  * Compare Operation
	  */
	public void setOperation (String Operation);

	/** Get Operation.
	  * Compare Operation
	  */
	public String getOperation();

    /** Column name PFR_Calculation_ID */
    public static final String COLUMNNAME_PFR_Calculation_ID = "PFR_Calculation_ID";

	/** Set PFR_Calculation ID	  */
	public void setPFR_Calculation_ID (int PFR_Calculation_ID);

	/** Get PFR_Calculation ID	  */
	public int getPFR_Calculation_ID();

	public I_PFR_Calculation getPFR_Calculation() throws RuntimeException;

    /** Column name PFR_WhereClause_ID */
    public static final String COLUMNNAME_PFR_WhereClause_ID = "PFR_WhereClause_ID";

	/** Set PFR_WhereClause ID	  */
	public void setPFR_WhereClause_ID (int PFR_WhereClause_ID);

	/** Get PFR_WhereClause ID	  */
	public int getPFR_WhereClause_ID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Value1 */
    public static final String COLUMNNAME_Value1 = "Value1";

	/** Set Value1	  */
	public void setValue1 (String Value1);

	/** Get Value1	  */
	public String getValue1();

    /** Column name Value2 */
    public static final String COLUMNNAME_Value2 = "Value2";

	/** Set Value To.
	  * Value To
	  */
	public void setValue2 (String Value2);

	/** Get Value To.
	  * Value To
	  */
	public String getValue2();
}
