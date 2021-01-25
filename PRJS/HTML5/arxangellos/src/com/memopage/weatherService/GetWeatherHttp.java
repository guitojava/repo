


package com.memopage.weatherService;


import org.apache.log4j.Logger;

import utils.HTTPRequestPoster;






/*
 
 
 <?xml version="1.0" encoding="utf-8"?>
<data>
	<request>
		<type>LatLon</type>
		<query>38.01,23.73</query>
	</request>
	<current_condition>
		<observation_time>09:41 AM</observation_time>
		<temp_C>11</temp_C>
		<weatherCode>119</weatherCode>
		<weatherIconUrl><![CDATA[http://www.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0003_white_cloud.png]]></weatherIconUrl>
		<weatherDesc><![CDATA[Cloudy ]]></weatherDesc>
		<windspeedMiles>8</windspeedMiles>
		<windspeedKmph>12</windspeedKmph>
		<winddirDegree>9</winddirDegree>
		<winddir16Point>N</winddir16Point>
		<precipMM>0.0</precipMM>
		<humidity>62</humidity>
		<visibility>10</visibility>
		<pressure>1009</pressure>
		<cloudcover>75</cloudcover>
	</current_condition>
	<weather>
		<date>2010-02-09</date>
		<tempMaxC>10</tempMaxC>
		<tempMaxF>50</tempMaxF>
		<tempMinC>5</tempMinC>
		<tempMinF>41</tempMinF>
		<windspeedMiles>15</windspeedMiles>
		<windspeedKmph>24</windspeedKmph>
		<winddirection>N</winddirection>
		<weatherCode>119</weatherCode>
		<weatherIconUrl><![CDATA[http://www.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0003_white_cloud.png]]></weatherIconUrl>
		<weatherDesc><![CDATA[Cloudy ]]></weatherDesc>
	</weather>
</data>

 
 */

public class GetWeatherHttp  {
	
 private static Logger logger = Logger.getLogger(GetWeatherHttp.class);
	 
    
 //TODO   make as a process to run once and get all weather data and then on run locally 
 
 
 public  Weather  getWeather (String lat, String lng )  {
	 
	 	 // lat / lng must be rounded to 38.01  example 
	     // and  string 
	 	 Weather ret = new Weather(); 
	 
    	 logger.debug("Start"); 
    	
    	
    	 String uri = "http://www.worldweatheronline.com/feed/weather.ashx" ;
    	 String params = "lat="+lat+"&lon="+lng+"&format=xml&num_of_days=1&key=4cf40412ca082637100902"; 
    	
    	 
        // http://www.worldweatheronline.com/feed/weather.ashx?key=4cf40412ca082637100902&q=48.85,2.35&cc=no&date=2010-04-23&format=xml
    	 // -33.85,151.20  
    	 
    	 String responce = HTTPRequestPoster.sendGetRequest(uri , params); 
    	
    	 
    	 String startTag = "<temp_C>";
    	 String endTag= "</temp_C>";
    	 String temp_C  = getValue( responce ,   startTag, endTag   ); 
    	 logger.debug(" temp_C = "+ temp_C); 
    	 
    	 
    	 
    	 startTag = "<weatherIconUrl><![CDATA["; 
    	 endTag = "]]></weatherIconUrl>" ; 
    	 String weatherIconUrl  = getValue( responce ,   startTag, endTag   ); 
    	 logger.debug(" weatherIconUrl  = "+ weatherIconUrl); 
    	 
    	 // 
    	 ret.setTempC(temp_C);
    	 ret.setIcon(weatherIconUrl);
    			
    	 
		 logger.debug(ret); 
    	return ret; 
    }
    
 
 
    private  String  getValue(String input,   String startTag , String endTag  ) {
    	 int beginIndex = input.indexOf(startTag); 
    	 int endIndex = input.indexOf( endTag ); 
    	 return input.substring( beginIndex + startTag.length() ,  endIndex);  
	}
    
    
    
  
}