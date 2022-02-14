/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.vtlenviron.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sdmx.vtlenviron.entities.Script;

/**
 *
 * @author jsg
 */
public class ScriptUtil {
    public static Script createScript(String text, String name,String description){
        Script s = new Script();
        s.setName(name);
        s.setDescription(description);
        s.setScript(text);
        ScriptInputUtil.createScriptInput(s);
        ScriptOutputUtil.createScriptOutput(s);
        return s;
    }
    public static List<Script> findAllScripts(EntityManager em) {
        Query q = em.createQuery("select s from Script s");
        return (List<Script>) q.getResultList();
    }
    public static Script findScript(EntityManager em,long id) {
        Query q = em.createQuery("SELECT s FROM Script s WHERE s.id = :id");
        q.setParameter("id", id);
        return (Script) q.getSingleResult();
    }
}
