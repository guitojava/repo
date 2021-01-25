

// The Best things in life really are free lol
var gridPageSize = 10; 

//window.history.forward(1);

/*
for production 

var UserServlet 		= '/UserServlet';//noslz
var GamesServlet 		= '/MemopageAppServlet';//noslz
var MapServlet			= '/MapInfoServlet';//noslz
var PublicServlet 		= '/PublicAppServlet';       //noslz
var DirectUrl			= 'http://'+ location.hostname +':'+location.port + '/gamesApp/public.html' ;
var FullCalanderUrl     = 'http://'+ location.hostname +':'+location.port +  '/fullcalendar-1.4.7/getCalTeamGames.html' ;
var footballFieldPlayerDefaultImage =  '/footballField/img/s.gif'  ;

var shareButtonLink= 	'<a target="_blank" class="a2a_dd" href="http://www.addtoany.com/share_save?linkname=pamempala.gr&amp;linkurl=' +
						'http%3A%2F%2F' + location.hostname +":"+location.port+ '%2FgamesApp%2Fpublic.html%3Fg%3D'+g+'%26gtp%3D'+gt+'"><img src="http://static.addtoany.com/buttons/share_save_256_24.png" width="256" height="24" border="0" alt="Share/Bookmark"/></a>' +
						'<script type="text/javascript">a2a_linkname="pamempala.gr";a2a_linkurl="http://'+location.hostname +":"+location.port+'/gamesApp/public.html?g=178&gtp=317   ";a2a_show_title=1;</script><script type="text/javascript" src="http://static.addtoany.com/menu/locale/el.js" charset="utf-8"></script><script type="text/javascript" src="http://static.addtoany.com/menu/page.js"></script>' ;
*/

var rootName = 'arxangellos';

var KoinAppServlet 		= '/'+rootName+'/KoinAppServlet';//noslz
var UserServlet 		= '/'+rootName+'/UserServlet';//noslz
var GamesServlet 		= '/'+rootName+'/MemopageAppServlet';//noslz
var PublicServlet 		= '/'+rootName+'/PublicAppServlet';       //noslz
var DirectUrl			= 'http://'+ location.hostname +':'+location.port + '/'+rootName+'/public.jsp' ;
var BaseLink			= 'http://'+ location.hostname +':'+location.port + '/'+rootName ;


