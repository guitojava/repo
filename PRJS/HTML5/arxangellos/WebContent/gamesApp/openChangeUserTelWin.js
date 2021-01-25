








var txtopenChangeUserTelWin = {
	title : 'Αλλαγή τηλέφωνου',
	oldTel : '<b> Προηγούμενο τηλέφωνο </b>',
	newTel : '<b> Νέο τηλέφωνο #</b>',
	saveNewTel : 'Αποθήκευση',
	telChanged : 'αποτέλεσμα ',
	telChangedMsg : 'Το τηλέφωνο σας έχει αλλάξει ',

	close : 'κλείσιμο'
}





function openChangeUserTelWin(rowObj) {



//-----   panel   
	 var changeUserTelFormPanel = new Ext.form.FormPanel({
		id : 'changeUserTelFormPanel',            //noslz
		name:'changeUserTelFormPanel',            //noslz
		labelAlign : 'top',            //noslz
		bodyStyle : 'padding:5px',            //noslz
		layout : 'fit',            //noslz
		items : [
		{

			xtype : 'panel',            //noslz
			//plain : true,
			deferredRender : false,
		
			border :false, 	
			//layoutOnTabChange : true,
			defaults : {
				bodyStyle : 'padding:10px'            //noslz
			},
			
			items : [

			 {
				// title : txtopenChangeUserTelWin.title,
				layout : 'form',            //noslz
				
				items : [
				
						{
							id : 'oldTel',            //noslz
							name : 'oldTel',            //noslz
							xtype : 'textfield',            //noslz
							readOnly : true,
							width : 150,
							fieldLabel : txtopenChangeUserTelWin.oldTel,
							anchor : '100%'            //noslz
						},
						{
							id : 'newTel',            //noslz
							name : 'newTel',            //noslz
							xtype : 'textfield',            //noslz
							width : 150,
							fieldLabel : txtopenChangeUserTelWin.newTel,
							anchor : '100%'            //noslz
						}

				]
			}		// form 
			
			]
		}		// tabpanel
		
		
		
		]
	});

	 changeUserTelWin = new Ext.Window({
		title : txtopenChangeUserTelWin.title,
		
		width : 340,
		height: 200,
		
		x:  150,
		y:  190,
		
		
		layout : "fit",              //noslz
		modal : true,
		resizable : true,
		maximizable : false,
		closable : false,
		items : [changeUserTelFormPanel],
		
		buttons : [
		{
			text : txtopenChangeUserTelWin.saveNewTel,
			iconCls : 'icon-accept',            //noslz
			scale : 'medium',            //noslz
			handler : function() {
				
				var newVal = Ext.getCmp('newTel').getValue();             //noslz
				
				// TODO 
				// 1) validate Tel 
				
				// 2) save new Tel to DB
				
				
				
								Ext.Ajax.request({
									method : "POST",            //noslz
									url : UserServlet,
									params : {
										  action : 'updateUserTel',            //noslz
										  userId : rowObj.sid,			  
										  tel: newVal
										  
									},
									scope : this,
									success : function(res) {
										
										// alert ("return from updateUserTel");
										
										
										// update rowObj and refresh GUI
										rowObj.tel = newVal;			
										
										
										Ext.Msg.show({
												title : txtopenChangeUserTelWin.telChanged,
											buttons : Ext.MessageBox.OK, 
												msg : txtopenChangeUserTelWin.telChangedMsg + newVal  ,
												fn : function(btn) {
													
													changeUserTelWin.close();
													userProfileWindow.close();
													// reload  the user window 
													openUserProfileWindow(rowObj); 
												}
											});
										
										
									 	
									
										
									}//success
									
									
									//TODO  failure message 
									
								});//Ext.Ajax.request
				
				
				
				
			}
		},
		{
			text : txtopenChangeUserTelWin.close,
			iconCls : 'icon-cancel',            //noslz
			scale : 'medium',            //noslz
			handler : function() {

				changeUserTelWin.close();
			}
		}

		]
	});

	
	// show it
	changeUserTelWin.show();
	
	Ext.getCmp('oldTel').setRawValue(rowObj.tel);            //noslz
	
	
}// openMyInfoWindow

