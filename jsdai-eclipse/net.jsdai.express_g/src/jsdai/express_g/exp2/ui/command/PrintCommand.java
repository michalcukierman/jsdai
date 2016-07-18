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

package jsdai.express_g.exp2.ui.command;

import jsdai.express_g.exp2.ui.panels.PaintPanel;

import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author Mantas Balnys
 * @version 1.0
 */

public class PrintCommand extends AbstractPrintableCommand {

	public PrintCommand(CommandInvoker invoker) {
		super(invoker);
	}

	public void start() {
		PrintDialog dialog = new PrintDialog(((PaintPanel)prop.getPainting()).getShell());
		PrinterData data = dialog.open();
		if (data != null) print(data);
		prop.handler().commandDone();
	}

}
