package config.properties;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import lombok.Getter;

@Getter
public class BancoDadosProperties {

    private static final BancoDadosProperties instance = new BancoDadosProperties();

    private String driver;
    private String url;
    private String user;
    private String password;

    private BancoDadosProperties() {
        loadConfig();
    }

    public static BancoDadosProperties getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void loadConfig() {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass()
                                          .getClassLoader()
                                          .getResourceAsStream("application.yml");

            Map<String, Object> springConfig = yaml.load(inputStream);
            String activeProfile = (String) ((Map<String, Object>) springConfig.get("spring.profiles")).get("active");

            inputStream = this.getClass()
                              .getClassLoader()
                              .getResourceAsStream("application.yml");
            
            Map<String, Object> databaseConfig = null;
            for (Object data : yaml.loadAll(inputStream)) {
                Map<String, Object> doc = (Map<String, Object>) data;
                if (doc.containsKey(activeProfile)) {
                    databaseConfig = (Map<String, Object>) doc.get(activeProfile);
                    break;
                }
            }

            if (databaseConfig == null) {
                throw new RuntimeException("No configuration found for active profile: " + activeProfile);
            }

            Map<String, String> databaseProps = (Map<String, String>) databaseConfig.get("database");

            this.driver = databaseProps.get("driver");
            this.url = databaseProps.get("url");
            this.user = databaseProps.get("user");
            this.password = databaseProps.get("password");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database configuration from application.yml", e);
        }
    }
}
