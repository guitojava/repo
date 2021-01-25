



function openSendMeMyPassword( ) {
	
	var dlg = new Ext.Window({
		
		layout : 'form',//noslz
				
		width : 435,
		height : 160,
		
		title : ' Email τον κωδικο μου' ,
	
		
		x:  60,
		y:  40,
		modal:true,
		closable : false,
		resizable : true,
		plain : true,
		border : false,
		defaultType : 'textfield',//noslz
		
		items : [	
			
						
						{
							xtype:"label",
							html:"<br><br>"
						},
		
		
						{
							xtype:"label",
							html:"   &nbsp;&nbsp;&nbsp;  Γράψε το e-mail σου και θα σου στέλνουμε τον  κωδικο σου "
						},
							
						{
			
							id : 'pval',//noslz
							fieldLabel : '   &nbsp;&nbsp;&nbsp;  <b>Email</b>',
							name : 'pval',//noslz
							width : 300,
							vtype:"email",
							allowBlank : false
						} 
						
			
			]
			
			,
				buttons : [
					
			
			
				{
						text : ' Email τον κωδικο μου',
						iconCls : 'icon-email',      //noslz
						scale : 'medium',      //noslz
						handler : function() {
						
						flagVal = "email";		
						var reason = validateEmail( Ext.getCmp('pval').getValue()); 
						
						
					//	alert (  Ext.getCmp('pval').getValue() ); 
						
						
						
						if (  flagVal == "email" && reason != "" ) {// noslz
						
							Ext.Msg.alert("Σφάλμα ", "<b> προσπαθήστε ξανά:<b><br>" 	+ reason);
							
						} 
						else if (  flagVal == "uname" && Ext.getCmp('pval').getValue() == "" ) {// noslz
						
							Ext.Msg.alert("Σφάλμα ", "<b> προσπαθήστε ξανά:<b><br>" 	);
							
						} 
						
						else {
							
							
							Ext.Ajax.request({ // noslz
								method : "POST", // noslz
								url : PublicServlet,
								params : {
									action : 'sendMeMyPassword', // noslz
									pval : Ext.getCmp('pval').getValue(), // noslz
									flagCmbx : flagVal 	// noslz

								},
								scope : this,
								success : function(res) {

									dlg.close();
									Ext.Msg.show({
												title : 'OK δες το email σου ',
												buttons : Ext.MessageBox.OK,
												msg : 'OK δες το email σου.  Κωδικος εστάλει.'
											});

								}// success
							});// Ext.Ajax.request

						}
								
								
								
								
								
								
								

						}// handler
					}		
					
					,
			
					{
						text : 'ακύρωση',
						iconCls : 'icon-cancel',      //noslz
						scale : 'medium',      //noslz
						handler : function() {

							dlg.close();
							

						}// handler
					}		
			
			]
			
		
	});

	dlg.show();
	

}