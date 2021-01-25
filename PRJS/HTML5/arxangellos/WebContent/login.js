
var mapModeFlag ='PLAYER';//noslz
var redirect = 'login.html';//noslz
var redirectOK = 'gamesApp/main.html';//noslz
var signupwin ;

function validateUsername(fld) {
    var error = ""; //noslz
    var illegalChars = /[\W_]/; // allow only letters and numbers
 
    if (fld == "" ) {//noslz
        error = "Δεν έχετε καταχωρήσει ένα όνομα χρήστη.<br>";
    } else if ((fld.length < 5) || (fld.length > 20)) {
        error = "Το όνομα χρήστη είναι το λάθος μήκος. Πρέπει να κυμαίνεται από 5 έως 20 χαρακτήρες  <br>";
    } else if (illegalChars.test(fld)) {
		error = "Το όνομα χρήστη περιέχει παράνομους χαρακτήρες . Μόνο γράμματα και αριθμοί επιτρέπονται. <br>";
	}
	return error;
}

function validatePassword(fld) {
	var error = "";//noslz
	var illegalChars = /[\W_]/; // allow only letters and numbers
	if (fld == "") {
		error = "Δεν έχετε καταχωρήσει έναν κωδικό πρόσβασης.<br>";
	} else if ((fld.length < 6 ) || (fld.length > 20)) {
		error = "Ο κωδικός πρόσβασης είναι λάθος μήκος. Πρέπει να κυμαίνεται από 6 έως 20 χαρακτήρες . <br>";
	} else if (illegalChars.test(fld)) {
		error = "Ο κωδικός πρόσβασης περιέχει παράνομους χαρακτήρες. Μόνο γράμματα και αριθμούς παρακαλώ. Και τουλάχιστον έναν αριθμό.<br>";
	} else if (!((fld.search(/(a-z)+/)) && (fld.search(/(0-9)+/)))) {
		error = "Ο κωδικός πρόσβασης πρέπει να περιέχει τουλάχιστον έναν αριθμό.<br>";
	} else {
	}
	return error;
}   
	

function trim(s)
{
  return s.replace(/^\s+|\s+$/, '').replace(/ /g, '');
}

function validateEmail(fld) {
    var error="";//noslz
    var tfld = trim(fld);                        // value of field with whitespace trimmed off
    var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
    var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
   
    if (fld == "") {
        error = "Δεν έχετε καταχωρήσει μια διεύθυνση email.<br>";
    } else if (!emailFilter.test(tfld)) {              //test email for illegal characters
        error = "Παρακαλώ εισάγετε μια έγκυρη διεύθυνση ηλεκτρονικού ταχυδρομείου.<br>";
    } 
    else if ((fld.length < 3) || (fld.length > 35)) {
		error = "Η διεύθυνση email είναι λάθος μήκος. Πρέπει να κυμαίνεται από 3 έως 35 χαρακτήρες. <br>";
	} 
    
    else if (fld.match(illegalChars)) {
        error = "Η διεύθυνση ηλεκτρονικού ταχυδρομείου περιέχει παράνομους χαρακτήρες.<br>";
    } else {
    }
    return error;
}

function validatePhone(fld) {
    var error = "";//noslz
    var stripped = fld.replace(/[\(\)\.\-\ ]/g, '');    

   if (fld == "") { //noslz
       //error = "You didn't enter a phone number.<br>";
    } else if (isNaN(parseInt(stripped))) {
        error = "Ο αριθμός τηλεφώνου να ειναι μόνο αριθμοι .<br>";
    } else if (!(stripped.length == 10)) {
        error = "Ο τηλεφωνικός αριθμός είναι το λάθος μήκος. Πρέπει να είναι ακριβώς 10 χαρακτήρες.<br>";
    }
    return error;
}


function validatePasswordsSame(p1, p2 ) {
    var error = "";//noslz
    if (p1 != p2) {
        error = "Οι κωδικοί πρόσβασης δεν ταιριάζουν. <br>";
    }
    return error;
}


function validateEveryThing(userName, pass1, pass2, email, tel ) {
  
  
  var reason = "";//noslz
  reason += validateUsername( userName );
  reason += validatePassword(pass1);
  reason += validatePassword(pass2);
  reason += validatePasswordsSame(pass1, pass2);
  reason += validateEmail(email);
  reason += validatePhone(tel);     
  if (reason != "") {//noslz
    Ext.Msg.alert("Σφάλμα ", "<b>Ορισμενα στοιχεια χρειαζονται διόρθωση,  προσπαθήστε ξανά:<b><br>"+ reason			);
    return false;
  }

  return true;
}

	
	
