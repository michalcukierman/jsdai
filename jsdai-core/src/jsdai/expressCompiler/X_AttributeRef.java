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

// %modified: 1016210368658 %

/* Generated By:JJTree: Do not edit this line. X_AttributeRef.java */
package jsdai.expressCompiler;

import jsdai.SExtended_dictionary_schema.*;
import jsdai.lang.*;


public class X_AttributeRef extends ExpressionNode {
  // public class X_AttributeRef extends SimpleNode {
  boolean left_side = false;
  String name = null;
  Object reference;
  boolean already_processed = false;
  EAttribute attribute;
  int type;
  EEntity_definition ed;
  EAggregation_type at;
  SdaiModel current_model;

  public X_AttributeRef(int id) {
    super(id);
    left_side = false;
  }

  public X_AttributeRef(Compiler2 p, int id) {
    super(p, id);
    left_side = false;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(Compiler2Visitor visitor, Object data)
                   throws SdaiException {

//		JavaClass jc = (JavaClass) data;
//    jc.pw.println("<<<IN AttributeRef>>>>>>> last_left_qualifier : " + jc.last_left_qualifier);

    return visitor.visit(this, data);
  }

  public void process(Object data) throws SdaiException {
		JavaClass jc = (JavaClass) data;
    already_processed = true;

    EEntity attr_type = null;

    if (attribute == null) {
      printDDebug("#_#_#_#_#_#_#_# NULL", jc);
    } else {
      printDDebug("#_#_#_#_#_#_#_# name: " + attribute.getName(null), jc);
    }

    if (attribute instanceof EExplicit_attribute) {
      printDDebug("###___###---### jjClose - explicit attribute", jc);
      attr_type = (EEntity) ((EExplicit_attribute) attribute).getDomain(null);
      printDDebug("###___###---### jjClose - explicit attribute 2", jc);

      while (attr_type instanceof EDefined_type) {
        // simple types, select, enumeration, aggregate types are possible
        attr_type = ((EDefined_type) attr_type).getDomain(null);
      }

      if (attr_type instanceof EInteger_type) {
        printDDebug("###___###---### jjClose - explicit attribute - INTEGER", jc);
        type = JavaClass.T_INTEGER;
      } else if (attr_type instanceof ENumber_type) {
        printDDebug("###___###---### jjClose - explicit attribute - NUMBER", jc);
        type = JavaClass.T_NUMBER;
      } else if (attr_type instanceof EReal_type) {
        printDDebug("###___###---### jjClose - explicit attribute - REAL", jc);
        type = JavaClass.T_REAL;
      } else if (attr_type instanceof EString_type) {
        printDDebug("###___###---### jjClose - explicit attribute - STRING", jc);
        type = JavaClass.T_STRING;
      } else if (attr_type instanceof EBoolean_type) {
        printDDebug("###___###---### jjClose - explicit attribute - BOOLEAN", jc);
        type = JavaClass.T_BOOLEAN;
      } else if (attr_type instanceof ELogical_type) {
        printDDebug("###___###---### jjClose - explicit attribute - LOGICAL", jc);
        type = JavaClass.T_LOGICAL;
      } else if (attr_type instanceof EBinary_type) {
        printDDebug("###___###---### jjClose - explicit attribute - BINARY", jc);
        type = JavaClass.T_BINARY;
      } else if (attr_type instanceof EArray_type) {
        printDDebug("###___###---### jjClose - explicit attribute - ARRAY", jc);
        type = JavaClass.T_ARRAY;
        at = (EAggregation_type) attr_type;
      } else if (attr_type instanceof EBag_type) {
        printDDebug("###___###---### jjClose - explicit attribute - BAG", jc);
        type = JavaClass.T_BAG;
        at = (EAggregation_type) attr_type;
      } else if (attr_type instanceof EList_type) {
        printDDebug("###___###---### jjClose - explicit attribute - LIST", jc);
        type = JavaClass.T_LIST;
        at = (EAggregation_type) attr_type;
      } else if (attr_type instanceof ESet_type) {
        printDDebug("###___###---### jjClose - explicit attribute - SET", jc);
        type = JavaClass.T_SET;
        at = (EAggregation_type) attr_type;
      } else if (attr_type instanceof EEntity_definition) {
        printDDebug("###___###---### jjClose - explicit attribute - ENTITY", jc);
        type = JavaClass.T_ENTITY;
        ed = (EEntity_definition) attr_type;
      } else {
        printDDebug("###___###---### jjClose - explicit attribute - UNKNOWN", jc);
      }
    } else if (attribute instanceof EDerived_attribute) {
      printDDebug("###___###---### jjClose - derived attribute", jc);
    } else if (attribute instanceof EInverse_attribute) {
      printDDebug("###___###---### jjClose - inverse attribute", jc);
      ed = ((EInverse_attribute) attribute).getDomain(null);
      type = JavaClass.T_ENTITY;
    } else {
      //         System.out.println("###___###---### jjClose - unknown attribute type: " + attribute.getName(null));
      if (attribute == null) {
        printDDebug("###___###---### jjClose - attribute = NULL", jc);
      } else {
        printDDebug("###___###---### jjClose - unknown attribute type", jc);
      }
    }

    printDDebug("###___###---### jjClose type: " + type, jc);
  }

  String getStaticTypeFieldName(JavaClass jc) throws SdaiException {
    current_model = jc.model;

    String type_name = "";
    EEntity rt = null;

    if (attribute instanceof EDerived_attribute) {
      rt = ((EDerived_attribute) attribute).getDomain(null);
    } else if (attribute instanceof EExplicit_attribute) {
      rt = ((EExplicit_attribute) attribute).getDomain(null);
    } else if (attribute instanceof EInverse_attribute) {
      rt = ((EInverse_attribute) attribute).getDomain(null);
    }


   if (!(attribute instanceof EInverse_attribute)) {

    // here we need the string for every type possible: entity, defined_type or no-name type
    if (rt instanceof EEntity_definition) {
      String base_entity_name = ((EEntity_definition) rt).getName(null);
      String base_name = getEntityPackage((EEntity_definition) rt) + "C" + 
                         base_entity_name.substring(0, 1).toUpperCase() + 
                         base_entity_name.substring(1).toLowerCase();
      type_name = base_name + ".definition";
    } else if (rt instanceof EDefined_type) {
      String base_entity_name = ((EDefined_type) rt).getName(null);
      String base_name = constructTypeAndFromReferencedSchemaClass((EDefined_type) rt);

      type_name = base_name + "_st_" + ((EDefined_type) rt).getName(null);

      return type_name;

      //      type_name = "_st_" + (( jsdai.SExtended_dictionary_schema.EDefined_type )rt).getName(null);
    } else if (rt instanceof ESimple_type) {
      // check the types that may have WIDTH FIXED PRECISSION - string binary real
      if (rt instanceof EString_type) {
        if (!((EString_type) rt).testWidth(null)) {
          type_name = "ExpressTypes.STRING_TYPE";
        } else {
          type_name = "_ST_STRING";

          EBound s_width = ((EString_type) rt).getWidth(null);

          if (s_width instanceof EInteger_bound) {
            EInteger_bound i_width = (EInteger_bound) s_width;
            boolean integer_bound_set = i_width.testBound_value(null);

            if (integer_bound_set) {
              int width_value = i_width.getBound_value(null);
              type_name += ("_" + width_value);
            }
          }

          boolean fixed_present = ((EString_type) rt).testFixed_width(null);

          if (fixed_present) {
            boolean fixed_value = ((EString_type) rt).getFixed_width(null);

            if (fixed_value) {
              type_name += "_FIXED";
            }
          }
        }
      } else if (rt instanceof EBinary_type) {
        if (!((EBinary_type) rt).testWidth(null)) {
          type_name = "ExpressTypes.BINARY_TYPE";
        } else {
          type_name = "_ST_BINARY";

          EBound b_width = ((EBinary_type) rt).getWidth(null);

          if (b_width instanceof EInteger_bound) {
            EInteger_bound i_width = (EInteger_bound) b_width;
            boolean integer_bound_set = i_width.testBound_value(null);

            if (integer_bound_set) {
              int width_value = i_width.getBound_value(null);
              type_name += ("_" + width_value);
            }
          }

          boolean fixed_present = ((EBinary_type) rt).testFixed_width(null);

          if (fixed_present) {
            boolean fixed_value = ((EBinary_type) rt).getFixed_width(null);

            if (fixed_value) {
              type_name += "_FIXED";
            }
          }
        }
      } else if (rt instanceof EReal_type) {
        if (!((EReal_type) rt).testPrecision(null)) {
          type_name = "ExpressTypes.REAL_TYPE";
        } else {
          type_name = "_ST_REAL";

          EBound r_precision = ((EReal_type) rt).getPrecision(null);

          if (r_precision instanceof EInteger_bound) {
            EInteger_bound i_precision = (EInteger_bound) r_precision;

            if (i_precision.testBound_value(null)) {
              int precision_value = i_precision.getBound_value(null);
              type_name += ("_" + precision_value);
            }
          }
        }
      } else if (rt instanceof EInteger_type) {
        type_name = "ExpressTypes.INTEGER_TYPE";
      } else if (rt instanceof ENumber_type) {
        type_name = "ExpressTypes.NUMBER_TYPE";
      } else if (rt instanceof ELogical_type) {
        type_name = "ExpressTypes.LOGICAL_TYPE";
      } else if (rt instanceof EBoolean_type) {
        type_name = "ExpressTypes.BOOLEAN_TYPE";
      }
    } else if (rt instanceof EAggregation_type) {
			if (rt instanceof ESet_type){
				// particular the case in LOCAL with USEDIN as initialization requires such type; should work for other cases too. 
				// FIXME: unclear
				return "ExpressTypes.SET_GENERIC_TYPE";
			}else
				type_name = "_st" + 
					constructAggregationTypeBaseTypeNameString((EAggregation_type) rt, current_model)
					.toLowerCase();

      return constructTypeAndFromReferencedSchemaClass(rt) + type_name;
    } else if (rt instanceof EData_type) {
      String data_name = null;

      if (((EData_type) rt).testName(null)) {
        data_name = ((EData_type) rt).getName(null);

        if (data_name.equals("_GENERIC")) {
          type_name = "ExpressTypes.GENERIC_TYPE";
        }
      }
    } else {
      // error?
    }

    if (type_name.length() > "ExpressTypes".length()) {
      if (type_name.substring(0, "ExpressTypes".length()).equals("ExpressTypes")) {
        return type_name;
      }
    }

    if (rt instanceof EEntity_definition) {
      return type_name;
    } else {
      type_name = type_name.toLowerCase();
      type_name = constructTypeSchemaClass(jc, rt) + type_name;
    }

    return type_name;

   } else	 { // inverse attribute

      EInverse_attribute inva = (EInverse_attribute)attribute;

      boolean duplicates = inva.getDuplicates(null);
      EBound min_cardinality = null;
      EBound max_cardinality = null;
      int min_cardinality_int = Integer.MIN_VALUE;
      int max_cardinality_int = Integer.MIN_VALUE;


      if (inva.testMin_cardinality(null)) {
        min_cardinality = inva.getMin_cardinality(null);

        if (min_cardinality instanceof EInteger_bound) {
          min_cardinality_int = ((EInteger_bound) min_cardinality).getBound_value(null);
        }
      }

      if (inva.testMax_cardinality(null)) {
        max_cardinality = inva.getMax_cardinality(null);

        if (max_cardinality instanceof EInteger_bound) {
          max_cardinality_int = ((EInteger_bound) max_cardinality).getBound_value(null);
        }
      }

      if (min_cardinality_int == Integer.MIN_VALUE) { // single entity inverse attribute

    		if (rt instanceof EEntity_definition) {
      		String base_entity_name = ((EEntity_definition) rt).getName(null);
      		String base_name = getEntityPackage((EEntity_definition) rt) + "C" + 
                         base_entity_name.substring(0, 1).toUpperCase() + 
                         base_entity_name.substring(1).toLowerCase();
      		type_name = base_name + ".definition";
		      return type_name;
    		} else {
    			// should not be here
		      return "_INTERNAL_ERROR_WITH_INVERSE_ATTRIBUTE_TYPE_";
    		}


      } else { // set or bag inverse attribute

        if (duplicates) { // bag
					return "ExpressTypes.BAG_GENERIC_TYPE";
				} else { // set
					return "ExpressTypes.SET_GENERIC_TYPE";
				}			
			}
   	
   } // inverse attribute

  }

  String constructTypeSchemaClass(JavaClass jc, EEntity rt) throws SdaiException {
    current_model = jc.model;

    String model_name = current_model.getName();
    String schema_name;

    if (rt instanceof EAggregation_type) {
    	// at least for this case we need not the current model, but the model where the aggregation type is defined,
    	// it may be the current model or a different model, the model of an interfaced schema
    	// the static variable for the aggregate is defined in the S... class of the package of that schema
    	// The same approach may be needed also for non-aggregates, so far everyhing seems to be ok, 
    	// so not changing for other cases so far, for non-aggregates non-java inheritance duplicates a number of things,
    	// so that the current model may be needed instead
    	// even for aggregates there may be unexpected issues, so thorough testing will be needed
// System.out.println("<> rt model: " + rt.findEntityInstanceSdaiModel());    	
    	model_name = rt.findEntityInstanceSdaiModel().getName();
    }


    if (model_name.length() > 16) {
      String part_model_name = model_name.substring(model_name.length() - 16);

      if (part_model_name.equalsIgnoreCase("_DICTIONARY_DATA")) {
        schema_name = model_name.substring(0, model_name.length() - 16);
      } else {
        schema_name = model_name;
      }
    } else {
      schema_name = model_name;
    }

    //     String type_schema_class = "jsdai.S" + schema_name.substring(0,1).toUpperCase() + schema_name.substring(1).toLowerCase() + ".class";
    String type_schema_class = "jsdai.S" + schema_name.substring(0, 1).toUpperCase() + 
                               schema_name.substring(1).toLowerCase() + ".S" + 
                               schema_name.substring(0, 1).toUpperCase() + 
                               schema_name.substring(1).toLowerCase() + ".";

    return type_schema_class;
  }

  String constructTypeAndFromReferencedSchemaClass(EEntity ed)
                                            throws SdaiException {
    String type_schema_class = "";
    SdaiModel entity_model = ed.findEntityInstanceSdaiModel();

    //    if (entity_model != current_model) {
    String schema_name = getSchema_definitionFromModel(entity_model).getName(null);
    type_schema_class = "jsdai.S" + schema_name.substring(0, 1).toUpperCase() + 
                        schema_name.substring(1).toLowerCase() + ".S" + 
                        schema_name.substring(0, 1).toUpperCase() + 
                        schema_name.substring(1).toLowerCase() + ".";

    //    }
    return type_schema_class;
  }

  String constructAggregationTypeBaseTypeNameString(EAggregation_type rt1, SdaiModel current_model)
                                             throws SdaiException {
    String type_name = "";

    if (rt1 instanceof EArray_type) {
      type_name += "_ARRAY";
      type_name += constructArrayBoundNames((EArray_type) rt1);

      // additionally - OPTIONAL and UNIQUE.
      if (((EArray_type) rt1).testOptional_flag(null)) {
        if (((EArray_type) rt1).getOptional_flag(null)) {
          type_name += "_OPTIONAL";
        }
      }

      if (((EArray_type) rt1).testUnique_flag(null)) {
        if (((EArray_type) rt1).getUnique_flag(null)) {
          type_name += "_UNIQUE";
        }
      }
    } else if (rt1 instanceof EBag_type) {
      type_name += "_BAG";
      type_name += constructBagListSetBoundNames((EVariable_size_aggregation_type) rt1);
    } else if (rt1 instanceof ESet_type) {
      type_name += "_SET";
      type_name += constructBagListSetBoundNames((EVariable_size_aggregation_type) rt1);
    } else if (rt1 instanceof EList_type) {
      type_name += "_LIST";
      type_name += constructBagListSetBoundNames((EVariable_size_aggregation_type) rt1);

      // UNIQUE
      if (((EList_type) rt1).testUnique_flag(null)) {
        if (((EList_type) rt1).getUnique_flag(null)) {
          type_name += "_UNIQUE";
        }
      }
    } else {
      // maybe AGGREGATE
      String aggr_name;

      if (rt1.testName(null)) {
        aggr_name = rt1.getName(null);

        if (aggr_name.substring(0, "_AGGREGATE".length()).equals("_AGGREGATE")) {
          type_name = "_AGGREGATE"; //aggr_name;
        } else {
          type_name += "_UNKNOWNAGGREGATE";
        }
      } else {
        type_name += "_NONAMEAGGREGATE";
      }
    }

    EEntity rt = null;
    int aggregate_depth = 1;
    EEntity an_ss = null;
    EEntity ass = rt1;
    String aggr_prefices = "";

    // an_ss = rt1.getElement_type(null);
    boolean element_type_set = rt1.testElement_type(null);

    if (element_type_set) {
      an_ss = rt1.getElement_type(null);
      rt = an_ss;
    } else {
      System.out.println("XP: In LocalVariable, aggregate element type value unset:" + rt1);
    }

    for (;;) {
      boolean done_something = false;

      if (an_ss instanceof EDefined_type) {
        //            ass = an_ss;
        //            boolean domain_set = ((EDefined_type)an_ss).testDomain(null);
        //            if (domain_set) {
        //               an_ss = ((EDefined_type)an_ss).getDomain(null);
        //               done_something = true;
        //            } else {
        //               System.out.println("XP: constructing type name, in aggregate nested defined type domain unset:" + an_ss);
        //            }
      } else if (an_ss instanceof EAggregation_type) {
        aggr_prefices += "a";
        aggregate_depth++;
        ass = an_ss;

        if (ass instanceof EArray_type) {
          type_name += "_ARRAY";
          type_name += constructArrayBoundNames((EArray_type) ass);

          if (((EArray_type) ass).testOptional_flag(null)) {
            if (((EArray_type) ass).getOptional_flag(null)) {
              type_name += "_OPTIONAL";
            }
          }

          if (((EArray_type) ass).testUnique_flag(null)) {
            if (((EArray_type) ass).getUnique_flag(null)) {
              type_name += "_UNIQUE";
            }
          }
        } else if (ass instanceof EBag_type) {
          type_name += "_BAG";
          type_name += constructBagListSetBoundNames((EVariable_size_aggregation_type) ass);
        } else if (ass instanceof ESet_type) {
          type_name += "_SET";
          type_name += constructBagListSetBoundNames((EVariable_size_aggregation_type) ass);
        } else if (ass instanceof EList_type) {
          type_name += "_LIST";
          type_name += constructBagListSetBoundNames((EVariable_size_aggregation_type) ass);

          if (((EList_type) ass).testUnique_flag(null)) {
            if (((EList_type) ass).getUnique_flag(null)) {
              type_name += "_UNIQUE";
            }
          }
        } else if (ass instanceof EAggregation_type) {
          // maybe AGGREGATE
          String aggr_name;

          if (((EAggregation_type) ass).testName(null)) {
            aggr_name = ((EAggregation_type) ass).getName(null);

            if (aggr_name.substring(0, "_AGGREGATE".length()).equals("_AGGREGATE")) {
              type_name = "_AGGREGATE"; //aggr_name;
            } else {
              type_name += "_UNKNOWNAGGREGATE";
            }
          } else {
            type_name += "_NONAMEAGGREGATE";
          }
        } else {
          type_name += "_NOTAGGREGATE";
        }

        element_type_set = ((EAggregation_type) an_ss).testElement_type(null);

        if (element_type_set) {
          an_ss = ((EAggregation_type) an_ss).getElement_type(null);
          done_something = true;
        } else {
          System.out.println(
                "XP: constructing type names, nested aggregate element type value unset:" + 
                an_ss);
        }
      }

      if (!done_something) {
        break;
      }
    }

    String aggr_prefix = "A" + aggr_prefices;
    rt = an_ss;

    if (rt == null) {
      type_name += "_NULL";
      System.out.println("XP: constructing type names: base type of aggregation type is null: " + 
                         rt1);
    } else if (rt instanceof EAggregation_type) {
      type_name += constructAggregationTypeBaseTypeNameString((EAggregation_type) rt, current_model);
    } else
    //      if (rt instanceof EAggregation_type) {
    //         type_name += "_AGGREGATE";
    //    } else
    if (rt instanceof EDefined_type) {
      type_name += constructDefinedTypeUnderlyingTypeNameString((EDefined_type) rt);
    } else if (rt instanceof ESelect_type) {
      // will not reach this case
      type_name += constructNameWithAggregatePackage(aggr_prefix, ass, current_model);
    } else if (rt instanceof EEntity_definition) {
      type_name += constructNameWithAggregatePackage(aggr_prefix, rt, current_model);
    } else if (rt instanceof EInteger_type) {
      type_name += "_INTEGER";
    } else if (rt instanceof EReal_type) {
      type_name += "_REAL";

      EReal_type real_type = (EReal_type) rt;
      boolean precision_present = real_type.testPrecision(null);

      if (precision_present) {
        EBound b_precision = real_type.getPrecision(null);

        if (b_precision instanceof EInteger_bound) {
          EInteger_bound i_precision = (EInteger_bound) b_precision;
          boolean integer_bound_set = i_precision.testBound_value(null);

          if (integer_bound_set) {
            int precision_value = i_precision.getBound_value(null);
            type_name += ("_" + precision_value);
          }
        }
      }
    } else if (rt instanceof ENumber_type) {
      type_name += "_NUMBER";
    } else if (rt instanceof EBoolean_type) {
      type_name += "_BOOLEAN";
    } else if (rt instanceof EBinary_type) {
      type_name += "_BINARY";

      EBinary_type binary_type = (EBinary_type) rt;
      boolean width_present = binary_type.testWidth(null);

      if (width_present) {
        EBound b_width = binary_type.getWidth(null);

        if (b_width instanceof EInteger_bound) {
          EInteger_bound i_width = (EInteger_bound) b_width;
          boolean integer_bound_set = i_width.testBound_value(null);

          if (integer_bound_set) {
            int width_value = i_width.getBound_value(null);
            type_name += ("_" + width_value);
          }
        }

        boolean fixed_present = binary_type.testFixed_width(null);

        if (fixed_present) {
          boolean fixed_value = binary_type.getFixed_width(null);

          if (fixed_value) {
            type_name += "_FIXED";
          }
        }
      }
    } else if (rt instanceof EEnumeration_type) {
      // will not reach
      type_name += "_ENUMERATION";
    } else if (rt instanceof ELogical_type) {
      type_name += "_LOGICAL";
    } else if (rt instanceof EString_type) {
      type_name += "_STRING";

      EString_type string_type = (EString_type) rt;
      boolean width_present = string_type.testWidth(null);

      if (width_present) {
        EBound b_width = string_type.getWidth(null);

        if (b_width instanceof EInteger_bound) {
          EInteger_bound i_width = (EInteger_bound) b_width;
          boolean integer_bound_set = i_width.testBound_value(null);

          if (integer_bound_set) {
            int width_value = i_width.getBound_value(null);
            type_name += ("_" + width_value);
          }
        }

        boolean fixed_present = string_type.testFixed_width(null);

        if (fixed_present) {
          boolean fixed_value = string_type.getFixed_width(null);

          if (fixed_value) {
            type_name += "_FIXED";
          }
        }
      }
    } else if (rt instanceof EData_type) {
      type_name += "_GENERIC";
    } else {
      System.out.println(
            "XP: constructing type names, not yet supported element type in aggregate: " + rt);
      type_name += "_UNSUPPORTED";
    }

    return type_name;
  }

  String constructBagListSetBoundNames(EVariable_size_aggregation_type vat)
                                throws SdaiException {
    // bounds may be absent.
    // if the lower bound is present, the upper bound may be ? - then I will have one bound only.
    // both bounds may be present. Bounds cannot be negative.   bound 2 >= bound 1
    // so we may have:
    // 1.  _SET_vector;
    // 2.  _SET_2_vector;
    // 3.  _SET_3_10_vector;
    String result = "";

    if (vat.testLower_bound(null)) {
      EBound lb = vat.getLower_bound(null);

      if (lb instanceof EInteger_bound) {
        EInteger_bound ilb = (EInteger_bound) lb;

        if (ilb.testBound_value(null)) {
          int lb_value = ilb.getBound_value(null);
          result += ("_" + lb_value);
        } else {
          // bound set but integer bound unset, strange - internal error
          result += "_1IUNSET";
        }
      } else {
        // may be population dependent bound
//        result += "_1PDB";
       	result += "_1PDB" + extractUniquePDBNumber((EPopulation_dependent_bound)lb);
      }

      if (vat.testUpper_bound(null)) {
        EBound ub = vat.getUpper_bound(null);

        if (ub instanceof EInteger_bound) {
          EInteger_bound iub = (EInteger_bound) ub;

          if (iub.testBound_value(null)) {
            int ub_value = iub.getBound_value(null);
            result += ("_" + ub_value);
          } else {
            // bound set but integer bound unset, strange - internal error
            result += "_2IUNSET";
          }
        } else {
          // may be population dependent bound
//          result += "_2PDB";
	       	result += "_2PDB" + extractUniquePDBNumber((EPopulation_dependent_bound)ub);
        }
      }
    }

    return result;
  }

  String constructArrayBoundNames(EArray_type vat) throws SdaiException {
    // both bounds are mandatory, may be negative, bound1 <= bound2,
    String result = "";

    if (vat.testLower_index(null)) {
      EBound lb = vat.getLower_index(null);

      if (lb instanceof EInteger_bound) {
        EInteger_bound ilb = (EInteger_bound) lb;

        if (ilb.testBound_value(null)) {
          int lb_value = ilb.getBound_value(null);

          if (lb_value < 0) {
            lb_value = -lb_value;
            result += ("_MINUS" + lb_value);
          } else {
            result += ("_" + lb_value);
          }
        } else {
          // bound set but integer bound unset, strange - internal error
          result += "_1IUNSET";
        }
      } else {
        // may be population dependent bound
//        result += "_1PDB";
       	result += "_1PDB" + extractUniquePDBNumber((EPopulation_dependent_bound)lb);
      }
    } else {
      result += "_1UNSET";
    }

    if (vat.testUpper_index(null)) {
      EBound ub = vat.getUpper_index(null);

      if (ub instanceof EInteger_bound) {
        EInteger_bound iub = (EInteger_bound) ub;

        if (iub.testBound_value(null)) {
          int ub_value = iub.getBound_value(null);

          if (ub_value < 0) {
            ub_value = -ub_value;
            result += ("_MINUS" + ub_value);
          } else {
            result += ("_" + ub_value);
          }
        } else {
          // bound set but integer bound unset, strange - internal error
          result += "_2IUNSET";
        }
      } else {
        // may be population dependent bound
//        result += "_2PDB";
       	result += "_2PDB" + extractUniquePDBNumber((EPopulation_dependent_bound)ub);
      }
    } else {
      result += "_2UNSET";
    }

    return result;
  }

  String constructNameWithAggregatePackage(String aggr_prefix, EEntity ee, SdaiModel current_model)
                                    throws SdaiException {
    String a_package = "";
    String a_name = "";

    if (ee instanceof ENamed_type) {
      a_name = ((ENamed_type) ee).getName(null);

      // a_name = a_name.substring(0,1).toUpperCase() + a_name.substring(1).toLowerCase();
    }

    SdaiModel a_model = ee.findEntityInstanceSdaiModel();

    if (a_model != current_model) {
      String a_schema_name = getSchema_definitionFromModel(a_model).getName(null);

      if (a_schema_name.equalsIgnoreCase("Sdai_dictionary_schema")) {
        a_package = "jsdai.dictionary.";
      } else {
        a_package = "jsdai.S" + a_schema_name.substring(0, 1).toUpperCase() + 
                    a_schema_name.substring(1).toLowerCase() + ".";
      }
    }

    //      return a_package;
    // the package must be handled differently.
    // return a_package + aggr_prefix + a_name;
    return "_" + a_name;
  }

  String constructDefinedTypeUnderlyingTypeNameString(EDefined_type rt1)
                                               throws SdaiException {
    String type_name = null;
    boolean name_set = rt1.testName(null);

    if (name_set) {
      return "_" + rt1.getName(null);
    } else {
      return "_UNSET";
    }
  }

  String getEntityPackage(EEntity_definition eds) throws SdaiException {
    // return an empty string if ed and eds in the same schema, and the package of eds otherwise
    String entity_package = "";
    SdaiModel entity_model = eds.findEntityInstanceSdaiModel();

    //    if (entity_model != current_model) {
    String entity_schema_name = getSchema_definitionFromModel(entity_model).getName(null);

    if (entity_schema_name.equalsIgnoreCase("Sdai_dictionary_schema")) {
      entity_package = "jsdai.dictionary.";
    } else {
      entity_package = "jsdai.S" + entity_schema_name.substring(0, 1).toUpperCase() + 
                       entity_schema_name.substring(1).toLowerCase() + ".";
    }

    //    }
    return entity_package;
  }

  ESchema_definition getSchema_definitionFromModel(SdaiModel sm)
                                            throws SdaiException {
    Aggregate ia = sm.getEntityExtentInstances(ESchema_definition.class);
    SdaiIterator iter_inst = ia.createIterator();

    while (iter_inst.next()) {
      ESchema_definition inst = (ESchema_definition) ia.getCurrentMemberObject(iter_inst);

      return inst;
    }

    return null;
  }
}
