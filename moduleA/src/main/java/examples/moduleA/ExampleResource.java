package examples.moduleA;

import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/examples/")
public interface ExampleResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Collection<String> getExamples();
}
