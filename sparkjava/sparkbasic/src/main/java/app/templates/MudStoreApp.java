package app.templates;

import org.apache.velocity.app.VelocityEngine;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.time.LocalDateTime;
import java.util.*;

import static app.templates.ContentGenerator.render;

public class MudStoreApp {
    public static void main(String[] args) {

        Map<String, Object> params = new HashMap<>();
        params.put("customer", new Customer("Tom", new HashSet<>(Collections.singleton("Mudcrack"))));
        params.put("mudsOnSpecial", Arrays.asList("Drilling mud", "Mudcrack", "Peloid"));
        params.put("flogger", new Flogger());

        String value = render(params, "/templates/mudstore.vm");
        System.out.println(value);

    }


    public static class Customer {
        private final String name;
        private final Set<String> items;

        public Customer(String name, Set<String> items) {
            this.name = name;
            this.items = items;
        }

        public String getName() {
            return name;
        }

        public boolean hasPurchased(String item) {
            return items.contains(item);
        }
    }

    public static class Flogger {
        public String getPromo(String name) {
            return name + "-" + LocalDateTime.now().getSecond();
        }
    }


}
