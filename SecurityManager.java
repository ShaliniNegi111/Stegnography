package Stegnography ;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

class SecurityManager
{
   static String Password ;
   static int len ;
   static int index ;
   
   SecurityManager( String Password )
   {
   	this.Password = Password ;
   	index = 0 ;
   	len = Password.length() ;
   }
   
   
   public static int getFlag()
   {
   	int sum = 0 ;
   	for ( int i = 0 ; i < Password.length() ; i ++ )
   	{
   		sum += Password.charAt ( i ) ;
   	}
   	return sum % 3 + 1 ;
   }
   
   public static int Encreption ( int x  )
   {
   	x = x ^ Password.charAt ( index ) ;
   	index = ( index + 1 ) % len ;
   	return x ;
   } 
		
}
