
// Need to verify default behavior with that defined in fckxhtml.js and fckxhtml_gecko.js

// We must process the image tags to replace with the real resulting value of the placeholder.

FCKXHtml.TagProcessors['img'] = function( node, htmlNode )
{
	if ( htmlNode._fckequation )
	{
		node = FCKXHtml.XML.createTextNode( unescape(htmlNode._fckequation) ) ;
	}
	else if ( htmlNode._fckGallery )
		node = FCKXHtml.XML.createTextNode( unescape(htmlNode._fckGallery) ) ;
	else	if ( htmlNode._fckCodeBlock )
		node = FCKXHtml.XML.createTextNode( '\\code' + htmlNode._fckCodeBlock + ' \\endcode' ) ;
	else
	{
		// The "ALT" attribute is required in XHTML.
		if ( ! node.attributes.getNamedItem( 'alt' ) )
			FCKXHtml._AppendAttribute( node, 'alt', '' ) ;
	
		var sSavedUrl = htmlNode.getAttribute( '_fcksavedurl' ) ;
		if ( sSavedUrl != null )
			FCKXHtml._AppendAttribute( node, 'src', sSavedUrl ) ;
	}

	return node ;
}

