package com.mywallet.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mywallet.model.User;
import com.mywallet.model.UserExpense;
import com.mywallet.model.UserProfile;
import com.mywallet.model.UserProfileType;
import com.mywallet.service.UserExpenseService;
import com.mywallet.service.UserProfileService;
import com.mywallet.service.UserService;

@Controller
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserExpenseService userExpenseService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listExpenses(ModelMap model) {
		String username = getPrincipalUsername();
		User user = userService.findByUsername(username);
		List<UserExpense> userExpenses = userExpenseService.findAllUserExpensesByUser(user);
		model.addAttribute("userexpenses", userExpenses);
		model.addAttribute("loggedinuser", username);
		return "expenseslist";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/list";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@RequestMapping(value = "/newexpense", method = RequestMethod.GET)
	public String newExpense(ModelMap model) {
		String username = getPrincipalUsername();
		UserExpense userExpense = new UserExpense();
		model.addAttribute("userExpense", userExpense);
		model.addAttribute("loggedinuser", username);
		return "expenseform";
	}

	@RequestMapping(value = "/newexpense", method = RequestMethod.POST)
	public String saveExpense(@Valid UserExpense userExpense, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			model.addAttribute("loggedinuser", getPrincipalUsername());
			return "expenseform";
		}

		String username = getPrincipalUsername();
		userExpense.setUser(userService.findByUsername(username));

		if (userExpense.getId() == null) {
			userExpenseService.saveExpense(userExpense);
		} else {
			userExpenseService.updateUserExpense(userExpense);
		}

		return "redirect:/list";
	}

	@RequestMapping(value = { "/delete-expense-{id}" }, method = RequestMethod.GET)
	public String deleteExpense(@PathVariable Long id) {
		userExpenseService.deleteUserExpenseById(id);
		return "redirect:/list";
	}

	@RequestMapping(value = { "/edit-expense-{id}" }, method = RequestMethod.GET)
	public String editExpense(@PathVariable Long id, ModelMap model) {
		String username = getPrincipalUsername();
		UserExpense userExpense = userExpenseService.findById(id);
		model.addAttribute("userExpense", userExpense);
		model.addAttribute("loggedinuser", username);
		return "expenseform";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!userService.isUserUsernameUnique(user.getId(), user.getUsername())) {
			FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			return "registration";
		}

		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		userProfiles.add(userProfileService.findByType(UserProfileType.USER.toString()));
		user.setUserProfiles(userProfiles);

		userService.saveUser(user);

		return "redirect:/login";
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	private String getPrincipalUsername() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
