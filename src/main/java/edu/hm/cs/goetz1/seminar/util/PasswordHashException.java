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

public class PasswordHashException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PasswordHashException(String message, Throwable e) {
        super(message, e);
    }

    public PasswordHashException(String message) {
        super(message);
    }

    public PasswordHashException(Throwable e) {
        super(e);
    }
}
