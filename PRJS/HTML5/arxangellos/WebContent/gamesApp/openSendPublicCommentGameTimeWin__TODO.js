

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
    else if ((tfld.length < 3) || (tfld.length > 35)) {
		error = "Η διεύθυνση email είναι λάθος μήκος. Πρέπει να κυμαίνεται από 3 έως 35 chracters . <br>";
	} 
    
    else if (tfld.match(illegalChars)) {
        error = "Η διεύθυνση ηλεκτρονικού ταχυδρομείου περιέχει παράνομους χαρακτήρες.<br>";
    } else {
    }
    return error;
}

	
function validateName(fld) {
	  var tfld = trim(fld);     
    var error = ""; //noslz
    var illegalChars = /[\W_]/; // allow only letters and numbers
 
    if ( tfld == "" && tfld.length < 3  ) {//noslz
        error = "Δεν έχετε καταχωρήσει ένα όνομα.  (χαρακτήρες > 3) <br>";
    }
	return error;
}
	
		
function validateComment(fld) {
    var error = ""; //noslz
    var illegalChars = /[\W_]/; // allow only letters and numbers
   var tfld = trim(fld);     
    if (tfld == "" && tfld.length < 5  ) {//noslz
        error = "Δεν έχετε καταχωρήσει ένα σχολιο. (χαρακτήρες > 3) <br>";
    }
	return error;
}


function validateEveryThing( name,  email, comment ) {
  
  
  var reason = "";//noslz
  reason += validateName( name );
 //  reason += validateEmail(email);
  reason += validateComment(comment);
  
  if (reason != "") {//noslz
    Ext.Msg.alert("Σφάλμα ", "<b>Ορισμενα στοιχεια χρειαζονται διόρθωση,  προσπαθήστε ξανά:<b><br>"+ reason			);
    return false;
  }

  return true;
}

	



function openSendPublicCommentGameTimeWin (  gSid, gtSid , inApp , saveFlg) {

	
	


	
	
	var dlg = new Ext.Window({
		title : "γραψε κατι για το ματς ",
		width : 700,
		height : 500,
		y:0, 
		minWidth : 100,
		minHeight : 100,
		layout : "border",      //noslz
		
		modal : true,
		resizable : false,
		maximizable : false,
		closable : false,
			
		hideMode : "offsets",      //noslz
		constrainHeader : true,
		items : [
		
		
		
		{
			xtype:"panel",
			layout : "form",      //noslz
			autoScroll : true, 
			bodyStyle : 'padding:10px',      //noslz
			border : false,
			bodyBorder : false,
			region: "center", 
			
			items : [
			
		
						{
			
								html: "<br>", 
								width : 300,
								xtype: "label" //noslz
						},
						
						
						{
								id : 'name',            //noslz
								name : 'name',            //noslz
								xtype : 'textfield',            //noslz
								width : 350,
								fieldLabel : '<b>Ονομα*</b>',
								value: ""           //noslz
						},
						
						
						{
								html: "<br>", //noslz
								xtype: "label"//noslz
						},
						
						
						{
							id : 'email',            //noslz
							name : 'email',            //noslz
							fieldLabel : '<b>Εμαιλ*</b>',
							xtype : 'textfield',            //noslz
							hidden : true,
							width : 300
							           //noslz
						},
						
						
						{
								html: "<br>", //noslz
								xtype: "label"//noslz
						},
						
						
						{
								id:'comment',//noslz
								name: 'comment',//noslz
								hideLabel: true,
								xtype: "htmleditor",//noslz
								fieldLabel : '<b>Σχόλιο*</b>',
								enableSourceEdit :true, 
								enableFontSize :true, 
							 	enableFont :true, 
						 		enableLists: false, 
						 		enableLinks: false, 
						 		enableColors : true, 
						 		enableAlignments : false, 
							    height : 200,
							    width : 500,
							    value: ""
						}
					
					]}	
						
			]
			
			,
			tbar : [
						
					
						
					{
						text : 'αποστολή',
						iconCls : 'icon-accept',      //noslz
						scale : 'medium',      //noslz
						handler : function() {
							
							
							
							
							
						var inName =		trim( Ext.getCmp('name').getValue()) ; 
						var inEmail= 		trim ( Ext.getCmp('email').getValue())  ;
						var inComment =					trim(	Ext.getCmp('comment').getValue() ) ;
							
							
							
						if (  validateEveryThing  (  
													inName   ,
													 inEmail, 
													 inComment 
												)    != ""
													
													)
													
									{

					
							Ext.Ajax.request({
										method : "POST", // noslz
										url : PublicServlet , // noslz
										params : {
											action : 'sendPublicMsgOnGameTime', // noslz
											gSid:gSid,
											gtSid:gtSid, 
											name : inName , // noslz
											comment :  inComment , // noslz
											email:  inEmail

										},
										scope : this,
										success : function(res) {


											// Ext.MessageBox.alert("Επιτυχία",		" Μηνύματα   ");
											// refresh 
											
											
											if ( PublicGameTimeWindow != null ) PublicGameTimeWindow.close(); 
											
											openDisplayPublicGameTimeWindow ( g, gt, inApp , saveFlg ); 
											
											
										}// success
									});// Ext.Ajax.request

							dlg.close();
						
						
						} // validation
				
						
						
						}// handler
					},

					{
						text : 'ακύρωση',
						iconCls : 'icon-cancel',      // noslz
						scale : 'medium',      //noslz
						handler : function() {

							dlg.close();
							

						}// handler
					}

			]
			
			
			
			
		
	});

	dlg.show();
	
	// this makes clicking outside window close window 
	Ext.select('.ext-el-mask').addListener('click', function() {      //noslz
	            dlg.close();
	});	
	
	dlg.on('afterrender ', function( cmp ){      //noslz
		dlg.getEl().unmask();
	});//rowdblclick
	
	
	dlg.on('beforerender', function( cmp ){      //noslz
		dlg.getEl().mask(); 
	});//rowdblclick
	
	
	

}