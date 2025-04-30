package it.uniroma1.lcl.babelarity.strategypattern;

import it.uniroma1.lcl.babelarity.Document;
import it.uniroma1.lcl.babelarity.LinguisticObject;
import it.uniroma1.lcl.babelarity.similarity.BabelDocumentSimilarity;

//Strategy Pattern

public class DocumentSimilarity implements Similarity
{
	/**
	 * Metodo che dati in input due oggetti linguistici, restituisce
	 * un double che equivale al loro grado di similarit�.
	 * @param LinguisticObject o1 
	 * @param LinguisticObject o2
	 * @return double
	 */
	@Override
	public double computeSimilarity(LinguisticObject o1, LinguisticObject o2)
	{
		return BabelDocumentSimilarity.computeSimilarity(((Document)o1), ((Document)o2));
	}
}
