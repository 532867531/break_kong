package break_kong;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.math3.analysis.function.Abs;


public class backTrack {
	public static int threads_addition=30;
	public static ArrayList<Thread> threadPool;
	synchronized public static void write(String s) {
			FileWriter fileWriter;
			try {
				
//				fileWriter = new FileWriter("/data/szj/kong/break/11_later_single.txt", true);
				fileWriter = new FileWriter("G:\\kong\\break\\11_later_single.txt", true);
				fileWriter.write(s);
				fileWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public backTrack() {
		// TODO Auto-generated constructor stub
	}
	public static class gosub implements Runnable {
		private int[] a;
		private int m;
		private int number;
		private int target;
		private int[] allposition;
		
		public gosub(int[] a,int m,int number,int target,int allposition[]) {
			this.a=a;
			this.m=m;
			this.number=number;
			this.target=target;
			this.allposition=allposition;
		}  
		@Override
		public void run() {
			// TODO Auto-generated method stub
			backTrack.backTrackCombination(this.a, this.m, this.number, this.target, this.allposition);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		threadPool=new ArrayList<Thread>();
		int arrays[]= {27086445,28668413,29209249,30104375,30206351,30985845,31496607,32029793,32682974,32825909,33217906,33245834,33256713,33579823,33668057,33937148,33958452,34079562,34241040,34444908,34466468,35558009,36182421,36771148,36841953,37021285,37182076,37267930,37389688,37458270,37696881,37698979,37925657,37971001,38184955,38374281,38814973,38909316,38962840,39038475,39140193,39304166,39806027,40057614,40117152,40151384,40807460,41510348,41586296,41760344,42139457,42573725,42828969,42945596,43637826,43969791,45045242,45919904};
		ArrayList<Integer> array=new ArrayList<Integer>();
		for (int i = 0; i < arrays.length; i++) {
			array.add(arrays[i]);
		}
		
		Collections.sort(array);
		System.out.println(Integer.MAX_VALUE);
		for(int index =0;index<array.size();index++) {
			arrays[index]=array.get(index);
		}
		
		int allpositions_init[]=new int[11+1];
		for (int i = 1; i <= allpositions_init.length-1; i++) {
			allpositions_init[i]=array.size()-allpositions_init.length+i;
		}
		backTrackCombination(arrays,11,1,38178792*11,allpositions_init);
		threadPool.forEach(item->{
			try {
				item.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		System.out.println("Finished！");
		//
		
	}
	
	
	public static int backTrackCombination(int[] a,int m,int number,int target,int allposition[]) {
		int maxshift;
		int sum=0;
		int allposition_[] = new int[allposition.length];
		for (int i = 0; i < allposition_.length; i++) {
			allposition_[i]=allposition[i];
		}
		if(number==1) {
			maxshift=allposition_[number];
			System.out.println("最小的开始移动!");
		}else if (number>m){
			return(0);
		}else{
			maxshift=allposition_[number]-allposition_[number-1]-1;
		}
		
		for(int shift =0;shift<=maxshift;shift++) {
			
//			System.out.println(shift);
			allposition_[number]=allposition[number]-shift;
			sum=0;
			for(int j=1;j<=m;j++) {
//				sum=sum+ a.get(allposition_[j]);
//				System.out.println(a[allposition_[j]]);
				sum=sum+ a[allposition_[j]];
			}
//			System.out.println(sum);
			if(Math.abs(sum-target)<=11) {
				System.out.println("------------------>");
				String out="";
				for(int j=1;j<=m;j++) {
					out=out+"|"+a[allposition_[j]];
				}
				backTrack.write(out+"\t"+Math.abs(sum-target)+"\n");
				return(0);
			}
			if(sum<target) {
				return(0);
			}
			
			//最小值最小组合大于target则直接下一个
			int sum_min=0;
			int shift_local=0;
			for(int j=1;j<=m;j++) {
				if(j<number) {
					sum_min=sum_min+ a[allposition_[j]];
				}else {
					sum_min=sum_min+ a[allposition_[number]+shift_local];
					shift_local++;
				}
			}
			if((sum_min-target)>14) {continue;}
			
			//进行下一步递归
//			if(number==1&&backTrack_MultiThread.threads_addition>0) {
			if(number<=1) {
				backTrack.threads_addition--;
				Thread newT=new Thread(new gosub(a, m, number+1, target, allposition_));
				threadPool.add(newT);
				newT.start();
			}else {
				backTrackCombination(a, m, number+1, target, allposition_);
			}
			
		}
		return(0);
	}

}
