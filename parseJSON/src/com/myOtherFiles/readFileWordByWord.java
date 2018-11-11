package com.myOtherFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class readFileWordByWord {
	public static <T> T coalesce(T a, T b) {
	    return a == null ? b : a;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();

		prop.load(new FileInputStream("file.map"));

		System.out.println(prop.getProperty("Device_Id"));

	}
}