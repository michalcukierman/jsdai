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

package jsdai.client;

public class SdaiRepositoryHeader implements java.io.Serializable {
	public static final int PERSISTENT_LABEL_BLOCK = 1000;

	  public String name;
	  public String description[];
	  public String changeDate;
	  public String author[];
	  public String organization[];
	  public String preprocessorVersion;
	  public String originatingSystem;
	  public String authorization;
	  public String defaultLanguage;
	  public String contextIdentifiers[];
//	  int rep_id;

}
