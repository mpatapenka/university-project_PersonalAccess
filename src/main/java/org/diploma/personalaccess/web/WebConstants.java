package org.diploma.personalaccess.web;

import java.io.File;

/**
 * Web constants
 *
 * @author Maksim Patapenka
 */
public final class WebConstants {

    private WebConstants() { }


    /**
     * Redirect prefix
     */
    public static String REDIRECT = "redirect:";


    /**
     * User constants
     */
    public static class User {

        /**
         * Anonymous user login
         */
        public static final String ANONYMOUS = "anonymous";

    }

    /**
     * Directories with specified category pages
     */
    public static class Dir {

        /**
         * Error pages directory
         */
        public static final String ERROR = "error-pages" + File.separator;

        /**
         * Report pages directory
         */
        public static final String REPORT = "report-pages" + File.separator;

        /**
         * User pages directory
         */
        public static final String USER = "user-pages" + File.separator;

    }


    /**
     * Pages names
     */
    public static class Page {

        /**
         * 403 page name
         */
        public static final String PAGE_403 = "403";

        /**
         * 404 page name
         */
        public static final String PAGE_404 = "404";

        /**
         * Index page name
         */
        public static final String INDEX = "index";

        /**
         * Login page name
         */
        public static final String LOGIN = "login";

        /**
         * Admin page name
         */
        public static final String ADMIN = "admin";

        /**
         * Report employees page name
         */
        public static final String REPORT_EMPLOYEES = "reportEmployees";

        /**
         * Report ranges page name
         */
        public static final String REPORT_RANGES = "reportRanges";

        /**
         * Report indexes page name
         */
        public static final String REPORT_INDEXES = "reportIndexes";

        /**
         * Profile page name
         */
        public static final String PROFILE = "profile";

        /**
         * Dashboard page name
         */
        public static final String DASHBOARD = "dashboard";

        /**
         * Subordinate page name
         */
        public static final String SUBORDINATE = "subordinate";

    }

}
