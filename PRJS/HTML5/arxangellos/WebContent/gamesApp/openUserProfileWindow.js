
var  userProfileWindow; 





var txtOpenUserProfileWindow = {

	changePwEmailTel : 'Αλλαγή κωδικού πρόσβασης ή Τελ # ή Εμαιλ',

	//changePw : 'Αλλαγή κωδικού πρόσβασης',
	//changeEmail : 'Αλλαγή Email',
	//changeTel : 'Αλλαγή τηλέφωνο',
	
	changePw : '',
	changeEmail : '',
	changeTel : '',
	
	
	name : '<b>Ονομα μου</b>',
	email : '<b>Εμαιλ </b>',
	tel : '<b> τηλέφωνο #</b>',
	role : '<b>Ρολος </b>',
	joinDate : '<b>Ημερομηνία Έγγραφης </b>',
	
	shareLink :    '<b>  Σύνδεσμο </b>' ,
	shareLinkCal : '<b>  Σύνδεσμο ημερολόγιου  </b>'  ,
	
	close : 'κλείσιμο'
}


function openUserProfileWindow( rowObj ) {



	
//-----   panel   
	 var userProfileFormPanel = new Ext.form.FormPanel({
		
		id : 'userProfileFormPanel', //noslz
		name : 'userProfileFormPanel', //noslz
		// labelAlign : 'top', //noslz
		bodyStyle : 'padding:5px', //noslz
		items : [
						
						
						
						{
							id : 'userName',//noslz
							name : 'userName',//noslz
							xtype : 'textfield',//noslz
							width : 300,
							fieldLabel : txtOpenUserProfileWindow .name,
							readOnly : true,
							anchor : '100%'//noslz
						},
						{
							id : 'userEmail',//noslz
							name : 'userEmail',//noslz
							xtype : 'textfield',//noslz
							width : 300,
							fieldLabel : txtOpenUserProfileWindow . email ,
							readOnly : true,
							anchor : '100%'//noslz
						},
						{
							id : 'userTel',//noslz
							name : 'userTel',//noslz
							xtype : 'textfield',//noslz
							width : 300,
							fieldLabel : txtOpenUserProfileWindow .tel,
							readOnly : true,
							anchor : '100%'//noslz
						},
						/*
						{
							id : 'userRole',//noslz
							name : 'userRole',//noslz
							xtype : 'textfield',//noslz
							width : 300,
							fieldLabel : txtOpenUserProfileWindow .role,
							readOnly : true,
							anchor : '100%'//noslz
						},
						*/
						{
							id : 'userJoinDate',//noslz
							name : 'userJoinDate',//noslz
							xtype : 'textfield',//noslz
							width : 300,
							fieldLabel : txtOpenUserProfileWindow .joinDate,
							readOnly : true,
							anchor : '100%'//noslz
						},
						
						
						{
							id : 'userProfileImage',//noslz
							name : 'userProfileImage',//noslz
							xtype : 'box',//noslz
							width : 300,
							html:""
						}

		
		
		]
		,
			tbar:  [
					
						{
							text : txtOpenUserProfileWindow.changePw ,
							iconCls : 'icon-key',//noslz
							scale : 'medium',//noslz
							handler : function() {
								
									openChangePasswordWin( rowObj ); 
								
							}// handler
						}

						, new Ext.Toolbar.Separator(),
						{
							text : txtOpenUserProfileWindow.changeEmail,
							iconCls : 'icon-email',//noslz
							scale : 'medium',//noslz
							handler : function() {
									
									
									openChangeUserEmailWin( rowObj ); 
									
									
							}// handler
						}
						
						, new Ext.Toolbar.Separator(),
						{
							text : txtOpenUserProfileWindow.changeTel,
							iconCls : 'icon-tel',//noslz
							scale : 'medium',//noslz
							handler : function() {
									
									
									openChangeUserTelWin( rowObj ); 
									
									
							}// handler
						}
						
						, new Ext.Toolbar.Separator(),
						{
						//text : ' επιλογη εικονας προφιλ   ',
						//iconAlign: 'top',
						iconCls : 'icon-camera',           //noslz
						handler : function() {
							
	openPickImageWindow( ""+rowObj.sid , "saveProfileImage" );      //noslz
			
						}// handler
					}	
					
						
						
						
				
				]
				
		
		
	});

	 userProfileWindow = new Ext.Window({
		width : 500,
		height: 580,
		
		x:  100,
		y:  70,
		
		layout : "fit",//noslz
		modal : true,
		resizable : true,
		maximizable : false,
		closable : true,
		items : [userProfileFormPanel],
		tbar : [
		
		{
			text : txtOpenUserProfileWindow .close,
			iconCls : 'icon-cancel',//noslz
			scale : 'medium',//noslz
			handler : function() {

				userProfileWindow.close();
			}
		}

		]
	});

	
	// show it
	userProfileWindow.show();

	
	function formatDate(value) {
		return value ? value.dateFormat('d/m/y') : '';//noslz
	};
	
	// populate init data 
	Ext.getCmp('userName').setRawValue(rowObj.uname);//noslz
	Ext.getCmp('userEmail').setRawValue(rowObj.email);//noslz
	
	Ext.getCmp('userTel').setRawValue(rowObj.tel);//noslz
	
	
	var urlLink =  BaseLink + "/searchUser.html?uname=" + rowObj.uname ;
	
	var urlLinkCal =  BaseLink + "/searchCalander.html?uname=" + rowObj.uname ;
	
	
	Ext.getCmp('userProfileImage').update(   
	
	 	
		"<br> <b> Σύνδεσμο  :</b> <br> " +
		"<a target='_blank'  class='colorlink'   href='"+urlLink+"' >  " + urlLink + "  </a>    <br> "      +
	 
			
		"<br> <b> Σύνδεσμο ημερολόγιου : </b> <br>" +	
		" <a target='_blank'  class='colorlink'   href='"+urlLinkCal+"'  >  " + urlLinkCal + "  </a>  <br>  "  +

		
		"<br> <br> <b>H εικόνα προφιλ σου είναι :</b> " +
		"<br> <br> <img  height=60px src='"+rowObj.profileImage+"'  ></img>"  ); 
	
	
//	Ext.getCmp('userRole').setRawValue(rowObj.roleType);//noslz
	//-
	joinDt = new Date(  rowObj.createDt ) ;
	Ext.getCmp('userJoinDate').setRawValue( formatDate(joinDt) );//noslz
	
	
}// openMyInfoWindow

