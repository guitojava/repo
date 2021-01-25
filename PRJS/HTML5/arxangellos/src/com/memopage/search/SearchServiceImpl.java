package com.memopage.search;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.el.GreekAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.context.ApplicationContext;

import com.koin.dao.KoinAreaCodeDao;
import com.koin.dto.KoinAreaCode;
import com.memopage.Constants;
import com.memopage.context.AppContext;
import com.memopage.dao.MemopageCategoryDao;
import com.memopage.dao.MemopageDao;
import com.memopage.dao.UserDao;
import com.memopage.dto.Memopage;
import com.memopage.dto.MemopageCategory;
import com.memopage.dto.User;
import com.memopage.service.impl.MemopageServiceImpl;
import com.memopage.service.impl.UserServiceImpl;
import com.memopage.view.CalenderJsonItem;
import com.memopage.view.MemopageBacking;

public class SearchServiceImpl implements SearchService {
	
	
	ApplicationContext ctx = AppContext.getApplicationContext(); 
	UserDao userDao = (UserDao) ctx.getBean("userDao");         
	MemopageDao memopageDao =(MemopageDao) ctx.getBean("memopageDao");   
	
	MemopageCategoryDao memopageCategoryDao =(MemopageCategoryDao) ctx.getBean("memopageCategoryDao");  
	
	KoinAreaCodeDao koinAreaCodeDao = (KoinAreaCodeDao) ctx.getBean("koinAreaCodeDao");      
	
