/* 
 * Global vars  
 * 
 */







function unloadMess(){
    mess = ""
    return mess;
}
function setBunload(on){
    window.onbeforeunload = (on) ? unloadMess : null;
}
//setBunload(true);




var txtMain = {
	
	

	
helpTitle: 'Βοήθεια',
helpHTML:' TODO',
linkname:"Με το www.pamempala.gr βρίσκω ματς σε γήπεδα και ομάδες ή πρωταθλήματα στην περιοχή  μου" ,
linkurl:"www.pamempala.gr",
shareBtnText : 'Κάντε κλικ στο Share κουμπί ', 
profileBtn:'Προφίλ',
availBtnText:'Διαθεσιμότητα',
gamesBtn: 'Ματς',

crtGameBtn:'Νέο ματς',
crtGameNonMemberBtn: 'Νέο ματς (μην μελος)',

createNewGame :	'Νέο ματς',

inBox:'Μηνύματα',

shwGameOrgBtn:'Ματς ως Υπεύθυνος',
shwGameAsPlyerBtn : 'Ματς ως Παίχτης',
shwGameAsFldBtn :'Ματς ως Γήπεδο', 
searchGamesBtn :'Αναζήτηση Ματς', 
allGamesBtn: 'Όλα τα παιχνίδια',

teamsBtn :'Ομάδες', 
crTeamsBtn :'Δημιουργία ομάδας',
teamOrgBtn :  'Ομάδες ως Αρχήγος',
teamAsPlayerBtn : 'Ομάδες ως Παίχτης', 
searchTeamsBtn  : 'Αναζήτηση Ομάδες', 
allTeamsBtn : 'Όλες οι ομάδες',
 
playersBtn	:'Παίκτες',
 
 
friendsBtn:	'Φίλοι',   
groupsBtn :'Οι Παρέες μου',

requestedFriendsBtn : 'Αίτηματα φιλίας',


searchBtn: 'Αναζήτηση',
searchPlayers: 'Αναζήτηση παίκτες',

allPlayers:  'Όλοι οι παίκτες',	

newsPages : 'Αθλητικά Πρωτοσέλιδα ', 
extraBtn  : 'Αλλα ', 

CoolSoccerMilanGame:"Milan Soccer Game",
SoccerGames : 'Soccer Games 1',
MoreSoccerGames : 'Soccer Games 2' ,


fieldsBtn:  'Γήπεδα',
infoMapBtn:  'Χαρτης' ,
internetBtn:  "Αλλα χρησιμα sites" ,
shareBtn: 'Share',
chatBtn: 'Chat',

trafficPage : 'Κινηση στος δρομους της Αθηνας (Athens Police)', 

musicBtn :'ON/OFF Music',


logoffBtn :'έξοδος',

logoffMsgTitle :  'Status', 
logoffMsg  : 'Ok Logout Successful! BB  ' 	,


notLogInMsgTitle:'Status',
notLogInMsg:'Δεν είστε συνδεδεμένος ' ,

contactUsTitle:'Επικοινωνία',
contactUsHTML:
		' <br><br> ' +
		' <h3> created by GEORGE LEON, OTHOS KARPATHOS <br>' +
		' <br>' +
		' <br> ' +
		' <object style="height: 344px; width: 425px"><param name="movie" value="http://www.youtube.com/v/U7M4QMs9YUU"><param name="allowFullScreen" value="true"><param name="allowScriptAccess" value="always"><embed src="http://www.youtube.com/v/U7M4QMs9YUU" type="application/x-shockwave-flash" allowfullscreen="true" allowScriptAccess="always" width="425" height="344"></object> ' +
		' <br> ' +
		' <br> ' +
		' <object style="height: 344px; width: 425px"><param name="movie" value="http://www.youtube.com/v/ECLp8A319pU"><param name="allowFullScreen" value="true"><param name="allowScriptAccess" value="always"><embed src="http://www.youtube.com/v/ECLp8A319pU" type="application/x-shockwave-flash" allowfullscreen="true" allowScriptAccess="always" width="425" height="344"></object> '+
		' <br> ' +
		' <br> ' +
		' <br> and yes thats me and my couzin playing when we were kids in Othos Karpathos at Xristo  (1987)  !!!  <br>' 


}
	