function connectToPameMpalaNetwork() {
	Ext.Ajax.request({
				method : "POST",// noslz
				url : UserServlet,

				waitTitle : 'Σύνδεση με pamempala .gr ... ',
				waitMsg : ' αποστολή δεδομένων ...',

				params : {
					action : 'login',// noslz
					loginUsername : Ext.getCmp('loginUsername').getValue(),// noslz
					loginPassword : Ext.getCmp('loginPassword').getValue()
					// noslz
				},
				scope : this,
				success : function(res) {

					var okFlag = Ext.util.JSON.decode(res.responseText);

					if (okFlag == false) {

						Ext.Msg.alert('', 'η σύνδεση απέτυχε!');
						window.location = redirect;

					} else if (okFlag == true) {

						Ext.Msg.alert('', '  επιτυχή σύνδεση !!! ');
						window.location = redirectOK;

						// window.open( redirectOK ,null,
						// 'toolbar=no,scrollbars=no,location=no,resizable
						// =yes');

					}

				}

			});// Ext.Ajax.request

}
	
	




Ext.onReady(function() {
	Ext.QuickTips.init();
	
	
	
	var userRoleTypeCmbx = new Ext.form.ComboBox({
		id:'roletype'// noslz
	//	,readOnly : true
	//  ,fieldLabel : '<b>User Role</b>'
		,displayField:'roletype'// noslz
		,mode: 'local'// noslz
		,width: 100
		,editable: false
		,forceSelection: true
		,triggerAction: 'all'// noslz
		,store: [  
		['PLAYER',  // noslz
				'PLAYER'],
		['FIELD',  //noslz
				'FIELD']  ]
		, listeners: {
            select:{fn: function(combo, record, index) {
                
                mapModeFlag = this.getValue();
				var msgText = mapModeFlag ;
				
				/*
				Ext.Msg.alert('Message', 
						msgText,
						function(btn, text) {
							if (btn == 'ok') {
	
							}
				});*/ 
				
				
            }}
        }
	});
	
	//userRoleTypeCmbx.setValue('PLAYER');
	
	
	
	var signup = new Ext.FormPanel({
				labelWidth : 200,
				url : UserServlet, 
				frame : true,
				
				title : 'Γίνετε μέλος',
				defaultType : 'textfield',//noslz
				monitorValid : true,

				items : [
					
						
						{
							xtype:"label",//noslz
							html:'<b> Σημαντική Σημείωση: Χρησιμοποιήστε μόνο αγγλικούς χαρακτήρες. </b><br><br>'
						},
				
				
						{
							xtype:"label",//noslz
							html:'<b>Όνομα χρήστη (*) (5-20 χαρ.)</b>'
						},
				
						{
							id: 'userId',//noslz
						//	fieldLabel : '<b>UserName (*) length(5-30)</b>',
							name : 'userId',//noslz
							width : 200,
							allowBlank : false
						}, 
						
						
						
						{
							xtype:"label",//noslz
							html:'<b>Κωδικός πρόσβασης (*) (6-20 χαρ.)</b>'
						},
				
						
						{
						//	fieldLabel : '<b>Password (*) length(6-20)</b>',
							id:'pword1', //noslz
							name : 'pword1', //noslz
							width : 200,
							//hideLabel : true,
							//itemCls: 'alignCen',  //this label will be styled
							inputType : 'password', //noslz
							allowBlank : false
						}, 
						
						{
							xtype:"label", //noslz
							html: '<b>Εκ νέου κωδικος πρόσβασης (*) (6-20 χαρ.)</b>'
						},
						
						{
						//	fieldLabel : '<b>RePassword (*) length(6-20)</b>',
							name : 'pword2',//noslz
							id : 'pword2',//noslz
								width : 200,
							inputType : 'password', //noslz
							allowBlank : false
						}, 
						
						
						{
							xtype:"label",//noslz
							html: '<b>Έγκυρο ηλεκτρονικό ταχυδρομείο (*) (3-35 χαρ.)</b>'
						},
						
						{
							//fieldLabel : '<b>Valid Email (*) length(3-35)</b>',
							name : 'email',//noslz
								width : 200,
							id : 'email',//noslz
							allowBlank : false
						},
						
						
						{
							xtype:"label",//noslz
							html: '<b>Τηλεφωνο  (10 χαρ.)</b>'
						},
						
						{
						//	fieldLabel : '<b>Telphone length(10)</b>',
							xtype:"textfield",//noslz
							name : 'tel',//noslz
							id : 'tel',//noslz
							width : 200,
							  maxLength: 10,
							allowBlank : true
						},
						
						/*
						{
							xtype:"label",//noslz
							html: '<b>Ρόλος χρήστη(*) </b>'
						},
						
						
						userRoleTypeCmbx
						,*/
						
						{
							xtype:'label'//noslz
							,html: '<br> <br> <b> ' +//noslz
									'&nbsp;&nbsp;&nbsp;Σημαντική Σημείωση: ' +
									' &nbsp;&nbsp;Όλα τα πεδία που σημειώνονται με (*) ΔΕΝ πρέπει να είναι κενα.</b> '
						}
						
						
						],

				// All the magic happens after the user clicks the button
				buttons : [{
					text : 'Δημιουργία λογαριασμου',//noslz
					iconCls : 'icon-accept',//noslz
						scale : 'medium',//noslz
					formBind : true,
					// Function that fires when user clicks the button
					handler : function() {
						
						
						// trim all spaces 
						Ext.getCmp('userId').setValue( trim( Ext.getCmp('userId').getValue() )     );//noslz
						Ext.getCmp('pword1').setValue( trim( Ext.getCmp('pword1').getValue() )   );//noslz
						Ext.getCmp('pword2').setValue( trim( Ext.getCmp('pword2').getValue() ));//noslz
						Ext.getCmp('email').setValue(  trim( 	Ext.getCmp('email').getValue() ));//noslz
						Ext.getCmp('tel').setValue(   trim( Ext.getCmp('tel').getValue()  ) );//noslz

						
						
						if  (  validateEveryThing(
									Ext.getCmp('userId').getValue(), //noslz
									Ext.getCmp('pword1').getValue(),//noslz
									Ext.getCmp('pword2').getValue(), //noslz
									Ext.getCmp('email').getValue(), //noslz
									Ext.getCmp('tel').getValue() )  //noslz
						) {
						
										
								signup.getForm().submit({
									method : 'POST',//noslz
									params : {
										action : 'signup'//noslz
									},
									waitTitle : 'Σύνδεση με pamempala .gr ... ',
									waitMsg : ' αποστολή δεδομένων ...',
		
									success : function(form, action ) {
										
										obj = Ext.util.JSON.decode(action.response.responseText);
										
										//alert ( action.response.responseText ); 
										
						var successMsg 
						=  
						'<b>Μπορείτε να εισέλθετε τώρα, με :- </b> <br>' + 
						'Όνομα χρήστη: <b>' 
						+ Ext.getCmp('userId').getValue() +//noslz 
						'</b> <br> και τον κωδικό πρόσβασής σας.';
						
						
					// 	+ 'Password: <b>'  + Ext.getCmp('pword1').getValue() + '</b> <br>  ';	
										
										
		
										
										Ext.Msg.alert(' επιτυχή εγγραφή !', successMsg ,
												function(btn, text) {
													if (btn == 'ok') {//noslz
														window.location = redirect;
													}
										});
							 					
												
												
									},
									failure : function(form, action) {
										
										
										obj = Ext.util.JSON.decode(action.response.responseText);
										Ext.Msg.alert(' απέτυχε !',	obj.errors.reason);
										
									}
		
								});
						
						} 
						
					}
				},

				{
					text : ' ακύρωση ',
					iconCls : 'icon-cancel',//noslz
						scale : 'medium',//noslz
					handler : function() {
						
						//window.location = redirect;
						
						signupwin.hide();
						
						
					}

				}

				]
			});



			
	signupwin = new Ext.Window({
				layout : 'fit',//noslz
				width : 650,
				height : 370,
				y: 10,
				modal : true,
				closable : false,
				//resizable : false,
				plain : true,
				border : false,
				items : [signup]
			});

	
			
			
			
			
			
	var win = new Ext.Window({
				layout : 'column',//noslz
				
				title :"<img title='MyWebWorld'  src='css/img/silk/icons/world.png'></img>MyWebWorld",
				//'<center> Παμε μπαλα με συμμετοχή ή νεο ματς στα γήπεδα της Ελλάδας    !!!    '
				//+  '<img src="css/img/silk/icons/emoticon_wink.png"></img> <br><br> ' +
				//		'Ναί, Τα καλύτερα πράγματα στη ζωή είναι δωρεάν, και σίγουρα ένα από αυτά είναι και το pamempala.gr ' +
				//		'</center>   ' ,
			
				width : 633,
				height : 147,
				//				x:  50,
				y:  18,
				
				closable : false,	
				resizable : false,
				plain : true,
				border : false,
				
					
				listeners : {
						
						show: {
							fn: function() {
							Ext.getCmp("loginUsername").focus();
							},
							delay: 1000
							}
						
					}, 
				
				items : [
						
				
			
	{
		xtype: "panel",	
		layout : 'form',//noslz
		width : 310,
		
		plain : true,
		border : false,
		
			items: [
			
									{
							xtype:"label",//noslz
							html:"<br>"//noslz
						},
				/*
						{
							xtype : 'label',
							html:'<b>username: </b>'
						},
				*/		
						{
							//fieldLabel :'<span style="font-size: 14px; font-family: Veranda; color:lightgray;" >NAME:</span>',
							//fieldLabel : " &nbsp;&nbsp;<small> ΟΝΟΜΑ</small> <img alt='username' title='ονομα' align='right' src='css/img/silk/icons/user.png'></img>",//noslz
							fieldLabel : " &nbsp;&nbsp;<small> ΟΝΟΜΑ</small>",//noslz
						
							labelWidth: 20, 
							name : 'loginUsername',//noslz
							labelAign: 'top',//noslz
							id : 'loginUsername',//noslz
							xtype:'textfield',
							//hideLabel : true,
							width: 200,
							allowBlank : false
						}, 
						
						/*
						{
							xtype : 'label',
							html:'<b>password: </b>'
						},
						*/
						
						{
							xtype:'textfield',//noslz
							
						//	fieldLabel : '<span style="font-size: 14px; font-family: Veranda; color:lightgray;" >PASSWORD:</span>',
						//	fieldLabel : "&nbsp;&nbsp; <small> ΚΩΔΙΚΟΣ</small> <img alt='password' title='κωδικος' align='right'src='css/img/silk/icons/key.png'>",//noslz
							fieldLabel : "&nbsp;&nbsp; <small> ΚΩΔΙΚΟΣ</small> ",//noslz
						
							labelWidth: 20, 
						
							labelAign: 'top',//noslz
							name : 'loginPassword',//noslz
								id : 'loginPassword',//noslz
							// hideLabel : true,
							inputType : 'password',//noslz
							width: 200,
							allowBlank : false
						},
						
						
							{
							xtype:"label",//noslz
							html:"<br>"//noslz
						}
				
				
				
			
			
			]}
			
			
			
			,
			
						{
		xtype: "panel",	
			layout : 'form',//noslz
			plain : true,
			width : 310,
				border : false,
			items: [
				
					
						{
							xtype:"label",//noslz
							html:'<br>  '//noslz
						},
								{
					text : '<b>Είσοδος</b>',
					xtype:'button',
					formBind : true,
					width : 300,
					hieght: 100,
					iconCls : 'icon-accept',//noslz
				
					// Function that fires when user clicks the button
					handler : function() {
						
						connectToPameMpalaNetwork(); 
					
											
					}
				}
				
				,
						{
							xtype:"label",//noslz
							html:'<br> &nbsp; &nbsp; &nbsp; <a target="_blank" class="colorlink" style=" font-size:12px" href="search.html" > Αναζήτηση σελίδας <img src="./css/img/silk/icons/page.png" > </a> '//noslz
						},
					{
							xtype:"label",//noslz
							html:"<br><br><br><br>"//noslz
						}
				
			
			]}
			
			
			
					]
					
					
				
				
				
				
				
				
				,
				// All the magic happens after the user clicks the button
				buttons : [
					
				
				
				new Ext.Spacer(), 
				new Ext.Spacer(), 
				
				{
					text : '<b>Δημιουργία λογαριασμου </b>',
					iconCls : 'icon-signup',//noslz
					scale : 'medium',//noslz
					handler : function() {
						signupwin.show();
					}
				} ,
						

			 
				new Ext.Spacer(), 
				new Ext.Spacer(), 
				
					{
					text : '<b>Ξεχάσατε τον κωδικό σας</b>',
					iconCls : 'icon-key',//noslz
					scale : 'medium',//noslz
					handler : function() {
						
						openSendMeMyPassword(); 
						
					}
				}
				
			
				]
				
				
			});

	win.show();
	

		
	

		
		
			
	
		
		
		
		
		
		
	
		
		
	
	
	
	
	
var welcomeWin = new Ext.Window({
		title : "  ",
		
		width : 660,
		height : 500,
		
		x:  5,
		y:  5,
				
		layout : "fit",//noslz
		
		//modal : false,
		collapsible: true, 
		closable : true,
		
		border : false,

		//draggable: false,
		resizable : true,
		maximizable : true,
		minimizable : false,
		
		items : [
			
			 new Ext.Panel({
				border : false,
			//	bodyStyle : "backgroundColor:#D3E0F2;",
				width : 350,
				height : 600,
				autoScroll : true, 
			//	closable : false,
			//	collapseMode : 'mini',
			//  deferredRender : false,
			  html : '<iframe src="welcome.html"   width=100%  height=100%  frameborder="0" scrolling="yes" name=" "></iframe> '
			
			 })
				
			
		]
	});

//  	welcomeWin.show();
	
	
	
	var nav1 = new Ext.KeyNav("loginUsername", {
    		"enter" : function(e){
	
			Ext.getCmp('loginPassword').focus(); 
    },
    scope : this
	});	
	
	var nav2 = new Ext.KeyNav("loginPassword", {
    		"enter" : function(e){
	
		// alert ("enter button ")
        
        connectToPameMpalaNetwork(); 
    },
    scope : this
	});	
	
});


