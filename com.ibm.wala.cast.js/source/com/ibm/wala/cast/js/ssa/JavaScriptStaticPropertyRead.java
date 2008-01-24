/******************************************************************************
 * Copyright (c) 2002 - 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *****************************************************************************/
package com.ibm.wala.cast.js.ssa;

import java.util.Collection;

import com.ibm.wala.cast.js.types.JavaScriptTypes;
import com.ibm.wala.ssa.SSAGetInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.strings.Atom;

public class JavaScriptStaticPropertyRead extends SSAGetInstruction {

  public JavaScriptStaticPropertyRead(int result,
				      int objectRef,
				      FieldReference memberRef) {
    super(result, objectRef, memberRef);
  }

  public JavaScriptStaticPropertyRead(int result,
				      int objectRef,
				      String fieldName) 
  {
    this(result, 
	 objectRef, 
	 FieldReference.findOrCreate(
	   JavaScriptTypes.Root,
	   Atom.findOrCreateUnicodeAtom(fieldName),
	   JavaScriptTypes.Root));
  }

  public SSAInstruction copyForSSA(int[] defs, int[] uses) {
    return
      new JavaScriptStaticPropertyRead(
        defs==null? getDef(): defs[0],
	uses==null? getRef(): uses[0],
	getDeclaredField());
  }

  /* (non-Javadoc)
   * @see com.ibm.domo.ssa.Instruction#getExceptionTypes()
   */
  public Collection<TypeReference> getExceptionTypes() {
    return Util.typeErrorExceptions();
  }

}
