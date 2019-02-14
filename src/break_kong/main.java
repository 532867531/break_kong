package break_kong;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}
	public static void combn(double x[],int m) {
		int n = x.length;
		int e = 0;
		int h =m;
		int a [] =new int[n+1];
		for(int i =1;i<=m;i++) {
			a[i]=i;
		}
		
		double i = 0;
		if(m>0) {
			i=2D;
		}
		double nmmp1;
		nmmp1=n-m+1;
		List<Integer> j =new ArrayList<Integer>();
		while(a[1]!=nmmp1) {
			if(e<n-h) {
				h=1;
				e=a[m];
				j=new ArrayList<Integer>();
				j.add(1);
			}else {
				e=a[m-h];
				h=h+1;
				j=new ArrayList<Integer>();
				for(int temp =1;temp<=h;temp++) {
					j.add(temp);
				}
			}
			for (int object : j) {
				a[m-h+object]=e+object;
			}
			//结果相加
			double r =0;
			for (int k = 1; k <=m; k++) {
				r=r+x[a[k]-1];
			}
			if(r==38178792) {
//				System.out.println(String.join("|", x.toString()));
			}
			//输出
			System.out.println(r);
			i++;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x[]= {45919904,45045242,43969791,43637826,42945596,42828969,42573725,42139457,41760344,41586296,41510348,40807460,40151384,40117152,40057614,39806027,39304166,39140193,39038475,38962840,38909316,38814973,38374281,38184955,37971001,37925657,37698979,37696881,37458270,37389688,37267930,37182076,37021285,36841953,36771148,36182421,35558009,34466468,34444908,34241040,34079562,33958452,33937148,33668057,33579823,33256713,33245834,33217906,32825909,32682974,32029793,31496607,30985845,30206351,30104375,29209249,28668413,27086445};
		
		combn(x, 6);
	}

}
