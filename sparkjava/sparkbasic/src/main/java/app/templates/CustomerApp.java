package app.templates;

import java.time.LocalDateTime;
import java.util.*;

import static app.templates.TemplateContentGenerator.render;

public class CustomerApp {
    public static void main(String[] args) {

        Map<String, Object> params = new HashMap<>();
        params.put("customer", new Customer("Tom", new HashSet<>(Arrays.asList("Glue", "Fake Insect", "Kids Camera", "Fish Tank"))));
        params.put("specialDiscount", Arrays.asList("Kids Camera"));
        params.put("promo", new PromotionCode());

        String value = render(params, "/templates/customer.html.vm");
        System.out.println(value);

        value = render(params, "/templates/customer.json.vm");
        System.out.println(value);

        value = render(params, "/templates/customer.java.vm");
        System.out.println(value);

        value = render(params, "/templates/customer.sql.vm");
        System.out.println(value);

        value = render(params, "/templates/customer.email.vm");
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

        public Set<String> getItems() {
            return items;
        }
    }

    public static class PromotionCode {
        public String getPromo(String name) {
            return name + "-" + LocalDateTime.now().getSecond();
        }
    }


}
