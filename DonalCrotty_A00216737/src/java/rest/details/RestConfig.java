package rest.details;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// plug-into the JAX-RS framework
@ApplicationPath("/rest")
public class RestConfig extends Application{
    
    @Override
    public Set<Class<?>> getClasses(){
        //System.out.println("rest");
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(DetailsResource.class);
        return classes;
    }
}

