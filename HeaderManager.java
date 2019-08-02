package Stegnography ;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.File;

class HeaderManager
{

        private String Name ;
        private  String  len  ;
        public static  long   Length = 25 ;
        private  long forName = Length - 9 ;
        private  long    forSize = 8 ;

	HeaderManager ( String Name , long    size )
	{
	
	     this.Name = Name ;
	     len = Long.toString ( size) ;
	}
	
	public   String HeaderFormation ()
	{
		
		while (  Name .length() < forName )
		{
		      	Name = '#' + Name ;
		}
		
		if ( Name.length() < forName )
		{
		        long   start = Name.length() -  forName ;
			Name = Name.substring ( ( int ) start  ) ;
	        }
		
		
		while (  len.length() < forSize)
		{
		      	len = '#' + len;
		}
		
		return  Name + "-" + len ;
	}
	
	
	
}
