package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import com.hp.hpl.jena.ontology.OntModel;


public class Tuning {

	public static ArrayList<String> continusClass2(SparseDoubleMatrix2D classResultMatrix,
												   OntModel o1,OntModel o2,double threshHold){

		ArrayList<HashSet<String>> o1ClassURIs = OWLOntParse2.getClassSameAsURIBlocks(o1);
		ArrayList<HashSet<String>> o2ClassURIs = OWLOntParse2.getClassSameAsURIBlocks(o2);
		ArrayList<String> outputList = new ArrayList<String>();

		int row = classResultMatrix.rows();
		int column = classResultMatrix.columns();


		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				double similarity = classResultMatrix.getQuick(i, j);
				if((similarity >= threshHold) && (similarity <= 1.0)){
					String output = o1ClassURIs.get(i) + "," + o2ClassURIs.get(j) + "," + similarity;
					outputList.add(output);
				}
			}
		}
		//Operator.writeInfo2file(outputList);
		return outputList;
	}

	public static ArrayList<String> continusClass(SparseDoubleMatrix2D classAvrMatrix,OntModel ontology,double left1,double right1) throws IOException  {
		
		double left = left1;
		double right = right1;
		
		ArrayList<String> classURIs = OWLOntParse1.getAllClassesURIs(ontology);
		ArrayList<String> outputList = new ArrayList<String>();
		
		int size = classAvrMatrix.rows();
		//size*size的矩阵，遍历这个矩阵本身
		for(int i=0;i<size;i++){
			for(int j=i+1;j<size;j++){
				double similarity = classAvrMatrix.getQuick(i, j);
				if((similarity >= left) && (similarity <= right)){
					String output = classURIs.get(i)+","+classURIs.get(j)+","+similarity;
					outputList.add(output);
				}
			}
		}	
		Operator.writeInfo2file(outputList);
		return outputList;
	}
	
	public static ArrayList<String> continusDP(SparseDoubleMatrix2D dpAvrMatrix,OntModel ontology,double left1,double right1) throws IOException  {
		
		double left = left1;
		double right = right1;
		
		ArrayList<String> dpURIs = OWLOntParse1.getDatatypeProptyURIs(ontology);
		ArrayList<String> outputList = new ArrayList<String>();
		
		int size = dpAvrMatrix.rows();
		//size*size的矩阵，遍历这个矩阵本身
		for(int i=0;i<size;i++){
			for(int j=i+1;j<size;j++){
				double similarity = dpAvrMatrix.getQuick(i, j);
				if((similarity >= left) && (similarity <= right)){
					String output = dpURIs.get(i)+","+dpURIs.get(j)+","+similarity;
					outputList.add(output);
				}
			}
		}
		Operator.writeInfo2file(outputList);
		return outputList;
	}
	
	
	
	public static ArrayList<String> continusOP(SparseDoubleMatrix2D opAvrMatrix,OntModel ontology,double left1,double right1)  {
		
		double left = left1;
		double right = right1;
		
		ArrayList<String> opURIs = OWLOntParse1.getObjectProptyURIs(ontology);
		ArrayList<String> outputList = new ArrayList<String>();
		
		
		int size = opAvrMatrix.rows();
		//size*size的矩阵，遍历这个矩阵本身
		for(int i=0;i<size;i++){
			for(int j=i+1;j<size;j++){
				double similarity = opAvrMatrix.getQuick(i, j);
				if((similarity >= left) && (similarity <= right)){
					String output = opURIs.get(i)+":"+opURIs.get(j);
					outputList.add(output);
				}
			}
		}	
		return outputList;
	}
	
}