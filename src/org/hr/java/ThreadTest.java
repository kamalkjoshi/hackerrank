package org.hr.java;

import java.util.Scanner;

public class ThreadTest {

	
	
	 public static void main(String[] args) {
	        
	        Scanner sc=new Scanner(System.in);
	        String A=sc.next();
	        String B=sc.next();
	        /* Enter your code here. Print output to STDOUT. */
	        System.out.println(A.length()+B.length());
	        System.out.println(B.compareTo(A) > 0?"Yes":"No");
	        System.out.println(camelCase(A)+" "+camelCase(B));       
	        sc.close();
	        
	    }
	    
	    static String camelCase(String input){
	        StringBuilder sb = new StringBuilder();
	        sb.append(Character.toUpperCase(input.charAt(0)));
	        sb.append(input.substring(1));
	        return sb.toString();
	    }
	    
	    static void testThread(){
	    	Thread t = new MyT() {
				public void run(){
					System.out.println("main");
				}
			};
				t.start();	
	    }

	
}

class MyT extends Thread {
	MyT(){
		System.out.println("Cons");
	}
	
	public void run(){
		System.out.println("Default run");
	}
	
	public void run(String s){
		System.out.println("String");
	}
}