var userObj;   




window.onunload = function ()
{
Ext.Ajax.request({
	method : 'POST',  //noslz  
	url : GamesServlet,
	params : {
		action : 'killSession' //noslz
	}
});//Ext.Ajax.request
//alert("Take it easy   bye4now,   automatic logout successful !"); 						     
}


/*
function testAjax() {

	Ext.Ajax.request({
				method : "POST",
				url : GamesServlet,
				params : {
					action : 'createPlayer'
				},
				scope : this,
				success : function(res) {
					
					alert("return OK")
				}//success
			});//Ext.Ajax.request

}
*/ 




Ext.onReady(function() {
	APP_SETUP();
	
		openMemopagesGridWin(); 
	
});

		
		
function APP_SETUP() {

	// ----------------
	// Set listeners on form fields
	// ----------------
	
	Ext.override(Ext.form.Field, {
				fireKey : function(e) {
					if (((Ext.isIE && e.type == 'keydown') || e.type == 'keypress')         //noslz
							&& e.isSpecialKey()) {
						this.fireEvent('specialkey', this, e);   //noslz
					} else {
						this.fireEvent(e.type, this, e);
					}
				},
				initEvents : function() {
					this.el.on('focus', this.onFocus, this);//noslz
					this.el.on('blur', this.onBlur, this);    //noslz
					this.el.on('keydown', this.fireKey, this);   //noslz
					this.el.on('keypress', this.fireKey, this);//noslz
					this.el.on('keyup', this.fireKey, this);//noslz
					this.originalValue = this.getValue();
				}
			});
	Ext.BLANK_IMAGE_URL = '../js/ext3/resources/images/default/s.gif';  //noslz
	Ext.QuickTips.init();
	Ext.useShims = true;

	/***************************************************************************
	 * MENU TOOLBAR
	 **************************************************************************/


	

	


	

	var northComp = new Ext.Panel({
				border : false,
				region : 'north',     //noslz
				
			//	bodyStyle : "backgroundColor:#D3E0F2;",    //noslz
				height : 50,
			//	closable : false,
			//	collapseMode : 'mini',     //noslz
			//  deferredRender : false,
			//  html : 'ads might go here or new ticker ',
			//	split : true,
				tbar : [ // Fill
						
				
				
				
				
						{
							text : txtMain.profileBtn,
							iconAlign: 'top',
							tooltip : txtMain.profileBtn,
							iconCls : 'icon-user',      //noslz
						//	scale : 'large',
							handler : function() {
								
								
								
								
								Ext.Ajax.request({
									method : "POST",      //noslz
									url : GamesServlet,
									params : {
										action : 'getUserInfo'      //noslz
									},
									scope : this,
									success : function(res) {
										var obj = Ext.util.JSON.decode(res.responseText);
										// TODO 
								//		alert(  res.responseText   ); 
										
										openUserProfileWindow(obj);
										
									}//success
								});//Ext.Ajax.request
								
								
							}// handler
						},
						
					
					
				
					new Ext.Toolbar.Separator(), 
						
					
					{
							text : " Οι Σελίδες  μου ",
							iconAlign: 'top',
							iconCls : 'icon-page',           //noslz
							id: 'memoGridBtn',                     //noslz
							name:'memoGridBtn',                    //noslz
							handler : function() {
								
								openMemopagesGridWin(); 
								
								
							}
							
					}, 
						
					
					
					
				
					/*
					new Ext.Toolbar.Separator(), 
					new Ext.Toolbar.Separator(),
					new Ext.Toolbar.Separator(),
					
					{
							text : "Memopage ",
							iconAlign: 'top',
							iconCls : 'icon-user',           //noslz
							id: 'memoBtn',                     //noslz
							name:'memoBtn',                    //noslz
							handler : function() {
								
								openMemopageWindow(  );     //noslz
								
							}
							
					}, 
					
					
					new Ext.Toolbar.Separator(), 
						
					
					{
							text : "Test HTML Editor ",
							iconAlign: 'top',
							iconCls : 'icon-user',           //noslz
							id: 'testBtn',                     //noslz
							name:'testBtn',                    //noslz
							handler : function() {
								
		openHtmlEditor( "sid_value" , "action_command" );     //noslz
								
							}
							
					}, 
					
					
					
				
					*/
		
					/*
						{
							
							id:'searchBtn',        //noslz
							xtype: 'button',         //noslz
							tooltip : "Αναζήτηση σελίδας" , 
							text : "Αναζήτηση σελίδας"  , 
							iconAlign: 'top',
							iconCls : 'icon-search',        //noslz
							handler : function() {
								
								window.location = "../search.html";
								
							}
						},
						*/
					
					
						{
							
							id:'logOffBtn',        //noslz
							xtype: 'button',         //noslz
							tooltip : txtMain.logoffBtn , 
							text : txtMain.logoffBtn , 
							iconAlign: 'top',
							iconCls : 'icon-logout',        //noslz
							handler : function() {
								
								Ext.Ajax.request({
									method : "POST",        //noslz
									url : GamesServlet,
									params : {
										action : 'killSession'        //noslz
									},
									scope : this,
									success : function(res) {
										Ext.Msg.alert(
												txtMain.logoffMsgTitle,
												txtMain.logoffMsg   ,   //noslz
												function(
														btn, text) {
													var redirect = '../login.html';          //noslz
													window.location = redirect;
													
													
													
													
												});

										var redirect = '../login.html';        //noslz
										window.location = redirect;

									}// success
								});// Ext.Ajax.request
								
								// TODO kill userSession
								
								//alert('LogOff actions')

							}// handler
						}
						
						
						
						
					
				
						

				]
				
			});

			
	var timePanel = new Ext.Panel ({
	  layout: "fit",         //noslz
	  border:false,
	  html: "<h1><div align='center' id='clock'></div></h1><br>"         //noslz
	});		
			
	
	
	
	
	
            
	/***************************************************************************
	 * VIEWPORT
	 **************************************************************************/

	
	var viewport = new Ext.Viewport({
				layout : 'border',        //noslz
				renderTo : Ext.getBody(),
				
				
				items : [
						// toolbar and control buttons
						northComp,
						
						{
							border : false,
							region : 'center', //noslz 
							//bodyStyle: "foreground-image: url(./logo-plus-bg.jpg); foreground-position: center;  foreground-repeat: no-repeat;  height: 100%; width: 100%;  !important"
							bodyStyle: "background-color:black; !important",         //noslz

							html:   ''
							//'<br><center><img  width= "80%"  align= "center"  ' +         //noslz
							//		' src="./logo/logo-plus-bg-2(1).jpg" />' +
						//			'</center>'           //noslz
							
							
							
						}
					
						,{
							
							region : 'south',        //noslz
							xtype : 'panel',        //noslz
							layout: 'fit',        //noslz
							border : false,
							height: 50,
							bodyStyle: "background-color:black; !important",         //noslz
							html:' '  //noslz 
						}
							
						
						]
						
						
			});
	
			



Ext.Ajax.request({
		method : "POST",        //noslz
		url : GamesServlet,
		params : {
			action : 'getUserInfo'        //noslz
		},
		scope : this,
		success : function(res) {
			
			//alert(  res.responseText   );
			
			 //deal with logouts here 
			 var obj = Ext.util.JSON.decode(res.responseText);
			 if ( obj[0] == 'empty_reply' )         //noslz
			 	{
			 	
			 		Ext.Msg.alert(    
			 			
			 			txtMain .notLogInMsgTitle,
			 			txtMain .notLogInMsg,
						
						function(btn, text) {
							var redirect = '../login.html';        //noslz
							window.location = redirect;
						});
						
						
					var redirect = '../login.html';        //noslz
					window.location = redirect;
					
			 	
			 	} 
			 else {
			 	
			 	
			 	userObj=obj; 
			 	userIcon = "";  //"<img src='../css/img/silk/icons/field.png'>";         //noslz
				
				 //alert ("xaxa")
				 Ext.getCmp('logOffBtn').setText(         //noslz
				 	
				 
				 txtMain.logoffBtn +  " ("+ userIcon +" "+userObj.uname +" )"          //noslz
				 
				 ); 
				
			
			 	
			 }
			 
		}//success
});//Ext.Ajax.request






/****************** 
 * GLOBAL EVENTS  DEFINITION 
 *******************/

	
	
}
