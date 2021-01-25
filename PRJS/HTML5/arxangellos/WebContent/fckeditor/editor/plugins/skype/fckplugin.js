//fckplugin.js
/*
 * your plugin must be put in the 'editor/plugins/#plug-in name#' (the name is specified in fckconfig.js -> addPlugin, see below)
 * in my case this is 'editor/plugins/insertvariables/'
 *
 * insert variable editor
 * @author: Tim Struyf, Roots Software (http://www.roots.be), tim.struyf@roots.be
 */
// Register the related command.
FCKCommands.RegisterCommand( 'My_Skype' , new FCKDialogCommand( FCKLang['DlgMySkypeTitle'] , FCKLang['DlgMySkypeTitle'] , FCKConfig.PluginsPath + 'skype/fck_skype.html' , 400, 400 ) ) ;

var oSkypeItem = new FCKToolbarButton( 'My_Skype', FCKLang['MySkypeBtn '] ) ;
oSkypeItem.IconPath = FCKConfig.PluginsPath + 'skype/skype.png' ;

FCKToolbarItems.RegisterItem( 'My_Skype', oSkypeItem ) ; // 'My_Find' is the name used in the Toolbar config.
var SkypeStatusCommand=function(){
        //create our own command, we dont want to use the FCKDialogCommand because it uses the default fck layout and not our own
};


SkypeStatusCommand.Add=function(name, but) {
   
		FCK.Focus();
		if (but == 1)
		   but_type = 'http://mystatus.skype.com/balloon/';
		else if (but == 2)
		   but_type = 'http://mystatus.skype.com/smallclassic/';
		else if (but == 3)
		   but_type = 'http://mystatus.skype.com/bigclassic/';
		else if (but == 4)
		   but_type = 'http://mystatus.skype.com/mediumicon/';	 
        hr = FCK.InsertHtml('<a href="skype:'+name+'?chat"><img src="'+but_type+name+'" style="border: none;"  alt="My status" /></a>');
}
