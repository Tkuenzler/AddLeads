package WorkFlow;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {
	public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(WorkFlow.Samples.class);
        s.add(WorkFlow.EngageIQ.class);
        s.add(WorkFlow.InternetThings.class);
        s.add(WorkFlow.Duplicate.class);
        s.add(WorkFlow.LaunchPotato.class);
        s.add(WorkFlow.LittleBrook.class);
        s.add(WorkFlow.Zeel.class);
        return s;
    }
}