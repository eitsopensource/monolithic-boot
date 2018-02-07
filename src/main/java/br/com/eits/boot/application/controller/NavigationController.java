package br.com.eits.boot.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 */
@Controller
public class NavigationController
{
	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/

	/**
	 *
	 */
	@GetMapping("/")
	public String home()
	{
		return "modules/app/index";
	}

	/**
	 *
	 */
	@GetMapping("/authentication")
	public String authentication()
	{
		return "modules/authentication/authentication";
	}

	@GetMapping("/password-reset")
	public String passwordReset()
	{
		return "modules/authentication/password-reset";
	}
}
