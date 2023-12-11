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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mksgroup.java.common.FileUtil;

/**
 * Implementation of {@link ProjectLogic}
 * 
 * @author Thach.Le (MyWorkspace App Marketplace)
 *
 */
@Service
@Slf4j
public class ProjectLogicImpl implements ProjectLogic {

	/**
	 * init - perform any actions required here for when this bean starts up
	 */
	public void init() {
		log.info("init");
	}

	@Override
	public String speech2Text(InputStream is, String fileName, String tmpDir) throws IOException {
		String outPath = FileUtil.buildPath(tmpDir, "speech2text");
		FileUtil.mkdir(outPath);
		
		// Out file path
		String outFilePath = FileUtil.buildPath(outPath, fileName);
		
		File outputFile = new File(outFilePath);
		FileUtils.copyInputStreamToFile(is, outputFile);
		
		log.info(String.format("View temporary file at' %s'", outFilePath));

		return "OK";
	}

}
