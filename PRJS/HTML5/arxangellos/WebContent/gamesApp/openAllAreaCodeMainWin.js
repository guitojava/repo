

var allAreaCodeWinOpenFlag =false;



var txtopenAllAreaCodeMainWin = {
	
	
	
	areacode:'Περιοχή',
	
	winTitle:'Περιοχή',
	panelHtml: "<br> Διάλεξε μια περιοχή ",
	selAreaCode:'Διάλεξε μια περιοχή ',
	selErrMsgTitle:'Λάθος επιλογή', 
	selErrMsg:'Διάλεξε μια περιοχή μόνο',
				
	close: 'κλείσε'
	
}



function  openAllAreaCodeMainWin( callBackFnc ){
	
	if ( !allAreaCodeWinOpenFlag ) {			

	allAreaCodeWinOpenFlag  = true;
	
	var AreaCodeDataStore = new Ext.data.Store({
				/*
				proxy : new Ext.data.HttpProxy({
						url : PublicServlet+'?action=loadAllAreaCodes', //noslz
						method : 'POST' //noslz
				}),
				*/
		
		 		data: areaCodeDataGlobal,
		 
		        reader : new Ext.data.JsonReader({
							root : 'results',//noslz
							totalProperty : 'total'//noslz
						}, [
							    {
									name : 'id',            //noslz
									type : 'int'            //noslz
								},{
									name : 'description',            //noslz
									type : 'string'            //noslz
								}
								
						])
				
			});
			
			
	// AreaCodeDataStore.load();
	
			
	var sm = new Ext.grid.CheckboxSelectionModel(  { singleSelect:true } );
	/*
	var sm = new Ext.grid.CheckboxSelectionModel({
    singleSelect:false,
    listeners: {
        beforerowselect: function (sm, row_index, keepExisting, record) {            
            sm.suspendEvents();
            if (sm.isSelected(row_index)) {
                // row already selected, deselect it (note: other selections remain intact on deselect).
                sm.deselectRow(row_index);
            } else {
                sm.selectRow(row_index, true);
            }
            sm.resumeEvents();
            return false;
        }
        
    }
    });*/
	
    
    
	var cm = new Ext.grid.ColumnModel([
					sm,
					{
						header : '#',             //noslz
						readOnly : true,
						dataIndex : 'id',            //noslz
						width : 40,
						id:'id',            //noslz
						hidden : true
						//renderer: renderColumn

					}, {
						header : txtopenAllAreaCodeMainWin.areacode,
						dataIndex : 'description',            //noslz
						id : 'description',            //noslz
						width : 300
					}
			 		
					
					]
	);
	// cm.defaultSortable = true;
	
	
				
					
	var AreaCodeGrid = new Ext.grid.GridPanel({
				border:false,
				store : AreaCodeDataStore,
				id:"AreaCodeGrid",            //noslz
				cm : cm,
				deferRowRender:false, 
				sm:sm,			
				width : 350,
				height : 300,
				region:'center',            //noslz
				loadMask: true,
				ctCls:'x-custgrid3-row',            //noslz
				autoExpandColumn: "description",            //noslz
				enableHdMenu: false// remove the colum sorting actions ect 
				
			});//grid game
	
	
	
	areaCodeMainWin = new Ext.Window({
				title : txtopenAllAreaCodeMainWin.winTitle,
				id:'areaCodeMainWin',            //noslz
				width : 400,
				height : 370,
				y:50,
				autoScroll:true, 
				layout:'border',            //noslz
				modal : true,
				resizable : true,
				maximized:false, 
				maximizable : false,
				closable : false,
				items:[
					
						{	
							 region: 'north',            //noslz
   							 height: 40,
							 xtype:'panel',            //noslz
							 html: txtopenAllAreaCodeMainWin.panelHtml
						},
						
						AreaCodeGrid
						
					
				], 
				
				
				
				buttons : [
						
				 	
						
						
						 {
							text : txtopenAllAreaCodeMainWin.selAreaCode,
							iconCls : 'icon-accept',            //noslz
							scale : 'medium',            //noslz
							handler : function() {
							
								var rec = AreaCodeGrid.getSelectionModel().getSelected() ; 	
								
								if ( rec!= null ) { 
									
									
									if ( Ext.getCmp('areaCode') != null  ) {             //noslz
										Ext.getCmp('areaCode').setValue( rec.get("id")  );            //noslz
									} 									
									
									if ( Ext.getCmp('areaCodeDescr') != null  ) {             //noslz
										Ext.getCmp('areaCodeDescr').setValue( rec.get("description")  );            //noslz
									}
									
									allAreaCodeWinOpenFlag = false; 
									areaCodeMainWin.close();
								
								}  else {
										Ext.MessageBox.alert( 
										txtopenAllAreaCodeMainWin.selErrMsgTitle , 
										txtopenAllAreaCodeMainWin.selErrMsg  );
								}
								
								
								
								// run call back function here 
								if (callBackFnc != null ) callBackFnc();
								
								
								
								
							}// handler
						},
				
						{
							text : txtopenAllAreaCodeMainWin.close,
							iconCls : 'icon-cancel',            //noslz
							scale : 'medium',            //noslz
							handler : function() {
								allAreaCodeWinOpenFlag = false; 
								areaCodeMainWin.close();
							}
						}

				]
				
				
				
				
			});

		
						
	// show it
	areaCodeMainWin.show();
	
	
	AreaCodeGrid.on('rowdblclick', function(grid, rowIndex, e){            //noslz
 		// alert ("dbl Click rowIndex="  + rowIndex );
	});//rowclick
	
	
	
	} // allAreaCodeWinOpenFlag
	
	
}