	MemopageServiceImpl MemopageService =    new MemopageServiceImpl();
	UserServiceImpl UserService =    new UserServiceImpl();
	
	
	@Override
	public String getSearchResults(String searchTerms, String searchType,  int nomosSid , int  pageTypeSid) {
		
		String ret = "";
		if  (searchType.equals(Constants.SEARCH_TYPE_PAGE)){
			
			ret = getSearchResultsForAPageSearch(  searchTerms ,  nomosSid ,  pageTypeSid) ;
			
		} else if (searchType.equals(Constants.SEARCH_TYPE_USER))  {
			
			ret =  getSearchResultsForAUserSearch(  searchTerms  );
		}
		
		
		else if (searchType.equals(Constants.SEARCH_TYPE_CALANDER))  {
			
			
			searchTerms = searchTerms.trim();
			
			User user = userDao.getUserByUName(searchTerms);
			
			
			/*
			int userSid = -1 ;
			
			try {
				
				userSid = Integer.parseInt( searchTerms );
			 
			} catch (Exception e) {
				userSid = -1 ;
			}
			*/
			
			
			if (  user != null   ){
				
				ret  = "<iframe style='overflow=auto' id='richText' name='calnder'  " +
		  		" src='./fullcalendar-1.4.7/calander.html?action=getMemopagesCalander&sid=" + user.getSid() +	"'" +
		  		" width=100% height=89% frameborder='0' scrolling='auto' name=''></iframe>"     ;


			} else {
				
				ret = "&nbsp;&nbsp;&nbsp; Δυστυχως δεν υπάρχει τέτοιο όνομα δημιουργού  <b>"  + searchTerms +  "</b>       " ;
				
			}
			
			
			
		}
		
		
									 
		
		
		
		
		
		
		
		return ret;
	}
	
	
	
	
	
	
	private  String getSearchResultsForAPageSearch( String searchTerms ,  int nomosSid , int  pageTypeSid)  {
		
		GreekAnalyzer analyzerGR = new GreekAnalyzer(Version.LUCENE_30);
		Directory directory = new RAMDirectory();
		
		indexMemopages(directory, analyzerGR, nomosSid ,  pageTypeSid);
		
		
		
		searchTerms = searchTerms.trim();
		if  ( searchTerms.length()==0  ){
			searchTerms = "*";  
		}
		
		
		ArrayList<Document> foundResultsList = runSearch(  searchTerms,   "page_name" ,  directory,  analyzerGR);
		
		
		
		
		//	ArrayList<Document> retKeywords = runSearch(  searchTerms,   "page_keywords" ,  directory,  analyzerGR);
		//	ArrayList<Document> retSummary = runSearch(  searchTerms,   "page_summary" ,  directory,  analyzerGR);
		//	ArrayList<Document> retContent = runSearch(  searchTerms,   "page_content" ,  directory,  analyzerGR);
		
		
		String retHtml  	=   "<b> Αποτελεσμάτα  ( " +  foundResultsList.size() + " ) </b>";
		
		
		String previous_page_when = "";
		
		
		for (Document page : foundResultsList) {
			
			
			String page_sid = page.get("sid");
			
			
			
			Memopage mpage = MemopageService.getMemopage(  Integer.parseInt(page_sid) ); 
			
			
			String page_name = page.get("page_name");
			String page_summary = page.get("page_summary");
			String page_address = page.get("page_address");
			String page_lat = page.get("page_lat");
			String page_lng = page.get("page_lng");
			String page_type = page.get("page_type");
			String area_code = page.get("area_code");
			String page_content = page.get("page_content");
			String page_tel = page.get("page_tel");
			String page_email = page.get("page_email");
			String page_web_link = page.get("page_web_link");
			String price_euros = page.get("price_euros");
			
			
			String page_when = page.get("page_when");
			
			//System.out.println("  page_content   " + page_content);
			//System.out.println("  ***  ");
				

			
			
			
			MemopageCategory  cat =  memopageCategoryDao.getMemopageCategory(Integer.parseInt(page_type));
			
			
			
			if ( ! previous_page_when.equals(page_when) ){
				retHtml = retHtml  + " <br><br> <b> " + page_when +" </b> ";    
			}
			
			
			
			int areaCodeSid = -1;
			try {
				areaCodeSid = Integer.parseInt(area_code);
			} catch (Exception e) {
			}
				
			KoinAreaCode areaCodeObj = 	koinAreaCodeDao.getKoinAreaCodeWithSid(areaCodeSid);
			
			if ( areaCodeSid == -1  ) areaCodeObj.setDescription("Όλη την Ελλάδα");
			
			retHtml = retHtml  
					+   "<br><br>" 
					+	"&nbsp;&nbsp;&nbsp; <small>α/α: " + page_sid +"</small>" +   "&nbsp;&nbsp;&nbsp; <b>"+ page_name  +"</b>"   ;
					
					
					if (page_summary.length()> 3 ) { 
						retHtml =  retHtml   +   "<br>" +  "&nbsp;&nbsp;&nbsp; <b>Περίληψη:</b> " +   page_summary+" " ;
					}
			
					
					if (cat.getSid() != -1  ) { 
						retHtml =  retHtml   +   "<br>&nbsp;&nbsp;&nbsp; <b>Θέμα:</b> "+ cat.getCategName() ;
					}
			
					
					
					
					
					
					if ( Integer.parseInt(page_type) != -1  ) { 
							retHtml = retHtml  	+   "<br>" +	"&nbsp;&nbsp;&nbsp; <b>Νομός:</b> " +  areaCodeObj.getDescription() +" ";
					} else {
						retHtml = retHtml  	+   "<br>" ;
					}
					
					
					if (page_address.length()> 3 ) { 
						retHtml =  retHtml  +  "&nbsp;&nbsp;&nbsp; <b>Διεύθυνση:</b>" +   page_address+" " ;
					}
					
					if ( !page_lat.equals("-1.0")  &&   !page_lng.equals("-1.0")  ) { 
						retHtml = retHtml   +  " " +
								"&nbsp;&nbsp;&nbsp; <a class='colorlink'  target='_blank' " +
								" href ='./mapDisplay.html?mapZoom="+mpage.getMapZoom()+"&ads=" +page_address + "&lat="+ page_lat + "&lng="+page_lng +"'>[χάρτης]</a>" +	"" ;
					
						
					}
						
					

					String  linkurl = Constants.DIRECT_PUBLIC_URL+ "?sid="+ page_sid;
					String  linkname = page_name;
					String shareHtml =  " &nbsp;&nbsp;&nbsp; " +
					"<a class='colorlink' target='_blank'   " +		        //noslz
					" href='http://www.addtoany.com/share_save?linkname="+ linkname+"&amp;linkurl="+linkurl+"'>" +         //noslz
					" [κοινοποίηση σε φίλους]</a> " +           //noslz
					" <script type='text/javascript'>a2a_linkname='"+linkname+"';a2a_linkurl='"+linkurl+"';</script>";           //noslz
					retHtml = retHtml   	+   "<br>"+  shareHtml;
						
					
					
					if  ( page_tel.length()> 0 ){
						retHtml = retHtml  	+
						"&nbsp;&nbsp;&nbsp; <b>Τελ.</b> "  + page_tel; 
					}	
					
					
					if  ( page_email .length()> 0 ){
						retHtml = retHtml  	+
						"&nbsp;&nbsp;&nbsp; <a class='colorlink'  target='_blank' href ='mailto:"+page_email+"'>"+ page_email +"</a> "; 
					}
					
					if  ( page_web_link .length()> 0 ){
						retHtml = retHtml  	+
						"&nbsp;&nbsp;&nbsp; <a class='colorlink'  target='_blank' href ='"+page_web_link+"'> "+page_web_link+" </a> "; 
					}
					
					
					if  ( price_euros.length() > 0 ){
						retHtml = retHtml  	+ 	 "<br>" +
						"&nbsp;&nbsp;&nbsp;  <b> ΖΗΤΟΥΜΕΝΗ ΤΙΜΗ.   "+  price_euros  + "  &euro;</b> "  ;
					}
					
					
					if  ( page_content.length()> 0 ){
						retHtml = retHtml  	+
						"&nbsp;&nbsp;&nbsp; <a class='colorlink'  target='_blank' href ='"+Constants.DIRECT_PUBLIC_URL+"?sid="  + page.get("sid")+"'> [περισσότερα]   </a> "; 
					}
					
						
					
					
					
					previous_page_when = page_when;
			
					
					
					
//+ " &nbsp;&nbsp;&nbsp; <a href ='#' onclick=' openPage( "+page.get("sid")+" , \""+ page.get("page_name") +"\"  )  '>   "+ page_name  +" </a>   " ;
			
			
		}
		
		return retHtml;
		
	}
	
