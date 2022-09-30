package com.lguplus.fleta.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class CustomNameFilter implements FilenameFilter {
	
	String type = null;
	String[] findName = null;

	public CustomNameFilter(String type, String findName){
		this.type = type;
		this.findName = new String[]{findName};
	}

	/**
	 * 특정 폴더의 찾고자하는 파일을 지정한다.
	 */
	@Override
	public boolean accept(File dir, String name) {
		return checkRtn(name);
	}
	
	private boolean checkRtn(String name){
		return
				Arrays.stream(findName)
						.filter(fName -> {
							if (StringUtils.equals("startWith", type)) {
								return name.startsWith(fName);
							} else if (StringUtils.equals("endsWith", type)) {
								return name.endsWith(fName);
							} else if (StringUtils.equals("equals", type)) {
								return name.equals(fName);
							} else if (StringUtils.equals("contains", type)) {
								return name.contains(fName);
							} else {
								return name.endsWith(fName);
							}
						})
						.findFirst()
						.map(fName -> true)
						.orElse(false);

	}

}
