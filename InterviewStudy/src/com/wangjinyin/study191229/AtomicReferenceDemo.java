package com.wangjinyin.study191229;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用
 * @author wang
 *
 */
public class AtomicReferenceDemo {

	public static void main(String[] args) {
		
		User user = new User("z3", 22);
		
		User user2   = new User("l4", 25);
		
		AtomicReference<User> atomicReference = new AtomicReference<User>();
		//设置当前的引用类型为user
		atomicReference.set(user);
		
		//如果当前的用户为user。则将user替换为user2刷新主内存
		System.out.println(atomicReference.compareAndSet(user, user2) + "\t" + atomicReference.get().toString());
		
		System.out.println(atomicReference.compareAndSet(user, user2) + "\t" + atomicReference.get().toString());
		
	}
}

class User {
	public User(String string, int i) {
		this.name = string;
		this.age = i;
	}

	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}

	String name;
	
	int age;
}