	public List<CalenderJsonItem> getCalanderResultsForAUserSearch(String sid) {

		SimpleDateFormat sdfCal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		List<CalenderJsonItem> calItems = new ArrayList<CalenderJsonItem>(); // ret

		// String
		// startStr=request.getParameter("startStr")==null?Constants.NULL:request.getParameter("startStr");
		// String
		// endStr=request.getParameter("endStr")==null?Constants.NULL:request.getParameter("endStr");

		int sidInt = Integer.parseInt(sid);

		// TODO get all gametimes in start - end and then popullate games

		// long start = Long.parseLong(startStr);
		// long end = Long.parseLong(endStr);

		// logger.debug("start as long " + start );
		// logger.debug("end as long "+ end);

		// Date startDate = new Date (start*1000);
		// Date endDate = new Date (end*1000);

		SimpleDateFormat mySqlDateFormatter = new SimpleDateFormat(	"yyyy-MM-dd HH:mm");

		// logger.debug("start as date " + mySqlDateFormatter.format(startDate)
		// );
		// logger.debug("end as date "+ mySqlDateFormatter.format(endDate));

		//String className = "memopage"; // CSS style for color in calander

		if (!sid.equals(Constants.NULL)) {

			// List <Game> gList =
			// GameService.getAllGamesForFieldBetweenDates("100", "0",
			// Integer.parseInt( sid ) , mySqlDateFormatter.format(startDate),
			// mySqlDateFormatter.format(endDate));

			User user = UserService.getUser(sidInt);
			List<MemopageBacking> mpList = MemopageService	.getUserMemopages(user);

			/*
			 * SELECT g.sid, gt.sid, g.field_fk, gt.aDayTime FROM mf_game g,
			 * mf_game_time gt WHERE g.field_fk = 54 AND g.sid = gt.game_fk AND
			 * DATE("") <= aDayTime AND aDayTime <= DATE("");
			 */


			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy_HH:mm");

			for (MemopageBacking m : mpList) {
				
				
				
				if (   m.getStatus().equals( ""+ Constants.MEMOPAGE_STATUS_ACTIVE)  && 
						m.getIsListed().equals(""+Constants.MEMOPAGE_IS_LISTED)  
				){
				
				
				
						CalenderJsonItem ci = new CalenderJsonItem();
			
						ci.setId("" + m.getSid()); // gametimesid
						String title = m.getPageWhen().replaceAll("_", " ") + " "		+ m.getPageName();
						ci.setTitle(title); // game name + gametime
						
						try {
							ci.setStart("" + sdfCal.format(sdf.parse(m.getPageWhen())));
							ci.setEnd("" + sdfCal.format(sdf.parse(m.getPageWhenEnd()  )));
							
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//  must use sdfCal here very important for calander date format in JSON
						
						String urlInApp="";
						if  ( m.getPageContent().length() > 0  ) {
							urlInApp = Constants.DIRECT_PUBLIC_URL + "?sid="		+ m.getSid();
						}
						
							
						ci.setUrl(urlInApp);
						ci.setClassName(m.getCalendarCssClass());
						calItems.add(ci);
						
						
				} // check its active and listed 
						
						
						
			}

		}
		return calItems;

	}
	
	
	private  String getSearchResultsForAUserSearch( String searchTerms  ) {
		
		
		String 
		retHtml = "&nbsp;&nbsp;&nbsp; Δυστυχως δεν υπάρχει τέτοιο όνομα δημιουργού  <b>"  + searchTerms +  "</b>       " ;
		
	
		searchTerms = searchTerms.trim();
		String[] resSplit = searchTerms.split(" ");
		
		if ( resSplit.length > 0   )	searchTerms = resSplit[0];
		else  return retHtml; 
		
		User  foundUser  = userDao.getUserByUName(searchTerms);
		
		if  ( foundUser != null ) {
			
						
			retHtml = "&nbsp;&nbsp;&nbsp; Υπάρχει τέτοιο όνομα δημιουργού  <b>"  + searchTerms +  "</b>  και οι σελιδες του ειναι   :   " ;
			
			List<Memopage> mList =  memopageDao.getUserMemopages(foundUser);
			
			int i=0;
			for (Memopage memopage : mList) {
				
				
				if (   memopage.getStatus()  ==  Constants.MEMOPAGE_STATUS_ACTIVE  && 
						memopage.getIsListed() == Constants.MEMOPAGE_IS_LISTED && 
						memopage.getPageContent().length() > 0 
				){
				
					String page_name = memopage.getPageName();
					String page_summary = memopage.getPageSummary();
					String page_sid = ""+memopage.getSid();
					
					retHtml = retHtml  
					+ "<br><br>" 
					+ " &nbsp;&nbsp;&nbsp; <bold>" + page_sid + "   </bold>"  
					+ " &nbsp;&nbsp;&nbsp; <a target='_blank' href ='"+Constants.DIRECT_PUBLIC_URL+"?sid="  + memopage.getSid() +"'>   "+ page_name  +" </a> " 
					+ " &nbsp;&nbsp;&nbsp; " + page_summary ; 
					
					i++;
				}
				
				
			}
			
		
		}
		
		return retHtml;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private  void indexMemopages(Directory directory, GreekAnalyzer analyzerGR, int nomosSid , int  pageTypeSid) {

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(Constants.DB_conString , Constants.DB_username, Constants.DB_password );
			IndexWriter iwriter = new IndexWriter(directory, analyzerGR, true, new IndexWriter.MaxFieldLength(25000));
			indexDocs(iwriter, conn, nomosSid , pageTypeSid);
			iwriter.optimize();
			iwriter.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private  void indexDocs( IndexWriter writer, Connection conn , int nomosSid , int  pageTypeSid) {

		try {

	
			
			String sql = "";
				
			
			if  (  nomosSid  > 0   &&   pageTypeSid  == -1 )
				sql = 
					"select * " +
					" from memopage " +
					" where " +
					" area_code = " + nomosSid +
					" and status="+Constants.MEMOPAGE_STATUS_ACTIVE +" and is_listed="+Constants.MEMOPAGE_IS_LISTED +"  ORDER BY page_when DESC  ";
			

			else if  (  pageTypeSid  > 0   &&  ( nomosSid  == -1  ||  nomosSid  == 0  ) )
				sql = 
					"select * " +
					" from memopage " +
					" where " +
					" page_type = " + pageTypeSid +
					" and status="+Constants.MEMOPAGE_STATUS_ACTIVE +" and is_listed="+Constants.MEMOPAGE_IS_LISTED + "  ORDER BY page_when DESC " ;
			
			
			else if  (  pageTypeSid  > 0   &&   nomosSid  > 0 )
				sql = 
					"select *  " +
					" from memopage " +
					" where " +
					"  page_type = " + pageTypeSid +	" and area_code = " + nomosSid +	" " +
					" and status="+Constants.MEMOPAGE_STATUS_ACTIVE +" and is_listed="+Constants.MEMOPAGE_IS_LISTED +  " ORDER BY page_when DESC " ;
			
			else 
				sql = 
				//	"select sid, page_name, page_summary, page_keywords, page_content, area_code, page_type " +
					"select *  " +
					" from memopage " +
					" where status="+Constants.MEMOPAGE_STATUS_ACTIVE +" and is_listed="+Constants.MEMOPAGE_IS_LISTED + "  ORDER BY page_when  DESC " ;
				
			
			SimpleDateFormat sdfCal = new SimpleDateFormat("dd-MM-yyyy");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Document d = new Document();

				d.add(new Field("sid", rs.getString("sid"), Field.Store.YES,	Field.Index.ANALYZED));
				d.add(new Field("page_name", rs.getString("page_name"),			Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("page_summary", rs.getString("page_summary"),	Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("page_keywords", rs.getString("page_keywords"),	Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("page_content", rs.getString("page_content"),	Field.Store.YES, Field.Index.ANALYZED));
				
				
				d.add(new Field("page_when",  sdfCal.format( rs.getDate("page_when") )     ,	Field.Store.YES, Field.Index.ANALYZED));
				
				
				d.add(new Field("page_address", rs.getString("page_address"),	Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("page_lat", rs.getString("page_lat"),	Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("page_lng", rs.getString("page_lng"),	Field.Store.YES, Field.Index.ANALYZED));
				
				
				d.add(new Field("page_tel", rs.getString("page_tel"),	Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("page_email", rs.getString("page_email"),	Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("page_web_link", rs.getString("page_web_link"),	Field.Store.YES, Field.Index.ANALYZED));
				d.add(new Field("price_euros", rs.getString("price_euros"),	Field.Store.YES, Field.Index.ANALYZED));
				
				
				
				d.add(new Field("area_code", rs.getString("area_code"),	Field.Store.YES, Field.Index.ANALYZED));
				
				d.add(new Field("page_type", rs.getString("page_type"),	Field.Store.YES, Field.Index.ANALYZED));

				
				
				writer.addDocument(d);
			}

			
			rs.close();
			stmt.close();
			
			
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	
	
	private boolean containsResult(ArrayList<Document> res, Document d) {
		boolean ret = false;
			for (Document i  : res) {
				if   (  	 d.get("sid").equals( i.get("sid")  )   ){
					ret=true;
					break;// loop 
				}
			}
		return ret;

	}
	
	
	
	
	
	private  ArrayList<Document> runSearch( String searchText,  String fieldToSearch, Directory directory, GreekAnalyzer analyzerGR) {
		ArrayList<Document> res  = new ArrayList<Document>();
		
		try {

			// Now search the index:
			IndexSearcher isearcher = new IndexSearcher(directory, true); // read-only=true

			// Parse a simple query that searches for "text":
			QueryParser parser = new QueryParser(Version.LUCENE_30,	 fieldToSearch , analyzerGR);
			
			
			System.out.println("  searchText ==   " + searchText );
			
			
			
			
			
			if  ( searchText.equals("*") ){
				
				System.out.println("  get all All start  "  );
				
				IndexReader reader  = isearcher.getIndexReader();
				for (int i = 0; i < reader.numDocs(); i++) {
					Document hitDoc =  reader.document(i) ;
					if ( ! containsResult ( res, hitDoc )   ){
						res.add(hitDoc);
					}
				}
				System.out.println("  serach All end  "  );
				
				
				
			}else {
			
			
			
			
			
			TokenStream tokenStream = analyzerGR.tokenStream("searchText", new StringReader(searchText)  );
			
			OffsetAttribute offsetAttribute = tokenStream.getAttribute(OffsetAttribute.class);
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);

			while (tokenStream.incrementToken()) {
			    int startOffset = offsetAttribute.startOffset();
			    int endOffset = offsetAttribute.endOffset();
			    String term = termAttribute.term();
			    System.out.println("  term ==   " + term );
			    searchText = term;
			    
				Query query = parser.parse( searchText + "*");
				
			    System.out.println("  searchText + '*'  ==   " + searchText + "*" );
				ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
				
				// Iterate through the results:
				for (int i = 0; i < hits.length; i++) {
					Document hitDoc = isearcher.doc(hits[i].doc);
					
					
					if ( ! containsResult ( res, hitDoc )   ){
					
						res.add(hitDoc);
					}
					/*
					System.out.println("  TestSearch ***  ");
					String page_name = hitDoc.get("page_name");
					System.out.println("  page_name   " + page_name);
					String page_content = hitDoc.get("page_content");
					System.out.println("  page_content   " + page_content);
					System.out.println("  ***  ");*/
					

				}
			    
			    
			    
			    
			}

			}
			
			
			
			

			isearcher.close();
			

		} catch (Exception e) {

			e.printStackTrace();

		}

		return res;
	}
	
	
	
	


	

}
