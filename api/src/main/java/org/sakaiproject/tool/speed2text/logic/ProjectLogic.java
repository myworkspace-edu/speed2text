/**
 * Licensed to MKS Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * MKS Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.sakaiproject.tool.speed2text.logic;

import java.io.IOException;
import java.io.InputStream;

/**
 * Business logic of the application.
 * 
 * @author Thach.Le (MyWorkpace App Marketplace)
 *
 */
public interface ProjectLogic {

	/**
	 * Convert audio of speech to text.
	 * @param tmpDir Temporary folder of system or Tomcat.
	 * @param fileName name of uploaded file
	 * @param cmdScripts 
	 * @return text of audio file.
	 * @throws IOException 
	 */
	String speech2Text(InputStream is, String fileName, String tmpDir, String scriptPath) throws IOException;
}
