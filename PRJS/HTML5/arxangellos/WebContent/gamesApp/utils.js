

// lang
var  txtUtils = {
	winPrint:'Print'
}



function trim(str, chars) {
	return ltrim(rtrim(str, chars), chars);
}

function ltrim(str, chars) {
	chars = chars || "\\s";
	return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
}
 
function rtrim(str, chars) {
	chars = chars || "\\s";
	return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
}

/**
 * 
 * renderImage
 * 
 * @param {} val
 * @return {}
 */	
function renderImage(val){
return '<img src='+val+'>';
}

/**
 * openReadOnlyWindow 
 * 
 * @param {} htmlContent
 * @param {} titleText
 * @param {} W
 * @param {} H
 */
function openReadOnlyWindow(  htmlContent , titleText, W, H ) {
			
var readOnlyEditor =  new Ext.Panel ({
	id:   "readOnlyWinId_",
	name: "readOnlyWinId_",	
	preventBodyReset:true, 
	border: false,
	html: "<div>" + htmlContent +"</div>",
	autoScroll :true
});
	

var dlg = new Ext.Window({
	title:  titleText,
	width: W,
	height: H,
	y:0,
	//autoScroll :true,
	layout: "fit",
//TODO one open window print not woring prints first opened window
	modal: true,
	resizable: true,
	maximizable: true,
	closable : true,
//	minimizable: true,
//	closeAction: "hide",
	hideMode: "offsets",
	constrainHeader: true,
	items: [
	{
		layout: "fit",
		//autoScroll: true,
		preventBodyReset:true, 
		bodyStyle: 'padding:10px; ',
		border: false,
		bodyBorder: false,
		items: [ readOnlyEditor ]
	}					
	],
	
	tbar :[
		{
			text:  txtUtils.winPrint,
        	iconCls:'icon-print',
        	handler : function(){
        		readOnlyEditor.getEl().print();
        	}// handler
        }
        
        ,
        
        {
				text : 'close',
				iconCls : 'icon-cancel',
				scale : 'medium',
				handler : function() {
					dlg.close();
				}// handler
			}
        ]
        
});
	dlg.show();
	
	
	
	
	Ext.select('.ext-el-mask').addListener('click', function() {
	            dlg.close();
	});	
	
	
}