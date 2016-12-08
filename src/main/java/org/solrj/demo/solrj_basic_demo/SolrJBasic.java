package org.solrj.demo.solrj_basic_demo;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

public class SolrJBasic {
	
	/*
	 * Download SOLR, setup and run , create one basic core, here it is "souro_core".
	 */
	private static final String url = "http://localhost:8998/solr/souro_core/";
	/*
	 * HttpSolrServer was deprecated and HttpSolrClient is its replacement
	 */
	SolrClient solrClient = new HttpSolrClient(url);

	public SolrJBasic() {
		if (solrClient == null) {
			solrClient = new HttpSolrClient(url);
		}
	}
	
	/*
	 * This will index the data.
	 */
	public void addDocument() {

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "5", 1.0f);
		doc.addField("name", "souro", 1.0f);
		doc.addField("price", 10);
		try {
			solrClient.add(doc);
			solrClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void deleteByQuery(String queryString) {
		try {
			solrClient.deleteByQuery(queryString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public QueryResponse getRueryResponse(String queryString) {
		SolrQuery query = new SolrQuery();
		query.setQuery(queryString);

		QueryResponse queryResponse = null;
		try {
			queryResponse = solrClient.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queryResponse;
	}

	public static void main(String[] args) {

		SolrJBasic solrJBasic = new SolrJBasic();
		solrJBasic.addDocument();
		QueryResponse response = solrJBasic.getRueryResponse("id:*");
		System.out.println("SolrJ response :" + response);
	}
}