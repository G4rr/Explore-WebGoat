
/*
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2019 Bruce Mayhew
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * Getting Source ==============
 *
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software projects.
 */

package org.owasp.webgoat.sql_injection.introduction;

import org.owasp.webgoat.assignments.AssignmentEndpoint;
import org.owasp.webgoat.assignments.AssignmentHints;
import org.owasp.webgoat.assignments.AttackResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AssignmentHints(value = {"SqlStringInjectionHint5-a"})
public class SqlInjectionLesson5 extends AssignmentEndpoint {

    @PostMapping("/SqlInjection/attack5")
    @ResponseBody
    public AttackResult completed(@RequestParam("_query") String query) {
        return injectableQuery(query);
    }

    protected AttackResult injectableQuery(String query) {
        try {
            String regex = "(?i)^(grant alter table to [\"']?unauthorizedUser[\"']?)(?:[;]?)$";
            StringBuffer output = new StringBuffer();

            // user completes lesson if the query is correct
            if (query.matches(regex)) {
                output.append("<span class='feedback-positive'>" + query + "</span>");
                return trackProgress(success().output(output.toString()).build());
            } else {
                return trackProgress(failed().output(output.toString()).build());
            }
        } catch (Exception e) {
            return trackProgress(failed().output(this.getClass().getName() + " : " + e.getMessage()).build());
        }
    }
}