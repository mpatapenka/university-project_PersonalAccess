package org.diploma.personalaccess.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.diploma.personalaccess.entity.*;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.*;

/**
 * Util methods for using in services
 *
 * @author Maksim Patapenka
 */
public final class ServiceUtils {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(ServiceUtils.class);

    /**
     * Can not be instantiated
     */
    private ServiceUtils() { }


    /**
     * Subtract two sets
     *
     * @param minuend minuend set
     * @param subtrahend subtrahend set
     * @param <T> any type (must have correct equals and hashCode)
     * @return subtraction result
     */
    public static <T> Set<T> subtract(Set<T> minuend, Set<T> subtrahend) {
        Set<T> result = new HashSet<>();
        for (T item : minuend) {
            if (!subtrahend.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Create empty user index
     *
     * @param date creation date
     * @param user assigned user
     * @param index referenced index
     * @return empty user index
     */
    public static UserIndex createUserIndexStub(Date date, User user, Index index) {
        UserIndex userIndex = new UserIndex();
        userIndex.setFillDate(date);
        userIndex.setUser(user);
        userIndex.setDocument(null);
        userIndex.setComplete(false);
        userIndex.setDescription(null);
        userIndex.setIndex(index);
        /* This incorrect values need for use like a flag */
        userIndex.setLeadEstimate(-1);
        userIndex.setSelfEstimate(-1);

        return userIndex;
    }

    /**
     * Create value of header 'Content-Disposition'
     *
     * @param filename file name for attach
     * @return content disposition value
     */
    public static String createContentDispositionWithFilename(String filename) {
        final String prefix = "attachment; filename=\"";
        final String suffix = "\"; filename*=UTF-8''";

        String encodedFilename = filename;
        try {
            encodedFilename = URLEncoder.encode(filename.replaceAll(" ", "_"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("Filename can't be converted to UTF-8!", e);
        }

        return prefix + filename + suffix + encodedFilename;
    }

    public static User buildUserFromLdapAttributes(Attributes attr, List<Position> positions) {
        Position position = parseCustomPosition(getAttr("title", attr), positions);

        Unit unit = new Unit();
        unit.setName(getAttr("ou", attr));

        Form form = new Form();
        form.setFirstName(getAttr("givenName", attr));
        form.setMiddleName(getAttr("initials", attr));
        form.setLastName(getAttr("sn", attr));
        form.setPosition(position);
        form.setUnit(unit);

        User user = new User();
        user.setUsername(getAttr("uid", attr));
        user.setPassword("unused");
        user.setForm(form);

        return user;
    }

    public static Faculty associateFacultyByUnit(Unit unit, List<Faculty> faculties) {
        final String propertyFile = "unit-association.properties";

        try (InputStream is = ServiceUtils.class.getClassLoader().getResourceAsStream(propertyFile)) {
            Properties properties = new Properties();
            properties.load(is);

            Map<String, Faculty> chairFacultyAssoc = chairFacultyMapAssociation(properties, faculties);

            return chairFacultyAssoc.get(unit.getName());

        } catch (IOException e) {
            String message = "Can not find '" + propertyFile + "' in classpath.";
            log.error(message, e);
            throw new IllegalArgumentException(message, e);
        }
    }

    private static Map<String, Faculty> chairFacultyMapAssociation(Properties bundle, List<Faculty> faculties)
            throws UnsupportedEncodingException {
        Map<String, Faculty> result = new HashMap<>();
        for (Object key : bundle.keySet()) {
            String keyValue = new String(("" + key).getBytes("ISO-8859-1"), "UTF-8");

            Faculty value = null;
            for (Faculty faculty : faculties) {
                if (keyValue.equals(faculty.getShortName())) {
                    value = faculty;
                    break;
                }
            }

            if (value == null) {
                continue;
            }

            String allCChairsNotEncoded = (String) bundle.get(key);
            String allCChairs = new String(allCChairsNotEncoded.getBytes("ISO-8859-1"), "UTF-8");
            String[] chairsStrs = allCChairs.split(";");

            if (chairsStrs.length == 1 && StringUtils.isEmpty(chairsStrs[0])) {
                continue;
            }

            for (String chairStr : chairsStrs) {
                result.put(chairStr, value);
            }
        }

        return result;
    }

    private static Position parseCustomPosition(String name, List<Position> positions) {
        Position employeePos = null;

        for (Position pos : positions) {
            String simplePosName = pos.getName().trim().toLowerCase();
            name = name.trim().toLowerCase();

            if (name.contains(simplePosName)) {
                return pos;
            }

            /* Hardcoded it lol */
            if ("сотрудник".equals(simplePosName)) {
                employeePos = pos;
            }
        }

        return employeePos;
    }

    private static String getAttr(String name, Attributes attr) {
        try {
            return "" + attr.get(name).get();
        } catch (NamingException e) {
            log.error("Default message from LDAP can't be interpreted to normal value. Attr name = " + name);
        }
        return "";
    }

}
