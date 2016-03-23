package org.diploma.personalaccess.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Report controller, forming pages with reports
 *
 * @author Maksim Patapenka
 */
@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/report")
public class ReportController {
}
