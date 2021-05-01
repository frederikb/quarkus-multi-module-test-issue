package examples.moduleC;

import examples.moduleB.GetExamplesQuery;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class GetExamplesService implements GetExamplesQuery {
  @Override
  public Collection<String> getExamples() {
    return List.of("Example 1", "Example 2", "Example 3");
  }
}
