import java.security.SecureRandom;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration.ClassList;

public class Controller {

	public static void main(String[] args) throws Exception {
		// creating server on port 8005
		Server server = new Server(8005);
		//System.out.println(randomString(20,"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"));
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("webapp");
		ctx.setContextPath("/");
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");
		// mappings
		ctx.addServlet("servlets.ServletApi", "/api");
		// Setting the handler and starting the server
		server.setHandler(ctx);
		server.start();
		server.join();
	}
	public static String randomString(int length, String characterSet) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {
	        int randomInt = new SecureRandom().nextInt(characterSet.length());
	        sb.append(characterSet.substring(randomInt, randomInt + 1));
	    }
	    return sb.toString();
	}
}