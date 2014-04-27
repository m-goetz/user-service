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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.NoSuchElementException;

import edu.hm.cs.goetz1.seminar.model.User;

public class StandardUserDAO extends AbstractSqlUserDAO implements UserDAO {

    Connection connection = null;
    PreparedStatement statement = null;

    public StandardUserDAO() {
        try {
            connection = getConnection();
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "Users", null);
            if (!tables.next()) {
                statement = connection.prepareStatement(CREATE_TABLE);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public User createUser(String email, String firstName, String lastName, String password, Date birthDate) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CREATE_USER);
            statement.setString(1, email);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, password);
            statement.setDate(5, new java.sql.Date(birthDate.getTime()));
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new User(email, firstName, lastName, password, birthDate);
    }

    @Override
    public void deleteUser(String email) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_USER);
            statement.setString(1, email);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public User getUser(String email) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String resultEmail = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
                Date birthDate = rs.getTimestamp(5);
                return new User(resultEmail, firstName, lastName, password, birthDate);
            } else {
                throw new NoSuchElementException("User with email '" + email + "' not found");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public User updateUser(String email, String firstName, String lastName, String password, Date birthDate) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, password);
            statement.setDate(4, new java.sql.Date(birthDate.getTime()));
            statement.setString(5, email);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new User(email, firstName, lastName, password, birthDate);
    }

}
