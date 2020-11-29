package com.cocoe.spring.ui.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class UIControllerHelper {
	
	 public Authentication getAuthentication() {
	        return SecurityContextHolder.getContext().getAuthentication();
	    }

}
