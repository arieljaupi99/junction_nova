package junction.al.nova_spring.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalSecure implements Condition {
    public static final String PROPERTY_KEY = "app.secure";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final Environment environment = context.getEnvironment();
        return environment.getProperty(PROPERTY_KEY, Boolean.class, true);
    }
}
