package genetico;

import java.util.Random;
@Deprecated
public class Utils {
	
	public static Integer rand0100(){
		return rand(101);
//		return (int) (Math.random()*100);
	}
	
	public static int rand(int v){
		Random r = new Random();
		return r.nextInt(v);
	}
	
	// sempre retorna string com 8 bits
	public static String int_to_bin(int valor){
		String tmp = Integer.toBinaryString(valor);
		int numero_bits = 8;
		
		String colocar = "0";
		String somador = "";
		int qnt = numero_bits - tmp.length();
		for(int i=0; i<qnt; i++){
			somador += colocar;
		}
		
		return somador + tmp;
	}
	
	public static String corrige_len(String str, int a){
		
		if(str.length() < a){
			String sub = "0", acumulador = "";
			int qnt = a - str.length();
			int i;
			
			for(i=0; i<qnt; i++){
				acumulador += sub;
			}
			
			return acumulador + str;
		} else {
			// pega os 00 +  seis ultimo
			return "00" + str.substring(str.length() - 6);
			
		}
		
		
	}
	
	public static <T> String  join_interable( T lst[], String st){
		int i, max = lst.length;
		String aux = "";
		for(i=0; i<max; i++){
			aux += lst[i].toString();
			if(i+1 < max){
				aux += st;
			}
		}
		return aux;
	}
	
	public static <T> boolean  contain(T lst[], T st){
		int i, max = lst.length;
		T aux;
		for(i=0; i<max; i++){
			aux = lst[i];
			if(aux != null && aux.equals(st)){
				return true;
			}
		}
		return false;
	}
	
	// considera que os elementos de lst sao string em binario
	public static int[] lst_str_to_int(String lst[]){
		int max = lst.length;
		
		int lst_int[] = new int[max];
		
		for(int i=0 ; i<max; i++){
			lst_int[i] = Integer.parseInt(lst[i], 2);
		}
		
		return lst_int;
	}
	
	public static int sum(int lst[]){
		int i, sum_ = 0;
		for(i=0; i<lst.length; i++){
			sum_ += lst[i];
		}
		return sum_;
	}
	
	public static void print_2_formatos(int v){
		String t = int_to_bin(v);
		System.out.println("Int " + v + "; Str " + t + "; len: " + t.length());
	}
	
	public static String at(String str, int index){
		return str.substring(index, index+1);
	}
}
