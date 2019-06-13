import com.alibaba.matcher.InfluencingFactor;
import com.alibaba.matcher.ObjectData;

public class User implements ObjectData {

    private int id;
    private String movie;
    private double score;
    private int age;

    public User() {
    }

    public User(int id, String movie, double score, int age) {
        this.id = id;
        this.movie = movie;
        this.score = score;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", score=" + score +
                ", age=" + age +
                '}';
    }

    //用户状态
    static class AgeFactor extends DefaultFactor{
        public AgeFactor() {
        }

        AgeFactor(double factor){
            super(factor);
        }
    }
    //hi
    static class ScoreFactor extends DefaultFactor{
        public ScoreFactor() {
        }

        ScoreFactor(double factor){
            super(factor);
        }

        @Override
        public double getProportion(ObjectData obj1, ObjectData obj2) throws NoSuchFieldException, IllegalAccessException {
            User user1 = (User) obj1;
            User user2 = (User) obj2;
            return super.getProportion(obj1, obj2);
        }
    }
    //电影类型
    static class RomanticFactor extends DefaultFactor {
        public RomanticFactor() {
            super();
        }

        RomanticFactor(double factor){
            super(factor);
        }
    }
    static class FunnyFactor extends DefaultFactor {
        public FunnyFactor() {
        }

        FunnyFactor(double factor){
            super(factor);
        }
    }

}

abstract class DefaultFactor implements InfluencingFactor {
    double factor = 0;

    public DefaultFactor() {
    }

    public DefaultFactor(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public double getProportion(ObjectData obj1, ObjectData obj2) throws NoSuchFieldException, IllegalAccessException {
        return 0;
    };
}
