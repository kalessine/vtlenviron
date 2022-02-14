/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.vtlenviron.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "Script", catalog = "vtlenviron", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Script.findAll", query = "SELECT s FROM Script s"),
    @NamedQuery(name = "Script.findById", query = "SELECT s FROM Script s WHERE s.id = :id"),
    @NamedQuery(name = "Script.findByName", query = "SELECT s FROM Script s WHERE s.name = :name"),
    @NamedQuery(name = "Script.findByDescription", query = "SELECT s FROM Script s WHERE s.description = :description")})
public class Script implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Lob
    @Size(max = 65535)
    @Column(name = "script", length = 65535)
    private String script;
    @Size(max = 300)
    @Column(name = "name", length = 300)
    private String name;
    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "script")
    private List<ScriptOutput> scriptOutputList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "script")
    private List<ScriptInput> scriptInputList;

    public Script() {
    }

    public Script(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
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

    @XmlTransient
    public List<ScriptOutput> getScriptOutputList() {
        return scriptOutputList;
    }

    public void setScriptOutputList(List<ScriptOutput> scriptOutputList) {
        this.scriptOutputList = scriptOutputList;
    }

    @XmlTransient
    public List<ScriptInput> getScriptInputList() {
        return scriptInputList;
    }

    public void setScriptInputList(List<ScriptInput> scriptInputList) {
        this.scriptInputList = scriptInputList;
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
        if (!(object instanceof Script)) {
            return false;
        }
        Script other = (Script) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.vtlenviron.entities.Script[ id=" + id + " ]";
    }
    
}
