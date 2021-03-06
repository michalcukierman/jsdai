/*
 * $Id$
 *
 * JSDAI(TM), a way to implement STEP, ISO 10303
 * Copyright (C) 1997-2008, LKSoftWare GmbH, Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License
 * version 3 as published by the Free Software Foundation (AGPL v3).
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * JSDAI is a registered trademark of LKSoftWare GmbH, Germany
 * This software is also available under commercial licenses.
 * See also http://www.jsdai.net/
 */

/* Generated By:JJTree: Do not edit this line. X_ConstantRef.java */

package jsdai.expressCompiler;

public class X_ConstantRef extends SimpleNode {

  jsdai.SExtended_dictionary_schema.EConstant_definition cd = null;
  String name;
  int depth;  // -1, if not found, 0 - if reference to a schema constant, 1 - if current function, 2 - if parent, 3 - if parent.parent, etc.

  

  public X_ConstantRef(int id) {
    super(id);
  }

  public X_ConstantRef(Compiler2 p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(Compiler2Visitor visitor, Object data) throws jsdai.lang.SdaiException {
    return visitor.visit(this, data);
  }



	String constructConstantReference() {
		String const_name = "c" + name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	
		for (int i = 1; i < depth; i++) {
			const_name = "parent." + const_name;
		}

		return const_name;
	}


}
