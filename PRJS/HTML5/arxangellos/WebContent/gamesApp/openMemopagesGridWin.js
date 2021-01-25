
var memopagesGridWin;
var memopagesGridWinOpenFlag = false;



function  openMemopagesGridWin(){
	
	
if ( !memopagesGridWinOpenFlag ) {			
	

memopagesGridWinOpenFlag  = true;

	

function newPage(){
	
	//var obj ={} ;
	//obj.sid = -1;
	
	
		Ext.Ajax.request({
					method : 'POST', // noslz
					url : GamesServlet,
					params : {
						action : 'createMemopage' // noslz
					},
					scope : this,
					success : function(res) {
	
						var obj = Ext.util.JSON.decode(res.responseText);
						// re fresh window and data 
						memopagesGridWinOpenFlag = false; 
						memopagesGridWin.close();
						openMemopagesGridWin();
	
					}// success
				});// Ext.Ajax.request
	
}



function clonePage( sidVal ){
	
	if ( null != sidVal ) {
		Ext.Ajax.request({
					method : 'POST', // noslz
					url : GamesServlet,
					params : {
						sid : sidVal ,
						action : 'cloneMemopage' // noslz
					},
					scope : this,
					success : function(res) {
						
						var obj = Ext.util.JSON.decode(res.responseText);
							
						memopagesGridWinOpenFlag = false; 
						memopagesGridWin.close();
						openMemopagesGridWin();
						
						
					}// success
				});// Ext.Ajax.request
	
		} 
}// clonePage fun




function editPage( sidVal ){
	
	if ( null != sidVal ) {
		Ext.Ajax.request({
					method : 'POST', // noslz
					url : GamesServlet,
					params : {
						sid : sidVal ,
						action : 'getMemopage' // noslz
					},
					scope : this,
					success : function(res) {
	
					
						var obj = Ext.util.JSON.decode(res.responseText);
							
						obj.sid = sidVal;
						
						// will be obj 
						openMemopageWindow(   obj   );
						
						
						
					}// success
				});// Ext.Ajax.request
	
		} 
}// edit fun 


function delPage( sidVal ){
	
	if ( null != sidVal ) {
		
		
		
		Ext.Msg.show({
			title : 'Διαγραφή  ',
			buttons : Ext.MessageBox.YESNO, 
			msg : 'Διαγραφή  #  (   ' +  sidVal +   '  ) ?',      
							
						
		fn : function(btn) {
			if (btn == 'yes') {      //noslz

		
				Ext.Ajax.request({
					method : 'POST', // noslz
					url : GamesServlet,
					params : {
						sid : sidVal ,
						action : 'deleteMemopage' // noslz
					},
					scope : this,
					success : function(res) {
						memopagesGridWinOpenFlag = false; 
						memopagesGridWin.close();
						openMemopagesGridWin();
					}// success
				});// Ext.Ajax.request
		
			} // if yes
			
		} // fun
		});// confim 
		
		
		
		} 
		
		
		
		
		
}// edit fun 





	function renderBold(value, p, record) {
		return "<b>"+value +"</b>";
	}
	
	
	function renderWebsite(value, p, record) {
		
		var str = value.substring(0,4);
		var url = value.substring(7);
		
		if ( str ==="http"  ) 		value = "<a class='colorlink'  href='"+value+"' target='_blank'>"+url+"</a>";        //noslz
		else value = '';
		
		if ( record.get('email').length > 3) 
		value = value + "<br><br><a target='_blank'  class='colorlink'  href='mailto:"+ record.get('email') +"'>"+ record.get('email')  +"</a>"; 
		
		
		return value;
	}
	
    
	

	function renderPublic(value, p, record) {
		//var html = buildGameToolTipTag(record);
		return " <a target='_blank' href = '"+DirectUrl+"?sid="+record.get("sid")+"'  > <img  src ='../css/img/silk/icons/zoom.png' ></img>  </a>   " ;
	}
	
	
	var MemopagesDataStore = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : GamesServlet ,      //noslz
							method : 'POST'      //noslz
						}),
		        
		        reader : new Ext.data.JsonReader({
							root : 'results',      //noslz
							totalProperty : 'total'      //noslz
						}, [
							    {
									name : 'sid',      //noslz
									type : 'int'      //noslz
								},
								
								{
									name : 'pageName',      //noslz
									type : 'string'      //noslz
								},
								
								{
									name : 'pagePuclicLink',      //noslz
									type : 'string'      //noslz
								}
								
								
						]),
						
				sortInfo : {
					field : 	'sid',      //noslz
					direction : 'ASC'      //noslz
				}
				
			});
			
		
			
	

			
	var sm = new Ext.grid.CheckboxSelectionModel(  { singleSelect:true} ); 
		
	var cm = new Ext.grid.ColumnModel([
					
					sm, 
					{
						header : '#',      //noslz
						readOnly : true,
						dataIndex : 'sid',      //noslz
						//hidden:true,
						width : 50
					}, 
					
					{
						header : '  ',
						dataIndex : 'pagePublicLink',      //noslz
						renderer: renderPublic,
						width : 30
					},
					{
						header : 'Όνομα σελίδας:',
						dataIndex : 'pageName',      //noslz
						//hidden:false,
						//renderer: renderWebsite,
						width : 500
					}
					
					
					
					]);
	// cm.defaultSortable = true;

					
					
	var MemopagesGrid = new Ext.grid.GridPanel({
				//title : 'Games',
				store : MemopagesDataStore,
				cm : cm,
				enableHdMenu: false,
				width : 455,
				height : 210,
				border:false, 
 			    loadMask: true,
 			    sm : sm 
				
			});//grid 
	
		
	//	MemopagesGrid.getEl().mask();
	// MemopagesGrid.store.reload({callback:function(){alert('test');  MemopagesGrid.getEl().unmask();   }});			
			
			
	memopagesGridWin = new Ext.Window({
		
				id:"mpageGridWinId",
			
				title : " Οι Σελίδες  μου ",
				width : 800,
				height : 550,
				layout : "fit",      //noslz
				y:60,
				x:10,
			//	modal : true,
				
				resizable : true,
				maximizable : false,
				
				maximized:false, 
				closable : false,
				
				items:[  MemopagesGrid ], 
					
				tbar :[
						
					
		{
				text : 'κλείσιμο',
				iconCls : 'icon-cancel',      //noslz
				iconAlign: 'top',
				// scale : 'medium',      //noslz
				handler : function() {

					memopagesGridWinOpenFlag = false; 
					memopagesGridWin.close();
					
				}
		},
					
					
	{ text:  "Δημιουργία" ,
	iconAlign: 'top',
	iconCls:'icon-plus',      //noslz
    	handler : function(){
    	  
    		newPage()
    		
    	}// handler
	},
	
	
	
	{ text:  "Αντιγραφή " ,
	iconAlign: 'top',
	iconCls:'icon-copy',      //noslz
    	handler : function(){

    		// ajax to get the memopage Data 
    		var rec = MemopagesGrid.getSelectionModel().getSelected();
    		
    		if ( 
    			typeof(rec) !== 'undefined' && 
    			null !=  rec.get('sid') )
    		{
    			clonePage( rec.get('sid') );
    		}
    		
    		
    	}// handler
	},
	
	
	
	
	{ text:  "Επεξεργασία" ,
	iconAlign: 'top',
	iconCls:'icon-edit',      //noslz
    	handler : function(){

    		// ajax to get the memopage Data 
    		var rec = MemopagesGrid.getSelectionModel().getSelected();
    		
    		if ( 
    			typeof(rec) !== 'undefined' && 
    			null !=  rec.get('sid') )
    		{
    			editPage( rec.get('sid') );
    		}
    		
    		
    	}// handler
	},
	{ text:  "Διαγραφή " ,
	iconAlign: 'top',
	iconCls:'icon-minus',      //noslz
    	handler : function(){
    		
    		// ajax to get the memopage Data 
    		var rec = MemopagesGrid.getSelectionModel().getSelected();
    		
    		if ( 
    			typeof(rec) !== 'undefined' && 
    			null !=  rec.get('sid') )
    		{
    			delPage( rec.get('sid') );
    		}
    		
    	}// handler
	} 
	
	
	
	
	/*

	{
			// iconAlign: 'top',
			text :  'Κοινοποίηση',
			iconAlign: 'top',
			tooltip :  'Κοινοποίηση',
			//iconCls : 'icon-key',
			icon:'../css/img/share_save_small.png',         //noslz
			handler : function() {
						
				 
					// GET   selected sid ID 
				// 
				
				
				var rec = MemopagesGrid.getSelectionModel().getSelected();
    		
    			if ( 
    			typeof(rec) !== 'undefined' && 
    			null !=  rec.get('sid') )
    			{
    			
    	
					var linkname =  " η νεα μου σελίδα : "  + rec.get('pageName')    ; 
					var linkurl  = DirectUrl+"?sid=" +  rec.get('sid') ; 
						
					
					var shareHtml = 		
					' <br><center> ' +
					'  ' + linkurl +
					' <br> Για Κοινοποίηση <b> ' + rec.get('pageName')  + '   ' + '</b> κανε κλικ εδω:  ' +  
					' <a class="colorlink" target="_blank"   ' +		        //noslz
					' href="http://www.addtoany.com/share_save?linkname='+ linkname+'&amp;linkurl='+ linkurl+'">' +         //noslz
					' <img src="../css/img/share_save.png" border="0" alt="Share/Save/Bookmark"/></a> </center>' +           //noslz
					' <script type="text/javascript">a2a_linkname="'+ linkname+'";a2a_linkurl="'+ linkurl+'";</script>';           //noslz
	
				
					var shareWin = new Ext.Window ({
						  height: 140, 
						  width: 700, 
						  y:30,
						  resizable : false,
						  maximizable : false,
						  modal:true,
						  title:  'Κοινοποίηση', 
						  
						  tbar: [
						  	{
									text : 'κλείσιμο',
									iconCls : 'icon-cancel',      //noslz
									iconAlign: 'top',
									// scale : 'medium',      //noslz
									handler : function() {
					
										shareWin.close();
										
									}
							}
						  ],
						  html: shareHtml  
					});
					
					shareWin.show();
				
					
					

					
					
					
					
					
    			}
			}
		}, 
						
	
	
	
	
	
	{ 
		text:  "Βοήθεια" ,
		iconAlign: 'top',
		iconCls:'icon-help',      //noslz
    	handler : function(){
    	  
    		alert ("todo add help of page")
			    		
    	}// handler
	}		
				
		*/
	
	
	]
				
				
				
	});

	
	
	memopagesGridWin.on('beforeshow', function(obj ){      //noslz
	
			Ext.getCmp('mpageGridWinId').getEl().mask();
			
					
				MemopagesDataStore.load({
					params : {
						action:'loadMyMemopages'      //noslz
					},
					
					callback  :	 function(rows, opt, loadFlag  ){   
					
						
							if (loadFlag ){		
								 
								 // fill any data if needed 
								
								
								 Ext.getCmp('mpageGridWinId').getEl().unmask();
							
							} 
							else {
							
								alert ("Internet communication error check your internet conection or Server is down"); 	
							}
					
					}
				
			});// MemopagesDataStore.load({

		
		
	});// memopagesGridWin.on('beforeshow', 
	
	
	
	
	// show it
	memopagesGridWin.show();
	
	
	
     
     
     
     
     
     
     
     
     
     
     
     
	
	
	
	MemopagesGrid.on('rowdblclick', function(grid, rowIndex, e){      //noslz
		

		// ajax to get the memopage Data 
    		var rec = MemopagesGrid.getSelectionModel().getSelected();
    		
    		if ( 
    			typeof(rec) !== 'undefined' && 
    			null !=  rec.get('sid') )
    		{
    			editPage( rec.get('sid') );
    		}
		
	});//rowclick
	
	
	
} // if ( !memopagesGridWinOpenFlag ) {		


}