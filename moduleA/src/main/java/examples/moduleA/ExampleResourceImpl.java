package examples.moduleA;

import examples.moduleB.GetExamplesQuery;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

@RequestScoped
public class ExampleResourceImpl implements ExampleResource {

  @Inject GetExamplesQuery getExamplesQuery;

  @Context ResourceContext resourceContext;

  @Override
  public Collection<String> getExamples() {
    return getExamplesQuery.getExamples();
  }
}
