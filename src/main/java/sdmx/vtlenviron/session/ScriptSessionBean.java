/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.vtlenviron.session;

import fr.insee.vtl.model.Dataset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import sdmx.repository.VTLRepository;
import sdmx.repository.exception.RepositoryException;
import sdmx.repository.service.VTLDatabaseRepository;
import sdmx.vtlenviron.entities.Script;
import sdmx.vtlenviron.entities.ScriptInput;
import sdmx.vtlenviron.entities.ScriptOutput;
import sdmx.vtlenviron.util.ScriptInputUtil;
import sdmx.vtlenviron.util.ScriptOutputUtil;
import sdmx.vtlenviron.util.ScriptUtil;

/**
 *
 * @author jsg
 */
public class ScriptSessionBean {
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sdmx-sax_vtlenviron_war_1.0-SNAPSHOTPU");
    EntityManager em = EMF.createEntityManager();
    public ScriptSessionBean() {
    }
    public List<Script> getScripts() {
        List<Script> scripts = ScriptUtil.findAllScripts(em);
        return scripts;
    }
    public Script createScript(){
       return ScriptUtil.createScript("New Script", "Script Name", "Script Description");
    }
    public Script findScript(long l) {
        Script script = ScriptUtil.findScript(em,l);
        return script;
    }
    public ScriptOutput findScriptOutput(long l) {
        ScriptOutput script = ScriptOutputUtil.findScriptOutput(em,l);
        return script;
    }
    public ScriptInput findScriptInput(long l) {
        ScriptInput script = ScriptInputUtil.findScriptInput(em,l);
        return script;
    }    
    public void mergeScript(Script s) {
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
    }
    public void mergeScriptOutput(ScriptOutput so) {
        em.getTransaction().begin();
        em.merge(so);
        em.getTransaction().commit();
    }
    public void mergeScriptInput(ScriptInput si) {
        em.getTransaction().begin();
        em.merge(si);
        em.getTransaction().commit();
    }
    public void invoke(long id) {
        Script s = ScriptUtil.findScript(em, id);
        try {
            VTLRepository vtl = new VTLDatabaseRepository();
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("vtl");
            Bindings bindings = new SimpleBindings();
            for(ScriptInput si:s.getScriptInputList()){
                Dataset ds1 = vtl.getDataset(si.getName());
                bindings.put(si.getName(),ds1 );
            }
            ScriptContext context = engine.getContext();
            context.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
            String script = s.getScript();
            try {
                engine.eval(script);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            Bindings outputBindings = engine.getContext().getBindings(ScriptContext.ENGINE_SCOPE);
            for(ScriptOutput so:s.getScriptOutputList()){
                fr.insee.vtl.model.Dataset ds = (Dataset) outputBindings.get(so.getName());
                vtl.createDataset(so.getName(), ds);
            }
        } catch (RepositoryException ex) {
            Logger.getLogger(ScriptSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteScriptInput(long id) {
         ScriptInputUtil.deleteScriptInput(em, id);
    }
    public void deleteScriptOutput(long l) {
        ScriptOutputUtil.deleteScriptOutput(em, l);
    }
}
