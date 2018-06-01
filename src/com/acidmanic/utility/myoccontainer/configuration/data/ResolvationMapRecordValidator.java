/*
 * Copyright (C) 2018 Mani Moayedi (acidmanic.moayedi@gmail.com)
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
 */
package com.acidmanic.utility.myoccontainer.configuration.data;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ResolvationMapRecordValidator {

    public boolean isSaveSafe(MapRecord mapRecord) {
        if (mapRecord.getTaggedClass() == null) {
            return false;
        }
        if (mapRecord.getResolveArguments() == null) {
            return false;
        }
        if (mapRecord.getTaggedClass().getType() == null) {
            return false;
        }
        if (mapRecord.getTaggedClass().getTag() == null
                || mapRecord.getTaggedClass().getTag().length() == 0) {
            return false;
        }
        if (mapRecord.getResolveArguments().getTargetType() == null) {
            return false;
        }
        if (mapRecord.getResolveArguments().hasAnonymousBuilder()) {
            return false;
        }

        if (isRecreatableByName(mapRecord.getResolveArguments().getBuilder().getClass()) == false) {
            return false;
        }
        return true;
    }

    private boolean isRecreatableByName(Class targetType) {
        try {
            Class.forName(targetType.getName());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
