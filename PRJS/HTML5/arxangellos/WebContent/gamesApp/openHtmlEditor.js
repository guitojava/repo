


function openHtmlEditor( obj, act  ) {
	
	var sid = obj  ;	

	var dlg = new Ext.Window({
		title : "  -  ",
		width : 800,
		height : 500,
		minWidth : 100,
		minHeight : 100,
		layout : "fit",      //noslz
		
		modal : true,
		closable : false,
		resizable : true,
		maximizable : true,
		maximized:true, 
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
				//width :"100%", 
				
			//	bodyStyle : "backgroundColor:#D3E0F2;",
				layout : "fit",
				
			//	closable : false,
			//	collapseMode : 'mini',
			//  deferredRender : false,
			  html : 
			  		'<iframe id="richText" name="richText"  ' +
			  		'src="../fckeditorImpl/htmlEditor.jsp?act='+act+'&sid='+sid+' "' +
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
								dlg.close();
							}// handler
						}
					]
		
	});

	dlg.show();
	
	

}