import com.alibaba.matcher.InfluencingFactor;
import com.alibaba.matcher.Matcher;
import com.alibaba.matcher.ObjectData;
import java.util.List;

public class DefaultMatcher implements Matcher {
    public static void main(String[] args) throws Exception {
        User user1 = new User(1,"凤凰传奇",2.3,24);
        User user2 = new User(2,"老男孩",3.2,20);
        Matcher matcher = new DefaultMatcher();
        System.out.println("相似度为："+matcher.getProportion(user1,user2,
                new User.ScoreFactor(3)));
    }

    @Override
    public double getProportion(ObjectData obj1, ObjectData obj2, InfluencingFactor... factors) throws NoSuchFieldException, IllegalAccessException {
        double power = 0;
        for (InfluencingFactor factor:factors){
            power = power + factor.getFactor();
            factor.getProportion(obj1,obj2);
        }
        return 0;
    }

    @Override
    public List<Double> getProportion(ObjectData obj, List<ObjectData> objs, InfluencingFactor... factors) {
        return null;
    }
}
