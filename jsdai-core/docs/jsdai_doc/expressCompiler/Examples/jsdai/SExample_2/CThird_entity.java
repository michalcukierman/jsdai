/* Generated by JSDAI Express Compiler, version 3.5, build 225, 2003-04-23 */

// Java class implementing entity third_entity

package jsdai.SExample_2;
import jsdai.lang.*;

public class CThird_entity extends jsdai.SExample_1.CFirst_entity implements EThird_entity {
	public static final jsdai.dictionary.CEntity_definition definition = initEntityDefinition(CThird_entity.class, SExample_2.ss);

	/*----------------------------- Attributes -----------*/

	// attr_1: protected int a0;   attr_1 - java inheritance - INTEGER
	protected Object a1; // attr_c - current entity - ENTITY second_entity
	protected static final jsdai.dictionary.CExplicit_attribute a1$ = CEntity.initExplicitAttribute(definition, 1);

	public jsdai.dictionary.EEntity_definition getInstanceType() {
		return definition;
	}

	protected void changeReferences(InverseEntity old, InverseEntity newer) throws SdaiException {
		super.changeReferences(old, newer);
		if (a1 == old) {
			a1 = newer;
		}
	}

	/*----------- Methods for attribute access -----------*/

	// attribute: attr_c, base type: entity second_entity
	public static int usedinAttr_c(EThird_entity type, ESecond_entity instance, ASdaiModel domain, AEntity result) throws SdaiException {
		return ((CEntity)instance).makeUsedin(definition, a1$, domain, result);
	}
	public boolean testAttr_c(EThird_entity type) throws SdaiException {
		return test_instance(a1);
	}
	public ESecond_entity getAttr_c(EThird_entity type) throws SdaiException {
		a1 = get_instance(a1);
		return (ESecond_entity)a1;
	}
	public void setAttr_c(EThird_entity type, ESecond_entity value) throws SdaiException {
		a1 = set_instance(a1, value);
	}
	public void unsetAttr_c(EThird_entity type) throws SdaiException {
		a1 = unset_instance(a1);
	}
	public static jsdai.dictionary.EAttribute attributeAttr_c(EThird_entity type) throws SdaiException {
		return a1$;
	}


	/*---------------------- setAll() --------------------*/

	protected void setAll(ComplexEntityValue av) throws SdaiException {
		if (av == null) {
			a0 = Integer.MIN_VALUE;
			a1 = unset_instance(a1);
			return;
		}
		a0 = av.entityValues[0].getInteger(0);
		a1 = av.entityValues[1].getInstance(0, this, a1$);
	}

	/*---------------------- getAll() --------------------*/

	protected void getAll(ComplexEntityValue av) throws SdaiException {
		// partial entity: first_entity
		av.entityValues[0].setInteger(0, a0);
		// partial entity: third_entity
		av.entityValues[1].setInstance(0, a1);
	}
}
