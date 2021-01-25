
var  changePasswordWin; 



var txtopenChangePasswordWin = {

	newPass : '<b> Νέος Κωδικός </b>',
	title : 'Αλλαγή Κωδικού',
	reNewPass : '<b> Ξανα ο Νέος Κωδικός  </b>-',
	save : 'Αποθήκευση',
	passChanged : 'αποτέλεσμα',
	passChangedMsg : 'OK νέος κωδικός πρόσβασης είναι ενεργή ',
	passNotChanged : 'αποτέλεσμα',
	passNotChangedMsg :'Οι κωδικοί πρόσβασης δεν ταιριάζουν',		
	close : 'κλείσιμο'
}



function openChangePasswordWin(rowObj) {



//-----   panel   
	 var changePassowdFormPanel = new Ext.form.FormPanel({
		id : 'changePassowdFormPanel',            //noslz
		labelAlign : 'top',            //noslz
		bodyStyle : 'padding:5px',            //noslz
		layout : 'fit',            //noslz
		items : [
		{

			xtype : 'panel',            //noslz
			deferredRender : false,            //noslz
			defaults : {
				bodyStyle : 'padding:10px'            //noslz
			},
			items : [

			 {
				layout : 'form',            //noslz
				
				items : [
				
						{
							id : 'pass1',            //noslz
							name : 'pass1',            //noslz
							xtype : 'textfield',            //noslz
							inputType :'password',            //noslz
							width : 300,
							fieldLabel : txtopenChangePasswordWin.newPass,
							anchor : '100%'            //noslz
						},
						{
							id : 'pass2',            //noslz
							name : 'pass2',            //noslz
							xtype : 'textfield',            //noslz
							inputType :'password',            //noslz
							width : 300,
							fieldLabel : txtopenChangePasswordWin.reNewPass,
							anchor : '100%'            //noslz
						}

				]
			}		// form 
			
			]
		}		// tabpanel
		
		
		
		]
	});

	 changePasswordWin = new Ext.Window({
		title : txtopenChangePasswordWin.title,
		
		width : 340,
		height: 200,
		
		x:  150,
		y:  190,
		
		
		
		layout : "fit",            //noslz
		modal : true,
		resizable : true,
		maximizable : false,
		closable : false,
		
		items : [changePassowdFormPanel],
		buttons : [
		{
			text : txtopenChangePasswordWin.save,
			iconCls : 'icon-accept',            //noslz
			scale : 'medium',            //noslz
			handler : function() {
				
					
				//TODO   client side validation 
				
				var pass1 = Ext.getCmp('pass1').getValue() ;            //noslz
				var pass2 = Ext.getCmp('pass2').getValue() ;            //noslz
				
				
				if (pass1==pass2 ) {
				
					Ext.Ajax.request({
									method : "POST",            //noslz
									url : UserServlet,
									params : {
										  action : 'updateUserPassword',            //noslz
										  userId : rowObj.sid,			  
										  pass1: pass1,
										  pass2: pass2
									},
									scope : this,
									success : function(res) {
										
										Ext.Msg.show({
											title : txtopenChangePasswordWin.passChanged,
											buttons : Ext.MessageBox.OK, 
											
												msg : txtopenChangePasswordWin.passChangedMsg												
											});
									 	
									   changePasswordWin.close();
										
									}//success
									
									
									//TODO  failure message 
									
								});//Ext.Ajax.request
				
				
				}// if 
				else {
					
					Ext.Msg.show({
						
						x:  190,
						y:  230,
						
						title : txtopenChangePasswordWin.passNotChanged,
						buttons : Ext.MessageBox.OK, 
							msg : txtopenChangePasswordWin.passNotChangedMsg 												
					});
					
					
				}
				
				
				
				
			}
		},
		{
			text : txtopenChangePasswordWin.close,
			iconCls : 'icon-cancel',            //noslz
			scale : 'medium',            //noslz
			handler : function() {

				changePasswordWin.close();
			}
		}

		]
	});

	
	// show it
	changePasswordWin.show();
	
	
	
	
}// openMyInfoWindow

