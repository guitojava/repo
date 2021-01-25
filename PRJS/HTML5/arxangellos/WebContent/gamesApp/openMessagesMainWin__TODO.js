
var msgsMainWin;
var msgsMainWinOpenFlag = false;


function  openMessagesMainWin(){
	
	
	
	if ( !msgsMainWinOpenFlag ) {			
	

	msgsMainWinOpenFlag  = true;
	

	function renderBold(value, p, record) {
		
		return '<b>'+ value +'</b>';      //noslz
	}
	
    
	
	 var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<hr> <div  style="background: 	#F8F8F8  ">  ',      //noslz
           
     /*       '&nbsp;&nbsp;&nbsp;<b>' +      //noslz
            'From:   ' +
            '{from}</b><br> ',      //noslz
        	'&nbsp;&nbsp;&nbsp;<b>' +      //noslz
        	'Subject:  ' +
        	' {subject}</b><br> ',      //noslz
        	'&nbsp;&nbsp;&nbsp;<b>' +      //noslz
        	'Date: ' +
        	'  {receivedDate}</b><br> ',      //noslz
            '&nbsp;&nbsp;&nbsp;<b>' +      //noslz
          */
            
            ' <img src="../css/img/silk/icons/user_edit.png"> ' +
            ' <br>',      //noslz
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  {msg}  <br>  </div> <br> '      //noslz
        )
    });


	
	var MsgsDataStore = new Ext.data.Store({
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
									name : 'subject',      //noslz
									type : 'string'      //noslz
								},	
								
								
								{
									name : 'from',      //noslz
									type : 'string'      //noslz
								},						
								{
									name : 'to',      //noslz
									type : 'string'      //noslz
								},{
									name : 'receivedDate',      //noslz
									type : 'string'      //noslz
								},{
									name : 'msg',      //noslz
									type : 'string'      //noslz
								},{
									name : 'msgType',      //noslz
									type : 'string'      //noslz
								},{
									name : 'msgFlag',      //noslz
									type : 'string'      //noslz
								},
								{
									name : 'actCode',      //noslz
									type : 'string'      //noslz
								},
								{
									name : 'readFlag',      //noslz
									type : 'string'      //noslz
								}
								
						])
						
				/*		
				sortInfo : {
					field : 'user.sid',
					direction : 'ASC'
				}*/
						
				
			});
			
			
	MsgsDataStore.load({
				params : {
					start : 0,
					limit : gridPageSize,
					action:'loadAllMyMessages'      //noslz
				}
			}); // limit display in grid
			
			
	var cm = new Ext.grid.ColumnModel([
					
					expander, 
					
					
					{
						header : '#',      //noslz
						readOnly : true,
						dataIndex : 'sid',      //noslz      //noslz
						hidden:true,
						width : 40
						//,
						//	renderer: renderPlayersTooltip

					},
					{
						header : 'Ημ/νία παραλαβής',
						dataIndex : 'receivedDate',      //noslz
						//hidden:true,
						renderer: renderBold,
						width : 150
					}
					, 
					
					{
						header : 'Από',
						dataIndex : 'from',      //noslz
						
						renderer: renderBold,
						width : 100
					},
					
					{
						header : 'Προς',
						dataIndex : 'to',      //noslz
						hidden:true,
						width : 100
					},
					
					{
						header : 'Θέμα ',
						dataIndex : 'subject',      //noslz
						id:'subject',       //noslz
						renderer: renderBold,
						width : 200
					},
					
					
					{
						header : 'Το μήνυμα',
						dataIndex : 'msg',      //noslz
						id:'msg',      //noslz
						hidden:true,
						//renderer: renderPlayersTooltip,
						width : 350
					},
					
					{
						header : 'Τύπος',
						dataIndex : 'msgType',      //noslz
						hidden:true,
						//renderer: renderPlayersTooltip,
						width : 60
					},
					{
						header : 'Flag',
						dataIndex : 'msgFlag',      //noslz
						hidden:true,
						//renderer: renderPlayersTooltip,
						width : 60
					},
					
					{
						header : 'ActionCode',
						dataIndex : 'actCode',      //noslz
						hidden:true,
						//renderer: renderPlayersTooltip,
						width : 60
					},{
						header : 'ReadFlag',
						dataIndex : 'readFlag',      //noslz
						hidden:true,
						//renderer: renderPlayersTooltip,
						width : 60
					}
					
					
					
					]);
	// cm.defaultSortable = true;

		
					
					
					
	var MsgsGrid = new Ext.grid.GridPanel({
				store : MsgsDataStore,
				cm : cm,
				width : 455,
				height : 210,
				border:false, 
 			    loadMask: true,
 			   
  				plugins: expander,
 			    
  		        autoExpandColumn: 'subject',
  		        frame:true, 
  		        
        		collapsible: true,
	        	animCollapse: false,

 			    /* to make row expander text selectable INBOX */
	        	callback : function(){
				    var elems=Ext.DomQuery.select("div[unselectable=on]", this.dom); //noslz
				    for(var i=0, len=elems.length; i<len; i++){
				        elems[i].unselectable = "off";//noslz
				    }
				},
				scope : this,
	        	/* to make row expander text selectable INBOX */
				
 			    // sm : new Ext.grid.RowSelectionModel( {	singleSelect : false  		}),
				//sm : new Ext.grid.RowSelectionModel( ),
					
					
				tbar:[
						
						{
							text : 'Ανανέωση',
								iconCls : 'icon-refresh',      //noslz
								xtype : 'button',      //noslz
								hieght:20,
							handler : function() {
								
								
								MsgsDataStore.load({
											params : {	
												start : 0,
												limit : gridPageSize,
												action:'loadAllMyMessages'      //noslz
													
											}
								}); // limit display in grid
										
			
							}// handler
						},		
				
				
				 		{
							text : 	"Αποστολή " ,
							iconCls : 	'icon-group',        //noslz
							//scale : 	'large',
							handler : function() {
 								
						
								openFriendsMainWin();
										
							}
						},
				
						
						
						
						
						
						{
							text : 'Απάντηση',
								iconCls : 'icon-email',      //noslz
								xtype : 'button',      //noslz
								hieght:20,
								handler : function() {
								
	
					        		var rec = MsgsGrid.getSelectionModel().getSelected() ; 	
									
				        			if (   rec != null ){
				        				
				        				
				        				// alert ( "reply to message with msgSid = " + rec.get( "sid" ) );
				        				
					        				
					        			Ext.Ajax.request({
											method : 'POST',  //noslz
											url : GamesServlet,
											params : {
												action : 'getUserInfo'   //noslz
											},
											scope : this,
											success : function(res) {
												
												 var obj = Ext.util.JSON.decode(res.responseText);
												 
												 //alert(  res.responseText   );
							openReplyMessageWin(  obj, rec.get( "sid" ), //noslz
												 rec.get( "subject" ) ,  //noslz
												 "<br><br><hr>" //noslz
 												 + "<br><small>(" + rec.get( "receivedDate" )+ ")</small> " + //noslz
 											"-<b>Από:</b>" 
                              + rec.get( "from" )+ " " + //noslz
 											"-<b> Θέμα: </b>"
                              + rec.get( "subject" )  //noslz 
											 + "<br><br> " +   rec.get( "msg" )  ); 	//noslz
												 
												 
											}//success
										});//Ext.Ajax.request
											
				        		
				        			}
				        			else {
										Ext.MessageBox.alert( 'Τίποτα επιλεγμένο', 
													'Επιλέξτε ένα μήνυμα και προσπαθήστε ξανά '  );
									}
										
			
							}// handler
						},	
						
						
						
						{
							text : 'Διαγράψτε όλα τα μηνύματα  ',
								iconCls : 'icon-minus',      //noslz
								xtype : 'button',      //noslz
								hieght:20,
							handler : function() {
								
								
								
							Ext.Msg.show({
								title : 'Διαγράψτε όλα τα μηνύματα  ',
								buttons : Ext.MessageBox.YESNO,
								msg : 'Διαγράψτε όλα τα μηνύματα?',
								fn : function(btn) {
									
									if (btn == 'yes') {      //noslz
									
										
											Ext.Ajax.request({
												method : "POST",      //noslz
												url : GamesServlet ,      //noslz
												params : {
													action : 'delAllMyMessages'		},      //noslz 
												scope : this,
												success : function(res) {

													//var obj = Ext.util.JSON.decode(res.responseText);
													//alert(res.responseText);
													
														MsgsDataStore.load({
															params : {
																start : 0,
																limit : gridPageSize,
																action:'loadAllMyMessages'      //noslz
															}
														}); // limit display in grid
														
											

												}// success
											});// Ext.Ajax.request
										
											
										
									}
								  }
								});
									
												
												
			
							}// handler
						},
				
						
						{
							text : 'κλείσιμο',
							iconCls : 'icon-cancel',      //noslz
							//scale : 'medium',      //noslz
							handler : function() {

								msgsMainWinOpenFlag = false; 
								msgsMainWin.close();
								
							}
						}
			
					
					
				]
				
				
				
				
				
			});//grid game
	
