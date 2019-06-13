package simple;
 
import java.util.List;
import java.util.Map;
import java.util.Set;

 
 
public class PredictTest {
	public static void main(String[] args) throws Exception {
		//String inputDir = "data/fresh_comp_offline/";
		//String outputDir = "data/fresh_comp_offline/sample/";
		//String inputDir = "data/fresh_comp_offline/sample/";
		//String outputDir = "data/fresh_comp_offline/sample/out/";
		String inputDir = args[0];
		String outputDir = args[1];
		//String userPath = inputDir + "tianchi_fresh_comp_train_user.csv";
		String inputPath = inputDir + args[2];
		String outputPath = inputDir + args[3];
		String userDir = args[4];
		
		Map<String, List<Score>> scoreMap = FileTool.loadScoreMap(inputPath, false, "\t");
		DataProcess.sortScoreMap(scoreMap);
		List<String> fileNameList = FileTool.traverseFolder(userDir);
		//我选择推荐该user的最相似的5个user的前5个item
		Map<String, Set<String>> predictMap = DataProcess.predict(scoreMap, fileNameList, userDir, 5, 5);
		FileTool.initWriter1(outputPath);
		DataProcess.outputRecommendList(predictMap);
		FileTool.closeWriter1();
		scoreMap.clear();
	}
}


