


areaCodeSid =-1; 
areaCodeTitle =""; 
function setAreaCodeSid(  acSid , acTitle  ){
		areaCodeSid = acSid; 
		areaCodeTitle = acTitle; 
				
		Ext.getCmp('areaCode').setRawValue(  acSid+"_"+acTitle );
		
		/*
		Ext.MessageBox.alert( " ενημέρωση   ",   "  Διάλεξες το Νομό " +  acTitle  +  "  "
			,
			function(btn, text){
			// 	alert ( 'goto next card' ); 
    		}
			); 
			*/
	
	//return 1; 
}


mapHtml =    

'<div id="Greece" align="left">'+
'<img src="greece.gif" usemap=#MapGr name=Map style="border:0px" alt="">'+
'<map name=MapGr>'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 1, \'Ν. Αττικής\'  )"    shape=POLY  title="Ν. Αττικής"       href="#"   coords=197,190,201,197,197,201,200,223,161,324,146,299,148,293,155,298,163,286,170,236,163,230,172,215,168,207,157,205,161,196,171,193,179,195,189,187     >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 2, \'Ν. Αιτολωακαρνανίας\'  )"    shape=POLY  title="Ν. Αιτολωακαρνανίας" href="#"  coords=82,143,84,154,89,159,88,163,102,167,108,162,108,166,103,180,102,182,91,186,83,187,76,183,67,188,66,184,67,181,62,173,53,154,68,152,67,148,69,143,77,142,80,138       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 3, \'Ν. Βοιωτίας\'  )"    shape=POLY  title="Ν. Βοιωτίας"      href="#"   coords=140,175,162,177,182,183,185,191,178,197,166,195,158,197,140,190,130,177,132,174      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 4, \'Ν. Θεσσαλονίκης\'  )"    shape=POLY  title="Ν. Θεσσαλονίκης"  href="#"   coords=173,50,177,51,185,55,187,55,183,64,161,74,157,77,155,75,149,76,146,70,151,65,148,63,143,66,138,67,132,57,133,52,137,51,145,51,150,53,156,49,165,43        >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 5, \'Ν. Ευρυτανίας\'  )"    shape=POLY  title="Ν. Ευρυτανίας"    href="#"   coords=94,139,99,141,105,142,104,150,102,155,106,165,97,167,88,164,85,157,81,152,84,138       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 6, \'Ν. Φθιώτιδας\'  )"    shape=POLY  title="Ν. Φθιώτιδας"     href="#"   coords=128,141,131,146,135,149,151,153,147,156,137,157,133,160,157,170,160,171,168,173,167,176,165,178,148,176,142,175,133,177,131,173,130,170,115,165,101,159,104,147,116,143,118,137       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 7, \'Ν. Φωκίδας\'  )"    shape=POLY  title="Ν. Φωκίδας"       href="#"   coords=121,164,124,165,132,174,131,178,134,187,131,186,125,183,120,187,110,185,101,180,105,174,110,164,114,163        >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 8, \'Ν. Ευβοίας\'  )"    shape=POLY  title="Ν. Ευβοίας"       href="#"   coords=169,152,180,164,184,165,201,169,225,155,230,165,224,166,207,182,214,197,225,203,220,207,217,206,213,206,207,195,205,192,195,184,181,182,179,175,156,160,155,156,162,151       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 9, \'Ν. Αρκαδίας\'  )"    shape=POLY  title="Ν. Αρκαδίας"        href="#" coords=111,215,114,215,124,219,130,232,134,235,140,236,148,260,137,257,128,243,124,246,116,250,112,251,103,238,100,230,96,223,99,213       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 10, \'Ν. Αχαϊας\'  )"    shape=POLY  title="Ν. Αχαϊας"          href="#" coords=113,193,123,200,120,207,111,216,101,214,96,209,89,212,77,194,85,196,92,195,101,187     >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 11, \'Ν. Ηλείας\'  )"    shape=POLY  title="Ν. Ηλείας"          href="#" coords=82,204,85,208,92,210,100,208,96,223,99,227,104,234,94,238,88,236,79,225,74,219,65,208,74,203,76,200       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 12, \'Ν. Κορινθίας\'  )"    shape=POLY  title="Ν. Κορινθίας"       href="#" coords=135,201,148,210,149,206,148,202,157,206,158,210,152,212,155,214,159,219,154,222,135,219,125,217,115,215,118,208,123,199,133,200       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 13, \'Ν. Λακωνίας\'  )"    shape=POLY  title="Ν. Λακωνίας"        href="#" coords=133,245,139,257,147,259,151,259,152,277,157,290,143,277,137,270,130,273,127,287,124,290,121,285,117,259,117,244,124,246,126,243     >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 14, \'Ν. Μεσσηνίας\'  )"    shape=POLY  title="Ν. Μεσσηνίας"       href="#" coords=105,236,109,246,114,249,118,259,120,273,101,261,97,273,89,265,85,249,90,240,94,237,99,234       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 15, \'Ν. Ζακύνθου\'  )"    shape=POLY  title="Ν. Ζακύνθου"        href="#" coords=56,219,53,219,49,222,42,210,46,207,53,213       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 16, \'Ν. Κερκύρας\'  )"    shape=POLY  title="Ν. Κερκύρας"        href="#" coords=16,106,13,113,15,115,16,122,25,130,25,141,14,124,2,105       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 17, \'Ν. Κεφαλληνίας\'  )"    shape=POLY  title="Ν. Κεφαλληνίας"     href="#" coords=47,180,47,189,51,199,37,197,35,190,30,195,33,185,40,183,40,178       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 18, \'Ν. Λευκάδας\'  )"    shape=POLY  title="Ν. Λευκάδας"        href="#" coords=49,160,47,170,41,166,48,158       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 19, \'Ν. Aρτας\'  )"    shape=POLY  title="Ν. Aρτας"         href="#"  coords=71,123,80,129,79,141,72,144,59,147,55,142,57,138,58,134,64,127,66,121      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 20, \'Ν. Θεσπρωτίας\'  )"    shape=POLY  title="Ν. Θεσπρωτίας"    href="#"   coords=40,109,41,115,40,119,46,124,45,132,33,134,28,112,36,106      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 21, \'Ν. Ιωαννίνων\'  )"    shape=POLY  title="Ν. Ιωαννίνων"     href="#"   coords=63,84,63,91,66,95,72,101,77,105,74,109,74,111,70,116,71,122,65,127,64,128,46,129,42,121,39,116,40,112,36,93,44,91,52,83,55,77,60,78       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 22, \'Ν. Πρέβεζας\'  )"    shape=POLY  title="Ν. Πρέβεζας"      href="#"   coords=64,129,58,140,52,147,49,152,46,144,39,134,41,131       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 23, \'Ν. Καρδίτσας\'  )"    shape=POLY  title="Ν. Καρδίτσας"     href="#"   coords=121,123,116,148,105,147,100,141,96,144,88,138,80,138,82,127,94,126,116,120      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 24, \'Ν. Λάρισας\'  )"     shape=POLY  title="Ν. Λάρισας"       href="#"   coords=119,89,124,87,126,91,127,95,138,99,147,114,147,120,137,134,133,141,129,144,119,137,117,133,118,122,113,116,104,108,108,93,118,86    >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 25, \'Ν. Μαγνησίας\'  )"    shape=POLY  title="Ν. Μαγνησίας"     href="#"   coords=155,122,178,142,190,143,207,130,213,135,207,133,200,139,201,143,205,150,197,149,187,148,168,144,156,148,156,145,162,144,155,135,150,135,145,141,152,151,147,153,133,150,130,148,136,133,135,129,150,119      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 26, \'Ν. Τρικάλων\'  )"    shape=POLY  title="Ν. Τρικάλων"      href="#"   coords=101,103,106,107,107,111,118,120,101,123,96,126,80,129,71,126,68,116,70,111,73,107,77,103,81,102     >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 27, \'Ν. Γρεβενών\'  )"    shape=POLY  title="Ν. Γρεβενών"      href="#"   coords=87,85,96,88,99,96,103,100,105,100,103,105,79,105,71,104,66,100,63,87,68,86,76,85,86,84      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 28, \'Ν. Δράμας\'  )"    shape=POLY  title="Ν. Δράμας"        href="#"   coords=225,17,232,24,223,34,205,43,196,38,186,29,184,21,222,13,225,15       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 29, \'Ν. Ημαθίας\'  )"    shape=POLY  title="Ν. Ημαθίας"       href="#"   coords=129,58,133,59,137,67,129,71,116,81,110,73,104,64,107,57       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 30, \'Ν. Αργολίδας\'  )"    shape=POLY  title="Ν. Αργολίδας"       href="#" coords=159,225,162,229,171,235,174,238,163,240,157,249,156,239,155,236,139,229,133,235,128,231,127,225,124,217,130,216       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 31, \'Ν. Καβάλας\'  )"    shape=POLY  title="Ν. Καβάλας"       href="#"   coords=231,33,234,40,233,65,224,64,211,54,200,58,194,53,208,43,231,31      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 32, \'Ν. Καστοριάς\'  )"    shape=POLY  title="Ν. Καστοριάς"     href="#"   coords=81,58,84,63,85,71,80,76,77,75,67,81,62,86,56,72,64,67,71,61,78,56       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 33, \'Ν. Κιλκίς\'  )"    shape=POLY  title="Ν. Κιλκίς"        href="#"   coords=150,27,151,31,159,34,164,39,164,43,161,48,156,50,147,53,144,53,135,52,125,45,122,41,129,36,139,33,143,34,147,25       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 34, \'Ν. Κοζάνης\'  )"    shape=POLY  title="Ν. Κοζάνης"       href="#"   coords=104,62,111,72,114,74,116,85,102,100,98,97,97,91,90,87,82,83,69,88,66,84,69,77,81,74,84,73,87,68,94,63,99,57,105,58     >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 35, \'Ν. Πέλλας\'  )"    shape=POLY  title="Ν. Πέλλας"        href="#"   coords=124,36,124,41,122,43,133,50,136,53,134,55,134,57,119,59,99,56,99,49,113,34      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 36, \'Ν. Πιερίας\'  )"    shape=POLY  title="Ν. Πιερίας"       href="#"   coords=137,70,136,83,135,93,137,100,130,97,124,92,123,89,117,89,114,84,123,74,135,68       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 37, \'Ν. Σερρών\'  )"    shape=POLY  title="Ν. Σερρών"        href="#"   coords=184,23,187,29,198,37,204,44,192,55,174,52,151,32,149,27,181,22      >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 38, \'Ν. Φλώρινας\'  )"    shape=POLY  title="Ν. Φλώρινας"      href="#"   coords=100,47,98,51,100,57,97,64,85,66,81,60,74,59,67,62,64,50       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 39, \'Ν. Χαλκιδικής\'  )"    shape=POLY  title="Ν. Χαλκιδικής"    href="#"   coords=192,66,194,69,191,72,202,77,217,86,217,91,212,88,201,80,189,79,186,82,196,89,199,99,194,98,180,85,170,84,171,89,181,98,185,102,171,98,161,82,155,77,157,74,160,74,165,68,185,63       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 40, \'Ν. Έβρου\'  )  "      shape=POLY  title="Ν. Έβρου"     href="#"       coords=313,6,315,10,315,24,303,33,303,46,292,55,287,52,276,52,274,76,266,75,274,44,284,38,285,34,287,25,296,19,294,11,292,4,301,2   >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 41, \'Ν. Ξάνθης\'  )"    shape=POLY  title="Ν. Ξάνθης"        href="#"   coords=254,26,251,30,248,37,250,43,244,46,234,48,229,32,220,32,232,23,242,21       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 42, \'Ν. Ροδόπης\'  )  "    shape=POLY  title="Ν. Ροδόπης"  href="#"       coords=286,30,286,38,279,44,273,48,265,50,255,47,250,44,247,33,252,29,263,28,266,26,285,26     >'+
'<area style="CURSOR: hand"    onClick="setAreaCodeSid( 43, \'Ν. Δωδεκανήσων\'  )"   shape=POLY  title="Ν. Δωδεκανήσων"     coords=396,294,384,349,330,349,294,282,316,238,336,235   href="#"  >  '+
'<area style="CURSOR: hand"    onClick="setAreaCodeSid( 44, \'Ν. Κυκλάδων\'  )"    shape=POLY  title="Ν. Κυκλάδων"        href="#" coords=236,208,243,215,246,223,260,231,292,266,280,296,254,293,204,272,214,220,234,206      >'+
'<area style="CURSOR: hand"     onClick="setAreaCodeSid( 45, \'Ν. Λέσβου\'  )"    shape=POLY  title="Ν. Λέσβου"          href="#" coords=256,100,259,99,267,100,308,136,318,152,310,153,281,146,242,126,248,98       > '+
'<area style="CURSOR: hand"     onClick="setAreaCodeSid( 46, \'Ν. Σάμου\'  )"      shape=POLY  title="Ν. Σάμου"           href="#" coords=338,217,335,221,311,231,289,229,295,225,302,224,328,215      >'+
'<area style="CURSOR: hand"     onClick="setAreaCodeSid( 47, \'Ν. Χίου\'  )"    shape=POLY  title="Ν. Χίου"            href="#" coords=295,176,297,179,295,194,287,196,269,173       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 48, \'Ν. Ηρακλείου\'  )"    shape=POLY  title="Ν. Ηρακλείου"       href="#" coords=248,349,267,353,266,357,263,359,264,363,267,369,229,370,234,354,243,347       >  '+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 49, \'Ν. Λασιθίου\'  )"    shape=POLY  title="Ν. Λασιθίου"        href="#" coords=277,351,279,361,301,354,300,366,292,367,264,366,263,356,266,352,271,349       > '+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 50, \'Ν. Ρεθύμνου\'  )"    shape=POLY  title="Ν. Ρεθύμνου"        href="#" coords=238,347,234,355,226,362,218,359,208,352,215,347,233,345       >'+
'<area style="CURSOR: hand"   onClick="setAreaCodeSid( 51, \'Ν. Χανίων\'  )"    shape=POLY  title="Ν. Χανίων"          href="#" coords=185,334,193,339,203,335,204,339,203,342,208,344,209,348,208,357,173,349,177,339,181,339,183,331      >'+
'</map>'+
'</div>'; 

