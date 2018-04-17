package rest.details;
        
import dao.details.Details;
import dao.details.DetailsServiceDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/DetailRWS")
public class DetailsResource {
    
    @Context
    private UriInfo context;
    
    // connect to the db
    private DetailsServiceDAO dao = new DetailsServiceDAO();
    
    public DetailsResource(){
        
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML,   
               MediaType.APPLICATION_JSON})  // Accept header
    public Response getDetails( @PathParam("id") int id, @Context Request request) throws ParseException{
        
        Details detail = dao.getDetails(id);
        // used for cache validation (back up if cookies are deleted)
        EntityTag eTag = new EntityTag(Integer.toString(detail.hashCode()));
        CacheControl cc = new CacheControl();
        
        cc.setMaxAge(1000);
        String timestamp = detail.getTimestamp();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp);
        
        Date ifModifiedSince = date;
        // if eval Preconditions returns null, they have been met
        //therefore resource has updated, new version must be fetched
        ResponseBuilder builder = request.evaluatePreconditions(date, eTag);
        
       //if not null, return 304 not modified
       if(builder != null){
            builder.cacheControl(cc);
            builder.lastModified(date);
            //builder.lastModified(ifModifiedSince);
            return builder.build();
       }
       builder = Response.ok(detail, "application/xml");
       builder.cacheControl(cc).header("If-Modified-Since", ifModifiedSince);
       builder.tag(eTag);
       builder.lastModified(date);
       //builder.lastModified(ifModifiedSince);
       
       return builder.build();
    }
}
