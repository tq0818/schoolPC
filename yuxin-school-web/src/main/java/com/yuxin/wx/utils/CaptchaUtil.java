/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.yuxin.wx.utils;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.AbstractFilterFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

public class CaptchaUtil {
	private static final AbstractFilterFactory[] factorys = {
			new CurvesRippleFilterFactory(), new MarbleRippleFilterFactory(),
			new DoubleRippleFilterFactory(), new WobbleRippleFilterFactory(),
			new DiffuseRippleFilterFactory() };
	private static final String FORMAT = "png";

	public static String getCaptcha(OutputStream out, int style) {
		AbstractFilterFactory factroy = factorys[(style - 1)];
		RandomWordFactory wordFactory= new RandomWordFactory();
		RandomFontFactory fontFactory=new RandomFontFactory();
		try {
			ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
			cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
			cs.setFilterFactory(factroy);
			wordFactory.setMinLength(4);
			wordFactory.setMaxLength(4);
			List<String> families= new ArrayList<String>();
			families.add("Times New Roman");
			fontFactory.setFamilies(families);
			cs.setWordFactory(wordFactory);
			cs.setFontFactory(fontFactory);
			String word = EncoderHelper.getChallangeAndWriteImage(cs, "png",
					out);
			return word;
		} catch (IOException e) {
		}
		return null;
	}

	public static Boolean checkCaptcha(String source, String target) {
		if (source.toLowerCase().equals(target)) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}
}