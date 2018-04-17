package dao.details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "details")
// default ordering is alphabetic - we want the field order maintained
@XmlType(propOrder={"id", "name", "age", "timestamp"})
public class Details {
    private int id;
    private String name;
    private double age;
    private String timestamp;
        
    public Details(){// required by JAXB
        
    }

    public Details(int id, String name, double age, String timestamp) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.timestamp= timestamp;
    }

    @XmlElement
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
    @XmlElement
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return "Details{" + "id=" + id + ", name=" + name + ", age=" + age +", timestamp=" + timestamp+ '}';
    }
 
}
