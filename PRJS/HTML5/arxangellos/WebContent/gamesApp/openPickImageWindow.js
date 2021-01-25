




function openPickImageWindow ( obj , act  ) {
	
	var sid 	= obj ;	
	
	
	var dlg = new Ext.Window({
		title : " επιλογη εικονας προφιλ ",
		
		y: 100,
		
		width : 700,
		height : 300,
		minWidth : 100,
		minHeight : 100,
		layout : "fit",      //noslz
		
		modal : true,
		closable : false,
		resizable : true,
		maximizable : true,
		hideMode : "offsets",      //noslz
		constrainHeader : true,
		items : [{
			layout : "fit",      //noslz
			bodyStyle : 'padding:10px',      //noslz
			border : false,
			bodyBorder : false,

			items : [

			 new Ext.Panel({
				border : false,
			//	bodyStyle : "backgroundColor:#D3E0F2;",
				layout : "fit",
			//	closable : false,
			//	collapseMode : 'mini',
			//  deferredRender : false,
			    html : 
			  		'<iframe id="richText" name="richText"  ' +
			  		'src="../fckeditorImpl/pickProfileImage.jsp?act='+act+'&sid='+ sid+' "' +
			  		'width=100% height=100% frameborder="0" scrolling="auto" name=" "></iframe>'          //noslz		
	            
			 })
			 
			 ]}
				
			], 
			
			tbar : [
					
						
					{
							text : 'κλείσιμο',
							iconCls : 'icon-cancel',//noslz
							scale : 'medium', //noslz
						    handler : function() {
								
							
								
								if  (act == "saveProfileImage"){
										
										userProfileWindow.close();
									
											Ext.Ajax.request({
												method : "POST",      //noslz
												url : GamesServlet,
												params : {
													action : 'getUserInfo'      //noslz
												},
												scope : this,
												success : function(res) {
													var obj = Ext.util.JSON.decode(res.responseText);
													// TODO 
											//		alert(  res.responseText   ); 
													
													openUserProfileWindow(obj);
													
												}//success
											});//Ext.Ajax.request
											
								}
							
								dlg.close();
							
							
							
						}// handler
					}
			]
		
	});

	dlg.show();
	
	

}