var areaCodeDataGlobal = {"results":[{"description":"ΑΤΤΙΚΗΣ","id":1},{"description":"ΑΙΤΩΛΟΑΚΑΡΝΑΝΙΑΣ","id":2},{"description":"ΒΟΙΩΤΙΑΣ","id":3},{"description":"ΘΕΣΣΑΛΟΝΙΚΗΣ","id":4},{"description":"ΕΥΡΥΤΑΝΙΑΣ","id":5},{"description":"ΦΘΙΩΤΙΔΟΣ","id":6},{"description":"ΦΩΚΙΔΟΣ","id":7},{"description":"ΕΥΒΟΙΑΣ","id":8},{"description":"ΑΡΚΑΔΙΑΣ","id":9},{"description":"ΑΧΑΙΑΣ","id":10},{"description":"ΗΛΕΙΑΣ","id":11},{"description":"ΚΟΡΙΝΘΙΑΣ","id":12},{"description":"ΛΑΚΩΝΙΑΣ","id":13},{"description":"ΜΕΣΣΗΝΙΑΣ","id":14},{"description":"ΖΑΚΥΝΘΟΥ","id":15},{"description":"ΚΕΡΚΥΡΑΣ","id":16},{"description":"ΚΕΦΑΛΛΗΝΙΑΣ","id":17},{"description":"ΛΕΥΚΑΔΟΣ","id":18},{"description":"ΑΡΤΗΣ","id":19},{"description":"ΘΕΣΠΡΩΤΙΑΣ","id":20},{"description":"ΙΩΑΝΝΙΝΩΝ","id":21},{"description":"ΠΡΕΒΕΖΗΣ","id":22},{"description":"ΚΑΡΔΙΤΣΗΣ","id":23},{"description":"ΛΑΡΙΣΗΣ","id":24},{"description":"ΜΑΓΝΗΣΙΑΣ","id":25},{"description":"ΤΡΙΚΑΛΩΝ","id":26},{"description":"ΓΡΕΒΕΝΩΝ","id":27},{"description":"ΔΡΑΜΑΣ","id":28},{"description":"ΗΜΑΘΙΑΣ","id":29},{"description":"ΑΡΓΟΛΙΔΟΣ","id":30},{"description":"ΚΑΒΑΛΑΣ","id":31},{"description":"ΚΑΣΤΟΡΙΑΣ","id":32},{"description":"ΚΙΛΚΙΣ","id":33},{"description":"ΚΟΖΑΝΗΣ","id":34},{"description":"ΠΕΛΛΗΣ","id":35},{"description":"ΠΙΕΡΙΑΣ","id":36},{"description":"ΣΕΡΡΩΝ","id":37},{"description":"ΦΛΩΡΙΝΗΣ","id":38},{"description":"ΧΑΛΚΙΔΙΚΗΣ","id":39},{"description":"ΕΒΡΟΥ","id":40},{"description":"ΞΑΝΘΗΣ","id":41},{"description":"ΡΟΔΟΠΗΣ","id":42},{"description":"ΔΩΔΕΚΑΝΗΣΟΥ","id":43},{"description":"ΚΥΚΛΑΔΩΝ","id":44},{"description":"ΛΕΣΒΟΥ","id":45},{"description":"ΣΑΜΟΥ","id":46},{"description":"ΧΙΟΥ","id":47},{"description":"ΗΡΑΚΛΕΙΟΥ","id":48},{"description":"ΛΑΣΙΘΙΟΥ","id":49},{"description":"ΡΕΘΥΜΝΗΣ","id":50},{"description":"ΧΑΝΙΩΝ","id":51}],"total":"51"};



var MapServlet			= '/'+rootName+'/MapInfoServlet';//noslz
var FullCalanderUrl     = 'http://'+ location.hostname +':'+location.port +  '/'+rootName+'/fullcalendar-1.4.7/getCalTeamGames.html' ;

var footballFieldPlayerDefaultImage =  '/'+rootName+'/footballField/img/s.gif'  ;

var shareLink1 = 		'http%3A%2F%2F' + location.hostname +":"+location.port+ '%2F'+rootName+'%2FgamesApp%2Fpublic.html' ;
var shareLink2 =		'http://'+location.hostname +":"+location.port+'/'+rootName+'/gamesApp/public.html' ;
var DirectNonMemberUrl			= 'http://'+ location.hostname +':'+location.port + '/'+rootName+'/gamesApp/publicNonMember.html' ;
var shareNonMemberLink1 = 		'http%3A%2F%2F' + location.hostname +":"+location.port+ '%2F'+rootName+'%2FgamesApp%2FpublicNonMember.html' ;
var shareNonMemberLink2 =		'http://'+location.hostname +":"+location.port+'/'+rootName+'/gamesApp/publicNonMember.html' ;
	



/*  For production update 

globals.js

applicationContext.xml

Constants.java 

fckeditorImpl/editor.jsp

		<FCK:config ImageBrowserURL= "http://pamempala.gr/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=http://pamempala.gr/fckeditor/editor/filemanager/connectors/php/connector.java"  />

		<form action="/MemopageAppServlet" method="post">




fckeditorImpl/pickProfileImage.jsp

		var  conStr = 
			"http://" + location.hostname +":"+location.port
			+ "/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&"+
			"Connector="+ 	"http://" + location.hostname +":"+location.port		
			+"/fckeditor/editor/filemanager/connectors/php/connector.java";


		<form action="/MemopageAppServlet" method="post">  -->
 
 
*/



