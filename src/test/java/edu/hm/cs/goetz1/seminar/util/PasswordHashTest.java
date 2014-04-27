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
package edu.hm.cs.goetz1.seminar.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordHashTest {

    @Test
    public void testCreateHashAndValidatePassword() {
        PasswordHash passwordHash = new Pbkdf2PasswordHash();
        String password = "totalGeheim!";

        String hashedPassword = passwordHash.createHash(password);

        assertTrue(passwordHash.validatePassword(password, hashedPassword));
    }
}
