/*
 * Copyright (c) 2018. UnifiedPost, SA. http://www.unifiedpost.com
 * This software is the proprietary information of UnifiedPost, SA.
 * Use is subject to license terms.
 */

package io.korhner.asciimg;

import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.BestCharacterFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.GifToAsciiConvert;

import java.awt.*;
import java.io.IOException;

public class GifExamples {

	public static void main(String[] args) throws IOException {

		// initialize caches
		AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier",Font.BOLD, 6));
		// initialize ssimStrategy
		BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();
		
		String srcFilePath = "examples/test.gif";
		String disFilePath = "examples/test-ascii.gif";
		int delay = 100;//ms
		
		GifToAsciiConvert asciiConvert = new GifToAsciiConvert(smallFontCache, ssimStrategy);
		
		asciiConvert.convertGitToAscii(srcFilePath, disFilePath, delay,0);
	}
}
