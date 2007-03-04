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
package wicket.examples.images;

import wicket.examples.WicketExampleApplication;
import wicket.markup.html.image.resource.DefaultButtonImageResource;
import wicket.protocol.http.request.urlcompressing.UrlCompressingWebRequestProcessor;
import wicket.protocol.http.request.urlcompressing.UrlCompressor;
import wicket.request.IRequestCycleProcessor;

/**
 * WicketServlet class for wicket.examples.linkomatic example.
 * 
 * @author Jonathan Locke
 */
public class ImagesApplication extends WicketExampleApplication
{
	/**
	 * Constructor
	 */
	public ImagesApplication()
	{

	}

	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class getHomePage()
	{
		return Home.class;
	}

	/**
	 * @see wicket.examples.WicketExampleApplication#init()
	 */
	protected void init()
	{
		getSharedResources().add("cancelButton", new DefaultButtonImageResource("Cancel"));
	}

	/**
	 * Special overwrite to have url compressing for this example.
	 * 
	 * @see UrlCompressor
	 * @see wicket.protocol.http.WebApplication#newRequestCycleProcessor()
	 */
	protected IRequestCycleProcessor newRequestCycleProcessor()
	{
		return new UrlCompressingWebRequestProcessor();
	}
}
