import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class IKBNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
