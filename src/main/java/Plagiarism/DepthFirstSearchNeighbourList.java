package Plagiarism;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
 
public class DepthFirstSearchNeighbourList
{ 
	public static List<Node> astNode = new ArrayList<>();
	public static List<Node> astNode2 = new ArrayList<>();
	public static int i;
	static class Node
	{
		ASTNode data;
		boolean visited;
		List<Node> neighbours;
 
		Node(ASTNode data)
		{
			this.data=data;
			this.neighbours=new ArrayList<>();
 
		}
		public void addneighbours(Node neighbourNode)
		{
			this.neighbours.add(neighbourNode);
		}
		public List<Node> getNeighbours() {
			return neighbours;
		}
		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
	}
	
	// Recursive DFS
	public  void dfs(Node node,int FNumber)
	{
		//System.out.print(node.data +" "+ node.data.structuralPropertiesForType() +"\n---------------\n");
		if(FNumber == 1) {
			astNode.add(node);
		}
		if(FNumber == 2 ) {
			astNode2.add(node);
		}
		List<Node> neighbours=node.getNeighbours();
        node.visited=true;
		for (int i = 0; i < neighbours.size(); i++) {
			Node n=neighbours.get(i);
			if(n!=null && !n.visited)
			{
				dfs(n,FNumber);
			}
		}
	}
 
	
	public static void main(String arg[]) throws FileNotFoundException, IOException
	{	List<Node> nodes = new ArrayList();
		List<Node> nodes2 = new ArrayList();
		String filePath="C:\\Users\\EMRE\\eclipse-workspace\\kisaornek\\src\\kisaornek\\Main.java";
		String filePath2="C:\\Users\\EMRE\\Desktop\\Örnekler\\ProjeDosya\\Furkan\\Projeler\\16014033\\src\\project\\Subscription.java";

		nodes = getDFS(nodes,filePath);
		
    	
		DepthFirstSearchNeighbourList dfsExample = new DepthFirstSearchNeighbourList();
		System.out.println("The DFS traversal of the graph using recursion - 1");
		dfsExample.dfs(nodes.get(0),1);
		System.out.println("*********************");
		
		
		nodes2 = getDFS(nodes2,filePath2);
		System.out.println("The DFS traversal of the graph using recursion - 2");
		dfsExample.dfs(nodes2.get(0),2);
		
	}
	
	public static List<Node> getDFS(List<Node> nodes,String filePath) throws FileNotFoundException, IOException {
		
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
    	parser.setKind(ASTParser.K_COMPILATION_UNIT);
    	char[] fileContent = getFileContent(filePath).toCharArray();
    	
    	parser.setSource(fileContent);
    	
    	CompilationUnit cu = (CompilationUnit) parser.createAST(null);
    	cu.accept(new ASTVisitor() {
    			int j = 0;
    			public boolean visit(FieldDeclaration node) {
    				if(j == 0 && node.getParent()!=null) {
        				nodes.add(new Node(node.getParent()));
        				j++;
        				}
    				node.accept(new ASTVisitor() {
					public void preVisit(ASTNode node) {
							nodes.add(new Node(node));
					}
					
				});
    				return false;
    			}
    			
    			public boolean visit(MethodDeclaration node) {
    				
				node.accept(new ASTVisitor() {
					public void preVisit(ASTNode node) {
							nodes.add(new Node(node));
					}
					
				});
				
				for(i = 0 ;i<nodes.size();i++) {
						nodes.get(i).data.accept(new ASTVisitor() {
							public void preVisit(ASTNode node) {
								for(int j=0;j<nodes.size();j++) {
									if(node.equals(nodes.get(j).data)) {
										nodes.get(i).addneighbours(nodes.get(j));
									}
								}
								
						
						}
						
					});
				}
				return false;
			}
    		
    		
    	});
    	return nodes;
	}

	public static String getFileContent(String filePath) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while(line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		return sb.toString();
	}
}