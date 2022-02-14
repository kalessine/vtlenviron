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
public class ScriptOutputUtil {

    public static Script createScriptOutput(Script s) {
        ScriptOutput so = new ScriptOutput();
        so.setAction("append");
        so.setDescription("New Output");
        so.setName("Output");
        so.setScript(s);
        if(s.getScriptOutputList()==null) {
          s.setScriptOutputList(new ArrayList<ScriptOutput>());
        }
        s.getScriptOutputList().add(so);
        return s;
    }
    public static ScriptOutput findScriptOutput(EntityManager em,long id) {
        Query q = em.createQuery("SELECT s FROM ScriptOutput s WHERE s.id = :id");
        q.setParameter("id", id);
        return (ScriptOutput) q.getSingleResult();
    }
     public static void deleteScriptOutput(EntityManager em,long id) {
         deleteScriptOutput(em, ScriptOutputUtil.findScriptOutput(em, id));

     }
    public static void deleteScriptOutput(EntityManager em,ScriptOutput so) {
         Script s = so.getScript();
         s.getScriptOutputList().remove(so);
         em.getTransaction().begin();
         em.remove(so);
         em.merge(s);
         em.getTransaction().commit();
     }
}
