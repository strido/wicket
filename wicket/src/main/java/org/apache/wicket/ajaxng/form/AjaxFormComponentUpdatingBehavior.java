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
package org.apache.wicket.ajaxng.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.FormView;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajaxng.AjaxEventBehavior;
import org.apache.wicket.ajaxng.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;

public class AjaxFormComponentUpdatingBehavior extends AjaxEventBehavior
{
	private static final long serialVersionUID = 1L;

	public AjaxFormComponentUpdatingBehavior(String event)
	{
		super(event);
	}

	@Override
	public void bind(Component component)
	{
		if (component instanceof FormComponent == false)
		{
			throw new WicketRuntimeException("Behavior " + getClass().getName() +
				" can only be added to an instance of a FormComponent");
		}
		super.bind(component);
	}

	private List<FormComponent<?>> getFormComponents()
	{
		List<FormComponent<?>> result = new ArrayList<FormComponent<?>>();

		for (Component c : getBoundComponents())
		{
			result.add((FormComponent<?>)c);
		}

		return result;
	}

	private void processComponentValidators(List<FormComponent<?>> components)
	{
		for (FormComponent<?> component : components)
		{
			component.inputChanged();
			component.validate();
			if (component.hasErrorMessage())
			{
				component.invalid();
			}
			else
			{
				component.valid();
			}
		}
	}

	protected Form<?> getForm(List<FormComponent<?>> components)
	{
		FormComponent<?> first = components.get(0);
		return first.getForm();
	}
	
	@Override
	protected Form<?> getForm(Component component)
	{
		return component.findParent(Form.class);
	}

	private boolean validatorApplies(IFormValidator validator, List<FormComponent<?>> components)
	{
		for (FormComponent<?> component : validator.getDependentFormComponents())
		{
			if (!components.contains(component))
			{
				return false;
			}
		}
		return true;
	}

	private void processFormValidators(List<FormComponent<?>> components)
	{
		Form<?> form = getForm(components);
		for (IFormValidator validator : form.getFormValidators())
		{
			if (validatorApplies(validator, components))
			{
				form.validateFormValidator(validator);
			}
		}
	}

	@Override
	protected final void onEvent(AjaxRequestTarget target)
	{
		try
		{
			List<FormComponent<?>> components = getFormComponents();

			processComponentValidators(components);
			processFormValidators(components);

			boolean validated = true;

			for (FormComponent<?> component : components)
			{
				if (component.hasErrorMessage())
				{
					validated = false;
					break;
				}
			}

			if (validated == true)
			{
				if (getUpdateModel())
				{
					for (FormComponent<?> component : components)
					{
						component.updateModel();
					}
				}
				onUpdate(target);
			}
			else
			{
				onError(target);
			}
		}
		catch (RuntimeException e)
		{
			onException(target, e);
		}
	}

	/**
	 * @return true if the model of form component should be updated, false otherwise
	 */
	protected boolean getUpdateModel()
	{
		return true;
	}

	protected void onUpdate(AjaxRequestTarget target)
	{

	}

	protected void onError(AjaxRequestTarget target)
	{

	}

	protected void onException(AjaxRequestTarget target, RuntimeException e)
	{
		throw (e);
	}
}
