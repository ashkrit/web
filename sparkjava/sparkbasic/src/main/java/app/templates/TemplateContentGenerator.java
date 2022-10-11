package app.templates;

import org.apache.velocity.app.VelocityEngine;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

/*
 HTML
 JSON
 Code like Java/c++ etc
 SQL
 */
public class TemplateContentGenerator {

    private static VelocityTemplateEngine strictVelocityEngine() {
        VelocityEngine configuredEngine = new VelocityEngine();
        configuredEngine.setProperty("runtime.references.strict", true);
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return new VelocityTemplateEngine(configuredEngine);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        String value = strictVelocityEngine().render(new ModelAndView(model, templatePath));
        return value;
    }
}
