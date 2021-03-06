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

// Java class implementing entity aggregate_target_parameter

package jsdai.dictionary;
import jsdai.lang.*;

public class CAggregate_target_parameter extends CEntity implements EAggregate_target_parameter {
	static jsdai.dictionary.CEntity_definition definition;

	/*----------------------------- Attributes -----------*/

	protected String a0; // name - non-java inheritance - STRING
	protected static jsdai.dictionary.CExplicit_attribute a0$;
	protected Object a1; // parent - non-java inheritance - ENTITY map_definition
	protected static jsdai.dictionary.CExplicit_attribute a1$;
	protected Object a2; // extent - non-java inheritance - ENTITY entity_definition
	protected static jsdai.dictionary.CExplicit_attribute a2$;
	protected int a3; // order - non-java inheritance - INTEGER
	protected static jsdai.dictionary.CExplicit_attribute a3$;
	protected Object a4; // lower_bound - current entity - ENTITY bound
	protected static jsdai.dictionary.CExplicit_attribute a4$;
	protected Object a5; // upper_bound - current entity - ENTITY bound
	protected static jsdai.dictionary.CExplicit_attribute a5$;

	public jsdai.dictionary.EEntity_definition getInstanceType() {
		return definition;
	}

	protected void changeReferences(InverseEntity old, InverseEntity newer) throws SdaiException {
		if (a1 == old) {
			a1 = newer;
		}
		if (a2 == old) {
			a2 = newer;
		}
		if (a4 == old) {
			a4 = newer;
		}
		if (a5 == old) {
			a5 = newer;
		}
	}

	/*----------- Methods for attribute access -----------*/

	/// methods for attribute: name, base type: STRING
	public boolean testName(ETarget_parameter type) throws SdaiException {
		return test_string(a0);
	}
	public String getName(ETarget_parameter type) throws SdaiException {
		return get_string(a0);
	}
	public void setName(ETarget_parameter type, String value) throws SdaiException {
		a0 = set_string(value);
	}
	public void unsetName(ETarget_parameter type) throws SdaiException {
		a0 = unset_string();
	}
	public static jsdai.dictionary.EAttribute attributeName(ETarget_parameter type) throws SdaiException {
		return a0$;
	}

	// attribute: parent, base type: entity map_definition
	public static int usedinParent(ETarget_parameter type, EMap_definition instance, ASdaiModel domain, AEntity result) throws SdaiException {
		return ((CEntity)instance).makeUsedin(definition, a1$, domain, result);
	}
	public boolean testParent(ETarget_parameter type) throws SdaiException {
		return test_instance(a1);
	}
	public EMap_definition getParent(ETarget_parameter type) throws SdaiException {
		a1 = get_instance(a1);
		return (EMap_definition)a1;
	}
	public void setParent(ETarget_parameter type, EMap_definition value) throws SdaiException {
		a1 = set_instance(a1, value);
	}
	public void unsetParent(ETarget_parameter type) throws SdaiException {
		a1 = unset_instance(a1);
	}
	public static jsdai.dictionary.EAttribute attributeParent(ETarget_parameter type) throws SdaiException {
		return a1$;
	}

	// attribute: extent, base type: entity entity_definition
	public static int usedinExtent(ETarget_parameter type, EEntity_definition instance, ASdaiModel domain, AEntity result) throws SdaiException {
		return ((CEntity)instance).makeUsedin(definition, a2$, domain, result);
	}
	public boolean testExtent(ETarget_parameter type) throws SdaiException {
		return test_instance(a2);
	}
	public EEntity_definition getExtent(ETarget_parameter type) throws SdaiException {
		a2 = get_instance(a2);
		return (EEntity_definition)a2;
	}
	public void setExtent(ETarget_parameter type, EEntity_definition value) throws SdaiException {
		a2 = set_instance(a2, value);
	}
	public void unsetExtent(ETarget_parameter type) throws SdaiException {
		a2 = unset_instance(a2);
	}
	public static jsdai.dictionary.EAttribute attributeExtent(ETarget_parameter type) throws SdaiException {
		return a2$;
	}

