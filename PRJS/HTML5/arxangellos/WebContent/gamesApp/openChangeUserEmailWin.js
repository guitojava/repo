



var txtopenChangeUserEmailWin = {
	
	oldEmail:'<b> Προηγούμενο Email</b>',
	newEmail:'<b> Νέο Email</b>',
	title:'Αλλαγή Email' ,
	save:'Αποθήκευση',
	emailChanged:'αποτέλεσμα',
	emailChangedMsg:'Εντάξει, άλλαξε το email σας ',
	emailChangedMsgKO:'Το email δεν άλλαξε, γιατι ήδη  υπάρχει  στο σύστημα  ',
	close : 'κλείσιμο'
}



function openChangeUserEmailWin(rowObj) {



//-----   panel   
	 var changeUserEmailFormPanel = new Ext.form.FormPanel({
		id : 'changeUserEmailFormPanel',            //noslz
		name:'changeUserEmailFormPanel',            //noslz
		labelAlign : 'top',            //noslz
		bodyStyle : 'padding:5px',            //noslz
		layout : 'fit',            //noslz
		items : [
		{

			xtype : 'panel',            //noslz
			deferredRender : false,
				
			defaults : {
				bodyStyle : 'padding:10px'            //noslz
			},
			items : [

			 {
				layout : 'form',            //noslz
				
				items : [
				
						{
							id : 'oldEmail',            //noslz
							name : 'oldEmail',            //noslz
							xtype : 'textfield',            //noslz
							readOnly : true,
							width : 300,
							fieldLabel : txtopenChangeUserEmailWin.oldEmail,
							anchor : '100%'            //noslz
						},
						{
							id : 'newEmail',            //noslz
							name : 'newEmail',            //noslz
							xtype : 'textfield',            //noslz
							width : 300,
							fieldLabel : txtopenChangeUserEmailWin.newEmail,
							anchor : '100%'            //noslz
						}

				]
			}		// form 
			
			]
		}		// tabpanel
		
		
		
		]
	});

	 changeUserEmailWin = new Ext.Window({
		title : txtopenChangeUserEmailWin.title,
		
		width : 340,
		height: 200,
		
		x:  150,
		y:  190,
		layout : "fit",            //noslz
		modal : true,
		resizable : true,
		maximizable : false,
		closable : false,
		
		items : [changeUserEmailFormPanel],
		buttons : [
		{
			text : txtopenChangeUserEmailWin.save,
			iconCls : 'icon-accept',            //noslz
			scale : 'medium',            //noslz
			handler : function() {
				
				var newVal = Ext.getCmp('newEmail').getValue();            //noslz
				
				// TODO 
				// 1) validate email 
				
				// 2) save new email to DB
				
				
				
								Ext.Ajax.request({
									method : "POST",            //noslz
									url : UserServlet,
									params : {
										  action : 'updateUserEmail',            //noslz
										  userId : rowObj.sid,			  
										  email: newVal
										  
									},
									scope : this,
									success : function(res) {
										
										
										var obj = Ext.util.JSON.decode(res.responseText);
										
										//alert ("return from updateUserEmail: " + res.responseText);
										
										if ( obj.success  ) {
										
										
										// update rowObj and refresh GUI
										rowObj.email = newVal;			
										
										
										Ext.Msg.show({
												title : txtopenChangeUserEmailWin.emailChanged,
											buttons : Ext.MessageBox.OK, 
												msg :  txtopenChangeUserEmailWin.emailChangedMsg + newVal
												,
												fn : function(btn) {
													
													changeUserEmailWin.close();
													userProfileWindow.close();
													// reload  the user window 
													openUserProfileWindow(rowObj); 
												}
											});
										
											
										} 
										else {
											
												Ext.Msg.show({
												title : txtopenChangeUserEmailWin.emailChanged,
											buttons : Ext.MessageBox.ERROR, 
												msg :  txtopenChangeUserEmailWin.emailChangedMsgKO + newVal,
												fn : function(btn) {
												}
											});
													
										}
										
									
										
									}//success
									
									
									//TODO  failure message 
									
								});//Ext.Ajax.request
				
				
				
				
			}
		},
		{
			text : txtopenChangeUserEmailWin.close,
			iconCls : 'icon-cancel',            //noslz
			scale : 'medium',            //noslz
			handler : function() {

				changeUserEmailWin.close();
			}
		}

		]
	});

	
	// show it
	changeUserEmailWin.show();
	
	Ext.getCmp('oldEmail').setRawValue(rowObj.email);            //noslz
	
	
}// openMyInfoWindow

