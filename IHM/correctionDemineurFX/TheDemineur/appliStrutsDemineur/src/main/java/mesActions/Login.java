/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package mesActions;

import modele.exceptions.ConnexionImpossible;
import modele.exceptions.JoueurDejaConnecte;

public class Login extends MonEnvironnementCommun {


    private String password;

    public String execute() throws Exception {

        try {
            int id = this.getMaFacade().connexion(username,password);
            this.getMapSession().put(MONID,id);
            this.getMapSession().put(MONPSEUDO,username);
            return SUCCESS;
        } catch (ConnexionImpossible connexionImpossible) {
         //   connexionImpossible.printStackTrace();
            addFieldError("username",getText("erreurconnexion"));
            return INPUT;
        } catch (JoueurDejaConnecte joueurDejaConnecte) {
            addFieldError("username",getText("pseudoconnecte"));
            return INPUT;

        }

    }


    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}