	/// methods for attribute: order, base type: INTEGER
	public boolean testOrder(ETarget_parameter type) throws SdaiException {
		return test_integer(a3);
	}
	public int getOrder(ETarget_parameter type) throws SdaiException {
		return get_integer(a3);
	}
	public void setOrder(ETarget_parameter type, int value) throws SdaiException {
		a3 = set_integer(value);
	}
	public void unsetOrder(ETarget_parameter type) throws SdaiException {
		a3 = unset_integer();
	}
	public static jsdai.dictionary.EAttribute attributeOrder(ETarget_parameter type) throws SdaiException {
		return a3$;
	}

	// attribute: lower_bound, base type: entity bound
	public static int usedinLower_bound(EAggregate_target_parameter type, EBound instance, ASdaiModel domain, AEntity result) throws SdaiException {
		return ((CEntity)instance).makeUsedin(definition, a4$, domain, result);
	}
	public boolean testLower_bound(EAggregate_target_parameter type) throws SdaiException {
		return test_instance(a4);
	}
	public EBound getLower_bound(EAggregate_target_parameter type) throws SdaiException {
		a4 = get_instance(a4);
		return (EBound)a4;
	}
	public void setLower_bound(EAggregate_target_parameter type, EBound value) throws SdaiException {
		a4 = set_instance(a4, value);
	}
	public void unsetLower_bound(EAggregate_target_parameter type) throws SdaiException {
		a4 = unset_instance(a4);
	}
	public static jsdai.dictionary.EAttribute attributeLower_bound(EAggregate_target_parameter type) throws SdaiException {
		return a4$;
	}

	// attribute: upper_bound, base type: entity bound
	public static int usedinUpper_bound(EAggregate_target_parameter type, EBound instance, ASdaiModel domain, AEntity result) throws SdaiException {
		return ((CEntity)instance).makeUsedin(definition, a5$, domain, result);
	}
	public boolean testUpper_bound(EAggregate_target_parameter type) throws SdaiException {
		return test_instance(a5);
	}
	public EBound getUpper_bound(EAggregate_target_parameter type) throws SdaiException {
		a5 = get_instance(a5);
		return (EBound)a5;
	}
	public void setUpper_bound(EAggregate_target_parameter type, EBound value) throws SdaiException {
		a5 = set_instance(a5, value);
	}
	public void unsetUpper_bound(EAggregate_target_parameter type) throws SdaiException {
		a5 = unset_instance(a5);
	}
	public static jsdai.dictionary.EAttribute attributeUpper_bound(EAggregate_target_parameter type) throws SdaiException {
		return a5$;
	}


	/*---------------------- setAll() --------------------*/

	protected void setAll(ComplexEntityValue av) throws SdaiException {
		if (av == null) {
			a4 = unset_instance(a4);
			a5 = unset_instance(a5);
			a0 = null;
			a1 = unset_instance(a1);
			a2 = unset_instance(a2);
			a3 = Integer.MIN_VALUE;
			return;
		}
		a4 = av.entityValues[0].getInstance(0, this);
		a5 = av.entityValues[0].getInstance(1, this);
		a0 = av.entityValues[1].getString(0);
		a1 = av.entityValues[1].getInstance(1, this);
		a2 = av.entityValues[1].getInstance(2, this);
		a3 = av.entityValues[1].getInteger(3);
	}

	/*---------------------- getAll() --------------------*/

	protected void getAll(ComplexEntityValue av) throws SdaiException {
		// partial entity: aggregate_target_parameter
		av.entityValues[0].setInstance(0, a4);
		av.entityValues[0].setInstance(1, a5);
		// partial entity: target_parameter
		av.entityValues[1].setString(0, a0);
		av.entityValues[1].setInstance(1, a1);
		av.entityValues[1].setInstance(2, a2);
		av.entityValues[1].setInteger(3, a3);
	}
}
