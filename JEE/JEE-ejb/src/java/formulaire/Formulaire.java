/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formulaire;

import javax.ejb.Stateless;

/**
 *
 * @author echallier
 */
@Stateless
public class Formulaire implements FormulaireLocal {

    @Override
    public String Formulaire() {
        return "Salut";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
