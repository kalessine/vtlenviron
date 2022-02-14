/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.vtlenviron.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "ScriptOutput", catalog = "vtlenviron", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScriptOutput.findAll", query = "SELECT s FROM ScriptOutput s"),
    @NamedQuery(name = "ScriptOutput.findById", query = "SELECT s FROM ScriptOutput s WHERE s.id = :id"),
    @NamedQuery(name = "ScriptOutput.findByAction", query = "SELECT s FROM ScriptOutput s WHERE s.action = :action"),
    @NamedQuery(name = "ScriptOutput.findByName", query = "SELECT s FROM ScriptOutput s WHERE s.name = :name"),
    @NamedQuery(name = "ScriptOutput.findByDescription", query = "SELECT s FROM ScriptOutput s WHERE s.description = :description")})
public class ScriptOutput implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 45)
    @Column(name = "action", length = 45)
    private String action;
    @Size(max = 300)
    @Column(name = "name", length = 300)
    private String name;
    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;
    @JoinColumn(name = "script", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Script script;

    public ScriptOutput() {
    }

    public ScriptOutput(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScriptOutput)) {
            return false;
        }
        ScriptOutput other = (ScriptOutput) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.vtlenviron.entities.ScriptOutput[ id=" + id + " ]";
    }
    
}
