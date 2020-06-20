package com.sparo.java.basic;

/**
 * 动态分派：方法重写 ： 根据实际类型的方法调用 为 动态分派
 * 用于理解 ： 静态分派和动态分派，静态多分派，动态单分派
 *
 */
public class Test{
 
	public static void main(String[] rags){
		Father f = new Father();
		Father s = new Son();
		System.out.println("f.i " +f.i);
		System.out.println("s.i " +s.i);
		f.hi();
		s.hi();
	}
}
 
class Father {
		int i = 0 ;
		public void hi(){
			System.out.println("WelcomeFather!");
		}
 
}
 
class Son extends Father{
	int i = 9 ;
	public void hi(){
		System.out.println("WelcomeSon!");
	}
 
}