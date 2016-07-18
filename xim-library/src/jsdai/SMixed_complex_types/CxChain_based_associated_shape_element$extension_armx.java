/*
 * $Id: CxChain_based_associated_shape_element\044extension_armx.java,v 1.1 2011/12/30 00:23:33 raira Exp $
 *
 * JSDAI(TM), a way to implement STEP, ISO 10303
 * Copyright (C) 1997-2011, LKSoftWare GmbH, Germany
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

package jsdai.SMixed_complex_types;

/**
 * 
 * @author Giedrius Liutkus
 * @version $$
 */

import jsdai.SDerived_shape_element_xim.CxDerived_shape_element;
import jsdai.SDerived_shape_element_xim.CxExtension_armx;
import jsdai.SDerived_shape_element_xim.EDerived_shape_element;
import jsdai.SDimension_tolerance_xim.CxChain_based_associated_shape_element;
import jsdai.SProduct_property_definition_schema.EShape_aspect;
import jsdai.SProduct_property_representation_schema.EItem_identified_representation_usage;
import jsdai.SShape_aspect_definition_schema.CExtension;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiContext;
import jsdai.lang.SdaiException;
import jsdai.libutil.EMappedXIMEntity;

public class CxChain_based_associated_shape_element$extension_armx extends CChain_based_associated_shape_element$extension_armx implements EMappedXIMEntity{

	// From CShape_aspect.java
	/// methods for attribute: product_definitional, base type: LOGICAL
/*	public boolean testProduct_definitional(EShape_aspect type) throws SdaiException {
		return test_logical(a9);
	}
	public int getProduct_definitional(EShape_aspect type) throws SdaiException {
		return get_logical(a9);
	}*/
	public void setProduct_definitional(EShape_aspect type, int value) throws SdaiException {
		a11 = set_logical(value);
	}
	public void unsetProduct_definitional(EShape_aspect type) throws SdaiException {
		a11 = unset_logical();
	}
	public static jsdai.dictionary.EAttribute attributeProduct_definitional(EShape_aspect type) throws SdaiException {
		return a9$;
	}
	// ENDOF From CShape_aspect.java

	/* Taken from EItem_identified_representation_usage
	//going through all the attributes: #5629499534233780=EXPLICIT_ATTRIBUTE('definition',#5629499534233776,2,#5629499534233768,$,.F.);
	//<01> generating methods for consolidated attribute:  definition
	//<01-0> current entity
	//<01-0-0> explicit attribute - generateExplicitCurrentEntityMethodsX()
	// -2- methods for SELECT attribute: definition
	public static int usedinDefinition(EItem_identified_representation_usage type, EEntity instance, ASdaiModel domain, AEntity result) throws SdaiException {
		return ((CEntity)instance).makeUsedin(definition, a3$, domain, result);
	}
	public boolean testDefinition(EItem_identified_representation_usage type) throws SdaiException {
		return test_instance(a3);
	}

	public EEntity getDefinition(EItem_identified_representation_usage type) throws SdaiException { // case 1
		return get_instance_select(a3);
	}*/

	public void setDefinition(EItem_identified_representation_usage type, EEntity value) throws SdaiException { // case 1
		a5 = set_instance(a5, value);
	}

	public void unsetDefinition(EItem_identified_representation_usage type) throws SdaiException {
		a5 = unset_instance(a5);
	}

	public static jsdai.dictionary.EAttribute attributeDefinition(EItem_identified_representation_usage type) throws SdaiException {
		return a3$;
	}
	
	public int attributeState = ATTRIBUTES_MODIFIED;	

	public void createAimData(SdaiContext context) throws SdaiException {
		if (attributeState == ATTRIBUTES_MODIFIED) {
			attributeState = ATTRIBUTES_UNMODIFIED;
		} else {
			return;
		}

		setTemp("AIM", CExtension.definition);

		setMappingConstraints(context, this);
		
		setDerived_from(context, this);
		
//		setId_x(context, this);
		
//		unsetId_x(null);
		
		unsetDerived_from(null);
		
		unsetName((EItem_identified_representation_usage)null);
		
		unsetDescription((EItem_identified_representation_usage)null);
		
		unsetDefinition(null);
		
		unsetUsed_representation(null);
		
		unsetIdentified_item(null);
	}

	public void removeAimData(SdaiContext context) throws SdaiException {
			unsetMappingConstraints(context, this);

			unsetDerived_from(context, this);
			
//			unsetId_x(context, this);			
	}

	/**
	 * Sets/creates data for mapping constraints.
	 * 
	 * <p>
	 * mapping_constraints; 
	 *  component_feature &lt;=
	 *  shape_aspect
	 * end_mapping_constraints;
	 * </p>
	 * 
	 * @param context
	 *            SdaiContext.
	 * @param armEntity
	 *            arm entity.
	 * @throws SdaiException
	 */
	public static void setMappingConstraints(SdaiContext context,
			CChain_based_associated_shape_element$extension_armx armEntity) throws SdaiException {
		unsetMappingConstraints(context, armEntity);

		CxChain_based_associated_shape_element.setMappingConstraints(context, armEntity);
		CxExtension_armx.setMappingConstraints(context, armEntity);
	}

	/**
	 * Unsets/deletes mapping constraint data.
	 * 
	 * @param context
	 * @param armEntity
	 * @throws SdaiException
	 */
	public static void unsetMappingConstraints(SdaiContext context,
			CChain_based_associated_shape_element$extension_armx armEntity) throws SdaiException {
		CxChain_based_associated_shape_element.unsetMappingConstraints(context, armEntity);
		CxExtension_armx.unsetMappingConstraints(context, armEntity);
	}

	//********** "shape_element" attributes

	/**
	 * Sets/creates data for mapping constraints.
	 * 
	 * <p>
	 * mapping_constraints; 
	 *  component_feature &lt;=
	 *  shape_aspect
	 * end_mapping_constraints;
	 * </p>
	 * 
	 * @param context
	 *            SdaiContext.
	 * @param armEntity
	 *            arm entity.
	 * @throws SdaiException
	 */
	/* Removed from XIM - see bug #3610
	public static void setId_x(SdaiContext context, EShape_element armEntity) throws SdaiException {
		CxShape_element.setId_x(context, armEntity);
	}
*/
	/**
	 * Unsets/deletes mapping constraint data.
	 * 
	 * @param context
	 * @param armEntity
	 * @throws SdaiException
	 */
	/* Removed from XIM - see bug #3610
	public static void unsetId_x(SdaiContext context, EShape_element armEntity) throws SdaiException {
		CxShape_element.unsetId_x(context, armEntity);
	}
	*/
	
	/**
	 * Sets/creates data for mapping constraints.
	 * 
	 * @param context
	 *            SdaiContext.
	 * @param armEntity
	 *            arm entity.
	 * @throws SdaiException
	 */
	public static void setDerived_from(SdaiContext context, EDerived_shape_element armEntity) throws SdaiException {
		CxDerived_shape_element.setDerived_from(context, armEntity);
	}

	/**
	 * Unsets/deletes mapping constraint data.
	 * 
	 * @param context
	 * @param armEntity
	 * @throws SdaiException
	 */
	public static void unsetDerived_from(SdaiContext context, EDerived_shape_element armEntity) throws SdaiException {
		CxDerived_shape_element.unsetDerived_from(context, armEntity);
	}
	
}