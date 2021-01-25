
var  memopageWindow; 

var PHONEMASK = '(+30) 999 999 9999'; // valid for greece TODO fix for world 




var txtMemopageeWindow = {

	reopen : 'Aνανέωση',
	back :   'Eπιστροφή '
}





	
	





function openMemopageWindow(  rowObj ) {
	
	
	
	function saveAll( confimflag ){
		
		
				
		
	
		    if  (  Ext.getCmp('pageEmail').isValid() && Ext.getCmp('pageWebLink').isValid()) 
		    {
		
				
				
				var isListedBool = Ext.getCmp('isListed').getValue(); 
				var strIsListed =""; 
				if ( isListedBool )  strIsListed ="1";
				else  strIsListed ="0";
				
				var hasCommentsBool = Ext.getCmp('hasComments').getValue(); 
				var strHasComments =""; 
				if ( hasCommentsBool )  strHasComments ="1";
				else  strHasComments ="0";
				
				
				var commentsAreReviewedBool = Ext.getCmp('commentsAreReviewed').getValue(); 
				var strCommentsAreReviewed =""; 
				if ( commentsAreReviewedBool )  strCommentsAreReviewed ="1";
				else  strCommentsAreReviewed ="0";
				
				
				var tDis  =  Ext.get('pageType').getValue()   ;
				var tPos = typeSimpleStore.find( 'displayFld', tDis);
				var tRow = typeSimpleStore.getAt( tPos );
				var tKey  = tRow.get('key'); 
				
				var sDis  =  Ext.get('status').getValue()   ;
				var sPos = statusSimpleStore.find( 'displayFld', sDis);
				var sRow = statusSimpleStore.getAt( sPos );
				var sKey  = sRow.get('key'); 
				
				
				
				var lDis  =  Ext.get('pageLang').getValue()   ;
				var lPos = langSimpleStore.find( 'displayFld', lDis);
				var lRow = langSimpleStore.getAt( lPos );
				var lKey  = lRow.get('key'); 
				
				
				
				var nDis  =  Ext.get('nomosType').getValue()   ;
				var nPos = nomosTypeSimpleStore.find( 'displayFld', nDis);
				var nRow = nomosTypeSimpleStore.getAt( nPos );
				var nKey  = nRow.get('key'); 
				
				
				var cDis  =  Ext.get('calendarCssClass').getValue()   ;
				var cPos = cssSimpleStore.find( 'displayFld', cDis);
				var cRow = cssSimpleStore.getAt( cPos );
				var cKey  = cRow.get('key'); 
				
				
				
				
				
				// todo save data
				/*
				alert  ( 
					  "\n   sid =="  + 	sid
				 	+ "\n   status =="  + 	sKey
					+ "\n   pageType ==  "  + 	tKey
					+ "\n   strIsListed ==  "  +  strIsListed
					+ "\n   pageWhenDay ==  "  +  Ext.get('pageWhenDay').getValue() 
					+ "\n   pageWhenTime ==  "  +  Ext.get('pageWhenTime').getValue() 
					+ "\n   pageWhenEndDay ==  "  +  Ext.get('pageWhenEndDay').getValue() 
					+ "\n   pageWhenEndTime ==  "  +  Ext.get('pageWhenEndTime').getValue() 
					
					+ "\n   pagekeywords ==  "  +  Ext.get('pageKeywords').getValue() 
					+ "\n   pageName ==  "  +  Ext.get('pageName').getValue() 
					+ "\n   pageUrl ==  "  +  Ext.get('pageUrl').getValue() 
					+ "\n   pageSummary ==  "  +  Ext.get('pageSummary').getValue() 
					
				)  ;
				
				*/
				
				
				
				
				
				// 	var areaCodeSplit =   Ext.get('areaCode').getValue().split("_");
				
				
				
					Ext.Ajax.request({
					method : 'POST', // noslz
					url : GamesServlet,
					params : {
						sid: sid,
						action : 'saveBasicInfo',
						status :	sKey,
						pageType:	tKey,
						pageLang:	lKey,
						
						calendarCssClass : cKey,
						    
						    
						areaCode : nKey  , 
					    
						
						isListed: strIsListed,
						
						hasComments:strHasComments,
						commentsAreReviewed: strCommentsAreReviewed,
					    
					  
					    pageAddress :  Ext.get('pageAddress').getValue()  ,
					    pageLat :  Ext.get('pageLat').getValue()  ,
					    pageLng :  Ext.get('pageLng').getValue()  ,
					    
					    pageTel :  Ext.get('pageTel').getValue()  ,
					    pageEmail :  Ext.get('pageEmail').getValue()  ,
					    pageWebLink :  Ext.get('pageWebLink').getValue()  ,
					    priceEuros :  Ext.get('priceEuros').getValue()  ,
					    
					
					    
					    pageWhenDay :  Ext.get('pageWhenDay').getValue()  ,
					    pageWhenTime : Ext.get('pageWhenTime').getValue() ,
					    pageWhenEndDay :  Ext.get('pageWhenEndDay').getValue()   ,
					    pageWhenEndTime :  Ext.get('pageWhenEndTime').getValue() ,
					    pagekeywords :  Ext.get('pageKeywords').getValue(), 
					    pageName :  Ext.get('pageName').getValue() ,
					    pageUrl  :  Ext.get('pageUrl').getValue() ,
					    pageSummary :  Ext.get('pageSummary').getValue() 
					},
					scope : this,
					success : function(res) {

						//var obj = Ext.util.JSON.decode(res.responseText);

						if ( confimflag == 1 )
						Ext.MessageBox.alert( " ενημέρωση   ",   "  'Εγινε αποθήκευσης πληροφοριών  - OK "  	); 
						
						
							
	 memopageWindow.setTitle( " σελίδα  " + sid + "   "  +  Ext.get('pageName').getValue()   ) ;
						
						
			/*		+ "\n\n    sid =="  + 	sid
				 	+ "\n   status =="  + 	sKey
					+ "\n   pageType ==  "  + 	tKey
					+ "\n   strIsListed ==  "  +  strIsListed
					+ "\n   pageWhenDay ==  "  +  Ext.get('pageWhenDay').getValue() 
					+ "\n   pageWhenTime ==  "  +  Ext.get('pageWhenTime').getValue() 
					+ "\n   pageWhenEndDay ==  "  +  Ext.get('pageWhenEndDay').getValue() 
					+ "\n   pageWhenEndTime ==  "  +  Ext.get('pageWhenEndTime').getValue() 
					
					+ "\n   pagekeywords ==  "  +  Ext.get('pageKeywords').getValue() 
					+ "\n   pageName ==  "  +  Ext.get('pageName').getValue() 
					+ "\n   pageUrl ==  "  +  Ext.get('pageUrl').getValue() 
					+ "\n   pageSummary ==  "  +  Ext.get('pageSummary').getValue() 
					*/
						
					}// success
				});// Ext.Ajax.request
			
		    } // validation 
		    else {
		    	
		    	
		    	   Ext.MessageBox.alert( " ενημέρωση   ",   " Πρέπει να κάνεις διόρθωση των στοιχείων ή αστα κενά" 
		    	   	+" <br>π.χ. <b>WebLink:</b> http://www.google.com  το <b>http://</b> είναι υποχρεωτικό." 
		    	   	+" <br>π.χ. <b>Email:</b> example@example.com"  	); 
		    	   		
		    	   		
		    			    						
		    }
		
	}
	
	
	
	
	
	
	
	
	
	var sid = null;
	
	if ( null!= rowObj && null!=  rowObj.sid )
	{
		sid= rowObj.sid ; 
	}
	
	// alert ( " sid  =   " + sid); 
	
	//--- settings panel 	
	
	
						 
						 
	var  editorIframecode = '<iframe id="richText" name="richText"  ' +
					  		'src="../fckeditorImpl/htmlEditor.jsp?act=saveMemoPage&sid=' + sid +'"' +
					  		'width=100% height=100% frameborder="0" scrolling="auto" name=" "></iframe>'     ;
						 
						 
	if ( Ext.isIE ) {
		
						 
						 
			editorIframecode = '<iframe id="richText" name="richText"  ' +
					  		'src="../fckeditorImpl/htmlEditor.jsp?act=saveMemoPage&sid=' + sid +'"' +
					  		'width=800px height=700px frameborder="0" scrolling="auto" name=" "></iframe>'     ;			 
						 
	}
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
		// ------
	var langData = [
	['EL', "ΕΛΛΗΝΙΚΑ"],            //noslz
	['EN', "ENGLISH"]
	];            //noslz
	
	var langSimpleStore = new Ext.data.SimpleStore({
				fields : ['key', 'displayFld'],            //noslz
				data : langData
			});				 
						 
	
	
	     
	      
	// ------
	var cssData = [
	['memopage',    	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: #D0D0D0;  color: black; '> γκρι </span> "],            //noslz
	['memopagered', 	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: red;  	color: white; '> κόκκινο</span> " ],
	['memopagegreen', 	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: green;  	color: white; '> πρασινο</span> " ],
	
	
	['memopageblue', 	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: blue;  	color: white; '> κυανό</span> " ],
	['memopageyellow', 	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: yellow;  	color: black; '> κίτρινο</span> " ],
	['memopageorange', 	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: orange;  	color: black; '> πορτοκαλί</span> " ],
	['memopageblack', 	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: black;  	color: white; '> μαύρο</span> " ],
	['memopagepink', 	"&nbsp;&nbsp;&nbsp; <span  STYLE=' background-color: pink;  	color: black; '> ρόζ</span> " ]
	
	
	];            //noslz

	
	var cssSimpleStore = new Ext.data.SimpleStore({
				fields : ['key', 'displayFld'],            //noslz
				data : cssData
			});				 
						 		
			
			
	
	
	// ------
	var statusData = [
	['1', "NAI"],            //noslz
	['2', "OXI"]
	];            //noslz
	
	var statusSimpleStore = new Ext.data.SimpleStore({
				fields : ['key', 'displayFld'],            //noslz
				data : statusData
			});

		
	

			
	var typeSimpleStore = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
						url : GamesServlet ,//noslz
						method : 'POST'//noslz
				}),
			 	
			 	remoteSort: false,
		        
		        reader : new Ext.data.JsonReader({
							root : 'results',//noslz
							totalProperty : 'total'//noslz
						}, [
							    {
									name : 'key',//noslz
									type : 'string'//noslz
								},{
									name : 'displayFld',//noslz
									type : 'string'//noslz
								}
								
						])
				
			});		
			
		
		
			
			
			
			
	var nomosData = [
	
		['0', "όλη την Ελλάδα"],            //noslz
		['1', "Ν. Αττικής"],            //noslz
		['2', "Ν. Αιτολωακαρνανίας"],            //noslz
		['3', "Ν. Βοιωτίας"],            //noslz
		['4', "Ν. Θεσσαλονίκης"],            //noslz
		['5', "Ν. Ευρυτανίας"],            //noslz
		['6', "Ν. Φθιώτιδας"],            //noslz
		['7', "Ν. Φωκίδας"],            //noslz
		['8', "Ν. Ευβοίας"],            //noslz
		['9', "Ν. Αρκαδίας"],            //noslz
		['10', "Ν. Αχαϊας"],            //noslz
		['11', "Ν. Ηλείας"],            //noslz
		['12', "Ν. Κορινθίας"],            //noslz
		['13', "Ν. Λακωνίας"],            //noslz
		['14', "Ν. Μεσσηνίας"],            //noslz
		['15', "Ν. Ζακύνθου"],            //noslz
		['16', "Ν. Κερκύρας"],            //noslz
		['17', "Ν. Κεφαλληνίας"],            //noslz
		['18', "Ν. Λευκάδας"],            //noslz
		['19', "Ν. Aρτας"],            //noslz
		['20', "Ν. Θεσπρωτίας"],            //noslz
		['21', "Ν. Ιωαννίνων"],            //noslz
		['22', "Ν. Πρέβεζας"],            //noslz
		['23', "Ν. Καρδίτσας"],            //noslz
		['24', "Ν. Λάρισας"],            //noslz
		['25', "Ν. Μαγνησίας"],            //noslz
		['26', "Ν. Τρικάλων"],            //noslz
		['27', "Ν. Γρεβενών"],            //noslz
		['28', "Ν. Δράμας"],            //noslz
		['29', "Ν. Ημαθίας"],            //noslz
		['30', "Ν. Αργολίδας"],            //noslz
		['31', "Ν. Καβάλας"],            //noslz
		['32', "Ν. Καστοριάς"],            //noslz
		['33', "Ν. Κιλκίς"],            //noslz
		['34', "Ν. Κοζάνης"],            //noslz
		['35', "Ν. Πέλλας"],            //noslz
		['36', "Ν. Πιερίας"],            //noslz
		['37', "Ν. Σερρών"],            //noslz
		['38', "Ν. Φλώρινας"],            //noslz
		['39', "Ν. Χαλκιδικής"],            //noslz
		['40', "Ν. Έβρου"],            //noslz
		['41', "Ν. Ξάνθης"],            //noslz
		['42', "Ν. Ροδόπης"],            //noslz
		['43', "Ν. Δωδεκανήσων"],            //noslz
		['44', "Ν. Κυκλάδων"],            //noslz
		['45', "Ν. Λέσβου"],            //noslz
		['46', "Ν. Σάμου"],            //noslz
		['47', "Ν. Χίου"],            //noslz
		['48', "Ν. Ηρακλείου"],            //noslz
		['49', "Ν. Λασιθίου"],            //noslz
		['50', "Ν. Ρεθύμνου"],            //noslz
		['51', "Ν. Χανίων"]         //noslz
		
	
	
	];		
		
	
	var nomosTypeSimpleStore = new Ext.data.SimpleStore({
			fields : ['key', 'displayFld'],            //noslz
			data : nomosData
		});
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
		var settings2FormPanel = new Ext.form.FormPanel({
		id : 'settings2FormPanel',            //noslz
		name : 'settings2FormPanel',            //noslz
		bodyStyle : 'padding:5px',            //noslz
		layout : 'form',            //noslz
		border : false,
		items : [
		
							
	
						
		
								{
								text : 'Όρισε Διεύθυνση',
								 	iconCls : 'icon-map',
									scale : 'medium',
									xtype : 'button',
									
									
									handler : function() {
										
											
										saveAll(0);
										
																	
																	
										var mapIframecode =     '<!-- start  -->'+
													    '<iframe name="mapIFrame" id="mapIFrame"'+
													      '	 allowtransparency="true" '+
													 	  '  src="map.html?' +
													 	  	'act=saveMemopageMapInfo' +
													 	  	      '&sid=' + sid    
													 	  		
													 	  	    + '&mapZoom=' +   rowObj.mapZoom
													 	  		+ '&ads=' +    Ext.get('pageAddress').getValue() 
													 	  		+ '&lat=' +    rowObj.pageLat 
													 	  		+ '&lng=' +    rowObj.pageLng
													 	  		+    ' "  '+
													      '  frameborder="0" '+
													      '  scrolling="no" '+
													      '  width="100%" '+
													      '  height="100%" '+
													      '  marginwidth="0" '+  
													      '  marginheight="0" '+ 
													  //  '     onload="javascript:alert(\'loaded\');">  '+
													  	  ' <p>Your browser does not support iframes.</p> '+
													 '</iframe> '+
													 '<!-- end --> ' ;
													 
													 
								if ( Ext.isIE ) {
									mapIframecode =			'<!-- start  -->'+
													    '<iframe name="mapIFrame" id="mapIFrame"'+
													      '	 allowtransparency="true" '+
													 	   '  src="map.html?' +
													 	  	'act=saveMemopageMapInfo' +
													 	  	      '&sid=' + sid    
	 										 	  	            + '&mapZoom=' +   rowObj.mapZoom
													 	  		+ '&ads=' +    Ext.get('pageAddress').getValue()
													 	  		+ '&lat=' +    rowObj.pageLat 
													 	  		+ '&lng=' +    rowObj.pageLng
													 	  		+    ' "  '+
													      '  frameborder="0" '+
													      '  scrolling="no" '+
													      '  width="800px" '+
													      '  height="600px" '+
													      '  marginwidth="0" '+  
													      '  marginheight="0" '+ 
													  //  '     onload="javascript:alert(\'loaded\');">  '+
													  	  ' <p>Your browser does not support iframes.</p> '+
													 '</iframe> '+
													 '<!-- end --> ' ;
										 
													 
								}
													 
						 
										
										
												
												 
									mapWindow = new Ext.Window({
											title : " - ",
											width : 500,
											id : "mapWindowId",
											layout : "fit",// noslz
											modal : true,
											resizable : false,
											maximizable : false,
											closable : true,
											maximized : true,
											
											tbar:[
									
												{
													text : 'Eπιστροφή',
													iconCls : 'icon-left',//noslz
													//scale : 'medium',//noslz
													handler : function() {
														
													
														
														mapWindow.close();
														memopageWindow.close();
														
														Ext.Ajax.request({
															method : 'POST', // noslz
															url : GamesServlet,
															params : {
																sid : sid,
																action : 'getMemopage' // noslz
															},
															scope : this,
															success : function(res) {
			
																var obj = Ext.util.JSON.decode(res.responseText);
																obj.sid = sid;
			
																// will be obj
																openMemopageWindow(obj );
																
															
																
															}// success
														});// Ext.Ajax.request
														
														
													}
												}
									
											],
											
											items : [

											new Ext.Panel({
														border : false,
														// width :"100%",
														// height :"1000px",
														// bodyStyle :
														// "backgroundColor:#D3E0F2;",
														layout : "fit",

														// closable : false,
														// collapseMode :
														// 'mini',
														// deferredRender :
														// false,
														html : mapIframecode

													})

											]

										});
												 
												 
									mapWindow.show();			 
												 
												 
												 
												 
												 
												 
										
								}// handler
								} ,
								
								{
										xtype:"label",            //noslz
										html:" <br>"            //noslz
							},
							
								{
								xtype : 'combo',            //noslz
								id : 'nomosType',            //noslz
								name : 'nomosType',            //noslz
								allowBlank : false,
								editable : false,
								width : 300,
								fieldLabel : 'Νομός'  ,
								store : nomosTypeSimpleStore,
								valueField : 'key',            //noslz
								displayField : 'displayFld',            //noslz
								triggerAction : 'all',            //noslz
								value: 0,
								mode : 'local'            //noslz
							},
							{
								id : 	'pageAddress',            //noslz
								name :  'pageAddress',            //noslz
								xtype : 'textfield',            //noslz
								width : 300,
								fieldLabel :  'Διεύθυνση' 

							}, 	
							 {
								id : 'pageLat',            //noslz
								name : 'pageLat',            //noslz
								xtype : 'textfield',            //noslz
								width : 300,
								hidden: true,
								fieldLabel :  'Google map lat' 

							}, 	
							 {
								id : 'pageLng',            //noslz
								name : 'pageLng',            //noslz
								xtype : 'textfield',            //noslz
								hidden:true,
								width : 300,
								fieldLabel :  'Google map lng' 

							} 	,
							
						
		
		
				
							{
								xtype:"label",            //noslz
								html:" <br><br>Nα εμφανίζεται στο ημερολογίο <img  src ='../css/img/silk/icons/calendar.png' ></img>    <br><br>"            //noslz
							},
							
						
						
							{
							xtype:"panel",
							layout: "column",
							border: false,
							width : 300,
							fieldLabel : "  Διάλεξε χρώμα  ",
							items :[
								
									{	
												id:"exampleCss",
												name:"exampleCss",
												fieldLabel : 'Χρώμα π.χ.  ',
												hideLabel: true,
												xtype:"label",            //noslz
												width : 94,
												html:""            //noslz
									},
								
							
								{
											xtype : 'combo', // noslz
											id : 'calendarCssClass', // noslz
											name : 'calendarCssClass', // noslz
											allowBlank : false,
											editable : false,
											width : 1,
											fieldLabel : '',
											hideLabel: true,
											store : cssSimpleStore,
											valueField : 'key', // noslz
											displayField : 'displayFld', // noslz
											triggerAction : 'all', // noslz
											value : "memopage",
											mode : 'local', // noslz
											listeners : {
												scope : this,
												'select' : function(combo, rec, index) {
								
													if (rec.get('key') == "memopage") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: #D0D0D0;  color: black; '> δείγμα </span>");
								
													} else if (rec.get('key') == "memopagered") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: red;  	color: white; '> δείγμα</span>");
								
													} else if (rec.get('key') == "memopagegreen") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: green;  	color: white; '> δείγμα</span>");
								
													} else if (rec.get('key') == "memopageblue") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: blue;  	color: white; '> δείγμα</span>");
													}
													else if (rec.get('key') == "memopageyellow") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: yellow;  	color: black; '> δείγμα</span>");
													}
														else if (rec.get('key') == "memopageorange") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: orange;  	color: black; '> δείγμα</span>");
													}
													
													else if (rec.get('key') == "memopageblack") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: black;  	color: white; '> δείγμα</span>");
													}
													
													else if (rec.get('key') == "memopagepink") {
								
														Ext
																.getCmp('exampleCss')
																.update("<span  STYLE=' background-color: pink;  	color: black; '> δείγμα</span>");
													}
													

													
													
													
													
													
													
													
													
													
													
													
													
													
													
													
													
													
													
													
												}
											}
								
										}
											
									
								
						]}, // column 
								
								
								
								
								
								
								
								
								
					
					 new Ext.form.DateField({
					  			id : 'pageWhenDay',            // noslz
								name : 'pageWhenDay',    
								format : 'd/m/Y'   ,         // noslz
								editable : false,
								width : 120,
								fieldLabel :  'Απο' 	
								
							}), 
			
							new Ext.form.TimeField( {
							id : 'pageWhenTime',            // noslz
							name : 'pageWhenTime',            // noslz
							xtype : 'timefield',            //noslz
							width : 120,
							minValue : '6:00',            //noslz
							maxValue : '23:00',            //noslz
							editable : false,
							forceSelection : true,
							format : 'H:i',            //noslz
							increment : 30,
							fieldLabel :  '' 				
						}),
			
							
						{
										xtype:"label",            //noslz
										html:"<BR>"            //noslz
							}
						,
						 new Ext.form.DateField({
					  			id : 'pageWhenEndDay',            //noslz
								name : 'pageWhenEndDay',    
								width : 120,
								format : 'd/m/Y'   ,         //noslz
								editable : false,
								fieldLabel :  'Εως' 	
								
							}), 
			
							new Ext.form.TimeField( {
							id : 'pageWhenEndTime',            //noslz
							name : 'pageWhenEndTime',            //noslz
							xtype : 'timefield',            //noslz
							width : 120,
							minValue : '9:00',            //noslz
							maxValue : '23:00',            //noslz
							editable : false,
							forceSelection : true,
							format : 'H:i',            //noslz
							increment : 30,
							fieldLabel :  '' 				
						})
						
						
						
							
							
								
							
											
											
							
		]
	});
	
	
	
	
	
	settingsFormPanel = new Ext.form.FormPanel({
		id : 'settingsFormPanel',            //noslz
		name : 'settingsFormPanel',            //noslz
		bodyStyle : 'padding:5px',            //noslz
		layout : 'form',            //noslz
		autoScroll :true,
		autoHeight :true,
		width : 430,
		height : 300,
		// columnWidth: .5,
		border : false,
		items : [
		
		
						/*	{
								text : 'Select the Memopage Type',
								// iconCls : 'icon-save',
									scale : 'medium',
									xtype : 'button',
									handler : function() {
										alert ( "show win to select type  pass in pageTypeCmp  , pageTypeImg " )
										//openSelTeamsMainWin( 'pageTypeCmp' ,  'pageTypeImg' );         
									// Note that openAreaCodeMainWin  setsValue of id:'areacode' 
								}// handler
								}, 
							
							
							{
										xtype:"label",            //noslz
										html:"<BR>"            //noslz
							},
							
							{
								id : 'pageTypeImg',            //noslz
								name : 'pageTypeImg',            //noslz
								width : 100,
								hieght: 60,
								xtype : 'panel',            //noslz
								html: "<center> <p><img src='../css/img/play.png' border='0' /></p>  </center> <br> ",            //noslz
								border : false
								//,fieldLabel : ''
							},
							
							{
										xtype:"label",            //noslz
										html:"<BR>"            //noslz
							},
							
							{
								id : 'pageTypeCmp',            //noslz
								name : 'pageTypeCmp',            //noslz
								xtype : 'textfield',            //noslz
								width : 300,
								hieght: 20,
								hidden: true, 
								readOnly : true,
								value: "-1"            //noslz
							},
							*/
	
		{
		xtype: "panel",	
		
	layout:'column',
		border : false,
	width : 300,
			items: [
			
			 {
			text : "Αποθήκευση",
			xtype: "button",
			scale : 'medium',
			iconCls : 'icon-save',//noslz
			handler : function() { 
				
				saveAll( 1 );
				
			}
		}
		
		/*
		, 
		{
			text : "Αποθήκευση",
			xtype: "button",

			scale : 'medium',
			iconCls : 'icon-map',//noslz
			handler : function() { 
			
				
				alert ("open close hide ");
				
			}
		}
			*/
			
			]

		},	
		
	
		
		
		
							{
								xtype:"label",            //noslz
								html:"<br>"            //noslz
							},
		
							
							
							{
								xtype:"label",            //noslz
								html: "&nbsp;&nbsp;<img  src ='../css/img/silk/icons/zoom.png' ></img> <a target='_blank' href = '"+ DirectUrl+"?sid="+rowObj.sid +"'>   "+
								DirectUrl+"?sid="+rowObj.sid +"</a>"     

												
							},
		
							
							{
								xtype:"label",            //noslz
								html:"<br><br>"            //noslz
							},
							
							{
								id : 'sidNumber',            //noslz
								name : 'sidNumber',            //noslz
								xtype : 'textfield',            //noslz
								readOnly:true,
								width : 150,
								fieldLabel : 'Αριθμός σελίδας',
								value: rowObj.sid
							}, 
							
							{
								id : 'pageUrl',            //noslz
								name : 'pageUrl',            //noslz
								xtype : 'textfield',            //noslz
								hidden:true,
								width : 300,
								fieldLabel : 'Σύνδεσμος'
							}, 
							
							
							{
								id : 'pageName',            //noslz
								name : 'pageName',            //noslz
								xtype : 'textfield',            //noslz
								width : 300,
								fieldLabel :  'Τίτλος' 

							}, 
							
							
							{
								id : 'pageSummary',            //noslz
								name : 'pageSummary',            //noslz
								xtype : 'textarea',            //noslz
								width : 300,
								fieldLabel :  'Περίληψη' 

							},
							
							{
								xtype : 'combo',            //noslz
								id : 'status',            //noslz
								name : 'status',            //noslz
								allowBlank : false,
								editable : false,
								width : 120,
								fieldLabel : 'Eνεργοποιημένη'  ,
								store : statusSimpleStore,
								valueField : 'key',            //noslz
								displayField : 'displayFld',            //noslz
								triggerAction : 'all',            //noslz
								value: 1,
								mode : 'local'            //noslz
							},
							
							
						
				            
				            {
				                id : 'hasComments',           
				                name : 'hasComments'  ,
				                checked: true,
				                xtype:'checkbox',
				                	hidden:true,
				              //  fieldLabel: '',
				                boxLabel:' allow others to comment on it'
				                  
				            },
				            
				            {
				                id : 'commentsAreReviewed',           
				                name : 'commentsAreReviewed'  ,
				                checked: true,
				                	hidden:true,
				                xtype:'checkbox',
				             //   fieldLabel: '',
				                boxLabel:' review what comments are shown on it'
				            },
				            
				            	
				            
				          
				            	{
				                id : 'isListed',           
				                name : 'isListed'  ,
				                checked: true,
				                xtype:'checkbox',
				               // fieldLabel: '',
				                boxLabel:' να επιτρέπεται σε όλους η αναζητηση της σελίδες '
				                  
				            },
				            
				            
				            {
								xtype : 'combo',            //noslz
								id : 'pageType',            //noslz
								name : 'pageType',            //noslz
								allowBlank : false,
								editable : false,
								width : 300,
								fieldLabel : 'Θέμα'  ,
								store : typeSimpleStore,
								valueField : 'key',            //noslz
								displayField : 'displayFld',            //noslz
								triggerAction : 'all',            //noslz
								value: -1,
								//mode: 'remote'  
								mode : 'local'            //noslz
							},
				            
				            
				            
				{
                    xtype:'textfield',
                    width: 300,
                    id: 'pageEmail',
                    name: 'pageEmail',
                    fieldLabel: 'Emai' ,
                    vtype:'email',
                  	//allowBlank:false,
                   listeners: {
		            blur  : function ( fld ){
		            	
		            	var emailVal 		= fld.getValue();
		            	emailVal = Ext.util.Format.trim(emailVal);
            			//	alert ( "loss focus |" + emailVal+"|" ) 
			            if ( emailVal.length==0) fld.clearInvalid();
			            
		            }
		            }// LISTENER 
                    
                },
                
                {
                    xtype:'textfield',
                    width: 300,
                    fieldLabel: 'WebLink',
                    id: 'pageWebLink',
                    name: 'pageWebLink',
                    vtype:'url',
                   	listeners: {
		            blur  : function ( fld ){
		            	
		            	var webVal 		= fld.getValue();
		            	webVal = Ext.util.Format.trim(webVal);
            			//	alert ( "loss focus |" + emailVal+"|" ) 
			            if ( webVal.length==0) fld.clearInvalid();
			            
		            }
		            /*
		            ,focus  : function ( fld ){
		            	
		            		
		            			 Ext.MessageBox.show({
						           title:'θέλεις να αλλάξεις το WebLink ;',
						           msg: ' π.χ  http://www.google.com ',
						           buttons: Ext.MessageBox.YESNOCANCEL,
									 fn: function(button) {
								        if (button == 'yes') {
								        	Ext.getCmp('pageWebLink').setRawValue( "http://"  );
								        	
								        } else {
								        }
								    },
						           animEl: 'mb4',
						           icon: Ext.MessageBox.QUESTION
						       });
							
		            	
		            	
		             	
		            }*/
		            
		            
		            }// LISTENER 
                },
				            
				            
                
                {
                     xtype:'textfield',
                      width: 200,
                    fieldLabel: 'Τελ.',
                    id: 'pageTel',
                    name: 'pageTel',
                    value: '',
                    plugins: [ new Ext.ux.InputTextMask(PHONEMASK , true) ]
                },
                
                
				 {	
				 	id: 'priceEuros',
                    name: 'priceEuros',
                    xtype:'textfield',
                    width: 70,
                    fieldLabel: 'Τιμη (&euro;)',      
                    value: ''
                   
                },            
				            
				            
				            {
								xtype : 'combo',            //noslz
								id : 'pageLang',            //noslz
								name : 'pageLang',            //noslz
								allowBlank : false,
								editable : false,
								width : 120,
								hidden:true,
								fieldLabel : 'Γλώσσα'  ,
								store : langSimpleStore,
								valueField : 'key',            //noslz
								displayField : 'displayFld',            //noslz
								triggerAction : 'all',            //noslz
								value: "EL",
								mode : 'local'            //noslz
							},
							
							
						
							
							
							{
								id : 'pageKeywords',            //noslz
								name : 'pageKkeywords',            //noslz
								xtype : 'textfield',            //noslz
								width : 300,
								hidden:true,
								fieldLabel :  'Keywords' 

							}, 
								{
										xtype:"label",            //noslz
										html:" <br>"            //noslz
							}
							
							
							
						
						
						
						
							
		]
	});
	
	
	
	
	
		
	
	
		
	
	
	
	
	
	
	
	
	//-----   panel   
	
	 var memopagePanel = new Ext.Panel({

		id : 'memopageFormPanel', //noslz
		name : 'memopageFormPanel', //noslz
		labelAlign : 'top', //noslz
		bodyStyle : 'padding:5px', //noslz
		border : false,
		layout : 'fit',  //noslz
		items : [

			{
            
            id:'grouptabpanelMemopage',
            name:'grouptabpanelMemopage',
            xtype: 'grouptabpanel',
    		tabWidth: 150,
    		activeGroup:  0  ,
    		
    		items: [{
    			
    			//mainItem: 1,
    			
    			items: [
    			
                /*     				
    			{
    				title: 'Location ',
                    layout: 'fit',
                    iconCls: 'x-icon-configuration',
                    tabTip: ' Memopage Location ',
                    style: 'padding: 10px;',
    				//html: 'Content'
                    items: [ 
                    
             
                    {
                   // title: 'Location, Location, Location ',
                     title: '',
                    iconCls: 'x-icon-configuration',
                    tabTip: 'use google map to select a location  ',
                    style: 'padding: 10px;',
                    items: [ 
                     				
			                new Ext.Panel({
							border : false,
							//width :"100%", 
							//height :"1000px", 
							//bodyStyle : "backgroundColor:#D3E0F2;",
							layout : "fit",
							
							//	closable : false,
							//	collapseMode : 'mini',
							//  deferredRender : false,
							  html : mapIframecode
							  
							 })
               		 ]   
                }   
    			
                    		]
                    
                }
    			*/
    			
    			
	
	
    			{
    			
    				
                    title: 'Στοιχεία σελίδας',
                    iconCls: 'x-icon-configuration',
                    tabTip: 'Πληροφορίες σελίδας',
                    style: 'padding: 10px;',
                    items: [ 
                    		
                    
                    {
                    	xtype:"label",
                    	html: " <h3> Στοιχεία σελίδας </h3>"
                    },
                    
                    
                    
                    
                    
							{
							    title: '',
							    layout:'column',
							    autoScroll :true,
								autoHeight :true,
							    columnWidth: .5,
							    
							    items: [
							    			settingsFormPanel 			,
							    			settings2FormPanel
							    			
							    ]
							}
                    		
                    		
                    		]
                    
                
    			}
    			
    			
    			
    			
    			
    			]
            }
            
            
            
            , 
            
            
             {
                
                items: [
    			
				{
                    title: 'Περιεχόμενο ',
                    iconCls: 'x-icon-configuration',
                    tabTip: 'περιεχόμενο  ',
                    style: 'padding: 10px;',
                    items: [ 
                    		
                    {
                    	xtype:"label",
                    	html: " <h3> Περιεχόμενο σελίδας </h3>"
                    },
                    
                    
                    
                     new Ext.Panel({
					border : false,
					//width :"100%", 
					//	bodyStyle : "backgroundColor:#D3E0F2;",
					layout : "fit",
					//	closable : false,
					//	collapseMode : 'mini',
					//  deferredRender : false,
					  html : 
					  		editorIframecode
					  		
					  		
					  		
					 })
                    ]
    			} 
                
                
                ]
            }
            
            
            ]
		
		
		
		}]
	});

	
	
	
	
	
	
	
	
	
	 memopageWindow = new Ext.Window({
		title : " σελίδα  " + sid + "   "  + rowObj.pageName,
		width : 500,
		id:"memopageWindowId",
		layout : "fit",//noslz
		// modal : true,
		
		
		resizable : false,
		maximizable : false,
		
		closable : false,
		maximized: true,
		
		items : [memopagePanel],
		tbar:[
		
		
			{
			text : txtMemopageeWindow .back,
			iconCls : 'icon-left',//noslz
					iconAlign: 'top',
			//scale : 'medium',//noslz
			handler : function() {
				
				
				// refresh it 
				memopagesGridWinOpenFlag = false; 
				memopagesGridWin.close();
				openMemopagesGridWin();
				
				
				memopageWindow.close();
				
				
			}
		},
		
	/*	
		{
			text : txtMemopageeWindow .reopen,
			iconCls : 'icon-refresh',//noslz
			//scale : 'medium',//noslz
			handler : function() {
				
				
				// refresh it 
				memopagesGridWinOpenFlag = false; 
				memopagesGridWin.close();
				openMemopagesGridWin();
				memopageWindow.close();
				
				openMemopageWindow(  rowObj );
				
				
				
			}
		},
		*/
		
		{ text:  "Βοήθεια" ,
		  iconAlign: 'top',
	iconCls:'icon-help',      //noslz
    	handler : function(){
    	  
    		alert ("todo add help of page")
    		
			    		
    	}// handler
	}		
		
		
		]
	});

	
	
	
	memopageWindow.on('beforeshow', function(obj ){      //noslz
		
		
		//alert ("beforeshow");
		
			Ext.getCmp('memopageWindowId').getEl().mask();
			
			typeSimpleStore.load({
					params : {
						action:'loadAllCategs'	
						},
						
					callback  :	 function(rows, opt, loadFlag  ){   
								
						if (loadFlag ){		
								
													
						
							if   (  sid > 0  && null != rowObj  ){
								
								// Fill form   with  data   -   get the data  and display it  else its new Memopage 
								
								Ext.getCmp('pageName').setRawValue(rowObj.pageName);
								
								//Ext.getCmp('areaCode').setRawValue(rowObj.areaCode);
								
								Ext.getCmp('pageSummary').setRawValue(rowObj.pageSummary);
							
								var tKey =  rowObj.pageType ;
								var tPos = typeSimpleStore.find( 'key', tKey);
								var tRow = typeSimpleStore.getAt( tPos );
								Ext.getCmp('pageType').setValue(  tRow.get('displayFld') );
								
								
								var pageWhenSplit =   rowObj.pageWhen.split("_");
								Ext.getCmp('pageWhenDay').setRawValue(pageWhenSplit[0] );
								Ext.getCmp('pageWhenTime').setRawValue(pageWhenSplit[1]);
								
								var pageWhenEndSplit =   rowObj.pageWhenEnd.split("_");
								
								Ext.getCmp('pageWhenEndDay').setRawValue(  pageWhenEndSplit[0] );
								
								Ext.getCmp('pageWhenEndTime').setRawValue(  pageWhenEndSplit[1] );
				
								//		 mapIFrame.document.getElementById('latlng').value = rowObj.pageLat +"," +rowObj.pageLng;
								//		 mapIFrame.document.getElementById('address').value = rowObj.rowObj.pageAddress;
											 
									//from ifram
								
								Ext.getCmp('pageTel').setRawValue(rowObj.pageTel);
								Ext.getCmp('pageEmail').setRawValue(rowObj.pageEmail);
							  	
							  	Ext.getCmp('pageWebLink').setRawValue(rowObj.pageWebLink);
								
							  	
							  	
								
								Ext.getCmp('priceEuros').setRawValue(rowObj.priceEuros);
								
								
								
								Ext.getCmp('pageAddress').setRawValue(rowObj.pageAddress);
								
								var   latF = parseFloat( rowObj.pageLat  );
								if ( latF  == -1  ) 
								{
									Ext.getCmp('pageLat').setRawValue("");
								} else {
									Ext.getCmp('pageLat').setRawValue(rowObj.pageLat);
								}
								
								
								var   lngF = parseFloat( rowObj.pageLng  );
								if ( lngF  == -1  ) 
								{
									Ext.getCmp('pageLng').setRawValue("");
								} else {
									Ext.getCmp('pageLng').setRawValue(rowObj.pageLng);
								}
								
								
								
								
									// set by jsp 		Ext.getCmp('pageContent').setRawValue(rowObj.pageContent);
								Ext.getCmp('pageKeywords').setRawValue(rowObj.pageKeywords);
											
								// automatic set in grid 			Ext.getCmp('pageUrl').setRawValue(rowObj.pageUrl);
								
								
								if ( rowObj.isListed == "1" )
								Ext.getCmp('isListed').setValue( true);
								else 
								Ext.getCmp('isListed').setValue( false);
								
								
								
								if ( rowObj.hasComments == "1" )
								Ext.getCmp('hasComments').setValue( true);
								else 
								Ext.getCmp('hasComments').setValue( false);
								
								if ( rowObj.commentsAreReviewed == "1" )
								Ext.getCmp('commentsAreReviewed').setValue( true);
								else 
								Ext.getCmp('commentsAreReviewed').setValue( false);
						
								
								
								
								
								var sKey =  rowObj.status ;
								var sPos = statusSimpleStore.find( 'key', sKey);
								var sRow = statusSimpleStore.getAt( sPos );
								Ext.getCmp('status').setValue( sRow.get('displayFld') ) ;
							
								var lKey =  rowObj.pageLang ;
								var lPos = langSimpleStore.find( 'key', lKey);
								var lRow = langSimpleStore.getAt( lPos );
								Ext.getCmp('pageLang').setValue( lRow.get('displayFld') ) ;
							
								
								
								
								var nKey =   rowObj.areaCode  ;
								var nPos = nomosTypeSimpleStore.find( 'key', nKey);
								var nRow = nomosTypeSimpleStore.getAt( nPos );
								Ext.getCmp('nomosType').setValue( nRow.get('displayFld') ) ;
				
								
								
								var cKey =   rowObj.calendarCssClass  ;
								var cPos = cssSimpleStore.find( 'key', cKey);
								var cRow = cssSimpleStore.getAt( cPos );
								Ext.getCmp('calendarCssClass').setValue( cRow.get('displayFld') ) ;
								
				
								
								if (rowObj.calendarCssClass == "memopage") {
			
									Ext
											.getCmp('exampleCss')
											.update(" <span  STYLE=' background-color: #D0D0D0;  color: black; '> δείγμα </span>");
			
								} else if (rowObj.calendarCssClass == "memopagered") {
			
									Ext
											.getCmp('exampleCss')
											.update(" <span  STYLE=' background-color: red;  	color: white; '> δείγμα</span>");
			
								} else if (rowObj.calendarCssClass == "memopagegreen") {
			
									Ext
											.getCmp('exampleCss')
										.update(" <span  STYLE=' background-color: green;  	color: white; '> δείγμα</span>");
								}

								else if (rowObj.calendarCssClass  == "memopageblue") {

									Ext
											.getCmp('exampleCss')
											.update("<span  STYLE=' background-color: blue;  	color: white; '> δείγμα</span>");
								} else if (rowObj.calendarCssClass  == "memopageyellow") {

									Ext
											.getCmp('exampleCss')
											.update("<span  STYLE=' background-color: yellow;  	color: black; '> δείγμα</span>");
								} else if (rowObj.calendarCssClass  == "memopageorange") {

									Ext
											.getCmp('exampleCss')
											.update("<span  STYLE=' background-color: orange;  	color: black; '> δείγμα</span>");
								}

								else if (rowObj.calendarCssClass == "memopageblack") {

									Ext
											.getCmp('exampleCss')
											.update("<span  STYLE=' background-color: black;  	color: white; '> δείγμα</span>");
								}

								else if (rowObj.calendarCssClass  == "memopagepink") {

									Ext
											.getCmp('exampleCss')
											.update("<span  STYLE=' background-color: pink;  	color: black; '> δείγμα</span>");
								}
												
							
								
								
									 Ext.getCmp('memopageWindowId').getEl().unmask();
								
								
								
								
								}
                
                
                
               				
							
							
						} else {
							
								alert ("Internet communication error check your internet conection or Server is down"); 	
						}
							
					}
					
					
				}); 	// typeSimpleStore.load({
		
		
	}); // memopageWindow.on('beforeshow',
	
	
	
	
	
		
		
	memopageWindow.show();
	

	
      
	
	
	function formatDate(value) {
		return value ? value.dateFormat('d/m/y') : '';//noslz
	};
	
	// populate init data 
	//Ext.getCmp('userName').setRawValue(rowObj.uname);//noslz
	//Ext.getCmp('userEmail').setRawValue(rowObj.email);//noslz
	//Ext.getCmp('userTel').setRawValue(rowObj.tel);//noslz
	
	//	Ext.getCmp('userRole').setRawValue(rowObj.roleType);//noslz
	//-
	//joinDt = new Date(  rowObj.createDt ) ;
	//Ext.getCmp('userJoinDate').setRawValue( formatDate(joinDt) );//noslz
	
	
}// openMyInfoWindow

