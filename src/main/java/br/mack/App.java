package br.mack;

import br.mack.persistencia.HeartRateDAO;
import br.mack.persistencia.HeartRateDAOMySQL;
import br.mack.resources.HeartRateResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
public class App extends Application<Configuration>
{
    public static void main( String[] args )
    {
        try {
            new App().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        AssetsBundle assetsBundle = new AssetsBundle("/site", "/", "index.html");
        bootstrap.addBundle(assetsBundle);
    }

    @Override
    public void run(Configuration configuration, Environment environment)  {

        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        HeartRateDAOMySQL dao = new HeartRateDAOMySQL();
        HeartRateResource heartRateResource = new HeartRateResource(dao);
        environment.jersey().register(heartRateResource);

        environment.jersey().setUrlPattern("/api/*");

    }

}

