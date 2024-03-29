/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2004-2010 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package net.fckeditor.localization.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

import net.fckeditor.localization.LocaleResolver;

/**
 * JSTL implementation of the locale resolver. It resolves the locale against
 * the user session.
 * 
 * @version $Id: JstlResolver.java 4785 2009-12-21 20:10:28Z mosipov $
 */
public class JstlResolver implements LocaleResolver {

	public JstlResolver() {
		Config.class.hashCode();
	}

	public Locale resolveLocale(final HttpServletRequest request) {

		return (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);
	}

}