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

/* Generated By:JJTree: Do not edit this line. X_DepMapPartition.java */

package jsdai.expressCompiler;

import java.util.*;
import java.io.*;
import jsdai.lang.*;
import jsdai.SExtended_dictionary_schema.*;

public class X_DepMapPartition extends SimpleNode {


	EDependent_map_partition partition = null;
	EDependent_map_definition definition;

	static boolean flag_generate_execute_in_nodes = true;

  public X_DepMapPartition(int id) {
    super(id);
  }

  public X_DepMapPartition(Compiler2 p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(Compiler2Visitor visitor, Object data) throws jsdai.lang.SdaiException {
    return visitor.visit(this, data);
  }

  public Object childrenAccept(Compiler2Visitor visitor, Object data) throws jsdai.lang.SdaiException {
    JavaClass jc = ( JavaClass )data;
		PrintWriter pw = jc.pw;
	
		switch (jc.secondary) {
			case 1: // supertype map, WHERE rule
				break;
			case 2: // supertype map, processing map attributes
				break;
			case 0:
			default:

//				pw.println("INSIDE DpMapPartition: " + partition);

				boolean first_for_this_partition = true;
				jc.partition = partition;

				String mp_name = partition.getName(null);

				if(mp_name.equalsIgnoreCase("_implicit_partition_")){
					mp_name = "default";
				}


		ATarget_parameter atp = definition.getTarget_parameters(null, null);

			ASource_parameter asp = partition.getSource_parameters(null, null);
//System.out.println("source parameters: " + asp);
			jc.asp = asp; // save reference for src parameters to be used in Identified_by node
			SdaiIterator spi = asp.createIterator();
			int ind = 0;
			String add_instances="";


//######### map call implementation - START ###################################

			if (atp.getMemberCount() < 2) {


		first_for_this_partition = true;

//################## start - for map call
      jc.single_map_call = true;
 			jc.current_target = null;
 
//			pw.println("\tpublic AEntity processPartition_" +mp_name +"(SdaiModel src_model, SdaiModel tar_model, Hashtable target_instances) throws SdaiException {");
			pw.println("\t static Value callPartition_" + mp_name +"(SdaiModel src_model, SdaiModel tar_model, Hashtable target_instances, SdaiContext _context, Value input) throws SdaiException {");
//			pw.println("\t\tValue result = null;");
			pw.println("\t\tValue result = Value.alloc().unset();");

			// FROM part, generate source binding extent
			pw.println("\t// FROM part, generate source binding extent");
//already			ASource_parameter asp = mp.getSource_parameters(null, null);
			asp = partition.getSource_parameters(null, null);
// System.out.println("source parameters: " + asp);
			jc.asp = asp; // save reference for src parameters to be used in Identified_by node
//already			SdaiIterator spi = asp.createIterator();
			spi = asp.createIterator();
//already			int ind=0;
			ind = 0;
//already			String add_instances="";
			add_instances = "";

			pw.println("\t\tVector binding_inst_extent = new Vector();");
			pw.println("\t\tVector binding_inst = new Vector();");
			pw.println("\t\tHashtable src_id_map = new Hashtable();");

			
			// if it is a subtype, then we need to get source parameters from supertype, etc
			// let's have a separate handling of subtype maps
    

    // do not need all the instances in extent, input parameter instead 


		if (hasIdentify_by()) {
     
			while (spi.next()) {
				ind++;
				ESource_parameter sp = (ESource_parameter)asp.getCurrentMemberObject(spi);
				String sp_name = sp.getName(null);

//				EEntity_or_view_definition sp_extent = (EEntity_or_view_definition)sp.getExtent(null);
				EData_type sp_extent = (EData_type)sp.getExtent(null);
				String sp_extent_name = sp_extent.getName(null);
				String sp_package = getTypePackage(sp_extent, jc.model); 
				String normalized_name = sp_extent_name.substring(0, 1).toUpperCase() + sp_extent_name.substring(1).toLowerCase();
				String esrc_type = sp_package + "E" + normalized_name;
				String asrc_type = sp_package + "A" + normalized_name;

				pw.println("\t\t" +asrc_type +" a" +ind +"_inst = (" +asrc_type +")src_model.getInstances(" +esrc_type +".class);");
			}
			
		} // has identified_by

			// could be optimized more for a single source instance
			// but also for partial binding calls needed
			
			spi.beginning();
			ind=0;
			while (spi.next()) {
				ind++;
				ESource_parameter sp = (ESource_parameter)asp.getCurrentMemberObject(spi);
				String sp_name = sp.getName(null);

//				EEntity_or_view_definition sp_extent = (EEntity_or_view_definition)sp.getExtent(null);
				EData_type sp_extent = (EData_type)sp.getExtent(null);
				String sp_extent_name = sp_extent.getName(null);
				String sp_package = getTypePackage(sp_extent, jc.model); 
				String normalized_name = sp_extent_name.substring(0, 1).toUpperCase() + sp_extent_name.substring(1).toLowerCase();
				String esrc_type = sp_package + "E" + normalized_name;
				String csrc_type = sp_package + "C" + normalized_name;
				String asrc_type = sp_package + "A" + normalized_name;

				//						tabByIndex(ind, pw); pw.println(asrc_type +" a" +ind +"_inst = (" +asrc_type +")src_model.getInstances(" +esrc_type +".class);");
				pw.println("");

				if (hasIdentify_by()) {
					tabByIndex(ind, pw); pw.println("for (int i" +ind +" = 1; i" +ind +" < a" +ind +"_inst.getMemberCount()+1; i" +ind +"++) {");
					tabByIndex(ind, pw); pw.println("\tValue " +sp_name +" = Value.alloc(" +csrc_type +".definition).set(_context, a" +ind +"_inst.getByIndex(i" +ind +"));");
				} else {
					tabByIndex(ind, pw); pw.println("for (int i" +ind +" = 1; i" +ind +" < 2; i" +ind +"++) {");
					tabByIndex(ind, pw); pw.println("\tValue " +sp_name +" = input;");
				}
				add_instances = tabByIndexToString(asp.getMemberCount(), pw, add_instances); add_instances += "\tbinding_inst.addElement(" +sp_name +");\n";
				if(asp.getMemberCount() < 2) { tabByIndex(ind, pw); pw.println("\tbinding_inst.addElement(\"\");"); }
				if(ind == asp.getMemberCount()){ 
					tabByIndex(ind, pw); pw.println("\tbinding_inst = new Vector();");
					pw.println(add_instances);
				}
				tabByIndex(ind, pw); pw.println("\tsrc_id_map.put(\""+ sp_name +"\", new Integer(" +(ind-1) +"));");
			}

			//
			// WHERE part 
			//
			

			

			if (children != null) {

		  boolean attributes_present = false;

			// this is stupid, better spread the implementation through the single children loop
      boolean binding_header_not_emulated = true;
			 			
		  for (int i = 0; i < children.length; i++) {
				// possible children: 
				// X_BindingHeader (includes inside optional clauses: From clause, local declarations, WHERE clause, Identified_by clause, Ordered_by clause)
				// X_MapAttributeDeclaration
				// etc

				// so move actions for all this stuff into the children nodes (BindingHeader etc)

// System.out.println("current child: " + children[i] + ", current partition: " + mp.getName(null));

//				if (children[i] instanceof X_BindingHeader) {
				if (binding_header_not_emulated) {
					binding_header_not_emulated = false;
					i--;

					jc.identified_by = false;
//					if (((X_BindingHeader)children[i]).partition == partition) {
					if (true) {
						jc.ind = ind;
//						children[i].jjtAccept(visitor, data);
//            put directly here:

//====== start =====================
		boolean children_found = false;

		for (int ig = 0; ig < children.length; ig++) {
//			if (children[i] instanceof X_FromClause) { // currently no such clause
//			} else 	
//			if (children[i] instanceof X_LocalDecl) {
//			} else
			if (children[ig] instanceof X_WhereClause) {
				children_found = true;
				pw.println("\t\t\t// WHERE clause");
				tabByIndex(ind, pw); pw.print("\tif(");
				children[ig].jjtAccept(visitor, data);
				pw.println("){"); 
				tabByIndex(ind, pw); pw.println("\t\tbinding_inst_extent.addElement(binding_inst); // where clause");
				tabByIndex(ind, pw); pw.println("\t}");
				// i++; // generate next clause
				while(ind > 0){
					tabByIndex(ind, pw); pw.println("}");
					ind--;
				}
			} else {
				children_found = true;
				tabByIndex(ind, pw); pw.println("\tbinding_inst_extent.addElement(binding_inst); // not where clause");
				// needed for non-in-node-version, at least
				if (!flag_generate_execute_in_nodes) {
					tabByIndex(ind, pw); pw.println("\t}");
				}
				//								children[ig].jjtAccept(visitor, data);
				while(ind > 0){
					tabByIndex(ind, pw); pw.println("}");
					ind--;
				}
				if (children[ig] instanceof X_IdentifiedByClause) {
					pw.println("\t\t// IDENTIFIED_BY clause");
					children[ig].jjtAccept(visitor, data);

					pw.println("\t\t// Evaluating expression parts for the bindings of the same eq class and setting appopriate values");
					pw.println("\t\tIterator iter = alist.iterator();");
					pw.println("\t\tfor(int i = 0; i < alist.size() ; i++) {");
					pw.println("\t\t\tint inst_num = 0;");
					// don't do checking, rely on the size
					pw.println("\t\t\tVector eq_class = (Vector)iter.next();");

					jc.identified_by = true;

//				} else
//				if (children[ig] instanceof X_OrderedByClause) {
				} else {
					// should not occur
				} 
			}
		} // for - children
		if (children.length == 0) {
			tabByIndex(ind, pw); pw.println("\tbinding_inst_extent.addElement(binding_inst); // no children");
			if (!flag_generate_execute_in_nodes) {
//				tabByIndex(ind, pw); pw.println("\t}");
			}
			//children[ig].jjtAccept(visitor, data);
			while(ind > 0){
				tabByIndex(ind, pw); pw.println("}");
				ind--;
			}
		}

	if (!children_found) {
		tabByIndex(ind, pw); pw.println("\tbinding_inst_extent.addElement(binding_inst); // the rest");
		if (!flag_generate_execute_in_nodes) {
			
			ASource_parameter asp0 = jc.asp;
			if (asp0.getMemberCount() > 0) {

				tabByIndex(ind, pw); pw.println("\t}");
			}
		}
		//children[ig].jjtAccept(visitor, data);
		while(ind > 0){
			tabByIndex(ind, pw); pw.println("}");
			ind--;
		}
	
	}

//====== end =======================

					}


				} else
				if (children[i] instanceof X_MapAttributeDeclaration) {

					
					attributes_present = true;
					jc.first_pass = true; // define variables in X_MapAttributeDeclaration in first pass
					jc.first_pass = first_for_this_partition;



	// System.out.println("child: " + children[i] + ", partition: " + mp_name + ", is 1st pass: " + jc.first_pass);	
						if (((X_MapAttributeDeclaration)children[i]).partition == partition) { 
 	pw.println("// <DepMapPartition-02> - X_MapAttributeDeclaration");
							if (first_for_this_partition) {
								if (jc.identified_by == false) {
									pw.println("\t\tfor(int j=0; j < binding_inst_extent.size(); j++){ ");
								}
								((X_MapAttributeDeclaration)children[i]).targets = new HashSet();
								first_for_this_partition = false;
								// is it perhaps the same as jc.first_pass ?
							}
						
							children[i].jjtAccept(visitor, data);
						} else {
 	pw.println("// <DepMapPartition-03> - X_MapAttributeDeclaration, partition: " + partition + " - " + ((X_MapAttributeDeclaration)children[i]).partition);
						}
						
						
//						jc.first_pass = false;

				} else // MapAttributeDeclaration
				if (children[i] instanceof X_EntityInstantiationLoop) {
					attributes_present = true;
					children[i].jjtAccept(visitor, data);
				} // X_EntityInstantiationLoop


			} // for
			if (!attributes_present) {
				// better move from attribute, but temporarily, duplicate these things here
			
				// this whole thing is inside the loop through partitions, so it is ok
				// we way need to handle the following cases:
				// 1. no source parameters (no FROM clause) - just generate 1 instance
				// 2. 1 source parameter
				// 3. multiple source parameters
				// 4. if source parameters, also WHERE clause present or not
				// 5 IDENTIFIED_BY clause present or not
				

				ATarget_parameter atp2 = definition.getTarget_parameters(null, null);
				jsdai.lang.SdaiIterator tpi2 = atp2.createIterator();
				while (tpi2.next()) {
					ETarget_parameter tp2 = (ETarget_parameter)atp2.getCurrentMemberObject(tpi2);

				  if (jc.identified_by == false) {
							pw.println("\t\tfor(int j=0; j < binding_inst_extent.size(); j++){ ");

							jsdai.SExtended_dictionary_schema.ETarget_parameter target_parameter = tp2;
							String tar_par_name = target_parameter.getName(null);
							String plain_target_name = tar_par_name;
							Vector src_par_names = new Vector();
							Vector src_par_orders = new Vector();
							// target instance should be created only once - when first encountered target_parameter 
							HashSet targets = new HashSet();  // this is stupid, it does not do that, just so that it is compileable
					
							SdaiIterator spi2 = jc.asp.createIterator();
							while (spi2.next()) {
								ESource_parameter sp2 = (ESource_parameter)jc.asp.getCurrentMemberObject(spi2);
								String sp_name2 = sp2.getName(null);
								src_par_names.add(sp_name2);
							}


							tar_par_name += "_" +jc.target_par_count;
							jc.target_par_count++;

							pw.println("");

							for(int si=0; si < src_par_names.size(); si++){
								first_for_this_partition = false;

							}

							if(!targets.contains(plain_target_name)){
		    		    EEntity_definition target_type = target_parameter.getExtent(null);
								String tar_extent_name  = target_type.getName(null);
								String normalized_name = tar_extent_name.substring(0, 1).toUpperCase() + tar_extent_name.substring(1).toLowerCase();
								String tar_package = getEntityPackage(target_type);
								String etar_type2 = tar_package + "E" + normalized_name;
								String ctar_type2 = tar_package + "C" + normalized_name;
		
								pw.println("\t\t\t" +etar_type2 +" " +plain_target_name +"_inst = (" +etar_type2 +")tar_model.createEntityInstance(" +etar_type2 +".class);");		
								//TODO - wrap in Value and assign to result
								targets.add(plain_target_name);
							}


					} else { // no identified_by
						// identified_by present
						// TODO
					}

				} // while - through target parameters 		

			} // if attributes NOT present - constant partition
			
		} // if children NOT null (children include binding header)


		pw.println("\t\t}");					
//		pw.println("\t\treturn result;");

//		pw.println("\t\treturn Value.replaceByAlreadyExistingAndDelete(_context, result, " + jc.x_target_type + ".class, tar_model);");
		pw.println("\t\treturn result.replaceByAlreadyExistingAndDelete(_context, " + jc.x_target_type + ".class, tar_model);");
		pw.println("\t}");					

    jc.single_map_call = false;
    jc.current_target = null;

		} else {
			// if more than 1 target
			
			jsdai.lang.SdaiIterator tpi3 = atp.createIterator();

			while (tpi3.next()) {
				
			ETarget_parameter tp3 = (ETarget_parameter)atp.getCurrentMemberObject(tpi3); //here
			String tp3_name = tp3.getName(null);




		first_for_this_partition = true;

//################## start - for map call
      jc.single_map_call = true;
 			jc.current_target = tp3;
//			jc.target = tp;

 
//			pw.println("\tpublic AEntity processPartition_" +mp_name +"(SdaiModel src_model, SdaiModel tar_model, Hashtable target_instances) throws SdaiException {");
			pw.println("\t static Value callTarget_"+ tp3_name +"Partition_" + mp_name +"(SdaiModel src_model, SdaiModel tar_model, Hashtable target_instances, SdaiContext _context, Value input) throws SdaiException {");
//			pw.println("\t\tValue result = null;");
			pw.println("\t\tValue result = Value.alloc().unset();");

			// FROM part, generate source binding extent
			pw.println("\t// FROM part, generate source binding extent");
//already			ASource_parameter asp = mp.getSource_parameters(null, null);
			asp = partition.getSource_parameters(null, null);
// System.out.println("source parameters: " + asp);
			jc.asp = asp; // save reference for src parameters to be used in Identified_by node
//already			SdaiIterator spi = asp.createIterator();
			spi = asp.createIterator();
//already			int ind=0;
			ind = 0;
//already			String add_instances="";
			add_instances = "";

			pw.println("\t\tVector binding_inst_extent = new Vector();");
			pw.println("\t\tVector binding_inst = new Vector();");
			pw.println("\t\tHashtable src_id_map = new Hashtable();");

			
			// if it is a subtype, then we need to get source parameters from supertype, etc
			// let's have a separate handling of subtype maps
    

    // do not need all the instances in extent, input parameter instead 

		if (hasIdentify_by()) {

     
			while (spi.next()) {
				ind++;
				ESource_parameter sp = (ESource_parameter)asp.getCurrentMemberObject(spi);
				String sp_name = sp.getName(null);

//				EEntity_or_view_definition sp_extent = (EEntity_or_view_definition)sp.getExtent(null);
				EData_type sp_extent = (EData_type)sp.getExtent(null);
				String sp_extent_name = sp_extent.getName(null);
				String sp_package = getTypePackage(sp_extent, jc.model); 
				String normalized_name = sp_extent_name.substring(0, 1).toUpperCase() + sp_extent_name.substring(1).toLowerCase();
				String esrc_type = sp_package + "E" + normalized_name;
				String asrc_type = sp_package + "A" + normalized_name;

				pw.println("\t\t" +asrc_type +" a" +ind +"_inst = (" +asrc_type +")src_model.getInstances(" +esrc_type +".class);");
			}
		} // has identified_by

			// could be optimized more for a single source instance
			// but also for partial binding calls needed
			
			spi.beginning();
			ind=0;
			while (spi.next()) {
				ind++;
				ESource_parameter sp = (ESource_parameter)asp.getCurrentMemberObject(spi);
				String sp_name = sp.getName(null);

//				EEntity_or_view_definition sp_extent = (EEntity_or_view_definition)sp.getExtent(null);
				EData_type sp_extent = (EData_type)sp.getExtent(null);
				String sp_extent_name = sp_extent.getName(null);
				String sp_package = getTypePackage(sp_extent, jc.model); 
				String normalized_name = sp_extent_name.substring(0, 1).toUpperCase() + sp_extent_name.substring(1).toLowerCase();
				String esrc_type = sp_package + "E" + normalized_name;
				String csrc_type = sp_package + "C" + normalized_name;
				String asrc_type = sp_package + "A" + normalized_name;

				//						tabByIndex(ind, pw); pw.println(asrc_type +" a" +ind +"_inst = (" +asrc_type +")src_model.getInstances(" +esrc_type +".class);");
				pw.println("");
				if (hasIdentify_by()) {
					tabByIndex(ind, pw); pw.println("for (int i" +ind +" = 1; i" +ind +" < a" +ind +"_inst.getMemberCount()+1; i" +ind +"++) {");
					tabByIndex(ind, pw); pw.println("\tValue " +sp_name +" = Value.alloc(" +csrc_type +".definition).set(_context, a" +ind +"_inst.getByIndex(i" +ind +"));");
				} else {
					tabByIndex(ind, pw); pw.println("for (int i" +ind +" = 1; i" +ind +" < 2; i" +ind +"++) {");
					tabByIndex(ind, pw); pw.println("\tValue " +sp_name +" = input;");
				}
				add_instances = tabByIndexToString(asp.getMemberCount(), pw, add_instances); add_instances += "\tbinding_inst.addElement(" +sp_name +");\n";
				if(asp.getMemberCount() < 2) { tabByIndex(ind, pw); pw.println("\tbinding_inst.addElement(\"\");"); }
				if(ind == asp.getMemberCount()){ 
					tabByIndex(ind, pw); pw.println("\tbinding_inst = new Vector();");
					pw.println(add_instances);
				}
				tabByIndex(ind, pw); pw.println("\tsrc_id_map.put(\""+ sp_name +"\", new Integer(" +(ind-1) +"));");
			}

			//
			// WHERE part 
			//
			

			if (children != null) {

		  boolean attributes_present = false;

      boolean binding_header_not_emulated = true;
			 			
		  for (int i = 0; i < children.length; i++) {
				// possible children: 
				// X_BindingHeader (includes inside optional clauses: From clause, local declarations, WHERE clause, Identified_by clause, Ordered_by clause)
				// X_MapAttributeDeclaration
				// etc

				// so move actions for all this stuff into the children nodes (BindingHeader etc)

// System.out.println("current child: " + children[i] + ", current partition: " + mp.getName(null));

//				if (children[i] instanceof X_BindingHeader) {
				if (binding_header_not_emulated) {
      		binding_header_not_emulated = false;
          i--;
          
					jc.identified_by = false;
//					if (((X_BindingHeader)children[i]).partition == partition) {
					if (true) {
						jc.ind = ind;
//						children[i].jjtAccept(visitor, data);
//           put directly here, or even better spread to the outer children loop


//====== start =====================
		boolean children_found = false;

		for (int ig = 0; ig < children.length; ig++) {
//			if (children[i] instanceof X_FromClause) { // currently no such clause
//			} else 	
//			if (children[i] instanceof X_LocalDecl) {
//			} else
			if (children[ig] instanceof X_WhereClause) {
				children_found = true;
				pw.println("\t\t\t// WHERE clause");
				tabByIndex(ind, pw); pw.print("\tif(");
				children[ig].jjtAccept(visitor, data);
				pw.println("){"); 
				tabByIndex(ind, pw); pw.println("\t\tbinding_inst_extent.addElement(binding_inst); // where clause");
				tabByIndex(ind, pw); pw.println("\t}");
				// i++; // generate next clause
				while(ind > 0){
					tabByIndex(ind, pw); pw.println("}");
					ind--;
				}
			} else {
				children_found = true;
				tabByIndex(ind, pw); pw.println("\tbinding_inst_extent.addElement(binding_inst); // not where clause");
				// needed for non-in-node-version, at least
				if (!flag_generate_execute_in_nodes) {
					tabByIndex(ind, pw); pw.println("\t}");
				}
				//								children[ig].jjtAccept(visitor, data);
				while(ind > 0){
					tabByIndex(ind, pw); pw.println("}");
					ind--;
				}
				if (children[ig] instanceof X_IdentifiedByClause) {
					pw.println("\t\t// IDENTIFIED_BY clause");
					children[ig].jjtAccept(visitor, data);

					pw.println("\t\t// Evaluating expression parts for the bindings of the same eq class and setting appopriate values");
					pw.println("\t\tIterator iter = alist.iterator();");
					pw.println("\t\tfor(int i = 0; i < alist.size() ; i++) {");
					pw.println("\t\t\tint inst_num = 0;");
					// don't do checking, rely on the size
					pw.println("\t\t\tVector eq_class = (Vector)iter.next();");

					jc.identified_by = true;

//				} else
//				if (children[ig] instanceof X_OrderedByClause) {
				} else {
					// should not occur
				} 
			}
		} // for - children
		if (children.length == 0) {
			tabByIndex(ind, pw); pw.println("\tbinding_inst_extent.addElement(binding_inst); // no children");
			if (!flag_generate_execute_in_nodes) {
//				tabByIndex(ind, pw); pw.println("\t}");
			}
			//children[ig].jjtAccept(visitor, data);
			while(ind > 0){
				tabByIndex(ind, pw); pw.println("}");
				ind--;
			}
		}

	if (!children_found) {
		tabByIndex(ind, pw); pw.println("\tbinding_inst_extent.addElement(binding_inst); // the rest");
		if (!flag_generate_execute_in_nodes) {
			
			ASource_parameter asp0 = jc.asp;
			if (asp0.getMemberCount() > 0) {

				tabByIndex(ind, pw); pw.println("\t}");
			}
		}
		//children[ig].jjtAccept(visitor, data);
		while(ind > 0){
			tabByIndex(ind, pw); pw.println("}");
			ind--;
		}
	
	}

//====== end =======================



					}


				} else
				if (children[i] instanceof X_MapAttributeDeclaration) {
					
					attributes_present = true;
					jc.first_pass = true; // define variables in X_MapAttributeDeclaration in first pass
					jc.first_pass = first_for_this_partition;



	// System.out.println("child: " + children[i] + ", partition: " + mp_name + ", is 1st pass: " + jc.first_pass);	
						if (((X_MapAttributeDeclaration)children[i]).partition == partition) { 
 	pw.println("// <DepMapPartition-05> - X_MapAttributeDeclaration");
							if (first_for_this_partition) {
								if (jc.identified_by == false) {
									pw.println("\t\tfor(int j=0; j < binding_inst_extent.size(); j++){ ");
								}
								((X_MapAttributeDeclaration)children[i]).targets = new HashSet();
								first_for_this_partition = false;
								// is it perhaps the same as jc.first_pass ?
							}
						
							children[i].jjtAccept(visitor, data);
						} else {
 	pw.println("// <DepMapPartition-06> - X_MapAttributeDeclaration, partition: " + partition + " - " + ((X_MapAttributeDeclaration)children[i]).partition);
						}
//						jc.first_pass = false;

				} else // MapAttributeDeclaration
				if (children[i] instanceof X_EntityInstantiationLoop) {
					attributes_present = true;
					children[i].jjtAccept(visitor, data);
				} // X_EntityInstantiationLoop


			} // for
			if (!attributes_present) {
				// better move from attribute, but temporarily, duplicate these things here
			
				// this whole thing is inside the loop through partitions, so it is ok
				// we way need to handle the following cases:
				// 1. no source parameters (no FROM clause) - just generate 1 instance
				// 2. 1 source parameter
				// 3. multiple source parameters
				// 4. if source parameters, also WHERE clause present or not
				// 5 IDENTIFIED_BY clause present or not
				

				ATarget_parameter atp2 = definition.getTarget_parameters(null, null);
				jsdai.lang.SdaiIterator tpi2 = atp2.createIterator();
				while (tpi2.next()) {
					ETarget_parameter tp2 = (ETarget_parameter)atp2.getCurrentMemberObject(tpi2);

				  if (jc.identified_by == false) {
							pw.println("\t\tfor(int j=0; j < binding_inst_extent.size(); j++){ ");

							jsdai.SExtended_dictionary_schema.ETarget_parameter target_parameter = tp2;
							String tar_par_name = target_parameter.getName(null);
							String plain_target_name = tar_par_name;
							Vector src_par_names = new Vector();
							Vector src_par_orders = new Vector();
							// target instance should be created only once - when first encountered target_parameter 
							HashSet targets = new HashSet();  // this is stupid, it does not do that, just so that it is compileable
					
							SdaiIterator spi2 = jc.asp.createIterator();
							while (spi2.next()) {
								ESource_parameter sp2 = (ESource_parameter)jc.asp.getCurrentMemberObject(spi2);
								String sp_name2 = sp2.getName(null);
								src_par_names.add(sp_name2);
							}


							tar_par_name += "_" +jc.target_par_count;
							jc.target_par_count++;

							pw.println("");

							for(int si=0; si < src_par_names.size(); si++){
								first_for_this_partition = false;

							}

							if(!targets.contains(plain_target_name)){
		    		    EEntity_definition target_type = target_parameter.getExtent(null);
								String tar_extent_name  = target_type.getName(null);
								String normalized_name = tar_extent_name.substring(0, 1).toUpperCase() + tar_extent_name.substring(1).toLowerCase();
								String tar_package = getEntityPackage(target_type);
								String etar_type2 = tar_package + "E" + normalized_name;
								String ctar_type2 = tar_package + "C" + normalized_name;
		
								pw.println("\t\t\t" +etar_type2 +" " +plain_target_name +"_inst = (" +etar_type2 +")tar_model.createEntityInstance(" +etar_type2 +".class);");		
								//TODO - wrap in Value and assign to result
								targets.add(plain_target_name);
							}


					} else { // no identified_by
						// identified_by present
						// TODO
					}

				} // while - through target parameters 		

			} // if attributes NOT present - constant partition
			
		} // if children NOT null (children include binding header)


		pw.println("\t\t}");					
//		pw.println("\t\treturn result;");

//		pw.println("\t\treturn Value.replaceByAlreadyExistingAndDelete(_context, result, " + jc.x_target_type + ".class, tar_model);");
		pw.println("\t\treturn result.replaceByAlreadyExistingAndDelete(_context, " + jc.x_target_type + ".class, tar_model);");
		pw.println("\t}");					

    jc.single_map_call = false;
    jc.target = null;
    jc.current_target = null;

		} // while - through the targets
			
		} // if more than one target parameter



//######### map call implementation - END #######################################



		} // switch jc	

		return data;
	}


  // check if identified_by can even be in dependent_maps, if not, this implementation is ok
	boolean hasIdentify_by() {
		return false;
	}


  String getTypePackage(EData_type eds, SdaiModel current_model)
		throws jsdai.lang.SdaiException {

    // return an empty string if ed and eds in the same schema, and the package of eds otherwise
    String type_package = "";
    
    SdaiModel type_model = eds.findEntityInstanceSdaiModel();
		
    if (type_model != current_model) {
      String type_schema_name = getSchema_definitionFromModel(type_model).getName(null);

      if (type_schema_name.equalsIgnoreCase("Sdai_dictionary_schema")) {
        type_package = "jsdai.dictionary.";
      } else {
        type_package = "jsdai.S" + type_schema_name.substring(0, 1).toUpperCase() + type_schema_name.substring(1).toLowerCase() + ".";
      }
    }

    return type_package;
  }


  String getEntityPackage(EEntity_or_view_definition eds, SdaiModel current_model)
		throws jsdai.lang.SdaiException {

    // return an empty string if ed and eds in the same schema, and the package of eds otherwise
    String entity_package = "";
    SdaiModel entity_model = eds.findEntityInstanceSdaiModel();
		
    if (entity_model != current_model) {
      String entity_schema_name = getSchema_definitionFromModel(entity_model).getName(null);

      if (entity_schema_name.equalsIgnoreCase("Sdai_dictionary_schema")) {
        entity_package = "jsdai.dictionary.";
      } else {
        entity_package = "jsdai.S" + entity_schema_name.substring(0, 1).toUpperCase() + entity_schema_name.substring(1).toLowerCase() + ".";
      }
    }

    return entity_package;
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


  EGeneric_schema_definition getSchema_definitionFromModel(SdaiModel sm) throws jsdai.lang.SdaiException {
    jsdai.lang.Aggregate ia = sm.getEntityExtentInstances(EGeneric_schema_definition.class);
    jsdai.lang.SdaiIterator iter_inst = ia.createIterator();

    while (iter_inst.next()) {
      EGeneric_schema_definition inst = ( EGeneric_schema_definition )ia.getCurrentMemberObject(iter_inst);
      return inst;
    }

    return null;
	  }

	void tabByIndex(int ind, PrintWriter pw){
		pw.print("\t");
		for(int tt=0; tt < ind; tt++) pw.print("\t");
	}

	String  tabByIndexToString(int ind, PrintWriter pw, String st){
		st += "\t";
		for(int tt=0; tt < ind; tt++)	st += "\t";
		return st;
	}


	  jsdai.SExtended_dictionary_schema.EMap_definition getRootSupertypeMap( jsdai.SExtended_dictionary_schema.EMap_definition map_definition) throws SdaiException {
			jsdai.SExtended_dictionary_schema.EMap_definition md = null;
			if (map_definition.testSuper_type(null)) {
				md = getRootSupertypeMap(map_definition.getSuper_type(null));
			} else {
				md = map_definition;
			}
			return md;
		}



}
