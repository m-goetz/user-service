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

import edu.hm.cs.goetz1.seminar.db.StandardUserDAO;
import edu.hm.cs.goetz1.seminar.db.UserDAO;
import edu.hm.cs.goetz1.seminar.model.User;
import edu.hm.cs.goetz1.seminar.util.PasswordHash;
import edu.hm.cs.goetz1.seminar.util.Pbkdf2PasswordHash;

public class StandardUserService implements UserService {

    private UserDAO userDatabase;
    private PasswordHash passwordHash;

    public StandardUserService() {
        this.userDatabase = new StandardUserDAO();
        this.passwordHash = new Pbkdf2PasswordHash();
    }

    @Override
    public void changeUserPassword(String email, String oldPassword, String newPassword) {

        User user = userDatabase.getUser(email);
        if (!passwordHash.validatePassword(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("The old password is not correct");
        }
        userDatabase.updateUser(user.getEmail(), user.getFirstName(), user.getLastName(),
                passwordHash.createHash(newPassword), user.getBirthDate());
    }

    @Override
    public User addNewUser(String email, String firstName, String lastName, String password, Date birthDate) {
        return userDatabase.createUser(email, firstName, lastName, passwordHash.createHash(password), birthDate);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDatabase.getUser(email);
    }
}
