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

package org.sakaiproject.tool.speed2text.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sakaiproject.tool.speed2text.controller.model.SpeechModel;
import org.sakaiproject.tool.speed2text.logic.ProjectLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 * @author Thach.Le (MyWorkpace App Marketplace)
 */
@Controller
public class HomeController extends BaseController {
    
	@Autowired
	ProjectLogic projectLogic;
	
	@Autowired
	ServletContext context;
	/**
	 * Simply selects the home view to render by returning its name.
     * @return 
	 */
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public ModelAndView displayHome(HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("home");

		initSession(request, httpSession);
		
		mav.addObject("currentSiteId", getCurrentSiteId());
		mav.addObject("userDisplayName", getCurrentUserDisplayName());

		return mav;
	}

    @PostMapping(value = "/speech2text/uploadfile")
    @ResponseBody
	public String processUploadFle(@ModelAttribute("model") SpeechModel model, BindingResult bindingResult) throws IOException {
    	String folderScript = context.getRealPath("WEB-INF/classes");

    	String text = projectLogic.speech2Text(model.getAttachment().getInputStream(), model.getAttachment().getOriginalFilename(), TMP_DIR, folderScript);

    	return text;
	}
		
}
