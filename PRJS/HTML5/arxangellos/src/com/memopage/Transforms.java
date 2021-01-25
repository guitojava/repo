package com.memopage;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.koin.dao.KoinAreaCodeDao;
import com.koin.dto.KoinAreaCode;
import com.memopage.context.AppContext;
import com.memopage.dao.MemopageCategoryDao;
import com.memopage.dto.Memopage;
import com.memopage.dto.MemopageCategory;
import com.memopage.view.MemopageBacking;


public final class Transforms {

	static Logger logger = Logger.getLogger(Transforms.class);
	
	

	
	public static MemopageBacking transformMemopageBacking (Memopage mpage) { 
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_HH:mm");
		
		ApplicationContext ctx = AppContext.getApplicationContext();
		KoinAreaCodeDao koinAreaCodeDao = (KoinAreaCodeDao) ctx.getBean("koinAreaCodeDao");   
		

		
		
		
		MemopageBacking ret = new MemopageBacking(); 
		
		// set ret 
		
		ret.setSid(""+mpage.getSid());	
		ret.setPageName(mpage .getPageName());
		ret.setPageSummary(mpage.getPageSummary());
		
		
		ret.setPageType( ""+ mpage.getPageType() );  // int 
		
		
		ret.setPageCategSid(""+mpage.getPageCateg().getSid());
		ret.setPageCategName(mpage.getPageCateg().getCategName());
		ret.setPageCategDescr(mpage.getPageCateg().getCategDescription());
		
		
		
		
		
		// add when is it empty 
		ret.setPageWhen(   sdf.format( mpage.getPageWhen() )  );   
		ret.setPageWhenEnd(  sdf.format( mpage.getPageWhenEnd() )  ); 
		
		logger.debug("getPageWhen 	 >>> "+ret.getPageWhen() );
		logger.debug("getPageWhenEnd   >>> "+ret.getPageWhenEnd() );
		
		ret.setPageAddress(mpage.getPageAddress());
		ret.setPageLat(mpage.getPageLat());
		ret.setPageLng(mpage.getPageLng());
		
		ret.setMapZoom(mpage.getMapZoom());
		
		ret.setCalendarCssClass(mpage.getCalendarCssClass());
		
		ret.setPageContent(mpage.getPageContent());
		ret.setPageKeywords(mpage.getPageKeywords() );
		ret.setPageLang(mpage.getPageLang() );	
		ret.setPageUrl(mpage.getPageUrl());
		
		ret.setIsListed(""+mpage.getIsListed()); //int   render it in front end 
		
		

		
		
		ret.setHasComments(""+mpage.getHasComments()); //int   render it in front end 
		ret.setCommentsAreReviewed(""+mpage.getCommentsAreReviewed() ); //int   render it in front end 
		
		KoinAreaCode ac = koinAreaCodeDao.getKoinAreaCodeWithSid( mpage.getAreaCode()  ) ;
		if ( ac.getId() == -1   ) 
			ret.setAreaCode( "0" );	
		else 
			ret.setAreaCode(""+ ac.getId()  );
		
		
		
		
		
		ret.setPageTel(    mpage.getPageTel());
		ret.setPageEmail( mpage.getPageEmail());
		ret.setPageWebLink(mpage.getPageWebLink());
		ret.setPriceEuros( mpage.getPriceEuros());
		
		
		
		ret.setStatus(""+mpage.getStatus() );	 //int   render it in front end 
		
		
		return ret;
	}

	
	
	public static String  getMemopageCategorySelectOptionHtml  ( int areaCode ) { 
		// re
		String ret ="";
		ApplicationContext ctx = AppContext.getApplicationContext();
		MemopageCategoryDao  memopageCategoryDao = (MemopageCategoryDao) ctx.getBean("memopageCategoryDao");   
		
		List <MemopageCategory> mlist = 	memopageCategoryDao.getCategoriesThatAreUsed( areaCode );
		
		
		MemopageCategory allCal  =  memopageCategoryDao.getMemopageCategory( -1 ); // -1 is special  its  all 
		ret = ret + 
		  "<option  value='"+allCal.getSid()+"'>"+allCal.getCategName()+"</option>";
		
		
		for (MemopageCategory mcateg  : mlist) {
			ret = ret + 
				  "<option  value='"+mcateg.getSid()+"'>"+mcateg.getCategName()+"</option>";
			// "<option selected='selected' value='-1'>Όλα</option>";
		}

		
		return ret ;
	}
	
	
	
	public static String  getAreaCodesUsedForMemopagesSelectOptionHtml  ( ) { 
		// re
		String ret ="";
		ApplicationContext ctx = AppContext.getApplicationContext();
		KoinAreaCodeDao  koinAreaCodeDao = (KoinAreaCodeDao) ctx.getBean("koinAreaCodeDao");   
		List <KoinAreaCode> mlist = 	koinAreaCodeDao.getKoinAreaCodesThatAreUsedForMemopages();
		
		
		KoinAreaCode ellada  =  koinAreaCodeDao.getKoinAreaCodeWithSid( 0 ); // -1 is special  its  all 
		ret = ret + 
		  "<option  value='"+ellada.getId()+"'>"+ellada.getDescription()+"</option>";
		
		
		for (KoinAreaCode ac  : mlist) {
			ret = ret + 
				  "<option  value='"+ac.getId()+"'>"+ac.getDescription()+"</option>";
		}

		
		return ret ;
	}
	
	
	
	
	
	
	
	
	
	
	
}
