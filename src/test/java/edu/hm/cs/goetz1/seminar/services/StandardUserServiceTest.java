/* 
 * Copyright 2014 Maximilian Goetz.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.hm.cs.goetz1.seminar.services;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;
import edu.hm.cs.goetz1.seminar.db.ConnectionFactory;
import edu.hm.cs.goetz1.seminar.model.User;
import edu.hm.cs.goetz1.seminar.util.PasswordHash;
import edu.hm.cs.goetz1.seminar.util.Pbkdf2PasswordHash;

public class StandardUserServiceTest {

    private static final String TEST_MAIL = "test@email.de";
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_LASTNAME = "Mustermann";
    private static final String TEST_PASSWORD = "totalGeheim";
    private static final Date TEST_BIRTHDATE = new GregorianCalendar(1993, 1, 21).getTime();

    @After
    public void clearDatabase() {
        ConnectionFactory factory = ConnectionFactory.getInstance();
        factory.dropTable("Users");
    }

    @Test
    public void testChangeUserPassword() {
        UserService userService = new StandardUserService();
        userService.addNewUser(TEST_MAIL, TEST_FIRSTNAME, TEST_LASTNAME, TEST_PASSWORD, TEST_BIRTHDATE);

        userService.changeUserPassword(TEST_MAIL, TEST_PASSWORD, "nochGeheimerAlsVorher");

        validateUserData(userService, TEST_MAIL, TEST_FIRSTNAME, TEST_LASTNAME, "nochGeheimerAlsVorher", TEST_BIRTHDATE);
    }

    @Test
    public void testAddNewUser() {
        UserService userService = new StandardUserService();

        userService.addNewUser(TEST_MAIL, TEST_FIRSTNAME, TEST_LASTNAME, TEST_PASSWORD, TEST_BIRTHDATE);

        validateUserData(userService, TEST_MAIL, TEST_FIRSTNAME, TEST_LASTNAME, TEST_PASSWORD, TEST_BIRTHDATE);
    }

    private void validateUserData(UserService userService, String email, String firstName, String lastName,
            String password, Date birthDate) {
        User user = userService.findUserByEmail(email);
        PasswordHash hash = new Pbkdf2PasswordHash();

        assertEquals("The expected email is not equal to the one in the database", email, user.getEmail());
        assertEquals("The expected first name is not equal to the one in the database", firstName, user.getFirstName());
        assertEquals("The expected last name is not equal to the one in the database", lastName, user.getLastName());
        assertTrue("The expected password is not equal to the one in the database",
                hash.validatePassword(password, user.getPassword()));
        assertEquals("The expected birth date is not equal to the one in the database", birthDate, user.getBirthDate());
    }
}