// TODO only allow one msgsMainWin open and NOT Modal  

	
			
	msgsMainWin = new Ext.Window({
				title : "Τα μηνύματα",
				width : 800,
				height : 450,
				layout : "fit",      //noslz
				x:0, 
				y:35,
				
				modal : false,
				maximized: true, 
				maximizable : false,
				closable : false,
				
				items:[  MsgsGrid ]
					
				
				
				
			});

	// show it
	msgsMainWin.show();
	
	
	
	
	Ext.Ajax.request({
	method : "POST",      //noslz
		url : GamesServlet,
		params : {
			action : 'getUserInfo'      //noslz
		},
		scope : this,
		success : function(res) {
			 	
			 	var obj = Ext.util.JSON.decode(res.responseText);
				 // Player and gameOrganiser 
				if (  obj.roleType == "PLAYER"  )      //noslz
				{	
				}
				// field owner
				if (  obj.roleType == "FIELD" )      //noslz
				{	
					
					
				} 
	}//success
	});//Ext.Ajax.request

	
	
	
	MsgsGrid.on('rowclick', function(grid, rowIndex, e){      //noslz
		var rec = MsgsGrid.getSelectionModel().getSelected() ;
		if(typeof rowIndex == 'number'){      //noslz
			expander.toggleRow(  rowIndex ); 
		} 
 		
	});//rowclick
	
	
	
} // if ( !msgsMainWinOpenFlag ) {		


}