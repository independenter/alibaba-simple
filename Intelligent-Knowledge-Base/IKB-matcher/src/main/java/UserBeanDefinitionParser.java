import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @SuppressWarnings("rawtypes")
    protected Class getBeanClass(Element element) {
        return User.class;
    }
    
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String id = element.getAttribute("id");
        String age = element.getAttribute("age");
        String movie = element.getAttribute("movie");
        String score = element.getAttribute("score");
        if (StringUtils.hasText(id)) {
            bean.addPropertyValue("id", id);
        }
        if (StringUtils.hasText(age)){
            bean.addPropertyValue("age", age);
        }
        if (StringUtils.hasText(movie)){
            bean.addPropertyValue("movie", movie);
        }
        if (StringUtils.hasText(score)){
            bean.addPropertyValue("score", score);
        }

    }
}