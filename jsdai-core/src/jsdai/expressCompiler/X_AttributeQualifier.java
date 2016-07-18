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

// %modified: 1016210368352 %

/* Generated By:JJTree: Do not edit this line. X_AttributeQualifier.java */
package jsdai.expressCompiler;

public class X_AttributeQualifier
  extends SimpleNode {
  boolean left_side = false;
  boolean last = false;

  public X_AttributeQualifier(int id) {
    super(id);
    left_side = false;
  }

  public X_AttributeQualifier(Compiler2 p, int id) {
    super(p, id);
    left_side = false;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(Compiler2Visitor visitor, Object data)
                   throws jsdai.lang.SdaiException {
//		JavaClass jc = (JavaClass) data;
//    jc.pw.println("<<<IN AttributeQualifier>> last_left_qualifier : " + jc.last_left_qualifier);
    return visitor.visit(this, data);
  }

}
