import javax.swing.*;
import java.math.*;


class RSA{
	public static void main (String args[]){

		//Select Encrypt or Dycrypt
		int ED = Integer.parseInt(JOptionPane.showInputDialog(" Enter 1 To Encrypt The Message \n Enter 2 To Dycrypt The Message"));
		for (int i=1; (ED!=1) && (ED!=2); i++){
			ED = Integer.parseInt(JOptionPane.showInputDialog(" Please Enter '1' To Encrypt The Message \n OR Enter '2' To Dycrypt The Message"));
		}


		//Select Digital signture ot Confidentioality
		int SC = Integer.parseInt(JOptionPane.showInputDialog(" Enter 1 if digital signture is more important \n Enter 2 if Confidentioality is more important"));
		for (int i=1; (SC!=1) && (SC!=2); i++){
				SC = Integer.parseInt(JOptionPane.showInputDialog(" Please Enter 1 if digital signture is more important \n OR Enter 2 if Confidentioality is more important"));
		}


		//Enter The Message
		String m = JOptionPane.showInputDialog("Enter Message");


		//First Step (Select two primes numbers)
			int p = Integer.parseInt(JOptionPane.showInputDialog("Enter Prime Number 'p' But Greater than 1"));
			for (int i=1; !IsPrime(p); i++){
				p = Integer.parseInt(JOptionPane.showInputDialog("This is Not Prime Number 'p' choose prime number > 1"));
			}//End For loop

			int q = Integer.parseInt(JOptionPane.showInputDialog("Enter Prime Number 'q' But Greater than 1"));
			for (int i=1; !IsPrime(q); i++){
				q = Integer.parseInt(JOptionPane.showInputDialog("This is Not Prime Number 'q' choose prime number > 1"));
			}//End For loop



		//Second Step Compute N = P * Q
			int n = p * q ;



		//Third Step Compute Phi (n)
			int phi = (p-1)*(q-1);



		//Fourth Step Select E
			int e = Integer.parseInt(JOptionPane.showInputDialog("Enter Prime Number 'e' greater then 1 and less the " + phi));
			for (int i=1; !IsPrime(e) && e>1 && e<phi; i++){
				e = Integer.parseInt(JOptionPane.showInputDialog("This is Not Prime Number"));
			}//End For loop



		//Fifth Step compute D
			int k ;
			int d = 0;
			for (k = 1; k<100 ;k++){
				int d1    = (1+phi*k)/e;
				double d2 = (1.0 +phi*k)/e;
				if (d1 == d2){
					d = d1;
					break;
					}//End IF
			}//End For Loop

		System.out.println(" P =     " + p );
		System.out.println(" q =     " + q );
		System.out.println(" e =     " + e );
		System.out.println(" n =     " + n );
		System.out.println(" phi   = " + phi);
		System.out.println(" k =     " + k );
		System.out.println(" d =     " + d );
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println(" Public Key  (" + n + "," + e + ")");
		System.out.println(" Private Key (" + p + "," + q + "," + d +")");
		System.out.println("------------------------------------------------------------------------------------");




		if (ED == 1 && SC == 1){
			System.out.println(" The Message Befor Encrypt Using Digital Signture:  " + m );
			System.out.print(  " The Message After Encrypt Using Digital Signture:  " );
			signture(d,n,m);
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------");
		}
		else if (ED == 1 && SC == 2){
			System.out.println(" The Message Befor Encrypt Using Confidentiality:  " + m );
			System.out.print(  " The Message After Encrypt Using Confidentiality:  " );
			Confidentioality(e,n,m);
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------");

		}
		else if (ED == 2 && SC == 2){
			System.out.println(" The Message Befor Decrypt Using Digital Signture:  " + m );
			System.out.print(  " The Message After Decrypt Using Digital Signture:  " );
			signture(d,n,m);
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------");
		}
		else if (ED == 2 && SC == 1){
			System.out.println(" The Message Befor Decrypt Using Confidentiality:  " + m );
			System.out.print(  " The Message After Decrypt Using Confidentiality:  " );
			Confidentioality(e,n,m);
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------");
		}

	}//End main method

	public static void signture(int d, int n, String m){
			char[] CharArray = m.toCharArray();
			for(int i=0; i<CharArray.length ; i++){
				int converted = CharArray[i];
				String convert = Integer.toString(converted);
				BigInteger domain = new BigInteger(String.valueOf(n));
				BigInteger BI1 = new BigInteger(convert);
				BigInteger BI2 = BI1.pow(d);
				BigInteger BI3 = BI2.mod(domain);
				int hh = BI3.intValue();
				char C = (char)hh;
				System.out.print(C);
			}//End For
		}//End signture


	public static void Confidentioality(int e, int n, String m){
			char[] CharArray = m.toCharArray();
			for(int i=0; i<CharArray.length ; i++){
				int converted = CharArray[i];
				String convert = Integer.toString(converted);
				BigInteger domain = new BigInteger(String.valueOf(n));
				BigInteger BI1 = new BigInteger(convert);
				BigInteger BI2 = BI1.pow(e);
				BigInteger BI3 = BI2.mod(domain);
				int hh = BI3.intValue();
				char C = (char)hh;
				System.out.print(C);

			}//End for
		}//End Confidentioality


		public static boolean IsPrime(int num){
			boolean prime = true;

			for(int i=2;i<=num/2;i++){
				if((num%i)==0){
					prime=false;
					break;
				}
			}//End For loop

			return prime;
		}//End IsPrime method


}//End Class