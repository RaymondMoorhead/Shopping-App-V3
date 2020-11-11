package com.shoppingapp.authentication;

import java.util.LinkedList;
import java.util.Random;

public final class Encrypt {
	
	// PUBLIC METHODS

	public static String encrypt(String toEncrypt, String key1, String key2, int setLength)
	{
		StringBuilder newToEncrypt = new StringBuilder(toEncrypt);
		newToEncrypt.setLength(setLength);
		for(int i = toEncrypt.length(), j = 0; i < setLength; ++i, ++j)
			newToEncrypt.setCharAt(i, (char)(LOWEST_VAL_CHAR + j));
		return stringToHash(newToEncrypt.toString(), key2);
	}

	// PRIVATE HASH VARIABLES
	
	private static final char LOWEST_VAL_CHAR = ' ';
	private static final char HIGHEST_VAL_CHAR = '~';
	
	// PRIVATE HASH METHODS
	
	private static char[] generateHash(String key) {
		final int size = (HIGHEST_VAL_CHAR - LOWEST_VAL_CHAR) + 1;
		char[] result = new char[size];
		LinkedList<Integer> indexPool = new LinkedList<Integer>();
		
		// set up index pool (to prevent duplicates)
		for(int i = size - 1; i >= 0; --i)
			indexPool.push(i);
		
		// set up the random generator
		Random rand = new Random();
		long seed = 0;
		if(key == null)
			seed = System.currentTimeMillis();
		else
			for(int i = key.length() - 1; i >= 0; --i)
				seed += key.charAt(i);
		rand.setSeed(seed);
		int nextInt;
		
		// create new hash
		for(int i = size - 1; i >= 0; --i)
		{
			nextInt = rand.nextInt(indexPool.size());
			result[i] = (char)(indexPool.get(nextInt) + LOWEST_VAL_CHAR);
			indexPool.remove(nextInt);
		}
		
		return result;
	}
	
	private static String stringToHash(String str, String key) {
		char[] hashIndices = generateHash(key);
		StringBuilder result = new StringBuilder();
		for(char c : str.toCharArray())
			result.append(toHash(c, hashIndices));
		return result.toString();
	}
	
	private static char toHash(char c, char[] hashIndices) {
		if(c < LOWEST_VAL_CHAR)
			return (char)(c + HIGHEST_VAL_CHAR);
		else if(c > HIGHEST_VAL_CHAR)
			return (char)(c + HIGHEST_VAL_CHAR + LOWEST_VAL_CHAR);
		else
			return hashIndices[c - LOWEST_VAL_CHAR];
	}
}
