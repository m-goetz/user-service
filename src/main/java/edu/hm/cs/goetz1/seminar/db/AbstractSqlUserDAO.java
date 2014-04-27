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
package edu.hm.cs.goetz1.seminar.db;

public abstract class AbstractSqlUserDAO {

    public static final String CREATE_TABLE = "CREATE TABLE Users (email VARCHAR(80) NOT NULL, "
            + "first_name VARCHAR(50) NOT NULL, " + "last_name VARCHAR(50) NOT NULL, "
            + "password CHAR(102) NOT NULL, " + "birth_date DATE," + "PRIMARY KEY (email));";

    public static final String FIND_BY_EMAIL = "SELECT * FROM Users WHERE email = ?;";

    public static final String CREATE_USER = "INSERT INTO Users "
            + "(email, first_name, last_name, password, birth_date) " + "VALUES (?,?,?,?,?);";

    public static final String DELETE_USER = "DELETE FROM Users WHERE email = ?;";

    public static final String UPDATE_USER = "UPDATE Users SET " + "first_name = ?," + "last_name = ?,"
            + "password = ?," + "birth_date = ?" + "WHERE email = ?;";
}
