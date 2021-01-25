package com.memopage.search;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.el.GreekAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class TestSearch {

	Connection conn;

	public static void main(String[] args) {

		// Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
		GreekAnalyzer analyzerGR = new GreekAnalyzer(Version.LUCENE_30);
		Directory directory = new RAMDirectory();
		
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/memopage", "memopage", "memopage");

			IndexWriter iwriter = new IndexWriter(directory, analyzerGR, true,
					new IndexWriter.MaxFieldLength(25000));

			indexDocs(iwriter, conn);

			iwriter.optimize();
			iwriter.close();

			
			
			
			
			
			// Now search the index:
			IndexSearcher isearcher = new IndexSearcher(directory, true); // read-only=true

			// Parse a simple query that searches for "text":
			QueryParser parser = new QueryParser(Version.LUCENE_30, 	"page_name", analyzerGR);
			
			
			String searchText = "λέων*  γιωργος  λ΄ς!~ων* ";
			System.out.println("  searchText ==   " + searchText );
			//TokenStream ts = analyzerGR.tokenStream("", new StringReader(searchText) );
			
			TokenStream tokenStream = analyzerGR.tokenStream("searchText", new StringReader(searchText)  );
			
			OffsetAttribute offsetAttribute = tokenStream.getAttribute(OffsetAttribute.class);
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);

			while (tokenStream.incrementToken()) {
			    int startOffset = offsetAttribute.startOffset();
			    int endOffset = offsetAttribute.endOffset();
			    String term = termAttribute.term();
			    System.out.println("  term ==   " + term );
			    searchText = term;
			}

			
			
			
			Query query = parser.parse(  searchText  );
			//Query query = parser.parse("memo*");

			ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;

			
			
			
			System.out.println("  RESULTS ***  ");

			// Iterate through the results:
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);

				System.out.println("  TestSearch ***  ");
				String page_name = hitDoc.get("page_name");
				System.out.println("  page_name   " + page_name);

				//String page_content = hitDoc.get("page_content");
				//System.out.println("  page_content   " + page_content);

				System.out.println("  ***  ");

			}

			isearcher.close();
			
			
			directory.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void indexDocs(IndexWriter writer, Connection conn)
			throws Exception {
		String sql = "select sid, page_name, page_summary, page_keywords, page_content from memopage";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Document d = new Document();

			d.add(new Field("sid", rs.getString("sid"), 					Field.Store.YES, Field.Index.ANALYZED));
			d.add(new Field("page_name", rs.getString("page_name"),			Field.Store.YES, Field.Index.ANALYZED));
			d.add(new Field("page_summary", rs.getString("page_summary"),	Field.Store.YES, Field.Index.ANALYZED));
			d.add(new Field("page_keywords", rs.getString("page_keywords"),	Field.Store.YES, Field.Index.ANALYZED));
			d.add(new Field("page_content", rs.getString("page_content"),	Field.Store.YES, Field.Index.ANALYZED));

			writer.addDocument(d);
		}

	}

}
