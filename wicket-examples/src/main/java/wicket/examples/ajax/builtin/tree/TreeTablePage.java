/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wicket.examples.ajax.builtin.tree;

import wicket.extensions.markup.html.tree.AbstractTree;
import wicket.extensions.markup.html.tree.table.ColumnLocation;
import wicket.extensions.markup.html.tree.table.IColumn;
import wicket.extensions.markup.html.tree.table.PropertyRenderableColumn;
import wicket.extensions.markup.html.tree.table.PropertyTreeColumn;
import wicket.extensions.markup.html.tree.table.TreeTable;
import wicket.extensions.markup.html.tree.table.ColumnLocation.Alignment;
import wicket.extensions.markup.html.tree.table.ColumnLocation.Unit;

/**
 * Page that shows a simple tree table.
 *  
 * @author Matej Knopp
 */
public class TreeTablePage extends BaseTreePage
{
	private TreeTable tree;

	/**
	 * Page constructor.
	 */
	public TreeTablePage()	
	{
		IColumn columns[] = new IColumn[] {
			new PropertyTreeColumn(new ColumnLocation(Alignment.LEFT, 18, Unit.EM), "Tree Column", "userObject.property1"),
			new PropertyRenderableColumn(new ColumnLocation(Alignment.LEFT, 12, Unit.EM), "L2", "userObject.property2"),
			new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 2, Unit.PROPORTIONAL), "M1", "userObject.property3"),
			new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 2, Unit.PROPORTIONAL), "M2", "userObject.property4"),
			new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 3, Unit.PROPORTIONAL), "M3", "userObject.property5"),
			new PropertyRenderableColumn(new ColumnLocation(Alignment.RIGHT, 8, Unit.EM), "R1", "userObject.property6"),			
		};
		
		tree = new TreeTable("treeTable", createTreeModel(), columns);
		tree.getTreeState().setAllowSelectMultiple(true);
		add(tree);
		tree.getTreeState().collapseAll();
	}
	
	protected AbstractTree getTree()
	{
		return tree;
	}

}
