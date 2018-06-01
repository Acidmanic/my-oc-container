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
package com.acidmanic.utility.myoccontainer.resolvestrategies;

import com.acidmanic.utility.myoccontainer.configuration.ResolvationMapRecordDictionary;
import com.acidmanic.utility.myoccontainer.configuration.data.TaggedClass;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveArguments;

/**
 *
 * @author diego
 */
public class DefaultOrAnyResolveStrategy extends ResolveStrategyBase {

    public DefaultOrAnyResolveStrategy(ResolvationMapRecordDictionary dependancyDictionary) {
        super(dependancyDictionary);
    }

    @Override
    public ResolveArguments search(Class resolving, String tagIfAny) {
        ResolveArguments result = null;

        try {
            result = this.dependancyDictionary.get(resolving, TaggedClass.DEFAULT_TAG)
                    .getResolveArguments();
        } catch (Exception e) {
        }
        if (result == null) {
            try {
                result = this.dependancyDictionary.searchForAKey(resolving)
                        .getResolveArguments();
            } catch (Exception e) {
            }
        }
        return result;
    }

}
