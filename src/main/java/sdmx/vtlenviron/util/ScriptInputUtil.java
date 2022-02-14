/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.vtlenviron.util;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sdmx.vtlenviron.entities.Script;
import sdmx.vtlenviron.entities.ScriptInput;
import sdmx.vtlenviron.entities.ScriptOutput;

/**
 *
 * @author jsg
 */
public class ScriptInputUtil {
     public static Script createScriptInput(Script s) {
        ScriptInput so = new ScriptInput();
        so.setDescription("New Input");
        so.setName("Input");
        so.setScript(s);
        if(s.getScriptInputList()==null) {
          s.setScriptInputList(new ArrayList<ScriptInput>());
        }
        s.getScriptInputList().add(so);
        return s;
    }
    public static ScriptInput findScriptInput(EntityManager em,long id) {
        Query q = em.createQuery("SELECT s FROM ScriptInput s WHERE s.id = :id");
        q.setParameter("id", id);
        return (ScriptInput) q.getSingleResult();
    }
     public static void deleteScriptInput(EntityManager em,long id) {
         deleteScriptInput(em, ScriptInputUtil.findScriptInput(em, id));

     }
     public static void deleteScriptInput(EntityManager em,ScriptInput si) {
         Script s = si.getScript();
         s.getScriptInputList().remove(si);
         em.getTransaction().begin();
         em.remove(si);
         em.merge(s);
         em.getTransaction().commit();
     }
}
