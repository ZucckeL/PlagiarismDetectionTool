package Plagiarism;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Plagiarism.DepthFirstSearchNeighbourList.Node;

public class FPD {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String s1="C:\\Users\\EMRE\\Desktop\\Örnekler\\ProjeDosya\\Furkan\\Projeler\\16014033\\src\\project\\Time.java";
		String s2="C:\\Users\\EMRE\\Desktop\\Örnekler\\ProjeDosya\\Furkan\\Projeler\\16014033\\src\\project\\Subscription.java";
		TotalSimilarity(s1,s2,1,0.8f);
		
		
		
	}
	
	public static float TotalSimilarity(String f1,String f2,int mML,float threshold) throws FileNotFoundException, IOException {
		
		GSTiling start = new GSTiling();
		PlagResult resultDetail = new PlagResult(0, 0);
		ArrayList<Float> simAll = new ArrayList<Float>();
		float totalSimilarity = 0f;
		float similarity;
		List<Node> nodes = new ArrayList();
		List<Node> nodes2 = new ArrayList();
		
		DepthFirstSearchNeighbourList dfsGraph = new DepthFirstSearchNeighbourList();
		dfsGraph.getDFS(nodes, f1);
		dfsGraph.getDFS(nodes2, f2);
		dfsGraph.dfs(nodes.get(0),1);
		dfsGraph.dfs(nodes2.get(0),2);
		int count = 0;
		/*for(int i=0;i<dfsGraph.astNode.size();i++) {
			for(int j=0;j<dfsGraph.astNode2.size();j++) {
				//System.out.println("\n1: "+ );
			}
			System.out.println(count);
		}*/
		int j=0;
		for(int i=0;i<dfsGraph.astNode.size();i++) {
			similarity = 0f;
			j=0;
			//for(int j=0;j<dfsGraph.astNode2.size();j++) {
				while(j<dfsGraph.astNode2.size() && similarity != 1.0f) {
				if(dfsGraph.astNode.get(i).data.getNodeType() == dfsGraph.astNode2.get(j).data.getNodeType()) {
				resultDetail  = start.run(dfsGraph.astNode.get(i).data.toString(), dfsGraph.astNode2.get(j).data.toString(), 1, 0.8f);
				//System.out.println(i+" "+ j + "result : " + resultDetail.similarity);
				if(resultDetail.similarity>similarity) {
					similarity = resultDetail.similarity;
					
				}
				start.tiles.clear();
				start.matchList.clear();
				}
			//}
				j++;
		}
			simAll.add(similarity);
		}
		
		for(int i=0;i<simAll.size();i++) {
			//System.out.println(i+" Value = " + simAll.get(i));
			totalSimilarity = totalSimilarity + simAll.get(i);
		}
		totalSimilarity = totalSimilarity / simAll.size();
		
		if(totalSimilarity>1.0f) {
			totalSimilarity = 1.0f;
		}
		simAll.clear();
		dfsGraph.astNode.clear();
		dfsGraph.astNode2.clear();
		System.out.println(totalSimilarity);
		return totalSimilarity;
		
			}
	
	public void getTable() {
		
	}

}
