import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;


import java.io.File;
import java.util.logging.Logger;

public class WebServerLauncher {
    private static final Logger logger = Logger.getLogger(WebServerLauncher.class.getName());

    public static void main(String[] args) throws Exception {
        String webappDirLocation = "./webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addWebapp("", new File("webapp").getAbsolutePath());

// 여기가 핵심
        ctx.setResources(new StandardRoot(ctx));
        ctx.getResources().createWebResourceSet(
                WebResourceRoot.ResourceSetType.PRE,
                "/WEB-INF/classes",
                new File("build/classes/java/main").getAbsolutePath(),
                null,
                "/"
        );

        logger.info("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
