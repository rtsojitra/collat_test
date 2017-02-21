/*
 * Author: Aarti Sojitra
 * EMAIL: rt.sojitra@gmail.com
 * 
 * This is the main class that reads txt files
 * and stores KEYWORDs, DEFINITIONS and PRIMITIVE DEFINITIONS
 * 
 * and prints PRIMITIVE DEFINITIONS, DEFINITIONS and KEYWORDS
 * 			in the order
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class CollatTesterMain {

	// Operating system
	private static final String OS = System.getProperty("os.name").toLowerCase(); 

	// function to check if OS = windows
	private static boolean isWindows() {
		return (OS.contains("win"));
	}

	/*****************************************************
	 * method that does following 
	 * - depending on OS, reads txt files from
	 * 				/tmp/collat_test directory 
	 * - stores all the words in KEYWORDS,  DEFINITIONS 
	 * 				and PRIMITIVE DEFINITIONS
	 * - prints KEYWORDS after PRIMITIVE DEFINITIONS and
	 * DEFINITIONS
	 * 
	 ******************************************************/
	private void collat_tester() {
		try {

			String path = "/tmp/collat_test";

			// change path is OS is windows
			if (isWindows()) {
				path = "c:\\tmp\\collat_test";
			}
			File folder = new File(path);

			// show error if folder does not exist
			if (!folder.exists()) {
				throw new java.lang.Error(path + " Directory does not exist");
			}

			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				String file = folder + "/" + listOfFiles[i].getName();
				if (file.endsWith(".txt")) {
					BufferedReader br = new BufferedReader(new FileReader(file));
					try {
						String line = null;

						Map<String, Set<String>> map = new HashMap<>();
						Set<String> primitivesSets = new HashSet<>();
						Set<String> definitionSets = new HashSet<>();
						Set<String> keywordsSets = new HashSet<>();

						while ((line = br.readLine()) != null) {

							// Skip any line that starts with comment
							if (!line.startsWith("#")) {

								// storing each words in an array after skipping
								// comments
								String[] words = line.split("#")[0].split(" ");
								String keyword = words[0]; // temporary keyword

								// check if word is primitive-definition
								if (words[1].equals(".")) {

									// removing primitive word from keywords set
									if (keywordsSets.contains(keyword)) {
										keywordsSets.remove(keyword);
									}

									// removing primitive word from definitions
									// set
									if (definitionSets.contains(keyword)) {
										definitionSets.remove(keyword);
									}

									// adding primitive words into primitives
									// list
									primitivesSets.add(keyword);
								}

								else {

									// looping through each keywords /
									// definitions
									for (int j = 0; j < words.length; j++) {
										String currentWord = words[j];

										// check if word isnt in primitives set
										if (!primitivesSets
												.contains(currentWord)) {

											// Add keyword to keywords set if it
											// doesn't exist in the definitions
											// sets,
											if (j == 0) {

												if (!definitionSets
														.contains(currentWord)) {
													keywordsSets
															.add(currentWord);
												}

											} else {
												// remove word from keywords if
												// its in keywords set
												if (keywordsSets
														.contains(currentWord)) {
													keywordsSets
															.remove(currentWord);
												}

												// add definition to definitions
												// set
												definitionSets.add(words[j]);
											}
										}
									}
								}
							}
						}
						map.put("primitive_definitions", primitivesSets);
						map.put("definitions", definitionSets);
						map.put("keywords", keywordsSets);

						StringJoiner joiner = new StringJoiner(", ");
						if (!map.get("primitive_definitions").isEmpty()) {
							joiner.add(map.get("primitive_definitions")
									.toString());
						}

						if (!map.get("definitions").isEmpty()) {
							joiner.add(map.get("definitions").toString());
						}

						if (!map.get("keywords").isEmpty()) {
							joiner.add(map.get("keywords").toString());
						}

						System.out.println(joiner.toString().replaceAll(
								"[\\[\\]]", ""));

					} finally {

						// closing buffer reader
						br.close();
					}

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {

		CollatTesterMain main = new CollatTesterMain();
		main.collat_tester();
	}
}
